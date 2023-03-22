package com.mj.search.external.naver.request;

import com.mj.search.external.exception.ExternalSearchServiceException;
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
            assert (query != null);
            assert (!query.equals(""));
            return setQueryParameter("query", query);
        }

        public Builder display(final Integer display) {
            assert (display != null);
            assert (10 <= display && display <= 100);
            return setQueryParameter("display", display);
        }

        public Builder start(final Integer start) {
            assert (start != null);
            assert (1 <= start && start <= 1000);
            return setQueryParameter("start", start);
        }

        public Builder sort(final String sort) {
            assert (sort != null);
            assert (sort.equals("sim") || sort.equals("date"));
            return setQueryParameter("sort", sort);
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
