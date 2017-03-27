package com.letv.tbtSps.common;

/**
 * Created by zhanghuibin on 2016/11/17.
 */
public class ResteasyServiceConfig {
    private String base;
    private String basePackages;
    private String token;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(String basePackages) {
        this.basePackages = basePackages;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
