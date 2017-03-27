package com.letv.tbtSps.common.cotext.config;

/**
 * Portal认证配置
 * 
 * @author yuguodong
 * @version 2016-11-21 下午9:30:33
 */
public class AuthenticatorConfig {

    /** Portal认证标志:多个用,隔开 */
    private String portalAuthenFlags;
    /** Portal Web域名 */
    private String portalWebDomains;
    /** Portal API 域名 */
    private String portalApiDomains;
    /** 子系统Web域名 */
//    private String myWebDomains;
    /** URL拦截器的排除路径，多个以逗号隔开 */
    private String excludePaths;

    /**
     * @param portalAuthenFlags
     *            the portalAuthenFlags to set
     */
    public void setPortalAuthenFlags(String portalAuthenFlags) {
        this.portalAuthenFlags = portalAuthenFlags;
    }

    /**
     * @param portalWebDomains
     *            the portalWebDomains to set
     */
    public void setPortalWebDomains(String portalWebDomains) {
        this.portalWebDomains = portalWebDomains;
    }

    /**
     * @param portalApiDomains
     *            the portalApiDomains to set
     */
    public void setPortalApiDomains(String portalApiDomains) {
        this.portalApiDomains = portalApiDomains;
    }

    /**
     * @param myWebDomains
     *            the myWebDomains to set
     */
//    public void setMyWebDomains(String myWebDomains) {
//        this.myWebDomains = myWebDomains;
//    }

    /**
     * @param excludePaths
     *            the excludePaths to set
     */
    public void setExcludePaths(String excludePaths) {
        this.excludePaths = excludePaths;
    }

    /**
     * @return the excludePaths
     */
    public String getExcludePaths() {
        return this.excludePaths;
    }

    /**
     * @return the portalAuthenFlags
     */
    public String getPortalAuthenFlags() {
        return this.portalAuthenFlags;
    }

    /**
     * @return the portalWebDomains
     */
    public String getPortalWebDomains() {
        return this.portalWebDomains;
    }

    /**
     * @return the portalApiDomains
     */
    public String getPortalApiDomains() {
        return this.portalApiDomains;
    }

    /**
     * @return the myWebDomains
     */
//    public String getMyWebDomains() {
//        return this.myWebDomains;
//    }

}
