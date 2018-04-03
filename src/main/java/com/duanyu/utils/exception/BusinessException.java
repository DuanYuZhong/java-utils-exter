package com.duanyu.utils.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -1313414387059842586L;

    private ExceptionInterface exceptionInterface;

    private String msg;

    private String logMsg;


    public BusinessException(String msg) {
        this.msg = msg;
    }

    public BusinessException(String msg, String logMsg) {
        this.msg = msg;
        this.logMsg = logMsg;
    }

    public BusinessException(ExceptionInterface exceptionInterface) {
        this.exceptionInterface = exceptionInterface;
    }

    public BusinessException(ExceptionInterface exceptionInterface, String logMsg) {
        this.exceptionInterface = exceptionInterface;
        this.msg = logMsg;
    }

    public ExceptionInterface getExceptionInterface() {
        return exceptionInterface;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public String getMsg() {
        return msg;
    }
}
