package com.mj.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonSearchResultDto {

    private String title;

    private String contents;

    private String link;

    private String time;
}
