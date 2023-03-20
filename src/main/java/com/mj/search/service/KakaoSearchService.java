package com.mj.search.service;

import com.mj.search.external.kakao.model.BlogSearchResult;

public interface KakaoSearchService {

    BlogSearchResult search(String query) throws Exception;
}
