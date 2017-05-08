package com.tesla.cloud.core.exception;

public class TeslaException extends RuntimeException {

    private Integer code;  //异常code

    private String errorMessage; //异常消息

    private Boolean isAlarm;  //是否发送预警信息

    private Integer errorLevel; //错误等级

    public TeslaException(String message) {
        super(message);
    }

    public TeslaException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeslaException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getIsAlarm() {
        return this.isAlarm;
    }

    public void setIsAlarm(Boolean isAlarm) {
        this.isAlarm = isAlarm;
    }

    public Integer getErrorLevel() {
        return this.errorLevel;
    }

    public void setErrorLevel(Integer errorLevel) {
        this.errorLevel = errorLevel;
    }
}