package com.car.carbook.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;
    private Long errorCode;

    public BaseException(HttpStatus httpStatus, String message, Long errorCode, Throwable throwable, boolean showPopUpInUI, String applicationName) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
        this.throwable = throwable;
        this.showPopUpInUI = showPopUpInUI;
        this.applicationName = applicationName;
    }

    public BaseException(String message, HttpStatus httpStatus, String message1, Long errorCode, Throwable throwable, boolean showPopUpInUI, String applicationName) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
        this.errorCode = errorCode;
        this.throwable = throwable;
        this.showPopUpInUI = showPopUpInUI;
        this.applicationName = applicationName;
    }

    public BaseException(String message, Throwable cause, HttpStatus httpStatus, String message1, Long errorCode, Throwable throwable, boolean showPopUpInUI, String applicationName) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.message = message1;
        this.errorCode = errorCode;
        this.throwable = throwable;
        this.showPopUpInUI = showPopUpInUI;
        this.applicationName = applicationName;
    }

    public BaseException(Throwable cause, HttpStatus httpStatus, String message, Long errorCode, Throwable throwable, boolean showPopUpInUI, String applicationName) {
        super(cause);
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
        this.throwable = throwable;
        this.showPopUpInUI = showPopUpInUI;
        this.applicationName = applicationName;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus httpStatus, String message1, Long errorCode, Throwable throwable, boolean showPopUpInUI, String applicationName) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
        this.message = message1;
        this.errorCode = errorCode;
        this.throwable = throwable;
        this.showPopUpInUI = showPopUpInUI;
        this.applicationName = applicationName;
    }

    public BaseException() {

    }

    public Throwable getThrowable() {
        return throwable;
    }

    public boolean isShowPopUpInUI() {
        return showPopUpInUI;
    }

    public String getApplicationName() {
        return applicationName;
    }

    private Throwable throwable;

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setShowPopUpInUI(boolean showPopUpInUI) {
        this.showPopUpInUI = showPopUpInUI;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    private boolean showPopUpInUI;
    private String applicationName;
    public BaseException(HttpStatus httpStatus, String message, Long errorCode) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }
}
