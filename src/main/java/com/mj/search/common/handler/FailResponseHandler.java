package com.mj.search.common.handler;

import com.mj.search.common.dto.ResponseWrapperDto;
import com.mj.search.common.error.OrmError;
import com.mj.search.common.error.ErrorResponse;
import com.mj.search.external.exception.ExternalSearchServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FailResponseHandler {

    /**
     * Service 계층
     * @param e
     * @return
     */
    @ExceptionHandler(ExternalSearchServiceException.class)
    public ResponseEntity<ResponseWrapperDto> handleException(ExternalSearchServiceException e) {

        final ErrorResponse response = ErrorResponse.of(e.getErrorCode(), e.getMessage());

        return ResponseEntity.status(response.getStatus()).body(
                ResponseWrapperDto.builder()
                        .message(response.getMessage())
                        .code(response.getCode())
                        .build());
    }

    /**
     * DAO, SQL 계층
     * @param e
     * @return
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseWrapperDto> handleException(DataAccessException e) {

        OrmError errorCode = OrmError.DATA_ACCESS_EXCEPTION;

        if (e instanceof BadSqlGrammarException) { // ex. table, column name typo
            errorCode = OrmError.SQL_BAD_GRAMMAR_EXCEPTION;
        }else if(e instanceof DuplicateKeyException){ // ex. Primary Key Duplicate
            errorCode = OrmError.DUPLICATE_KEY_EXCEPTION;
        }else if( e instanceof DataIntegrityViolationException){ // ex. Not Null
            errorCode = OrmError.DATA_INTEGRITY_VIOLATION_EXCEPTION;
        }

        final ErrorResponse response = ErrorResponse.of(errorCode);

        return ResponseEntity.status(response.getStatus()).body(
                ResponseWrapperDto.builder()
                        .message(response.getMessage())
                        .code(response.getCode())
                        .build());
    }
}
