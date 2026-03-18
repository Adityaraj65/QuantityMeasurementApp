package com.app.quantitymeasurement.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public static DatabaseException queryFailed(String msg, Exception e) {
        return new DatabaseException(msg, e);
    }
}