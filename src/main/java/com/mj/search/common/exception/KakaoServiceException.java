package com.mj.search.common.exception;

import com.mj.search.external.error.IErrorCodeEnum;

public class KakaoServiceException extends ServiceException {
    public KakaoServiceException(IErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public KakaoServiceException(IErrorCodeEnum errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public KakaoServiceException(IErrorCodeEnum errorCode, Throwable e) {
        super(errorCode, e);
    }

    public KakaoServiceException(IErrorCodeEnum errorCode, Object causeObject) {
        super(errorCode, causeObject);
    }

    public KakaoServiceException(IErrorCodeEnum errorCode, Throwable e, Object causeObject) {
        super(errorCode, e, causeObject);
    }
}
