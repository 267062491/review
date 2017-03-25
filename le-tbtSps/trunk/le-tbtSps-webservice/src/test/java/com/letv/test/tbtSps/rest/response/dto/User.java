package com.letv.test.tbtSps.rest.response.dto;

import com.letv.common.sdk.api.dto.LetvDto;

import java.util.Date;

/**
 * UserResponse：用户表返回对象<br/>
 * 提供rest接口时方法的返回对象
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class User extends LetvDto {

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
    /** 用户状态 */
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
}
