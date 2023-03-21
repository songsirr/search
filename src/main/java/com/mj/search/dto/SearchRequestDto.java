package com.mj.search.dto;

import com.mj.search.common.constant.CommonConstant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchRequestDto {

    private String query;

    @Builder.Default
    private Integer page = CommonConstant.DEFAULT_SEARCH_PAGE;

    @Builder.Default
    private Integer size = CommonConstant.DEFAULT_SEARCH_SIZE;

    @Builder.Default
    private String sort = CommonConstant.SEARCH_SORT_ACCURACY;
}
