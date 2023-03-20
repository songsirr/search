package com.mj.search.service;

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
    void searchTest() {
        try {
            NaverBlogSearchResult b = naverSearchService.search("안녕");
            Assertions.assertNotNull(b);
        } catch (Exception e){
            e.getMessage();
        }
    }
}
