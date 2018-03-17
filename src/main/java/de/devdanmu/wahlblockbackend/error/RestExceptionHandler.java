package de.devdanmu.wahlblockbackend.error;

import de.devdanmu.wahlblockbackend.exception.CustomException;
import de.devdanmu.wahlblockbackend.exception.ElectionNotFoundException;
import de.devdanmu.wahlblockbackend.exception.NotAVoterException;
import de.devdanmu.wahlblockbackend.exception.VoterLoggedInException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author danmu
 * created on 2018-03-15
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse(ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = {NotAVoterException.class, VoterLoggedInException.class, ElectionNotFoundException.class})
    protected ResponseEntity<ExceptionResponse> handleCustomExceptions(CustomException ex, WebRequest request) {
        return buildResponseEntity(new ExceptionResponse(ex.getMessage(), request.getDescription(false), ex.getHttpStatus()));
    }

    private ResponseEntity<ExceptionResponse> buildResponseEntity(ExceptionResponse exceptionResponse) {
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }
}
