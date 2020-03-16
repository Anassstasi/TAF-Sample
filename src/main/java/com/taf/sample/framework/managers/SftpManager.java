package com.taf.sample.framework.managers;

import com.jcraft.jsch.*;
import com.taf.sample.framework.constants.CommonConstants;
import com.taf.sample.framework.errorMsgs.ExceptionErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.common.SshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Vector;

import static com.jcraft.jsch.ChannelSftp.SSH_FX_NO_SUCH_FILE;

@Component
@Slf4j
@PropertySource(value = {"classpath:application.properties"}, encoding = "UTF-8", ignoreResourceNotFound = true)
public class SftpManager {

    @Value("${sftp.privateKey}")
    private String sftpPrivateKey;
    @Value("#{@properties.getProperty('sftp.host')}")
    private String host;
    @Value("#{@properties.getProperty('sftp.user')}")
    private String user;

    @Autowired
    private JSch jsch;
    private Session jschSession;

    /**
     * Configures SFTP session and connects o it.
     *
     * @return chanel for SFTP session.
     * @throws SshException
     */
    public ChannelSftp startSession() throws SshException {
        try {
            ChannelSftp channelSftp = (ChannelSftp) this.configureJschSessionAndConnect().openChannel(CommonConstants.SFTP);
            channelSftp.connect();
            return channelSftp;
        } catch (JSchException e) {
            throw new SshException(ExceptionErrorMessages.CANNOT_CONNECT_TO_SFTP_CHANNEL_ERROR_MESSAGE);
        }
    }

    /**
     * Downloads file from remotePath to localPath.
     *
     * @param remotePath remote file path(includes file name and extension).
     * @param localPath  local file path(includes file name and extension).
     * @throws SshException
     */
    public void downloadFile(String remotePath, String localPath) throws SshException {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.startSession();
            channelSftp.get(remotePath, localPath);
        } catch (SftpException e) {
            throw new SshException(String.format(ExceptionErrorMessages.CANNOT_DOWNLOAD_FILE_ERROR_MESSAGE, remotePath, localPath));
        } finally {
            channelSftp.exit();
            jschSession.disconnect();
        }
    }

    /**
     * Downloads files from remotePath to localPath.
     *
     * @param remotePath remote file paths.
     * @param localPath  local file path.
     * @param fileNames  list of file names.
     * @throws SshException
     */
    public void downloadFiles(String remotePath, String localPath, List<String> fileNames) throws SshException {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.startSession();
            ChannelSftp finalChannelSftp = channelSftp;
            fileNames.forEach(fileName -> {
                try {
                    finalChannelSftp.get(remotePath + fileName, localPath + fileName);
                } catch (SftpException e) {
                    log.error(String.format(ExceptionErrorMessages.CANNOT_DOWNLOAD_FILE_ERROR_MESSAGE, remotePath + fileName, localPath));
                }
            });
        } finally {
            channelSftp.exit();
            jschSession.disconnect();
        }
    }

    /**
     * Uploads local file to remote server.
     *
     * @param localPath  local file path(includes file name and extension).
     * @param remotePath remote file path(includes file name and extension).
     * @throws SshException
     */
    public void uploadFile(String localPath, String remotePath) throws SshException {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.startSession();
            channelSftp.put(localPath, remotePath);
        } catch (SftpException e) {
            throw new SshException(String.format(ExceptionErrorMessages.CANNOT_UPLOAD_FILE_ERROR_MESSAGE, localPath, remotePath));
        } finally {
            channelSftp.exit();
            jschSession.disconnect();
        }
    }

    /**
     * Uploads local file to remote server.
     *
     * @param file       local file.
     * @param remotePath remote file path.
     * @throws SshException
     * @throws FileNotFoundException
     */
    public void uploadFile(File file, String remotePath) throws SshException, FileNotFoundException {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.startSession();
            channelSftp.cd(remotePath);
            channelSftp.put(new FileInputStream(file), file.getName());
        } catch (SftpException e) {
            throw new SshException(ExceptionErrorMessages.CANNOT_UPLOAD_FILE_ERROR_MESSAGE);
        } finally {
            channelSftp.exit();
            jschSession.disconnect();
        }
    }

    /**
     * Deletes remote file.
     *
     * @param remotePath remote file path(includes file name and extension).
     * @throws SshException
     */
    public void deleteRemoteFile(String remotePath) throws SshException {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.startSession();
            channelSftp.rm(remotePath);
        } catch (SftpException e) {
            throw new SshException(String.format(ExceptionErrorMessages.CANNOT_DELETE_FILE_ERROR_MESSAGE, remotePath));
        } finally {
            channelSftp.exit();
            jschSession.disconnect();
        }
    }

    /**
     * Deletes remote file.
     *
     * @param remotePath remote file path(includes file name and extension).
     * @throws SshException
     */
    public Vector<ChannelSftp.LsEntry> listFiles(String remotePath) throws SshException {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.startSession();
            return channelSftp.ls(remotePath);
        } catch (SftpException e) {
            throw new SshException(String.format(ExceptionErrorMessages.CANNOT_LIST_FILES_ERROR_MESSAGE, remotePath));
        } finally {
            channelSftp.exit();
            jschSession.disconnect();
        }
    }

    /**
     * Configures JSch session and connects o it.
     *
     * @return chanel for SFTP session.
     * @throws SshException
     */
    private Session configureJschSessionAndConnect() throws SshException {
        try {
            jsch.addIdentity(sftpPrivateKey);
            jschSession = jsch.getSession(user, host);
            jschSession.setConfig("StrictHostKeyChecking", "no");
            jschSession.connect();
            log.info(String.format("Creating SFTP session. HOST: [%s]", host));
            return jschSession;
        } catch (JSchException e) {
            throw new SshException(String.format(ExceptionErrorMessages.CONNECTION_ERROR_MESSAGE, host));
        }
    }

    /**
     * Checks whether file or directory exists on remote server.
     *
     * @param filePath path to the file or directory.
     * @return true if exists and false if not.
     */
    public boolean exists(String filePath, String fileName) {
        Vector<ChannelSftp.LsEntry> resource = null;
        ChannelSftp channelSftp = null;
        try {
            channelSftp = this.startSession();
            channelSftp.cd(filePath);
            resource = channelSftp.ls(filePath + fileName);
        } catch (SftpException e) {
            if (e.id == SSH_FX_NO_SUCH_FILE) {
                return false;
            }
        } catch (SshException e) {
            log.error("CANNOT_CONNECT_TO_SFTP_CHANNEL_ERROR_MESSAGE");
        } finally {
            channelSftp.exit();
            jschSession.disconnect();
        }
        return resource != null && !resource.isEmpty();
    }

}
