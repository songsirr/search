package com.mj.search.external.exception;

import com.mj.search.external.error.IErrorCodeEnum;

public class NaverExternalSearchServiceException extends ExternalSearchServiceException {
    public NaverExternalSearchServiceException(IErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public NaverExternalSearchServiceException(IErrorCodeEnum errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public NaverExternalSearchServiceException(IErrorCodeEnum errorCode, Throwable e) {
        super(errorCode, e);
    }

    public NaverExternalSearchServiceException(IErrorCodeEnum errorCode, Object causeObject) {
        super(errorCode, causeObject);
    }

    public NaverExternalSearchServiceException(IErrorCodeEnum errorCode, Throwable e, Object causeObject) {
        super(errorCode, e, causeObject);
    }
}
