package com.mj.search.common.enums;

import com.mj.search.common.constant.CommonConstant;

import java.util.Arrays;

public enum NaverSearchOption {

    NAVER_SORTED_ACCURACY(CommonConstant.SEARCH_SORT_ACCURACY, "sim"),
    NAVER_SORTED_RECENCY(CommonConstant.SEARCH_SORT_RECENCY, "date");

    private final String commonCode;
    private final String naverCode;

    NaverSearchOption(String commonCode, String naverCode) {
        this.commonCode = commonCode;
        this.naverCode = naverCode;
    }

    public String getCommonCode() {
        return commonCode;
    }

    public String getNaverCode() {
        return naverCode;
    }

    public static NaverSearchOption findByCommonCode(String code) {
        return Arrays.stream(values())
                .filter(o -> o.commonCode.equals(code))
                .findAny()
                .get();
    }
}
