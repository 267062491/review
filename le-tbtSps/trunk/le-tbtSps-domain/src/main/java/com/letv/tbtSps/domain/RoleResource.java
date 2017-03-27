package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * RoleResource：角色-资源实体类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public class RoleResource implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 角色编码 */
    private String roleCode; 
    /** 资源编码 */
    private String resourceCode; 
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
     * pc端资源编码
     */
    private String pcResourceCode ;
    /**
     * rf端资源编码
     */
    private String rfResourceCode ;
    
    public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRoleCode(){
        return roleCode;
    }
        
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public String getResourceCode(){
        return resourceCode;
    }
        
    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
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

    public String getPcResourceCode() {
        return pcResourceCode;
    }

    public void setPcResourceCode(String pcResourceCode) {
        this.pcResourceCode = pcResourceCode;
    }

    public String getRfResourceCode() {
        return rfResourceCode;
    }

    public void setRfResourceCode(String rfResourceCode) {
        this.rfResourceCode = rfResourceCode;
    }
}
