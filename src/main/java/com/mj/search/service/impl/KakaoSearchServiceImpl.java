package com.mj.search.service.impl;

import com.mj.search.external.kakao.KakaoApi;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import com.mj.search.external.kakao.request.KakaoBlogSearchRequest;
import com.mj.search.service.KakaoSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaoSearchServiceImpl implements KakaoSearchService {

    private final String secretKey;

    private final KakaoApi kakaoApi;

    public KakaoSearchServiceImpl(@Value("${kakao.api.key}") String secretKey) {
        this.secretKey = secretKey;
        this.kakaoApi = new KakaoApi.Builder().setApiToken(secretKey).build();
    }

    @Override
    public KakaoBlogSearchResult search(String query) throws Exception {

        KakaoBlogSearchRequest request = kakaoApi.blogSearch(query).build();

        final KakaoBlogSearchResult result = request.execute();

        return result;
    }
}
