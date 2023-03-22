package com.mj.search.external.kakao.request;

import com.mj.search.external.exception.ExternalSearchServiceException;
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
            assert (query != null);
            assert (!query.equals(""));
            return setQueryParameter("query", query);
        }

        public Builder page(final Integer page) {
            assert (page != null);
            assert (1 <= page && page <= 50);
            return setQueryParameter("page", page);
        }

        public Builder size(final Integer size) {
            assert (size != null);
            assert (1 <= size && size <= 50);
            return setQueryParameter("size", size);
        }

        public Builder sort(final String sort) {
            assert (sort != null);
            assert (sort.equals("accuracy") || sort.equals("recency"));
            return setQueryParameter("sort", sort);
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
