package com.mj.search.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchResultCache {

    SEARCH_RESULT_CACHE("searchResult", 60, 10000);

    private final String cacheName;
    private final int expireAfterWrite;
    private final int maximumSize;
}
