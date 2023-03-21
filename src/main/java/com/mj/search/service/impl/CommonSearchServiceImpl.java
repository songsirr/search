package com.mj.search.service.impl;

import com.mj.search.common.util.ModelUtil;
import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.external.kakao.KakaoApi;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import com.mj.search.external.naver.NaverApi;
import com.mj.search.service.CommonSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommonSearchServiceImpl implements CommonSearchService {

    private final String secretKey;
    private final String clientId;
    private final String clientSecret;
    private final KakaoApi kakaoApi;
    private final NaverApi naverApi;

    public CommonSearchServiceImpl(
            @Value("${kakao.api.key}") String secretKey,
            @Value("${naver.client.id}") String clientId,
            @Value("${naver.client.secret}") String clientSecret) {
        this.secretKey = secretKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.kakaoApi = new KakaoApi.Builder().setApiToken(secretKey).build();
        this.naverApi = new NaverApi.Builder().setClientId(clientId).setClientSecret(clientSecret).build();
    }

    @Override
    public ArrayList<CommonSearchResultDto> search(SearchRequestDto dto) {
        ArrayList<CommonSearchResultDto> result = new ArrayList<>();
        try {
            KakaoBlogSearchResult k = kakaoApi.blogSearch("더글로리").build().execute();
            result = ModelUtil.toCommonSearchResult(k);
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }
}
