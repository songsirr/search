package com.mj.search.service;

import com.mj.search.entity.SearchHistory;
import com.mj.search.repository.SearchHistoryRepository;
import com.mj.search.service.impl.SearchHistoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchHistoryServiceTest {

    @InjectMocks
    private SearchHistoryServiceImpl searchHistoryService;

    @Mock
    private SearchHistoryRepository searchHistoryRepository;

    @Test
    void updateHotKeyword_case_null(){

        //given
        String keyword = "hello";
        SearchHistory newOne = SearchHistory.builder()
                .keyword(keyword)
                .hit(1)
                .build();

        // return
        given(searchHistoryRepository.findByKeyword(keyword)).willReturn(null);
        given(searchHistoryRepository.save(any(SearchHistory.class))).willReturn(newOne);

        // when
        searchHistoryService.updateHotKeyword(keyword);

        // then
        verify(searchHistoryRepository).save(any(SearchHistory.class));
    }

    @Test
    void updateHotKeyword_case_not_null(){

        //given
        String keyword = "hello";
        SearchHistory existEntity = SearchHistory.builder()
                .keyword(keyword)
                .hit(1)
                .build();

        // return
        given(searchHistoryRepository.findByKeyword(keyword)).willReturn(existEntity);

        // when
        searchHistoryService.updateHotKeyword(keyword);

        // then
        Assertions.assertEquals(2, existEntity.getHit());
    }

    @Test
    void findHotKeyword(){
        // given
        List<SearchHistory> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            SearchHistory s = SearchHistory.builder()
                    .keyword(Integer.toString(i))
                    .hit(i)
                    .build();
            list.add(s);
        }

        // return
        given(searchHistoryRepository.findTop10ByOrderByHitDesc()).willReturn(list);

        // when
        searchHistoryService.findHotKeyword();

        // then
        Assertions.assertEquals(list.size(), 10);
    }
}
