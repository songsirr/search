package com.mj.search.service;

import com.mj.search.dto.HotKeywordDto;

import java.util.List;

public interface SearchHistoryService {

    List<HotKeywordDto> findHotKeyword();

    void updateHotKeyword(String keyword);
}
