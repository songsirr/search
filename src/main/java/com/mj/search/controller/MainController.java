package com.mj.search.controller;

import com.mj.search.common.dto.ResponseWrapperDto;
import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.dto.HotKeywordDto;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.service.SearchHistoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/main")
@Api(tags = {"main화면 진입을 상정한 컨트롤러"})
@RequiredArgsConstructor
public class MainController {

    private final SearchHistoryService searchHistoryService;

    @GetMapping
    public ResponseEntity<ResponseWrapperDto> findHotKeyword() throws Exception {
        List<HotKeywordDto> r = searchHistoryService.findHotKeyword();

        ResponseWrapperDto responseWrapperDto = ResponseWrapperDto.builder()
                .data(r)
                .build();

        return ResponseEntity.ok(responseWrapperDto);
    }
}
