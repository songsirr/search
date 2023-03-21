package com.mj.search.common.error;

public enum OrmError implements IErrorCodeEnum {

    DATA_ACCESS_EXCEPTION(530, "Q100", "DATA_ACCESS_EXCEPTION"),
    DATA_INTEGRITY_VIOLATION_EXCEPTION(530, "Q100", "DATA_INTEGRITY_VIOLATION_EXCEPTION"),
    DUPLICATE_KEY_EXCEPTION(540, "Q300", "DUPLICATE_KEY_EXCEPTION"),
    SQL_BAD_GRAMMAR_EXCEPTION(550, "Q900", "SQL_BAD_GRAMMAR_EXCEPTION");

    private final String code;
    private final String message;
    private int status;

    OrmError(final int status, final String code, final String message) {
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
