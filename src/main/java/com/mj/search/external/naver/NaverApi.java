package com.mj.search.external.naver;

import com.mj.search.external.IHttpManager;
import com.mj.search.external.kakao.KakaoHttpManager;
import com.mj.search.external.kakao.request.KakaoBlogSearchRequest;
import com.mj.search.external.naver.request.NaverBlogSearchRequest;

public class NaverApi {

    public static final String NAVER_API_HOST = "openapi.naver.com";

    public static final int NAVER_API_PORT = 443;

    public static final String NAVER_API_SCHEME = "https";

    public static final IHttpManager NAVER_HTTP_MANAGER = new NaverHttpManager.Builder().build();

    private final IHttpManager httpManager;

    private final String host;

    private final Integer port;

    private final String scheme;

    private String clientId;

    private String clientSecret;

    public NaverBlogSearchRequest.Builder blogSearch(String query){
        return new NaverBlogSearchRequest.Builder(clientId, clientSecret)
                .setDefaults(httpManager, scheme, host, port)
                .query(query);
    }

    public static NaverApi.Builder builder() {
        return new NaverApi.Builder();
    }

    public NaverApi(Builder builder){
        assert (builder.httpManager != null);
        this.httpManager = builder.httpManager;
        this.host = builder.host;
        this.port = builder.port;
        this.scheme = builder.scheme;
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
    }

    public static class Builder {
        private IHttpManager httpManager = NAVER_HTTP_MANAGER;
        private String host = NAVER_API_HOST;
        private Integer port = NAVER_API_PORT;
        private String scheme = NAVER_API_SCHEME;
        private String clientId;
        private String clientSecret;

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

        public Builder setClientId(String clientId){
            this.clientId = clientId;
            return this;
        }

        public Builder setClientSecret(String clientSecret){
            this.clientSecret = clientSecret;
            return this;
        }

        public NaverApi build(){
            return new NaverApi(this);
        }
    }
}
