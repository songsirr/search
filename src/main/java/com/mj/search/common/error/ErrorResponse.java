package com.mj.search.common.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    private ErrorResponse(final IErrorCodeEnum code, final List<FieldError> errors) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }


    private ErrorResponse(final IErrorCodeEnum code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(final IErrorCodeEnum code, final String message) {
        this.message = message;
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
        this.code = code.getCode();
    }

    public static ErrorResponse of(final IErrorCodeEnum code, final BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(final IErrorCodeEnum code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final IErrorCodeEnum code, final List<FieldError> errors) {
        return new ErrorResponse(code, errors);
    }

    public static ErrorResponse of(final IErrorCodeEnum code, final String message) {
        return new ErrorResponse(code, message);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {

        private String field;

        private String value;

        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
