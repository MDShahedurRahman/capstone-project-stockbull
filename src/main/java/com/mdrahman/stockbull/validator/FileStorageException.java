package com.mdrahman.stockbull.validator;

public class FileStorageException extends RuntimeException {
    private String msg;
    // Constructor

    /**
     * Constructs a new FileStorageException with the specified error message.
     *
     * @param msg The error message describing the reason for the exception.
     */
    public FileStorageException(String msg) {
        this.msg = msg;
    }

// getMsg method

    /**
     * Retrieves the error message associated with the exception.
     *
     * @return The error message.
     */
    public String getMsg() {
        return msg;
    }

// setMsg method

    /**
     * Sets the error message associated with the exception.
     *
     * @param msg The error message to be set.
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}