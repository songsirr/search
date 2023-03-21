package com.mj.search.service;

import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.dto.SearchRequestDto;

public interface CommonSearchService {

    CommonSearchResultDto search(SearchRequestDto dto) throws Exception;
}
