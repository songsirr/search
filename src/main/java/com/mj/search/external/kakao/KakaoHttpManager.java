package com.mj.search.external.kakao;

import com.google.gson.*;
import com.mj.search.common.error.KakaoErrorCode;
import com.mj.search.common.exception.ServiceException;
import com.mj.search.external.IHttpManager;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.net.URI;

public class KakaoHttpManager implements IHttpManager {

    private final CloseableHttpClient httpClient;

    private final HttpHost proxy;

    private final Integer connectionRequestTimeout;

    private final Integer connectTimeout;

    public KakaoHttpManager(Builder builder){
        this.proxy = builder.proxy;
        this.connectionRequestTimeout = builder.connectionRequestTimeout;
        this.connectTimeout = builder.connectTimeout;

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setCookieSpec(StandardCookieSpec.STRICT)
                .setProxy(proxy)
                .setConnectionRequestTimeout(builder.connectionRequestTimeout != null
                        ? Timeout.ofMilliseconds(builder.connectionRequestTimeout)
                        : RequestConfig.DEFAULT.getConnectionRequestTimeout())
                .setConnectTimeout(builder.connectTimeout != null
                        ? Timeout.ofMilliseconds(builder.connectTimeout)
                        : RequestConfig.DEFAULT.getConnectTimeout())
                .build();

        this.httpClient = HttpClients
                .custom()
                .setDefaultRequestConfig(requestConfig)
                .disableContentCompression()
                .build();
    }

    @Override
    public String get(URI uri, Header[] headers) throws IOException, ParseException, ServiceException {
        assert (uri != null);
        assert (!uri.toString().equals(""));

        final HttpGet httpGet = new HttpGet(uri);

        httpGet.setHeaders(headers);

        String responseBody = getResponseBody(httpClient.execute(httpGet));

        httpGet.reset();

        return responseBody;
    }

    @Override
    public String getResponseBody(CloseableHttpResponse httpResponse) throws IOException, ParseException, ServiceException {
        final String responseBody = httpResponse.getEntity() != null
                ? EntityUtils.toString(httpResponse.getEntity(), "UTF-8")
                : null;
        String errorMessage = httpResponse.getReasonPhrase();

        if (responseBody != null && !responseBody.equals("")) {
            try {
                final JsonElement jsonElement = JsonParser.parseString(responseBody);

                if (jsonElement.isJsonObject()) {
                    final JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

                    if (jsonObject.has("errorType")) {
                        if (jsonObject.has("message")) {
                            errorMessage = jsonObject.get("message").getAsString();
                        }
                    }
                }
            } catch (JsonSyntaxException e) {
                // Not necessary
            }
        }

        switch (httpResponse.getCode()) {
            case HttpStatus.SC_BAD_REQUEST:
                throw new ServiceException(KakaoErrorCode.EXTERNAL_BAD_REQUEST, errorMessage);
            case HttpStatus.SC_UNAUTHORIZED:
                throw new ServiceException(KakaoErrorCode.EXTERNAL_UNAUTHORIZED, errorMessage);
            case HttpStatus.SC_FORBIDDEN:
                throw new ServiceException(KakaoErrorCode.EXTERNAL_FORBIDDEN, errorMessage);
            case HttpStatus.SC_NOT_FOUND:
                throw new ServiceException(KakaoErrorCode.EXTERNAL_NOT_FOUND, errorMessage);
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                throw new ServiceException(KakaoErrorCode.EXTERNAL_INTERNAL_SERVER_ERROR, errorMessage);
            case HttpStatus.SC_BAD_GATEWAY:
                throw new ServiceException(KakaoErrorCode.EXTERNAL_BAD_GATEWAY, errorMessage);
            case HttpStatus.SC_SERVICE_UNAVAILABLE:
                throw new ServiceException(KakaoErrorCode.EXTERNAL_SERVICE_UNAVAILABLE, errorMessage);
            default:
                return responseBody;
        }
    }

    public static class Builder {
        private HttpHost proxy;

        private Integer connectionRequestTimeout;

        private Integer connectTimeout;

        public Builder setProxy(HttpHost proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder setConnectionRequestTimeout(Integer connectionRequestTimeout) {
            this.connectionRequestTimeout = connectionRequestTimeout;
            return this;
        }

        public Builder setConnectTimeout(Integer connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public KakaoHttpManager build(){
            return new KakaoHttpManager(this);
        }
    }
}
