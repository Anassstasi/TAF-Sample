package com.taf.sample.framework.validators;

import com.taf.sample.framework.errorMsgs.AssertionMessages;
import com.taf.sample.framework.services.SftpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

/**
 * Validation class validator for domain on sftp server.
 */
@Component
public class SftpValidator extends BaseValidator {
    @Autowired
    private SftpService sftpService;

    /**
     * Validates whether the file is present on remote server or not.
     *
     * @param filePath         path to the file.
     * @param fileName         filename.
     * @param expectedPresence true if file should be present,
     *                         false if should not be present.
     */
    public void validateFilePresence(String filePath, String fileName, boolean expectedPresence) {
        boolean actualPresence = sftpService.exists(filePath, fileName);

        Assert.assertEquals(actualPresence, expectedPresence, String.format(AssertionMessages.FILE_PRESENCE_ERROR, fileName,
                filePath, actualPresence, expectedPresence));
    }
}
