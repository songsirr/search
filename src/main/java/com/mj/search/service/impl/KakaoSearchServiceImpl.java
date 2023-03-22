package com.mj.search.service.impl;

import com.mj.search.common.enums.SearchResultCache;
import com.mj.search.external.exception.KakaoExternalSearchServiceException;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.external.kakao.KakaoApi;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import com.mj.search.external.kakao.request.KakaoBlogSearchRequest;
import com.mj.search.service.KakaoSearchService;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KakaoSearchServiceImpl implements KakaoSearchService {

    private final String secretKey;

    private final KakaoApi kakaoApi;

    public KakaoSearchServiceImpl(@Value("${kakao.api.key}") String secretKey) {
        this.secretKey = secretKey;
        this.kakaoApi = new KakaoApi.Builder().setApiToken(secretKey).build();
    }

    @Override
    @Cacheable(cacheNames = "searchResult", key = "'kakao'+#dto?.getQuery()+#dto?.getPage()+#dto?.getSize()+#dto?.getSort()", condition = "#dto.getSort().equals('accuracy') && #dto.getPage() == 1")
    public KakaoBlogSearchResult search(SearchRequestDto dto)
            throws KakaoExternalSearchServiceException, IOException, ParseException {

        KakaoBlogSearchRequest kakaoBlogSearchRequest = kakaoApi.blogSearch(dto.getQuery())
                .size(dto.getSize())
                .page(dto.getPage())
                .sort(dto.getSort())
                .build();

        final KakaoBlogSearchResult k = kakaoBlogSearchRequest.execute();

        return k;
    }
}
