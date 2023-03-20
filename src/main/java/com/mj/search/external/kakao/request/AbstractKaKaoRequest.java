package com.mj.search.external.kakao.request;

import com.mj.search.external.AbstractRequest;

public abstract class AbstractKaKaoRequest<T> extends AbstractRequest<T> {

    protected AbstractKaKaoRequest(final Builder<T, ?> builder) {
        super(builder);
    }

    public static abstract class Builder<T, BT extends Builder<T, ?>> extends AbstractRequest.Builder<T, BT> {
        protected Builder(String apiToken) {
            super();

            assert (apiToken != null);
            assert (!apiToken.equals(""));

            setHeader("Authorization", "KakaoAK " + apiToken);
        }
    }
}
