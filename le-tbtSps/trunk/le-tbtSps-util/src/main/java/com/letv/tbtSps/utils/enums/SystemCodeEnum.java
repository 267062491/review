package com.letv.tbtSps.utils.enums;

/**
 * 系统编码枚举类
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-2
 * Time: 上午9:07
 * To change this template use File | Settings | File Templates.
 */
public enum SystemCodeEnum {

    SUCCESS("200","操作成功"),

    ATTR_UPLOAD_FAIL("301","附件上传失败"),

    SPS_CODE_HAVE_EXISTS("302","通报编码已经存在"),

    SPS_CODE_NOT_EXISTS("303","通报编码不存在"),

    SYSTEM_RUNTIME_EXCEPTION("304","系统运行时异常");

    private String code ;

    private String content ;

    private SystemCodeEnum(String code , String content){
        this.code = code ;
        this.content = content ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
