package com.mj.search.service;

import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.dto.HotKeywordDto;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.entity.SearchHistory;
import com.mj.search.external.exception.ExternalSearchServiceException;
import com.mj.search.external.exception.NaverExternalSearchServiceException;
import com.mj.search.repository.SearchHistoryRepository;
import com.mj.search.service.impl.SearchHistoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CommonSearchServiceTest {

    private final CommonSearchService commonSearchService;

    private final SearchHistoryService searchHistoryService;

    public CommonSearchServiceTest(CommonSearchService commonSearchService, SearchHistoryService searchHistoryService) {
        this.commonSearchService = commonSearchService;
        this.searchHistoryService = searchHistoryService;
    }

    @Test
    void total_search_test() throws Exception {
        // given
        SearchRequestDto dto = SearchRequestDto.builder()
                .query("asd")
                .page(1)
                .size(10)
                .sort("accuracy")
                .build();

        // when
        CommonSearchResultDto d = commonSearchService.search(dto);
        List<HotKeywordDto> s = searchHistoryService.findHotKeyword();

        // then
        Assertions.assertEquals(d.getList().size(), 10); // rest
        Assertions.assertEquals(s.size(), 1); // event in rest
    }

    @Test
    void total_search_fail_test(){
        // given
        SearchRequestDto dto = SearchRequestDto.builder()
                .query("asd")
                .page(-1)
                .size(10)
                .sort("accuracy")
                .build();

        // when & then
        Assertions.assertThrows(ExternalSearchServiceException.class, () -> {
            CommonSearchResultDto d = commonSearchService.search(dto);
        });
    }
}
