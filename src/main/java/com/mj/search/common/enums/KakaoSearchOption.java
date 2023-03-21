package com.mj.search.common.enums;

import com.mj.search.common.constant.CommonConstant;

public enum KakaoSearchOption {

    KAKAO_SORTED_ACCURACY(CommonConstant.SEARCH_SORT_ACCURACY, "sim"),
    KAKAO_SORTED_RECENCY(CommonConstant.SEARCH_SORT_RECENCY, "date");

    private final String commonCode;
    private final String kakaoCode;

    KakaoSearchOption(String commonCode, String kakaoCode) {
        this.commonCode = commonCode;
        this.kakaoCode = kakaoCode;
    }

    public String getCommonCode() {
        return commonCode;
    }

    public String getKakaoCode() {
        return kakaoCode;
    }
}
