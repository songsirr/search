package com.mj.search.external;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.http.protocol.HttpCoreContext;

import java.io.IOException;

@Slf4j
public abstract class AbstractHttpManager implements IHttpManager {

    protected CloseableHttpResponse execute(CloseableHttpClient httpClient, ClassicHttpRequest method) throws
            IOException {
        HttpContext context = HttpCoreContext.create();
        CloseableHttpResponse response = httpClient.execute(method, context);

        return response;
    }
}
