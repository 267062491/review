package com.letv.tbtSps.domain.query;

import com.letv.common.utils.page.Query;

import java.util.Date;
import java.util.List;

/**
 * RoleQuery：角色表查询类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public class RoleQuery extends Query implements java.io.Serializable  {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
	private Long id; 
    /** 角色名称 */
	private String roleName; 
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
    /** 开始时间 */
    private Date startTime; 
    /** 结束时间 */
    private Date endTime;

    /**
     * 用户编码list集合
     */
    private List<String> list_userCode ;

    /**
     * 启用、禁用标示 1启用 ， 0禁用
     */
    private Integer enable ;

    /**
     * 角色类型，和用户类型一致
     */
    private Integer roleType ;

    public Long getId(){
		return id;
	}
	    
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getRoleName(){
		return roleName;
	}
	    
    public void setRoleName(String roleName) {
		this.roleName = roleName;
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

    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getList_userCode() {
        return list_userCode;
    }

    public void setList_userCode(List<String> list_userCode) {
        this.list_userCode = list_userCode;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}
