package com.mj.search.common.exception;

import com.mj.search.common.error.IErrorCodeEnum;
import com.mj.search.common.error.KakaoErrorCode;

public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1970261436008527990L;

    private IErrorCodeEnum errorCode;
    private Object causeObject;

    private String code;

    public ServiceException(IErrorCodeEnum errorCode){
        super(errorCode.getCode() +": "+ errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ServiceException(IErrorCodeEnum errorCode, String errorMessage){
        super(errorCode.getCode() +": "+ errorMessage);
        this.errorCode = errorCode;
    }

    public ServiceException(IErrorCodeEnum errorCode, Throwable e){
        super(errorCode.getCode() +": "+ errorCode.getMessage(), e);
        this.errorCode = errorCode;
        this.code = errorCode.getCode();
    }

    public ServiceException(IErrorCodeEnum errorCode, Object causeObject){
        super(errorCode.getCode() +": "+ errorCode.getMessage());
        this.errorCode = errorCode;

        this.code = errorCode.getCode();
        this.causeObject = causeObject;
    }

    public ServiceException(IErrorCodeEnum errorCode, Throwable e, Object causeObject){
        super(errorCode.getCode() +": "+ errorCode.getMessage(), e);
        this.errorCode = errorCode;

        this.code = errorCode.getCode();
        this.causeObject = causeObject;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public IErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public Object getCauseObject() {
        return causeObject;
    }

    public String getCode() {
        return code;
    }
}
