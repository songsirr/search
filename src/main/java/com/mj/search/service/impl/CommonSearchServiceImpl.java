//package com.mj.search.service.impl;
//
//import com.mj.search.common.enums.NaverSearchOption;
//import com.mj.search.common.error.CommonError;
//import com.mj.search.common.exception.KakaoServiceException;
//import com.mj.search.common.exception.NaverServiceException;
//import com.mj.search.common.exception.ServiceException;
//import com.mj.search.common.util.ModelUtil;
//import com.mj.search.dto.CommonSearchItemDto;
//import com.mj.search.dto.CommonSearchResultDto;
//import com.mj.search.dto.Pagination;
//import com.mj.search.dto.SearchRequestDto;
//import com.mj.search.external.kakao.KakaoApi;
//import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
//import com.mj.search.external.kakao.request.KakaoBlogSearchRequest;
//import com.mj.search.external.naver.NaverApi;
//import com.mj.search.external.naver.model.NaverBlogSearchResult;
//import com.mj.search.external.naver.request.NaverBlogSearchRequest;
//import com.mj.search.service.CommonSearchService;
//import org.apache.hc.core5.http.ParseException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Service
//public class CommonSearchServiceImpl implements CommonSearchService {
//
//    private final String secretKey;
//    private final String clientId;
//    private final String clientSecret;
//    private final KakaoApi kakaoApi;
//    private final NaverApi naverApi;
//
//    public CommonSearchServiceImpl(
//            @Value("${kakao.api.key}") String secretKey,
//            @Value("${naver.client.id}") String clientId,
//            @Value("${naver.client.secret}") String clientSecret) {
//        this.secretKey = secretKey;
//        this.clientId = clientId;
//        this.clientSecret = clientSecret;
//        this.kakaoApi = new KakaoApi.Builder().setApiToken(secretKey).build();
//        this.naverApi = new NaverApi.Builder().setClientId(clientId).setClientSecret(clientSecret).build();
//    }
//
//    @Override
//    public CommonSearchResultDto search(SearchRequestDto dto) {
//        CommonSearchResultDto result = null;
//        int totalPage = 0;
//        boolean hasNext = false;
//        ArrayList<CommonSearchItemDto> list = new ArrayList<>();
//        Pagination pagination = Pagination.builder().build();
//        try {
//            KakaoBlogSearchResult k = kakaoSearch(dto);
//
//            list = ModelUtil.toCommonSearchResult(k);
//            pagination = calcPagination(k.getMeta().getPageableCount(), dto.getSize(), dto.getPage());
//        } catch (KakaoServiceException | IOException | ParseException ke) {
//            ke.printStackTrace();
//
//            NaverBlogSearchResult n = naverSearch(dto);
//            list = ModelUtil.toCommonSearchResult(n);
//            pagination = calcPagination(n.getTotal(), n.getDisplay(), dto.getPage());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            result.setList(list);
//            result.setPagination(pagination);
//        }
//
//        return result;
//    }
//
//    private KakaoBlogSearchResult kakaoSearch(SearchRequestDto dto) throws Exception {
//        KakaoBlogSearchRequest kakaoBlogSearchRequest = kakaoApi.blogSearch(dto.getQuery())
//                .size(dto.getSize())
//                .page(dto.getPage())
//                .sort(dto.getSort())
//                .build();
//
//        KakaoBlogSearchResult k = kakaoBlogSearchRequest.execute();
//
//        return k;
//    }
//
//    private NaverBlogSearchResult naverSearch(SearchRequestDto dto) {
//        try {
//            NaverBlogSearchRequest naverBlogSearchRequest = naverApi.blogSearch(dto.getQuery())
//                    .display(dto.getSize())
//                    .start(dto.getPage())
//                    .sort(NaverSearchOption.valueOf(dto.getSort()).getNaverCode())
//                    .build();
//
//            NaverBlogSearchResult n = naverBlogSearchRequest.execute();
//            return n;
//        } catch (NaverServiceException | IOException | ParseException e){
//            throw Exception;
//        }
//    }
//
//    private Pagination calcPagination(int totalCount, int size, int page){
//        Pagination pagination = Pagination.builder().build();
//    }
//}
