package com.mj.search.external.naver.request;

import com.mj.search.external.exception.ExternalSearchServiceException;
import com.mj.search.external.naver.constant.NaverConstant;
import com.mj.search.external.naver.model.NaverBlogSearchResult;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class NaverBlogSearchRequest extends AbstractNaverRequest<NaverBlogSearchResult> {

    private NaverBlogSearchRequest(final Builder builder) {
        super(builder);
    }

    public NaverBlogSearchResult execute() throws
            IOException,
            ParseException,
            ExternalSearchServiceException {
        return new NaverBlogSearchResult.JsonUtil().createModelObject(getJson());
    }

    public static final class Builder extends AbstractNaverRequest.Builder<NaverBlogSearchResult, Builder> {
        public Builder(final String clientId, final String clientSecret) {
            super(clientId, clientSecret);
        }

        public Builder query(final String query) {
            return setQueryParameter("query", query == null ? NaverConstant.NAVER_DEFAULT_SEARCH_KEYWORD : query);
        }

        public Builder display(final Integer display) {
            return setQueryParameter("display", display == null ? NaverConstant.NAVER_DEFAULT_DISPLAY : display);
        }

        public Builder start(final Integer start) {
            return setQueryParameter("start", start == null ? NaverConstant.NAVER_DEFAULT_START : start);
        }

        public Builder sort(final String sort) {
            return setQueryParameter("sort", sort == null ? NaverConstant.NAVER_DEFAULT_SORT : sort);
        }

        @Override
        public NaverBlogSearchRequest build() {
            setPath("/v1/search/blog.json");
            return new NaverBlogSearchRequest(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
