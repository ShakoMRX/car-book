package com.car.carbook.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BadRequestException extends BaseException {
    private String message;
    private Long errorCode;
    private Throwable throwable;
    private boolean showPopUpInUI;

    // No-args constructor
    public BadRequestException() {

    }

    // All-args constructor
    public BadRequestException(String message, Long errorCode, Throwable throwable, boolean showPopUpInUI) {
        this.message = message;
        this.errorCode = errorCode;
        this.throwable = throwable;
        this.showPopUpInUI = showPopUpInUI;
    }

    // Constructor with selected fields
    public BadRequestException(String message, Long errorCode, Throwable throwable) {
        this.message = message;
        this.errorCode = errorCode;
        this.throwable = throwable;
        this.showPopUpInUI = true;
    }

    public BadRequestException(String message, Long errorCode, boolean showPopUpInUI) {
        this.message = message;
        this.errorCode = errorCode;
        this.showPopUpInUI = showPopUpInUI;
    }

    // Getter and Setter methods
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public boolean isShowPopUpInUI() {
        return showPopUpInUI;
    }

    public void setShowPopUpInUI(boolean showPopUpInUI) {
        this.showPopUpInUI = showPopUpInUI;
    }
}
