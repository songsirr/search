package com.mj.search.service;

import com.mj.search.external.exception.NaverExternalSearchServiceException;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.external.naver.model.NaverBlogSearchResult;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public interface NaverSearchService {

    NaverBlogSearchResult search(SearchRequestDto dto) throws NaverExternalSearchServiceException, IOException, ParseException;
}
