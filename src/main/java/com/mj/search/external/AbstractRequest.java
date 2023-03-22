package com.mj.search.external;

import com.mj.search.external.exception.ExternalSearchServiceException;
import com.mj.search.external.kakao.KakaoApi;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRequest<T> implements IRequest<T>{

    private final IHttpManager httpManager;

    private final List<Header> headers;

    private URI uri;

    protected AbstractRequest(Builder<T, ?> builder) {
        assert (builder != null);

        this.httpManager = builder.httpManager;

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder
                .setScheme(builder.scheme)
                .setHost(builder.host)
                .setPort(builder.port)
                .setPath(builder.path);
        if (builder.queryParameters.size() > 0) {
            uriBuilder
                    .setParameters(builder.queryParameters);
        }

        try {
            this.uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            // log
        }

        this.headers = builder.headers;
    }

    public String getJson() throws
            IOException,
            ParseException,
            ExternalSearchServiceException {

        String json = httpManager.get(uri, headers.toArray(new Header[0]));

        return returnJson(json);
    }

    private String returnJson(String json) {
        if (json == null) {
            // log
            return null;
        } else if (json.equals("")) {
            // log
            return null;
        } else {
            // log
            return json;
        }
    }

    public IHttpManager getHttpManager() {
        return httpManager;
    }

    public URI getUri() {return uri;}

    public List<Header> getHeaders() {
        return headers;
    }

    public static abstract class Builder<T, BT extends Builder<T, ?>> implements IRequest.Builder<T, BT> {

        private final List<NameValuePair> pathParameters = new ArrayList<>();
        private final List<NameValuePair> queryParameters = new ArrayList<>();
        private final List<Header> headers = new ArrayList<>();
        private IHttpManager httpManager = KakaoApi.KAKAO_HTTP_MANAGER;
        private String scheme = KakaoApi.KAKAO_API_SCHEME;
        private String host = KakaoApi.KAKAO_API_HOST;
        private Integer port = KakaoApi.KAKAO_API_PORT;

        private String path = null;

        protected Builder() {
        }

        public BT setHttpManager(final IHttpManager httpManager) {
            assert (httpManager != null);
            this.httpManager = httpManager;
            return self();
        }

        public BT setScheme(final String scheme) {
            assert (scheme != null);
            assert (!scheme.equals(""));
            this.scheme = scheme;
            return self();
        }

        public BT setHost(final String host) {
            assert (host != null);
            assert (!scheme.equals(""));
            this.host = host;
            return self();
        }

        public BT setPort(final Integer port) {
            assert (port != null);
            assert (port >= 0);
            this.port = port;
            return self();
        }

        public BT setPath(final String path) {
            assert (path != null);
            assert (!path.equals(""));

            String builtPath = path;

            for (NameValuePair nameValuePair : pathParameters) {
                String key = "\\{" + nameValuePair.getName() + "\\}";
                builtPath = builtPath.replaceAll(key, nameValuePair.getValue());
            }

            this.path = builtPath;
            return self();
        }

        public BT setPathParameter(final String name, final String value) {
            assert (name != null && value != null);
            assert (!name.equals("") && !value.equals(""));

            listAddOnce(this.pathParameters, new BasicNameValuePair(name, value));
            return self();
        }

        public BT setDefaults(final IHttpManager httpManager,
                              final String scheme,
                              final String host,
                              final Integer port) {
            setHttpManager(httpManager);
            setScheme(scheme);
            setHost(host);
            setPort(port);

            return self();
        }

        public <X> BT setQueryParameter(final String name, final X value) {
            assert (name != null);
            assert (!name.equals(""));
            assert (value != null);
            listAddOnce(this.queryParameters, new BasicNameValuePair(name, String.valueOf(value)));
            return self();
        }

        public <X> BT setHeader(final String name, final X value) {
            assert (name != null);
            assert (!name.equals(""));
            assert (value != null);
            listAddOnce(this.headers, new BasicHeader(name, String.valueOf(value)));
            return self();
        }

        private void listAddOnce(List<NameValuePair> nameValuePairs, NameValuePair newNameValuePair) {
            nameValuePairs.removeAll(nameValuePairs.stream()
                    .filter(nameValuePair -> nameValuePair.getName().equals(newNameValuePair.getName()))
                    .collect(Collectors.toList()));
            nameValuePairs.add(newNameValuePair);
        }

        private void listAddOnce(List<Header> headers, Header newHeader) {
            headers.removeAll(headers.stream()
                    .filter(header -> header.getName().equals(newHeader.getName()))
                    .collect(Collectors.toList()));
            headers.add(newHeader);
        }

        protected abstract BT self();
    }
}
