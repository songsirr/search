package com.mj.search.service.impl;

import com.mj.search.common.constant.CommonConstant;
import com.mj.search.common.event.event.SearchHistoryEvent;
import com.mj.search.common.util.ModelUtil;
import com.mj.search.common.util.NumberUtil;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonSearchServiceImpl implements CommonSearchService {

    private final KakaoSearchService kakaoSearchService;

    private final NaverSearchService naverSearchService;

    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 통합검색 (카카오 우선으로 검색 -> 카카오 에러 사용불가(5xx) 시 네이버 검색)
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public CommonSearchResultDto search(SearchRequestDto dto) throws Exception {
        CommonSearchResultDto result = CommonSearchResultDto.builder().build();
        ArrayList<CommonSearchItemDto> list = new ArrayList<>();
        Pagination pagination = Pagination.builder().build();

        applicationEventPublisher.publishEvent(new SearchHistoryEvent(dto.getQuery()));

        try {
            KakaoBlogSearchResult k = kakaoSearchService.search(dto);
            list = ModelUtil.toCommonSearchResult(k);
            pagination = createPagination(pagination, k.getMeta().getPageableCount(), dto.getSize(), dto.getPage(), dto.getSort());
        } catch (KakaoExternalSearchServiceException ke) {
            log.error(ke.getMessage());
            if (ke.getErrorCode().equals(KakaoErrorCode.KAKAO_INTERNAL_SERVER_ERROR)
                    || ke.getErrorCode().equals(KakaoErrorCode.KAKAO_BAD_GATEWAY)
                    || ke.getErrorCode().equals(KakaoErrorCode.KAKAO_SERVICE_UNAVAILABLE)) {
                NaverBlogSearchResult n = naverSearchService.search(dto);
                list = ModelUtil.toCommonSearchResult(n);
                pagination = createPagination(pagination, n.getTotal(), dto.getSize(), dto.getPage(), dto.getSort());
            } else {
                throw ke;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception();
        }

        result.setList(list);
        result.setPagination(pagination);

        return result;
    }

    /**
     * 페이징을 위한 변수를 셋팅하는 메서드
     * @param pagination
     * @param total
     * @param size
     * @param page
     * @param sort
     * @return
     */
    private Pagination createPagination(Pagination pagination, Integer total, Integer size, Integer page, String sort){
        int sizeValue = NumberUtil.defaultIfNull(CommonConstant.DEFAULT_SEARCH_SIZE, size);
        int pageValue = NumberUtil.defaultIfNull(CommonConstant.DEFAULT_SEARCH_PAGE, page);
        int totalPage = total/sizeValue;
        if (total%sizeValue != 0){
            totalPage++;
        }
        if (totalPage > 50){
            totalPage = 50;
        }
        boolean hasNext = pageValue < totalPage;
        pagination.setSize(sizeValue);
        pagination.setNext(hasNext);
        pagination.setTotalPage(totalPage);
        pagination.setPage(pageValue);
        if (sort != null){
            pagination.setSort(sort);
        }
        return pagination;
    }
}
