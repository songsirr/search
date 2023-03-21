package com.mj.search.common.util;

import com.mj.search.dto.CommonSearchItemDto;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import com.mj.search.external.naver.model.NaverBlogSearchResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ModelUtil {

    public static ArrayList<CommonSearchItemDto> toCommonSearchResult(KakaoBlogSearchResult data){
        ArrayList<CommonSearchItemDto> list;
        list = (ArrayList<CommonSearchItemDto>) Arrays.stream(data.getBlogs()).map(
                k -> CommonSearchItemDto.builder()
                    .name(k.getBlogname())
                    .link(k.getUrl())
                    .title(k.getTitle())
                    .time(k.getDatetime())
                    .contents(k.getContents())
                    .thumbnail(k.getThumbnail())
                    .build()).collect(Collectors.toList());
        return list;
    }

    public static ArrayList<CommonSearchItemDto> toCommonSearchResult(NaverBlogSearchResult data){
        ArrayList<CommonSearchItemDto> list;
        list = (ArrayList<CommonSearchItemDto>) Arrays.stream(data.getItems()).map(
                n -> CommonSearchItemDto.builder()
                        .name(n.getBloggername())
                        .link(n.getLink())
                        .title(n.getTitle())
                        .time(n.getPostdate())
                        .contents(n.getDescription())
                        .build()).collect(Collectors.toList());
        return list;
    }
}
