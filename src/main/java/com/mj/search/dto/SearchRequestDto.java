package com.mj.search.dto;

import lombok.Data;

@Data
public class SearchRequestDto {

    private String query;

    private Integer page;

    private Integer size;

    private String sort;
}
