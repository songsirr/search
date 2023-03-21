package com.mj.search.external.error;

public enum KakaoErrorCode implements IErrorCodeEnum {

    KAKAO_BAD_REQUEST(400, "K001", "Bad Request Within Kakao Api"),
    KAKAO_UNAUTHORIZED(401, "K002", "Unauthorized Within Kakao Api"),
    KAKAO_FORBIDDEN(403, "K003", "Forbidden Within Kakao Api"),
    KAKAO_TOO_MANY_REQUEST(429, "K004", "Too Many Request Within Kakao Api"),
    KAKAO_INTERNAL_SERVER_ERROR(500, "K005", "Internal Server Error Within Kakao Api"),
    KAKAO_BAD_GATEWAY(502, "K006", "Bad Gateway Within Kakao Api"),
    KAKAO_SERVICE_UNAVAILABLE(503, "K007", "Service Unavailable Within Kakao Api");

    private final String code;
    private final String message;
    private int status;

    KakaoErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }


    @Override
    public Integer getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
