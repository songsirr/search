package com.mj.search.dto;

import com.mj.search.common.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {

    private String query = "";

    private Integer page = CommonConstant.DEFAULT_SEARCH_PAGE;

    private Integer size = CommonConstant.DEFAULT_SEARCH_SIZE;

    private String sort = CommonConstant.SEARCH_SORT_ACCURACY;
}
