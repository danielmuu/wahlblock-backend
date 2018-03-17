package de.devdanmu.wahlblockbackend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author danmu
 * created on 2018-03-17
 */
public class ElectionNotFoundException extends CustomException {

    public ElectionNotFoundException() {
        super("No Election found in Database", HttpStatus.NOT_FOUND);
    }
}
