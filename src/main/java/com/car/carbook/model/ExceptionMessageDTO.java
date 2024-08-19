package com.car.carbook.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Model class for returning Exception DTO.")
public class ExceptionMessageDTO {
    @Schema(description = "Timestamp of the Exception.")
    private String time;
    @Schema(description = "Exception Class Name")
    private String exception;
    @Schema(description = "Exception Message")
    private String message;
    @Schema(description = "Exception custom error code")
    private Long errorCode;
    @Schema(description = "Show PopUp or not In UI")
    private boolean showPopUpInUI;
    @Schema(description = "Micro service name")
    private String applicationName;

    public ExceptionMessageDTO(String time, String exception, String message, Long errorCode, boolean showPopUpInUI, String applicationName) {
        this.time = time;
        this.exception = exception;
        this.message = message;
        this.errorCode = errorCode;
        this.showPopUpInUI = showPopUpInUI;
        this.applicationName = applicationName;
    }

    public ExceptionMessageDTO() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

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

    public boolean isShowPopUpInUI() {
        return showPopUpInUI;
    }

    public void setShowPopUpInUI(boolean showPopUpInUI) {
        this.showPopUpInUI = showPopUpInUI;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}