package de.devdanmu.wahlblockbackend.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author danmu
 * created on 2018-03-15
 */
@Data
class ExceptionResponse {

    private final HttpStatus status;
    private final String message;
    private final String detail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    ExceptionResponse(final String message, final String detail, final HttpStatus status) {
        this.message = message;
        this.detail = detail;
        this.status = status;
        timestamp = LocalDateTime.now();
    }
}
