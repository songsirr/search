package com.mj.search.service;

import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.dto.SearchRequestDto;

import java.util.ArrayList;

public interface CommonSearchService {

    ArrayList<CommonSearchResultDto> search(SearchRequestDto dto);
}
