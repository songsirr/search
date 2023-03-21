package com.mj.search.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CommonSearchResultDto {

    ArrayList<CommonSearchItemDto> list;

    Pagination pagination;
}
