package com.mj.search.service.impl;

import com.mj.search.external.naver.NaverApi;
import com.mj.search.external.naver.model.NaverBlogSearchResult;
import com.mj.search.external.naver.request.NaverBlogSearchRequest;
import com.mj.search.service.NaverSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    public NaverBlogSearchResult search(String query) throws Exception {
        NaverBlogSearchRequest request = naverApi.blogSearch(query).build();
        NaverBlogSearchResult result = request.execute();
        return result;
    }
}
