package com.mj.search.service.impl;

import com.mj.search.domain.SearchHistory;
import com.mj.search.dto.HotKeywordDto;
import com.mj.search.repository.SearchHistoryRepository;
import com.mj.search.service.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchHistoryServiceImpl implements SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;

    @Override
    public List<HotKeywordDto> findHotKeyword() {
        List<SearchHistory> s = searchHistoryRepository.findTop10ByOrderByHitDesc();

        return s.stream().map(SearchHistory::toDto).collect(Collectors.toList());
    }

    @Override
    public void updateHotKeyword(String keyword) {
        SearchHistory s = searchHistoryRepository.findByKeyword(keyword);

        if (s == null){
            SearchHistory newS = SearchHistory.builder()
                    .keyword(keyword)
                    .hit(1)
                    .build();
            searchHistoryRepository.save(newS);
        } else {
            s.updateHit();
        }
    }
}
