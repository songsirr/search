package com.mj.search.service;

import com.mj.search.common.exception.KakaoServiceException;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public interface KakaoSearchService {

    KakaoBlogSearchResult search(SearchRequestDto dto) throws KakaoServiceException, IOException, ParseException;
}
