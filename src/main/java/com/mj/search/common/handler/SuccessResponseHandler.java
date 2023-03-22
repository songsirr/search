package com.mj.search.common.handler;

import com.mj.search.common.dto.ResponseWrapperDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SuccessResponseHandler {

    @Pointcut("execution(* com.mj.search.controller.*.*(..))")
    public void SuccessResponsePointCut() {
    }

    @Around("SuccessResponsePointCut()")
    public ResponseEntity<ResponseWrapperDto> ok(ProceedingJoinPoint joinPoint) throws Throwable{
        ResponseEntity<ResponseWrapperDto> entity = (ResponseEntity<ResponseWrapperDto>) joinPoint.proceed();
        ResponseWrapperDto resource = (ResponseWrapperDto) entity.getBody();
        resource.getResult().setCode("00");
        resource.getResult().setMessage("SUCCESS");
        return entity;
    }
}
