package com.taf.sample.framework.utils;

import com.taf.sample.framework.exceptions.FileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtil {

    /**
     * Deletes file.
     *
     * @param path     path to the file.
     * @param fileName file name(including extension).
     * @throws IOException
     */
    public static void deleteFile(String path, String fileName) throws FileException {
        File file = new File(path + fileName);
        log.info("Deleting file [" + path + fileName + "]");
        if (file.isDirectory()) {
            throw new FileException("Bad input : not a file");
        }
        if (file.exists() == false) {
            throw new FileException("Bad input : file doesn't exist");
        }
        if (file.delete() == false) {
            throw new FileException("Unable to delete a file");
        }
    }

    /**
     * Creates a new file.
     *
     * @param localPath path to the new file.
     * @param fileName  file name(including extension).
     * @throws IOException
     */
    public static File createFile(String localPath, String fileName) throws FileException, IOException {
        Path path = Paths.get(localPath);
        if (!Files.exists(Paths.get(localPath))) {
            Files.createDirectories(path);
        }
        File file = new File(localPath + fileName);
        try {
            if (file.createNewFile()) {
                log.info("File [" + path + fileName + "] is created");
            } else {
                log.info("Cannot create file [" + path + fileName + "]");
            }
        } catch (IOException e) {
            throw new FileException("Unable to create a new file");
        }
        return file;
    }

    /**
     * Reads content from a file.
     *
     * @param file file.
     * @return file content.
     * @throws FileException
     */
    public static String readFile(File file) throws FileException, IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    /**
     * Writes content to a file.
     *
     * @param file         the file to which domain should be written.
     * @param data         domain which should be written to the file.
     * @param shouldAppend true in case when domain should be appended to existed domain
     *                     false in case when domain in the file should be overwritten.
     */
    public static void wruteToFile(File file, String data, boolean shouldAppend) throws FileException {
        try (FileWriter fileWriter = new FileWriter(file, shouldAppend)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new FileException("Unable to write domain to the file");
        }
    }
}
