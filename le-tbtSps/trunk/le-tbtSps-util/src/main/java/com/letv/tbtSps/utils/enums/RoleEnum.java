package com.letv.tbtSps.utils.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-3
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
public enum RoleEnum {
    // 高，中， 低

    CCPR("CCPR","CCPR"),
    outExports("outExports","外部专家"),
    notice_manager("notice_manager","通报管理员"),
    limit_manager("limit_manager","限量数据管理员"),
    sys_manager("sys_manager","系统管理员");


    private String statusCode ;
    private String statusContent ;

    private RoleEnum(String statusCode, String statusContent){
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
