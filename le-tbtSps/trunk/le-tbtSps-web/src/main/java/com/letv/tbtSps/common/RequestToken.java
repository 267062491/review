package com.letv.tbtSps.common;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

import java.io.IOException;

/**
 * Created by zhanghuibin on 2016/11/15.
 */
public class RequestToken implements HttpRequestInterceptor {
    private final String token;

    public RequestToken(String token) {
        this.token = token;
    }

    public RequestToken() {
        this((String)null);
    }

    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        if(!request.containsHeader("Token")) {
            String s = this.token;
            if(s != null) {
                request.addHeader("Token", s);
            }
        }

    }
}
