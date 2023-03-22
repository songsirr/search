package com.mj.search.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mj.search.common.error.ErrorResponse;
import lombok.*;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"code", "message", "httpStatus", "objectMapper", "errorList"})
public class ResponseWrapperDto<T> {

    private Result result;

    private T data;

    private String code;
    private String message;
    private List<ErrorResponse.FieldError> errorList;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Builder
    public ResponseWrapperDto(String code, String message, T data, List<ErrorResponse.FieldError> errorList){
        this.code = code;
        this.message = message;
        this.data = data;
        this.errorList = errorList ;

        this.result = Result.builder()
                .code(code)
                .message(message)
                .errorList(errorList)
                .build();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Result {

        private String code;

        private String message;

        private List<ErrorResponse.FieldError> errorList;
    }

    public String toJson() throws IOException {
     return objectMapper.writeValueAsString(this);
    }
}
