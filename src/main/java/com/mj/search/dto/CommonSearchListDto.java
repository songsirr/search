package com.mj.search.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommonSearchListDto {

    List<CommonSearchResultDto> list;

    Pagination pagination;
}
