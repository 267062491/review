package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * User：用户表实体类
 * 
 * @author yuguodong
 * @version 2016-10-26 9:37:54
 * 
 */
public class User implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 用户登录名 */
    private String userName; 
    /** 用户显示名称 */
    private String showName; 
    /** 用户编码 */
    private String userCode; 
    /** 密码 */
    private String password; 
    /** email */
    private String email; 
    /** 手机号 */
    private String phoneno; 
    /** 用户职位 */
    private String duty; 
    /** 用户对应名 */
    private String userOthername; 
    /** 用户类型 */
    private Integer userType; 
    /** 用户状态 1、启用 ； 0：禁用*/
    private Integer userState; 
    /** 用户地址 */
    private String userAddress; 
    /** 备注 */
    private String note; 
    /** 创建时间 */
    private Date createTime; 
    /** 修改时间 */
    private Date updateTime; 
    /** 创建人 */
    private String createUser; 
    /** 修改人 */
    private String updateUser; 
    /** 是否删除（0：删除；1：不删除） */
    private Integer yn;
    /**
     * 角色名称
     */
    private String roleName ;

    /**
     * 仓库名称
     */
    private String warehouseName ;
    /**
     * 原始密码
     */
    private String oraPassword ;
    /**
     * 确认密码
     */
    private String confirmPassword ;
    
    public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserName(){
        return userName;
    }
        
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getShowName(){
        return showName;
    }
        
    public void setShowName(String showName) {
        this.showName = showName;
    }
    
    public String getUserCode(){
        return userCode;
    }
        
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    public String getPassword(){
        return password;
    }
        
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail(){
        return email;
    }
        
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneno(){
        return phoneno;
    }
        
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
    
    public String getDuty(){
        return duty;
    }
        
    public void setDuty(String duty) {
        this.duty = duty;
    }
    
    public String getUserOthername(){
        return userOthername;
    }
        
    public void setUserOthername(String userOthername) {
        this.userOthername = userOthername;
    }
    
    public Integer getUserType(){
        return userType;
    }
        
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    
    public Integer getUserState(){
        return userState;
    }
        
    public void setUserState(Integer userState) {
        this.userState = userState;
    }
    
    public String getUserAddress(){
        return userAddress;
    }
        
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    
    public String getNote(){
        return note;
    }
        
    public void setNote(String note) {
        this.note = note;
    }
    
    public Date getCreateTime(){
        return createTime;
    }
        
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime(){
        return updateTime;
    }
        
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getCreateUser(){
        return createUser;
    }
        
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public String getUpdateUser(){
        return updateUser;
    }
        
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
    public Integer getYn(){
        return yn;
    }
        
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getOraPassword() {
        return oraPassword;
    }

    public void setOraPassword(String oraPassword) {
        this.oraPassword = oraPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
