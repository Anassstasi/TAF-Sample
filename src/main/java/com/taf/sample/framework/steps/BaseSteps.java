package com.taf.sample.framework.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taf.sample.entities.mongo.repository.MongoEntityRepository;
import com.taf.sample.entities.oracle.repository.OracleEntityRepository;
import com.taf.sample.framework.services.RestService;
import com.taf.sample.framework.services.SftpService;
import com.taf.sample.framework.test_dev.context.TestContext;
import com.taf.sample.framework.validators.MongoDbValidator;
import com.taf.sample.framework.validators.SftpValidator;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class BaseSteps {
    @Value("${sftp.remote.directory}")
    protected String remoteDir;
    @Value("${sftp.local.directory}")
    protected String localDir;
    @Value("#{@properties.getProperty('common.environment')}")
    protected String currentProfile;

    @Autowired
    protected MongoEntityRepository mongoEntityRepository;
    @Autowired
    protected OracleEntityRepository oracleEntityRepository;
    @Autowired
    protected SftpService sftpService;
    @Autowired
    protected RestService restService;
    @Autowired
    protected MongoDbValidator mongoDbValidator;
    @Autowired
    protected SftpValidator sftpValidator;
    @Autowired
    protected TestContext testContext;
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * Attaches file to Allure report.
     *
     * @param fileNames list of fileNames.
     * @param localDir  local directory where the files are stored.
     */
    protected void attachFiles(List<String> fileNames, String localDir) {
        fileNames.forEach(file ->
                {
                    try (InputStream attachment = Files.newInputStream(Paths.get(localDir + file))) {
                        Allure.addAttachment(file, attachment);
                    } catch (IOException e) {
                        log.error("Unable to attach file {} to Allure", file);
                    }
                }
        );
    }
}
