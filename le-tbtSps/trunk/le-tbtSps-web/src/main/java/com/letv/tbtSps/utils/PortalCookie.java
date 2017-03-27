package com.letv.tbtSps.utils;

import java.io.Serializable;

/**
 * Portal Cookie 定义
 * 
 * @author yuguodong
 * @version 2016-11-15 下午1:27:36
 */
public class PortalCookie implements Serializable {

    /** 序列化标志 */
    private static final long serialVersionUID = 1L;
    /** cookie名称 */
    private String name;
    /** cookie所属的domain */
    private String domain;
    /** cookie所属的path */
    private String path;

    /**
     * @param name
     * @param domain
     * @param path
     */
    public PortalCookie(String name, String domain, String path) {
        this.name = name;
        this.domain = domain;
        this.path = path;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     *            the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

}
