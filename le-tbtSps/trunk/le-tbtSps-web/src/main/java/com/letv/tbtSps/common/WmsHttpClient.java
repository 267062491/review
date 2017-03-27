package com.letv.tbtSps.common;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpProcessor;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by zhanghuibin on 2016/11/15.
 */
public class WmsHttpClient extends DefaultHttpClient {
    private String token;
    private List<HttpRequestInterceptor> interceptors;

    public void setInterceptors(List<HttpRequestInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @since 4.1
     */
    public WmsHttpClient(
            final ClientConnectionManager conman) {
        super(conman, null);
    }

    @Override
    protected BasicHttpProcessor createHttpProcessor() {
        final BasicHttpProcessor httpproc = super.createHttpProcessor();
        if (!StringUtils.isEmpty(token)) {
            httpproc.addInterceptor(new RequestToken(token));
        }
        if (interceptors != null) {
            for (HttpRequestInterceptor interceptor : interceptors) {
                httpproc.addInterceptor(interceptor);
            }
        }
        return httpproc;
    }
}
