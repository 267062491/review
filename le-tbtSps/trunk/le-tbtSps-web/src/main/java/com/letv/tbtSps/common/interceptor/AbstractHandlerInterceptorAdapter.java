package com.letv.tbtSps.common.interceptor;

import com.letv.tbtSps.common.cotext.AuthenticatorContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 抽象的拦截器：定义Portal认证上下文
 * 
 * @author yuguodong
 * @version 2016-11-21 下午9:30:33
 */
public abstract class AbstractHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
    /** LOG */
    protected final Log LOG = LogFactory.getLog(getClass());

    /** Portal认证上下文 */
    private AuthenticatorContext context;

    /**
     * @param context
     *            the context to set
     */
    public void setContext(AuthenticatorContext context) {
        this.context = context;
    }

    /**
     * @return the context
     */
    public AuthenticatorContext getContext() {
        return this.context;
    }

}
