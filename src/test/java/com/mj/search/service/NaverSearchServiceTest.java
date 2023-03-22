package com.mj.search.service;

import com.mj.search.dto.SearchRequestDto;
import com.mj.search.external.exception.NaverExternalSearchServiceException;
import com.mj.search.external.naver.model.NaverBlogSearchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class NaverSearchServiceTest {

    private final NaverSearchService naverSearchService;

    public NaverSearchServiceTest(NaverSearchService naverSearchService) {
        this.naverSearchService = naverSearchService;
    }

    @Test
    void searchTest() throws Exception{
        // given
        SearchRequestDto dto = SearchRequestDto.builder()
                .query("asd")
                .page(1)
                .size(10)
                .sort("accuracy")
                .build();

        // when
        NaverBlogSearchResult b = naverSearchService.search(dto);

        // then
        Assertions.assertEquals(b.getItems().length, 10);
    }

    @Test
    void searchFailTest(){
        // given
        SearchRequestDto dto = SearchRequestDto.builder()
                .query("asd")
                .page(-1)
                .size(10)
                .sort("accuracy")
                .build();

        // when & then
        Assertions.assertThrows(NaverExternalSearchServiceException.class, () -> {
            NaverBlogSearchResult b = naverSearchService.search(dto);
        });
    }
}
