package de.devdanmu.wahlblockbackend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author danmu
 * created on 2018-03-15
 */
public class NotAVoterException extends CustomException {

    public NotAVoterException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
