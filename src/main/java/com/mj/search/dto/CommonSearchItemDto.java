package com.mj.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonSearchItemDto {

    private String name;

    private String title;

    private String contents;

    private String link;

    private String thumbnail;

    private String time;
}
