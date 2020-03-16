package com.taf.sample.framework.exceptions;

public class CsvException extends Exception {

    public CsvException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvException(String message){
        super(message);
    }
}
