package com.letv.tbtSps.common.cotext;

import com.letv.common.utils.serialize.JsonHelper;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 登录用户信息
 * 
 * @author yuguodong
 * @version 2016-10-14 下午2:10:18
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 3254397744596286299L;
    /** 登录用户的Id */
    private Long userId;

    /** 登录用户的账号 */
    private String userName;

    /** 登录用户的中文姓名 */
    private String cnName;

    /** 登录用户的所属仓库编码 */
    private String warehouseNo;

    /** 登录用户的所属仓库名称 */
    private String warehouseName;
    /** 当前语言 */
    private String language ;
    /**登录用户编码**/
    private String userCode ;
    /**登录用户所属库房简码**/
    private String warehouseSimpleCode ;

    /**
     * 用户类型
     */
    private Integer userType ;

    public LoginUser() {
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the cnName
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * @param cnName
     *            the cnName to set
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Override
    public String toString() {
        return JsonHelper.toJson(this);
    }

    public static LoginUser parse(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return JsonHelper.toBean(value, LoginUser.class);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getWarehouseSimpleCode() {
        return warehouseSimpleCode;
    }

    public void setWarehouseSimpleCode(String warehouseSimpleCode) {
        this.warehouseSimpleCode = warehouseSimpleCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
