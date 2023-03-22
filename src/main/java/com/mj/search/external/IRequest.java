package com.mj.search.external;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mj.search.external.exception.ExternalSearchServiceException;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public interface IRequest<T> {

    IHttpManager getHttpManager();

    URI getUri();

    List<Header> getHeaders();

    T execute() throws
            IOException,
            ParseException,
            ExternalSearchServiceException;

    String getJson() throws
            IOException,
            ParseException,
            ExternalSearchServiceException;

    @JsonPOJOBuilder(withPrefix = "set")
    interface Builder<T, BT extends Builder<T, ?>> {

        BT setHttpManager(final IHttpManager httpManager);

        BT setScheme(final String scheme);

        BT setHost(final String host);

        BT setPort(final Integer port);

        BT setPath(final String path);

        BT setDefaults(final IHttpManager httpManager,
                       final String scheme,
                       final String host,
                       final Integer port);

        <ST> BT setQueryParameter(final String name, final ST value);

        <ST> BT setHeader(final String name, final ST value);

        IRequest<T> build();
    }
}
