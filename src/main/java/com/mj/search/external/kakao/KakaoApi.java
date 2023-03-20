package com.mj.search.external.kakao;

import com.mj.search.external.IHttpManager;
import com.mj.search.external.kakao.request.KakaoBlogSearchRequest;

public class KakaoApi {

    public static final String KAKAO_API_HOST = "dapi.kakao.com";

    public static final int KAKAO_API_PORT = 443;

    public static final String KAKAO_API_SCHEME = "https";

    public static final IHttpManager KAKAO_HTTP_MANAGER = new KakaoHttpManager.Builder().build();

    private final IHttpManager httpManager;

    private final String host;

    private final Integer port;

    private final String scheme;

    private String apiToken;

    public KakaoBlogSearchRequest.Builder blogSearch(String query){
        return new KakaoBlogSearchRequest.Builder(apiToken)
                .setDefaults(httpManager, scheme, host, port)
                .query(query);
    }

    public static KakaoApi.Builder builder() {
        return new KakaoApi.Builder();
    }

    public KakaoApi(Builder builder){
        assert (builder.httpManager != null);
        this.httpManager = builder.httpManager;
        this.host = builder.host;
        this.port = builder.port;
        this.scheme = builder.scheme;
        this.apiToken = builder.apiToken;
    }

    public static class Builder {
        private IHttpManager httpManager = KAKAO_HTTP_MANAGER;
        private String host = KAKAO_API_HOST;
        private Integer port = KAKAO_API_PORT;
        private String scheme = KAKAO_API_SCHEME;
        private String apiToken;

        public Builder setHttpManager(IHttpManager httpManager) {
            this.httpManager = httpManager;
            return this;
        }

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setPort(Integer port) {
            this.port = port;
            return this;
        }

        public Builder setScheme(String scheme) {
            this.scheme = scheme;
            return this;
        }

        public Builder setApiToken(String apiToken){
            this.apiToken = apiToken;
            return this;
        }

        public KakaoApi build(){
            return new KakaoApi(this);
        }
    }
}
