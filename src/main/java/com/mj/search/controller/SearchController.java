package com.mj.search.controller;

import com.mj.search.external.kakao.model.BlogSearchResult;
import com.mj.search.service.KakaoSearchService;
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

    @GetMapping
    @ApiOperation(value = "검색")
    public String search() throws Exception {
        BlogSearchResult b = kakaoSearchService.search("안녕");
        return b.toString();
    }
}
