package com.mj.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {

    int page;

    int size;

    boolean finish;
}
