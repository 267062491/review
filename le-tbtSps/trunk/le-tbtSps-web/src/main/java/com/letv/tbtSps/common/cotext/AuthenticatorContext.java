package com.letv.tbtSps.common.cotext;

import com.letv.common.utils.StringUtil;
import com.letv.tbtSps.common.cotext.config.Authenticator;
import com.letv.tbtSps.common.cotext.config.AuthenticatorConfig;
import com.letv.tbtSps.common.url.UrlBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Portal认证上下文
 * 
 * @author yuguodong
 * @version 2016-11-21 下午9:30:33
 */
public class AuthenticatorContext implements InitializingBean {
    /** LOG */
    protected final Log LOG = LogFactory.getLog(getClass());

    /** Portal认证配置 */
    private AuthenticatorConfig authenticatorConfig;
    /** Portal认证MAP */
    private Map<String, Authenticator> authenticatorMap;
    /** Portal默认认证 */
    private Authenticator DEFAULT_AUTHEN = null;

    /**
     * @param authenticatorConfig
     *            the authenticatorConfig to set
     */
    public void setAuthenticatorConfig(AuthenticatorConfig authenticatorConfig) {
        this.authenticatorConfig = authenticatorConfig;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.hasText(authenticatorConfig.getPortalAuthenFlags(), "'portalWebDomains' must be not null");
        Assert.hasText(authenticatorConfig.getPortalWebDomains(), "'portalWebDomains' must be not null");
        Assert.hasText(authenticatorConfig.getPortalApiDomains(), "'portalApiDomains' must be not null");
//        Assert.hasText(authenticatorConfig.getMyWebDomains(), "'myWebDomains' must be not null");

        String[] authenFlags = authenticatorConfig.getPortalAuthenFlags().split(StringUtil.SEP_COMMA);
        String[] webDomains = authenticatorConfig.getPortalWebDomains().split(StringUtil.SEP_COMMA);
        String[] apiDomains = authenticatorConfig.getPortalApiDomains().split(StringUtil.SEP_COMMA);
//        String[] myWebDomainArray = authenticatorConfig.getMyWebDomains().split(StringUtil.SEP_COMMA);

        Assert.noNullElements(authenFlags, "authenFlags' must be not empty");
        Assert.noNullElements(webDomains, "webDomains' must be not empty");
        Assert.noNullElements(apiDomains, "apiDomains' must be not empty");
//        Assert.noNullElements(myWebDomainArray, "myWebDomainArray' must be not empty");

        Assert.state(authenFlags.length == webDomains.length, "siteFlags.length != webDomains.length");
        Assert.state(authenFlags.length == apiDomains.length, "siteFlags.length != apiDomains.length");
//        Assert.state(authenFlags.length == myWebDomainArray.length, "siteFlags.length != myWebDomainArray.length");

        authenticatorMap = new ConcurrentHashMap<String, Authenticator>(authenFlags.length);
        for (int i = 0; i < authenFlags.length; i++) {
            String authenFlag = authenFlags[i];
            authenticatorMap.put(authenFlag, new Authenticator(authenFlag, webDomains[i], apiDomains[i],
                    ""));
        }
        DEFAULT_AUTHEN = new Authenticator(authenFlags[0], webDomains[0], apiDomains[0], "");
    }

    /**
     * 判断请求的URL是否包含在排除路径之中
     * 
     * @param requestUrl
     *            请求的URL
     * @return
     */
    public boolean isExclude(String requestUrl) {
        return StringUtil.isContains(authenticatorConfig.getExcludePaths(), requestUrl);
    }


    /**
     * @param request
     * @return
     * @throws java.net.MalformedURLException
     */
    public String getLoginUrl(HttpServletRequest request) throws MalformedURLException {
        Authenticator authenticator = getAuthenticator(null);
        UrlBuilder.Builder currentUrlBuilder = new UrlBuilder(authenticator.getPortalWebDomain() + "/").forPath(request
                .getRequestURI());
        currentUrlBuilder.put(request.getParameterMap());

        UrlBuilder.Builder loginUrlBuilder = new UrlBuilder(authenticator.getPortalWebDomain() + "/portal/login")
                .forPath(null);

        loginUrlBuilder.put("ReturnUrl", currentUrlBuilder.build());

        return loginUrlBuilder.build();
    }

    /**
     * @param authenFlag
     * @return
     */
    private Authenticator getAuthenticator(String authenFlag) {
        if (StringUtils.isBlank(authenFlag)) {
            return DEFAULT_AUTHEN;
        }
        return this.authenticatorMap.get(authenFlag);
    }

    /**
     * 获取没权限操作的URL
     *
     * @param user
     * @param resource
     * @return
     * @throws java.net.MalformedURLException
     */
    public String getNoPermissionUrl(HttpServletRequest request, String user, String resource)
            throws MalformedURLException {
        Authenticator authenticator = getAuthenticator(null);
        UrlBuilder.Builder builder = new UrlBuilder(authenticator.getPortalWebDomain() + "/portal/error/noPermission")
                .forPath(null);
        builder.put("user", user);
        builder.put("resource", resource);
        builder.put("messageCode", request.getAttribute("messageCode"));
        return builder.build();
    }
    public String getPortalApiDomain(HttpServletRequest request){
        Authenticator authenticator = getAuthenticator(null);
        String domain = authenticator.getPortalApiDomain() + "/" ;
        return domain;
    }
}
