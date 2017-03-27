package com.letv.tbtSps.common.cotext.config;

/**
 * Portal认证
 * 
 * @author yuguodong
 * @version 2016-11-21 下午9:30:33
 */
public class Authenticator {
    /** Portal 站点标志 */
//    private String portalAuthenFlag;
    /** Portal Web域名 */
    private String portalWebDomain;
    /** Portal API 域名 */
    private String portalApiDomain;
    /** 子系统Web域名 */
//    private String myWebDomain;

    /**
     * @param portalAuthenFlag
     *            Portal认证标志
     * @param portalWebDomain
     *            Portal Web域名
     * @param portalApiDomain
     *            Portal api 域名
     * @param myWebDomain
     *            子系统Web域名
     */
    public Authenticator(String portalAuthenFlag, String portalWebDomain, String portalApiDomain, String myWebDomain) {
//        this.portalAuthenFlag = portalAuthenFlag;
        this.portalWebDomain = portalWebDomain;
        this.portalApiDomain = portalApiDomain;
//        this.myWebDomain = myWebDomain;
    }

    /**
     * @return the portalAuthenFlag
     */
//    public String getPortalAuthenFlag() {
//        return this.portalAuthenFlag;
//    }

    /**
     * @return the portalWebDomain
     */
    public String getPortalWebDomain() {
        return this.portalWebDomain;
    }

    /**
     * @return the portalApiDomain
     */
    public String getPortalApiDomain() {
        return this.portalApiDomain;
    }

    /**
     * @return the myWebDomain
     */
//    public String getMyWebDomain() {
//        return this.myWebDomain;
//    }

}
