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

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@Api(tags = {"search"})
@RequiredArgsConstructor
public class SearchController {

    private final CommonSearchService commonSearchService;

    @GetMapping
    @ApiOperation(value = "검색", notes = "검색")
    @ApiImplicitParams(value = {
            @ApiImplicitParam()
    })
    public ResponseEntity<ResponseWrapperDto> search(SearchRequestDto request) throws Exception {
        List<CommonSearchResultDto> r = commonSearchService.search(request);

        ResponseWrapperDto responseWrapperDto = ResponseWrapperDto.builder()
                .data(r)
                .build();

        return ResponseEntity.ok(responseWrapperDto);
    }
}
