package com.mj.search.service;

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

//    @Test
//    void searchTest(){
//        try {
//            KakaoBlogSearchResult b = kakaoSearchService.search("안녕");
//            Assertions.assertNotNull(b);
//        } catch (Exception e){
//            e.getMessage();
//        }
//    }
}
