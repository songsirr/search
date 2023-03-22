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
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 네이버 블로그 검색
     * @param dto
     * @return
     * @throws NaverExternalSearchServiceException // 네이버 자체 에러
     * @throws IOException
     * @throws ParseException
     */
    @Override
    @Cacheable(cacheNames = "searchResult", key = "'naver'+#dto?.getQuery()+#dto?.getPage()+#dto?.getSize()+#dto?.getSort()", condition = "#dto?.getSort().equals('accuracy') && #dto?.getPage() == 1")
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
