package com.car.carbook.exception;

import com.car.carbook.model.ExceptionMessageDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ExceptionHelper {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHelper.class);


    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private ExceptionMessageDTO handleBadRequestException(BadRequestException e) {
        return convertAndLogException(e, "Bad Request Exception");
    }

    private ExceptionMessageDTO convertAndLogException(BaseException e, String logMessage) {
        ExceptionMessageDTO message = new ExceptionMessageDTO();
        message.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        message.setMessage(e.getMessage());
        message.setException(e.getClass().getSimpleName());
        message.setErrorCode(e.getErrorCode());
        log.error("{%s} Occurred : {%s}. Stack Trace : {%s} ".formatted(logMessage, message, e.getMessage()));
        return message;
    }
}