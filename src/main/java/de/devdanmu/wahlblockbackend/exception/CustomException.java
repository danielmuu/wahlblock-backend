package de.devdanmu.wahlblockbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author danmu
 * created on 2018-03-15
 */
public class CustomException extends Exception {

    @Getter
    private final HttpStatus httpStatus;

    CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
