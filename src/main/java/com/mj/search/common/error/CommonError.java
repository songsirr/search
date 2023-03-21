package com.mj.search.common.error;

import com.mj.search.external.error.IErrorCodeEnum;

public enum CommonError implements IErrorCodeEnum {

    COMMON_INTERNAL_SERVER_ERROR(500, "500", "Internal Server Error"),
    COMMON_NOT_FOUND_ERROR(404, "404", "NOT FOUND");

    private final String code;
    private final String message;
    private int status;

    CommonError(final int status, final String code, final String message) {
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
