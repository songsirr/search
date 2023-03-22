package com.mj.search.common.event.event;

public class SearchHistoryEvent {

    private String keyword;

    public SearchHistoryEvent(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
