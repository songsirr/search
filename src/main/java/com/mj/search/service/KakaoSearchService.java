package com.mj.search.service;

import com.mj.search.external.kakao.model.KakaoBlogSearchResult;

public interface KakaoSearchService {

    KakaoBlogSearchResult search(String query) throws Exception;
}
