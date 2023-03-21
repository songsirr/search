package com.mj.search.controller;

import com.mj.search.common.dto.ResponseWrapperDto;
import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.service.CommonSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@Api(tags = {"search"})
@RequiredArgsConstructor
public class SearchController {

    private final CommonSearchService commonSearchService;

    @GetMapping
    @ApiOperation(value = "검색", notes = "검색")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "query", example = "검색어", value = "검색어"),
            @ApiImplicitParam(name = "page", example = "1", value = "페이지"),
            @ApiImplicitParam(name = "size", example = "10", value = "한 페이지당 보여질 게시글의 수"),
            @ApiImplicitParam(name = "sort", example = "accuracy", value = "정렬 방식: accuracy(정확도순), recency(최신순)")
    })
    public ResponseEntity<ResponseWrapperDto> search(SearchRequestDto request) throws Exception {
        CommonSearchResultDto r = commonSearchService.search(request);

        ResponseWrapperDto responseWrapperDto = ResponseWrapperDto.builder()
                .data(r)
                .build();

        return ResponseEntity.ok(responseWrapperDto);
    }
}
