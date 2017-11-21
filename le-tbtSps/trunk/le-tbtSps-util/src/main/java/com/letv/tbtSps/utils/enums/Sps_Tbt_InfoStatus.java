package com.letv.tbtSps.utils.enums;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-3
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */
public enum Sps_Tbt_InfoStatus {
    // 未分配  已分配 汇总评议 已反馈

    UN_FENPEI("10","未分配"),
    HAVE_FENPEI("20","已分配"),// 也叫做待评议（在专家处叫做）
        HAVE_FENPEI_HUIDA("21","已经评议"),// log表才有的状态， spsinfo表么有这个状态
        HAVE_FENPEI_HUIZONG("22","已经评议汇总"),// log表才有的状态， spsinfo表么有这个状态    这个应该是个没有用的的状态
    HUIZONG_PINGYI("30","汇总评议"),
    HAVE_FANKUI("40","反馈信息"),


    // 专家对应的状态
    EXPERTS_UN_REVIEW("20_00","未评议"),
    EXPERTS_REVIEW("21_01","已评议");


    private String statusCode ;
    private String statusContent ;

    private Sps_Tbt_InfoStatus(String statusCode , String statusContent){
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
