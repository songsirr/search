package com.mj.search.service.impl;

import com.mj.search.common.util.ModelUtil;
import com.mj.search.dto.CommonSearchItemDto;
import com.mj.search.dto.CommonSearchResultDto;
import com.mj.search.dto.Pagination;
import com.mj.search.dto.SearchRequestDto;
import com.mj.search.external.error.KakaoErrorCode;
import com.mj.search.external.exception.KakaoExternalSearchServiceException;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import com.mj.search.external.naver.model.NaverBlogSearchResult;
import com.mj.search.service.CommonSearchService;
import com.mj.search.service.KakaoSearchService;
import com.mj.search.service.NaverSearchService;
import com.mj.search.service.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonSearchServiceImpl implements CommonSearchService {

    private final KakaoSearchService kakaoSearchService;

    private final NaverSearchService naverSearchService;

    private final SearchHistoryService searchHistoryService;

    @Override
    public CommonSearchResultDto search(SearchRequestDto dto) throws Exception {
        CommonSearchResultDto result = CommonSearchResultDto.builder().build();
        ArrayList<CommonSearchItemDto> list = new ArrayList<>();
        Pagination pagination = Pagination.builder().sort(dto.getSort()).build();

        // event publishing -> save search history
        searchHistoryService.updateHotKeyword(dto.getQuery());

        try {
            KakaoBlogSearchResult k = kakaoSearchService.search(dto);
            list = ModelUtil.toCommonSearchResult(k);
            pagination = createPagination(pagination, k.getMeta().getPageableCount(), dto.getSize(), dto.getPage());
        } catch (KakaoExternalSearchServiceException ke) {
            log.error(ke.getMessage());
            if (ke.getErrorCode().equals(KakaoErrorCode.KAKAO_INTERNAL_SERVER_ERROR)
                    || ke.getErrorCode().equals(KakaoErrorCode.KAKAO_BAD_GATEWAY)
                    || ke.getErrorCode().equals(KakaoErrorCode.KAKAO_SERVICE_UNAVAILABLE)) {
                NaverBlogSearchResult n = naverSearchService.search(dto);
                list = ModelUtil.toCommonSearchResult(n);
                pagination = createPagination(pagination, n.getTotal(), dto.getSize(), dto.getPage());
            } else {
                throw ke;
            }
        } catch (Exception e) {
            throw new Exception();
        }

        result.setList(list);
        result.setPagination(pagination);

        return result;
    }

    private Pagination createPagination(Pagination pagination, int total, int size, int page){
        int totalPage = total/size;
        if (totalPage > 50){
            totalPage = 50;
        }
        boolean hasNext = page < totalPage;
        pagination.setSize(size);
        pagination.setNext(hasNext);
        pagination.setTotalPage(totalPage);
        pagination.setPage(page);
        return pagination;
    }
}
