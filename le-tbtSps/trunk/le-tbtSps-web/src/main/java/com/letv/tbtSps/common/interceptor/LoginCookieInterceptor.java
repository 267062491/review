package com.letv.tbtSps.common.interceptor;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.config.PropertiesHelper;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.common.cotext.LoginUser;
import com.letv.tbtSps.common.cotext.UserContext;
import com.letv.tbtSps.common.util.JsonUtil;
import com.letv.tbtSps.common.util.LsCookieUtils;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.manager.UserManager;
import com.letv.tbtSps.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录用户上下文的拦截器<br/>
 * 主要作用：<br/>
 * 拦截用户请求，获取并解析登录的用户cookie；<br/>
 * 如果有用户信息则存到UserContext。
 *
 * @author yuguodong
 */
public class LoginCookieInterceptor extends AbstractHandlerInterceptorAdapter {
    @Autowired
    IndexService indexService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (getContext().isExclude(uri)) {
            LOG.debug("in request [" + uri + "], this url is excluded");
        } else {
            LOG.debug("in request [" + uri + "], this url is not excluded");

            try {
                String cookieName = PropertiesHelper.newInstance().getValue("portal.cookie.names");
                String cookieValue = LsCookieUtils.getCookieValueSimple(request, cookieName);
                LOG.debug("get cookie, cookieValue = " + cookieValue);
                if (!StringUtils.isEmpty(cookieValue)) {
//                    LetvResponse<String> letvResponse = indexService.getLoginUserInfoFromRedis(cookieValue);
                    LoginUser loginUser = null;
//                    if (LetvResponse.SUCCESS_CODE == letvResponse.getCode()) {
//                        loginUser = JsonUtil.fromJson(letvResponse.getResult(), LoginUser.class);
                    ;
                    String userInfo = (String) request.getSession().getAttribute(cookieValue.split("_")[1]);
                        loginUser = JsonUtil.fromJson(userInfo, LoginUser.class);
//                    }
                    LOG.debug("LoginCookieInterceptor#preHandle.loginUser = " + JsonHelper.toJson(loginUser));
//                    if (null != loginUser) {
                        UserContext.set(loginUser);
//                        LOG.debug("getCookieValue has user value");
//                    } else {
//                        LOG.debug("getCookieValue has no user value");
//                    }
                }
                else {
                    response.sendRedirect(PropertiesHelper.newInstance().getValue("portal.api.domain"));
                    LOG.error("LoginRequiredInterceptor#preHandle.用户未登录");
                    return false;
                }
            } catch (Throwable t) {
                LOG.error("LoginCookieInterceptor#preHandle.error!", t);
                return false;
            }
        }
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserContext.remove();
    }

}
