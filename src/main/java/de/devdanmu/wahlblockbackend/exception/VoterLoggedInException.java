package de.devdanmu.wahlblockbackend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author danmu
 * created on 2018-03-15
 */
public class VoterLoggedInException extends CustomException {

    public VoterLoggedInException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
