package com.mj.search.external.exception;

import com.mj.search.external.error.IErrorCodeEnum;

public class KakaoExternalSearchServiceException extends ExternalSearchServiceException {
    public KakaoExternalSearchServiceException(IErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public KakaoExternalSearchServiceException(IErrorCodeEnum errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public KakaoExternalSearchServiceException(IErrorCodeEnum errorCode, Throwable e) {
        super(errorCode, e);
    }

    public KakaoExternalSearchServiceException(IErrorCodeEnum errorCode, Object causeObject) {
        super(errorCode, causeObject);
    }

    public KakaoExternalSearchServiceException(IErrorCodeEnum errorCode, Throwable e, Object causeObject) {
        super(errorCode, e, causeObject);
    }
}
