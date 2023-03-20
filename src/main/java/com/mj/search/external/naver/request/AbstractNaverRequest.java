package com.mj.search.external.naver.request;

import com.mj.search.external.AbstractRequest;

public abstract class AbstractNaverRequest<T> extends AbstractRequest<T> {

    protected AbstractNaverRequest(final Builder<T, ?> builder) {
        super(builder);
    }

    public static abstract class Builder<T, BT extends Builder<T, ?>> extends AbstractRequest.Builder<T, BT> {
        protected Builder(String clientId, String clientSecret) {
            super();

            assert (clientId != null);
            assert (!clientId.equals(""));

            assert (clientSecret != null);
            assert (!clientSecret.equals(""));

            setHeader("X-Naver-Client-Id", clientId);
            setHeader("X-Naver-Client-Secret", clientSecret);
        }
    }
}
