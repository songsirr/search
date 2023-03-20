package com.mj.search.service;

import com.mj.search.external.kakao.model.BlogSearchResult;
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
    void searchTest(){
        try {
            BlogSearchResult b = kakaoSearchService.search("안녕");
            System.out.println(b.getBlogs().length);
            Assertions.assertNull(b);
        } catch (Exception e){
            e.getMessage();
        }
    }
}
