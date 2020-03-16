package com.taf.sample.framework.services;

import com.jcraft.jsch.ChannelSftp;
import com.taf.sample.framework.managers.SftpManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.common.SshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

@Service
@Slf4j
public class SftpService {

    @Autowired
    private SftpManager sftpManager;

    public void downloadFile(String remotePath, String localPath) {
        try {
            sftpManager.downloadFile(remotePath, localPath);
        } catch (SshException e) {
            log.error(e.getMessage());
        }
    }

    public void downloadFiles(String remotePath, String localPath, List<String> fileNames) throws IOException {
        Path path = Paths.get(localPath);
        if (!Files.exists(Paths.get(localPath))) {
            Files.createDirectories(path);
        }
        try {
            sftpManager.downloadFiles(remotePath, localPath, fileNames);
        } catch (SshException e) {
            log.error(e.getMessage());
        }
    }

    public void uploadFile(String localPath, String remotePath) {
        try {
            sftpManager.uploadFile(localPath, remotePath);
        } catch (SshException e) {
            log.error(e.getMessage());
        }
    }

    public void uploadFile(File file, String remotePath) {
        try {
            sftpManager.uploadFile(file, remotePath);
        } catch (SshException e) {
            log.error(e.getMessage());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    public void deleteRemoteFile(String remotePath) {
        try {
            sftpManager.deleteRemoteFile(remotePath);
        } catch (SshException e) {
            log.error(e.getMessage());
        }
    }

    public Vector<ChannelSftp.LsEntry> listFiles(String remotePath) {
        Vector<ChannelSftp.LsEntry> listFiles = new Vector<>();
        try {
            listFiles.addAll(sftpManager.listFiles(remotePath));
        } catch (SshException e) {
            log.info("Unable to list file in remote directory {}", remotePath);
        }
        return listFiles;
    }

    /**
     * Checks whether  file or directory exists on remote server.
     *
     * @param filePath path to the file or directory.
     * @return tru if exists and false if not.
     */
    public boolean exists(String filePath, String fileName) {
        return sftpManager.exists(filePath, fileName);
    }

}
