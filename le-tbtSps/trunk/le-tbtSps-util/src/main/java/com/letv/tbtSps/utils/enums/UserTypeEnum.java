package com.letv.tbtSps.utils.enums;

/**
 * 用户类型枚举
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-26
 * Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
public enum UserTypeEnum {

    SELF_DEFIND("SELF_DEFIND","1"), // 自定义用户
    SUPPER_USER("SUPPER_USER","3"); // 超级用户

    private String code_d ;
    private String value ;
    private UserTypeEnum(String code_d, String value){
        this.code_d = code_d ;
        this.value = value ;
    }

    public String getCode_d() {
        return code_d;
    }

    public void setCode_d(String code_d) {
        this.code_d = code_d;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
