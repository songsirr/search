package com.mj.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotKeywordDto {

    private String keyword;

    private Integer count;
}
