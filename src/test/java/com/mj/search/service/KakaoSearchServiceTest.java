package com.mj.search.service;

import com.mj.search.dto.SearchRequestDto;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class KakaoSearchServiceTest {

    private final KakaoSearchService kakaoSearchService;

    public KakaoSearchServiceTest(KakaoSearchService kakaoSearchService) {
        this.kakaoSearchService = kakaoSearchService;
    }

    @Test
    void searchTest() throws Exception {
        // given
        SearchRequestDto dto = SearchRequestDto.builder()
                .query("asd")
                .page(1)
                .size(10)
                .sort("accuracy")
                .build();

        // when
        KakaoBlogSearchResult b = kakaoSearchService.search(dto);

        // then
        Assertions.assertEquals(b.getBlogs().length, 10);
    }
}
