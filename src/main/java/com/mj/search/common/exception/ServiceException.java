package com.mj.search.common.exception;

import com.mj.search.common.error.KakaoErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1970261436008527990L;

    private KakaoErrorCode kakaoErrorCode;
    private Object causeObject;

    private String code;
    private String companyErrorDetail;

    public ServiceException(KakaoErrorCode kakaoErrorCode){
        super(kakaoErrorCode.getCode() +": "+ kakaoErrorCode.getMessage());
        this.kakaoErrorCode = kakaoErrorCode;
    }

    public ServiceException(KakaoErrorCode kakaoErrorCode, String errorMessage){
        super(kakaoErrorCode.getCode() +": "+ errorMessage);
        this.kakaoErrorCode = kakaoErrorCode;
    }

    public ServiceException(KakaoErrorCode kakaoErrorCode, Throwable e){
        super(kakaoErrorCode.getCode() +": "+ kakaoErrorCode.getMessage(), e);
        this.kakaoErrorCode = kakaoErrorCode;
        this.code = kakaoErrorCode.getCode();
    }

    public ServiceException(KakaoErrorCode kakaoErrorCode, Object causeObject){
        super(kakaoErrorCode.getCode() +": "+ kakaoErrorCode.getMessage());
        this.kakaoErrorCode = kakaoErrorCode;

        this.code = kakaoErrorCode.getCode();
        this.causeObject = causeObject;
    }

    public ServiceException(KakaoErrorCode kakaoErrorCode, Throwable e, Object causeObject){
        super(kakaoErrorCode.getCode() +": "+ kakaoErrorCode.getMessage(), e);
        this.kakaoErrorCode = kakaoErrorCode;

        this.code = kakaoErrorCode.getCode();
        this.causeObject = causeObject;
    }
}
