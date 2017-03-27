package com.letv.tbtSps.domain.query;

import com.letv.common.utils.page.Query;

import java.util.Date;
import java.util.List;

/**
 * RoleResourceQuery：角色-资源查询类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public class RoleResourceQuery extends Query implements java.io.Serializable  {

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
    /** 开始时间 */
    private Date startTime; 
    /** 结束时间 */
    private Date endTime;

    /**
     * 用户编码
     */
    private String userCode ;

    private Integer level ;

    /**
     * 是否按钮
     */
    private Integer buttonFlag ;

    /**
     *
     */
    private String platForm ;

    /**
     * 资源类型，同用户类型
     */
    private Integer resourceType ;

    List<String> codes ;
	
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getButtonFlag() {
        return buttonFlag;
    }

    public void setButtonFlag(Integer buttonFlag) {
        this.buttonFlag = buttonFlag;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }
}
