package com.mj.search.dto;

import com.mj.search.common.constant.CommonConstant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {

    @Builder.Default
    private int page = CommonConstant.DEFAULT_SEARCH_PAGE;

    @Builder.Default
    private int size = CommonConstant.DEFAULT_SEARCH_SIZE;

    @Builder.Default
    private String sort = CommonConstant.SEARCH_SORT_ACCURACY;

    @Builder.Default
    private int totalPage = 0;

    @Builder.Default
    boolean next = false;
}
