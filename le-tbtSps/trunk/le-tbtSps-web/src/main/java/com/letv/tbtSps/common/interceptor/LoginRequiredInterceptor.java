package com.letv.tbtSps.common.interceptor;


import com.letv.tbtSps.common.cotext.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * 登录用户验证的拦截器<br/>
 * 主要作用：<br/>
 * 拦截用户请求， 判断UserContext中是否有登录用户信息；<br/>
 * 如果没有则跳转到登录界面。
 * 
 *
 * @author yuguodong
 * 
 */
public class LoginRequiredInterceptor extends AbstractHandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException, MalformedURLException {
        String uri = request.getRequestURI();
        if (getContext().isExclude(uri)) {
            LOG.debug("in request [" + uri + "], this url is excluded");
        } else {
            LOG.debug("in request [" + uri + "], this url is not excluded");

            try {
                // check 有没有登录
                UserContext context = UserContext.get();
                if (context == null || !context.isLogin()) {// 没登录
                    response.sendRedirect(getContext().getLoginUrl(request));
                    LOG.error("LoginRequiredInterceptor#preHandle.用户未登录");
                    return false;
                }
            } catch (Throwable t) {
                LOG.error("LoginRequiredInterceptor#preHandle.error!", t);
            }
        }
        return true;
    }

}
