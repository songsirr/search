package com.mj.search.service;

import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.dto.SearchRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class SearchServiceTest {

    private final CommonSearchService commonSearchService;

    public SearchServiceTest(CommonSearchService commonSearchService) {
        this.commonSearchService = commonSearchService;
    }

    @Test
    void search() {
        SearchRequestDto dto = SearchRequestDto.builder().build();
        dto.setQuery("더글로리");

        CommonSearchResultDto r = commonSearchService.search(dto);

        Assertions.assertNotNull(r);
    }
}
