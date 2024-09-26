package com.assessment.bookstore.util;

import lombok.Getter;

@Getter
public enum ResponseCode {

    TIMEOUT("-1", "Timeout"),
    SUCCESS("00", "Successful"),
    DATABASE_RECORD("01", "Database Error"),
    UNABLE_TO_LOCATE_RECORD("02", "Cannot Locate Record"),
    GENERIC_ERROR("03", "Something went wrong while trying to process your request"),
    DATABASE_SAVE_ERROR("04", "Error Occurred While Saving To The Database"),
    WEBSERVICE_CALL_FAILED("05", "API call failed"),
    VALIDATION_ERROR("06", "Validation Error");

    private String code;
    private String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
