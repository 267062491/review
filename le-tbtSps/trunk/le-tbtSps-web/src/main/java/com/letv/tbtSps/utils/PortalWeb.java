package com.letv.tbtSps.utils;

import com.letv.common.utils.StringUtil;
import com.letv.common.utils.config.PropertiesHelper;
import com.letv.common.utils.security.MD5Util;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.common.util.LsCookieUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Portal Web定义:域名、cookie等
 * 
 * @author yuguodong
 * @version 2016-11-11 下午1:46:29
 */
public class PortalWeb implements InitializingBean, Serializable {

    protected final Log LOG = LogFactory.getLog(getClass());
    /** 序列化标志 */
    private static final long serialVersionUID = -6701008909109654860L;
    /** 默认cookie路径 */
    private static final String DEFAULT_COOKIE_PATH = "/";
    /** Web域名 */
    private String webDomain;
    /** cookie名称 */
    private String cookieNames;
    /** cookie所属的domain */
    private String cookieDomains;
    /** cookies */
    private List<PortalCookie> cookies;

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(webDomain, "'webDomain' must be not empty");
        Assert.hasText(cookieNames, "'cookieName' must be not empty");
        Assert.hasText(cookieDomains, "'cookieDomain' must be not empty");
        LOG.info("cookieNames=" + cookieNames);
        LOG.info("cookieDomains=" + cookieDomains);

        String[] names = cookieNames.split(StringUtil.SEP_COMMA);
        String[] domains = cookieDomains.split(StringUtil.SEP_COMMA);
        Assert.noNullElements(names, "names' must be not null");
        Assert.noNullElements(domains, "domains' must be not null");

        cookies = new ArrayList<PortalCookie>(names.length);
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            String domain = domains[i];
            cookies.add(new PortalCookie(name, domain, DEFAULT_COOKIE_PATH));
        }
    }


    /**
     * @return the webDomain
     */
    public String getWebDomain() {
        return webDomain;
    }

    /**
     * @param webDomain
     *            the webDomain to set
     */
    public void setWebDomain(String webDomain) {
        this.webDomain = webDomain;
    }


    /**
     * @param cookieNames
     *            the cookieNames to set
     */
    public void setCookieNames(String cookieNames) {
        this.cookieNames = cookieNames;
    }

    /**
     * @param cookieDomains
     *            the cookieDomains to set
     */
    public void setCookieDomains(String cookieDomains) {
        this.cookieDomains = cookieDomains;
    }

    /**
     * 写入portal定义的所有cookie
     * 
     * @param response
     * @param cookieValue
     */
    public void setCookies(HttpServletRequest request,HttpServletResponse response, String cookieValue,String userCode) {
        for (PortalCookie cookie : this.cookies) {
            /**
             * 1、调用写的 LsCookieUtils 写cookie
             */
//            String cookieValue_md5 = LsCookieUtils.createCookieValue(cookieValue, PropertiesHelper.newInstance().getValue("portal.redis.platform"), PropertiesHelper.newInstance().getValue("portal.redis.environment"))  ;
            LsCookieUtils.setCookie(request,response, cookie.getName(), cookieValue, cookie.getDomain(), cookie.getPath(),userCode);
            LOG.debug("set cookie, name=" + cookie.getName() + ", domain= " + cookie.getDomain() + ", value= "
                    + cookieValue);
        }

    }
    public void invalidateCookie(HttpServletResponse response) {
            /**
             * 1、调用写的 LsCookieUtils 写cookie
             */
            LsCookieUtils.invalidateCookie(response, PropertiesHelper.newInstance().getValue("portal.authen.flag"), PropertiesHelper.newInstance().getValue("portal.web.domain"), "/");

    }
}
