package com.mj.search.common.exception;

import com.mj.search.external.error.IErrorCodeEnum;

public class NaverServiceException extends ServiceException {
    public NaverServiceException(IErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public NaverServiceException(IErrorCodeEnum errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public NaverServiceException(IErrorCodeEnum errorCode, Throwable e) {
        super(errorCode, e);
    }

    public NaverServiceException(IErrorCodeEnum errorCode, Object causeObject) {
        super(errorCode, causeObject);
    }

    public NaverServiceException(IErrorCodeEnum errorCode, Throwable e, Object causeObject) {
        super(errorCode, e, causeObject);
    }
}
