package com.mj.search.service.impl;

import com.mj.search.external.kakao.KakaoApi;
import com.mj.search.external.kakao.model.BlogSearchResult;
import com.mj.search.external.kakao.request.KakaoBlogSearchRequest;
import com.mj.search.service.KakaoSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoSearchServiceImpl implements KakaoSearchService {

//    @Value("${kakao.api.key}")
    private String secretKey = "8637f135593c71343ec84139e3dcac88";

    private final KakaoApi kakaoApi = new KakaoApi.Builder().setApiToken(secretKey).build();

    @Override
    public BlogSearchResult search(String query) throws Exception {

        KakaoBlogSearchRequest request = kakaoApi.blogSearch(query).build();

        final BlogSearchResult result = request.execute();

        return result;
    }
}
