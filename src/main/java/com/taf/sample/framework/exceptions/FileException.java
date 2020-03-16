package com.taf.sample.framework.exceptions;

public class FileException extends Exception {

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(String message) {
        super(message);
    }
}
