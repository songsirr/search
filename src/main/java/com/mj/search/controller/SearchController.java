package com.mj.search.controller;

import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import com.mj.search.external.naver.model.NaverBlogSearchResult;
import com.mj.search.service.KakaoSearchService;
import com.mj.search.service.NaverSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@Api(tags = {"search"})
@RequiredArgsConstructor
public class SearchController {

    private final KakaoSearchService kakaoSearchService;

    private final NaverSearchService naverSearchService;

    @GetMapping
    @ApiOperation(value = "검색")
    public String search() throws Exception {
        KakaoBlogSearchResult k = kakaoSearchService.search("안녕");
        NaverBlogSearchResult n = naverSearchService.search("안녕");
        return k.toString() + n.toString();
    }
}
