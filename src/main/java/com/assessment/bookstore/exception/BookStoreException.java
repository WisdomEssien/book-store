package com.assessment.bookstore.exception;

import com.assessment.bookstore.util.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookStoreException extends Exception {

    private ResponseCode responseCode;

    public BookStoreException(ResponseCode responseCode,
                              String message, Throwable cause) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public BookStoreException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
