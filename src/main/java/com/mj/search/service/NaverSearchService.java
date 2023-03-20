package com.mj.search.service;

import com.mj.search.external.naver.model.NaverBlogSearchResult;

public interface NaverSearchService {

    NaverBlogSearchResult search(String query) throws Exception;
}
