package com.mj.search.external;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import java.net.URI;
import org.apache.hc.core5.http.Header;

import java.io.IOException;
import org.apache.hc.core5.http.ParseException;
import com.mj.search.external.exception.ExternalSearchServiceException;

public interface IHttpManager {

    String get(URI uri, Header[] headers) throws IOException, ParseException, ExternalSearchServiceException;

    String getResponseBody(CloseableHttpResponse httpResponse) throws IOException, ParseException, ExternalSearchServiceException;
}
