package com.mj.search.common.error;

public enum NaverErrorCode implements IErrorCodeEnum {

    NAVER_BAD_REQUEST(400, "N001", "Bad Request Within External Api"),
    NAVER_UNAUTHORIZED(401, "N002", "Unauthorized Within External Api"),
    NAVER_FORBIDDEN(403, "N003", "Forbidden Within External Api"),
    NAVER_NOT_FOUND(404, "N004", "Not Found Within External Api"),
    NAVER_TOO_MANY_REQUEST(429, "N005", "Too Many Request Within External Api"),
    NAVER_INTERNAL_SERVER_ERROR(500, "N006", "Internal Server Error Within External Api");

    private final String code;
    private final String message;
    private int status;

    NaverErrorCode(final int status, final String code, final String message) {
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
