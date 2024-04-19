package com.gerimedi.tasks.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Generic exception to be handled in {@link RestExceptionHandler}
 */
public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ApplicationException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
