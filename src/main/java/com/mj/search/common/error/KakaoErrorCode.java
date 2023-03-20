package com.mj.search.common.error;

import lombok.Getter;

@Getter
public enum KakaoErrorCode {

    EXTERNAL_BAD_REQUEST(400, "K001", "Bad Request Within External Api"),
    EXTERNAL_UNAUTHORIZED(401, "K002", "Unauthorized Within External Api"),
    EXTERNAL_FORBIDDEN(403, "K003", "Forbidden Within External Api"),
    EXTERNAL_NOT_FOUND(429, "K004", "Not Found Within External Api"),
    EXTERNAL_INTERNAL_SERVER_ERROR(500, "K005", "Internal Server Error Within External Api"),
    EXTERNAL_BAD_GATEWAY(502, "K006", "Bad Gateway Within External Api"),
    EXTERNAL_SERVICE_UNAVAILABLE(503, "K007", "Service Unavailable Within External Api");

    private final String code;
    private final String message;
    private int status;

    KakaoErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
