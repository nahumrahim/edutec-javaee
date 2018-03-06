package com.edutech.javaee.s07.e01.dto;

/**
 *
 * @author nahum
 */

/**
 *
 * @author nahum
 */
public class ErrorMessageDto {

    private boolean success;
    private Integer errorCode;
    private String message;
    private String debugMessage;
    private String url;
    private String documentation;
    
    public ErrorMessageDto() {
    }

    public ErrorMessageDto(boolean success, Integer code, String message) {
        this.success = success;
        this.errorCode = code;
        this.message = message;
        this.url = "/edutec-javaee/info";
    }

    public ErrorMessageDto(boolean success, Integer errorCode, String message, String url) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.url = url;
    }

    public ErrorMessageDto(boolean success, Integer errorCode, String message, String debugMessage, String url) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.debugMessage = debugMessage;
        this.url = url;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
    
}
