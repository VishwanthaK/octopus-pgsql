package com.octopus.service.util;

import org.springframework.http.HttpStatus;

/**
 * <h1>ErrorCode</h1>
 * Custom errors and corresponding http status codes.
 */
public enum ErrorCode {
    OK(HttpStatus.OK),                                          // 200
    MULTI_STATUS(HttpStatus.MULTI_STATUS),                      // 207
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST),                   // 400
    BAD_REQUEST(HttpStatus.BAD_REQUEST),                        // 400
    INVALID_HEADER(HttpStatus.BAD_REQUEST),                     // 400
    MISSING_HEADER(HttpStatus.BAD_REQUEST),                     // 400
    NULL_VALUE(HttpStatus.BAD_REQUEST),                         // 400
    DUPLICATE(HttpStatus.BAD_REQUEST),                          // 400
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),                      // 401
    ACCESS_DENIED(HttpStatus.FORBIDDEN),                        // 403
    NOT_FOUND(HttpStatus.NOT_FOUND),                            // 404
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED),          // 405
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE),  // 415
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY),      // 422
    OVERLAPPING_DATE(HttpStatus.UNPROCESSABLE_ENTITY),          // 422
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),    // 500
    CONFLICT(HttpStatus.CONFLICT),                              // 409
    ALREADY_EXISTS(HttpStatus.CONFLICT);                        // 409

    private HttpStatus statusCode;

    private ErrorCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public Integer value() {
        return statusCode.value();
    }

    public HttpStatus statusCode() {
        return statusCode;
    }
}
