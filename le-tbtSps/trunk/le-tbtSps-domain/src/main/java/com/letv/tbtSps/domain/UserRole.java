package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * UserRole：用户-角色实体类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public class UserRole implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 用户编码 */
    private String userCode; 
    /** 角色编码 */
    private String roleCode; 
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

    private String[] roleCodes ;
    
    public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserCode(){
        return userCode;
    }
        
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    public String getRoleCode(){
        return roleCode;
    }
        
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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

    public String[] getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(String[] roleCodes) {
        this.roleCodes = roleCodes;
    }
}
