package com.mj.search.external.kakao.request;

import com.mj.search.external.exception.ExternalSearchServiceException;
import com.mj.search.external.kakao.constant.KakaoConstant;
import com.mj.search.external.kakao.model.KakaoBlogSearchResult;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class KakaoBlogSearchRequest extends AbstractKaKaoRequest<KakaoBlogSearchResult> {

    private KakaoBlogSearchRequest(final Builder builder) {
        super(builder);
    }

    public KakaoBlogSearchResult execute() throws
            IOException,
            ParseException,
            ExternalSearchServiceException {
        return new KakaoBlogSearchResult.JsonUtil().createModelObject(getJson());
    }

    public static final class Builder extends AbstractKaKaoRequest.Builder<KakaoBlogSearchResult, Builder> {
        public Builder(final String apiToken) {
            super(apiToken);
        }

        public Builder query(final String query) {
            return setQueryParameter("query", query == null ? KakaoConstant.KAKAO_DEFAULT_SEARCH_KEYWORD : query);
        }

        public Builder page(final Integer page) {
            return setQueryParameter("page", page == null ? KakaoConstant.KAKAO_DEFAULT_PAGE : page);
        }

        public Builder size(final Integer size) {
            return setQueryParameter("size", size == null ? KakaoConstant.KAKAO_DEFAULT_SIZE : size);
        }

        public Builder sort(final String sort) {
            return setQueryParameter("sort", sort == null ? KakaoConstant.KAKAO_DEFAULT_SORT : sort);
        }

        @Override
        public KakaoBlogSearchRequest build() {
            setPath("/v2/search/blog");
            return new KakaoBlogSearchRequest(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
