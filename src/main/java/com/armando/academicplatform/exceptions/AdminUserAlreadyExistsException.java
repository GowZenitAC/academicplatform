package com.armando.academicplatform.exceptions;

public class AdminUserAlreadyExistsException extends RuntimeException {
    public AdminUserAlreadyExistsException(String message) {
        super(message);
    }
}
