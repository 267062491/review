package com.letv.tbtSps.utils.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-3
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
public enum ScopeEnum {

    ALL("","全部"),
    SELF("self","自己");


    private String statusCode ;
    private String statusContent ;

    private ScopeEnum(String statusCode, String statusContent){
        this.statusCode = statusCode ;
        this.statusContent = statusContent ;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }
}
