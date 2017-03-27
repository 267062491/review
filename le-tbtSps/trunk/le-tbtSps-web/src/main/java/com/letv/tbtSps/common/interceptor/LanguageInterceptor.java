package com.letv.tbtSps.common.interceptor;

import com.letv.tbtSps.common.cotext.config.LanguageContext;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 登录用户上下文的拦截器<br/>
 * 主要作用：<br/>
 * 拦截用户请求，获取并解析登录的用户cookie；<br/>
 * 如果有用户信息则存到UserContext。
 * 
 *
 * @author yuguodong
 * 
 */
public class LanguageInterceptor extends AbstractHandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (getContext().isExclude(uri)) {
            LOG.debug("in request [" + uri + "], this url is excluded");
        } else {
            LOG.debug("in request [" + uri + "], this url is not excluded");

            try {
                RequestContext requestContext = new RequestContext(request);
                Locale locale = requestContext.getLocale();
                String language = locale.toString();
                if (null != language) {
                    LanguageContext.set(language);
                    request.setAttribute("language", language);
                    LOG.debug("set language value=" + language);
                } else {
                    LOG.debug("set language has no value");
                }
            } catch (Throwable t) {
                LOG.warn("set language error!", t);
            }
        }
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        LanguageContext.remove();
    }

}
