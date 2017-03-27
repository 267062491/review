package com.letv.tbtSps.utils.constant;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-3-25
 * Time: 下午11:36
 * To change this template use File | Settings | File Templates.
 */
public enum PortalSystemTipCodeEnum {
    SCUESS("SCUESS",200,"操作成功"),
    ERROR("ERROR",500,"系统错误"),
    UPDATE_FAIL("UPDATE_FAIL",300,"更新失败"),
    PAR_ERROR("PAR_ERROR",10001,"参数错误"),
    DATA_NOTEXIST("DATA_NOTEXIST",10002,"数据不存在"),
    WORKBENCH_MORETHANONE("WORKBENCH_MORETHANONE",11001,"返回数据多余一条"),
    USER_NOTEXISTS("USER_NOTEXISTS",300001,"用户不存在"),
    USER_WAREHOUSE_NOTEXISTS("USER_WAREHOUSE_NOTEXISTS",300002 ,"用户没有任何仓库") ,
    WAREHOUSE_NOTEXISTS("WAREHOUSE_NOTEXISTS",300003 ,"仓库不存在") ,
    WAREHOUSE_MORETHANONE("WAREHOUSE_MORETHANONE",300004 ,"一个仓库编码对应多于一个仓库") ,
    USER_NAMEORPASSWORD_ERROR("USER_NAMEORPASSWORD_ERROR",30016,"用户名或密码不正确"),
    USER_DISTIBUTE("USER_DISTIBUTE",30017,"账户已禁用"),
    //
    UPLOAD_EXCEL_FILE("UPLOAD",300005,"请上传EXCEL文件"),
    UPLOAD_EXCEL_FAIL("UPLOAD",300006,"上传失败"),
    UPLOAD_INPUTSTREAM_CLOSE_FAIL("UPLOAD",300007,"关闭流失败"),
    UPLOAD_USERSTATUS_ERROR("UPLOAD",300008,"用户状态错误"),

    //
    USER_UPDATE_PASSWORD_ORASAME("USER",30009,"修改后的密码不能与原密码一致"),
    USER_UPDATE_PASSWORD_SAMEDOUBLE("USER",30009,"密码和确认密码不一致"),
    USER_UPDATE_PASSWORD_SIZE("USER",30010,"密码长度不能小于10位"),
    USER_UPDATE_ORA_PASSWORD_ERROR("USER",30011,"原始密码错误，请重新输入") ,
    USER_PAR_ERROR("USER_PAR_ERROR",30012,"请输入密码和确认密码"),
    USER_SCUESS("SCUESS",200,"修改成功，请重新登录"),

    // 资源验证
    RESOURCE_NOT_EXISTS("RESOURCE",30013,"资源不存在") ,
    RESOURCE_USER_NOT_HAVE("RESOURCE",30014,"用户不能访问资源") ,

    // 角色验证
    ROLE_USER_NOT_EXISTS("ROLE",30015,"该用户无系统权限") ;

    private String code_d ;

    private int value ;

    private String note ;

    private PortalSystemTipCodeEnum(String code_d , int value ,String note ){
        this.code_d = code_d ;
        this.value  = value ;
        this.note = note ;
    }

    public String getCode_d() {
        return code_d;
    }

    public void setCode_d(String code_d) {
        this.code_d = code_d;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
