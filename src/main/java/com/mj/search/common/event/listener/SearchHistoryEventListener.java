package com.mj.search.common.event.listener;

import com.mj.search.common.event.event.SearchHistoryEvent;
import com.mj.search.service.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchHistoryEventListener {

    private final SearchHistoryService searchHistoryService;

    @Async
    @EventListener
    public void insertOrUpdateSearchHistory(SearchHistoryEvent searchHistory) throws InterruptedException {
        searchHistoryService.updateHotKeyword(searchHistory.getKeyword());
    }
}
