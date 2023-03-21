package com.mj.search.common.util;

import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModelUtil {

    public static ArrayList<CommonSearchResultDto> toCommonSearchResult(KakaoBlogSearchResult data){
        ArrayList<CommonSearchResultDto> list = new ArrayList<>();
        list = (ArrayList<CommonSearchResultDto>) Arrays.stream(data.getBlogs()).map(k -> {
            return CommonSearchResultDto.builder()
                    .link(k.getUrl())
                    .title(k.getTitle())
                    .time(k.getDatetime())
                    .contents(k.getContents())
                    .build();
        }).collect(Collectors.toList());
        return list;
    }
}
