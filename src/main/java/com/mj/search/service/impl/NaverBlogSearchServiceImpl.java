package com.mj.search.service.impl;

import com.mj.search.common.enums.NaverSearchOption;
import com.mj.search.external.exception.NaverExternalSearchServiceException;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.external.naver.NaverApi;
import com.mj.search.external.naver.model.NaverBlogSearchResult;
import com.mj.search.external.naver.request.NaverBlogSearchRequest;
import com.mj.search.service.NaverSearchService;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NaverBlogSearchServiceImpl implements NaverSearchService {

    private final String clientId;

    private final String clientSecret;

    private final NaverApi naverApi;

    public NaverBlogSearchServiceImpl(
            @Value("${naver.client.id}") String clientId
            , @Value("${naver.client.secret}") String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.naverApi = new NaverApi.Builder().setClientId(clientId).setClientSecret(clientSecret).build();
    }


    @Override
    public NaverBlogSearchResult search(SearchRequestDto dto)
            throws NaverExternalSearchServiceException, IOException, ParseException {
        NaverBlogSearchRequest naverBlogSearchRequest = naverApi.blogSearch(dto.getQuery())
                    .display(dto.getSize())
                    .start(dto.getPage())
                    .sort(NaverSearchOption.findByCommonCode(dto.getSort()).getNaverCode())
                    .build();

        NaverBlogSearchResult n = naverBlogSearchRequest.execute();
        return n;
    }
}
