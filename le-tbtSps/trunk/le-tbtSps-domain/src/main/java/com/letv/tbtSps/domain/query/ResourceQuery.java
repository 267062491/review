package com.letv.tbtSps.domain.query;

import com.letv.common.utils.page.Query;

import java.util.Date;
import java.util.List;

/**
 * ResourceQuery：资源表查询类
 * 
 * @author yuguodong
 * @version 2016-10-25 15:20:03
 * 
 */
public class ResourceQuery extends Query implements java.io.Serializable  {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
	private Long id; 
    /** 资源名称 */
	private String resourceName; 
    /** 资源编码 */
	private String resourceCode; 
    /** URL */
	private String url; 
    /** 父节点编码 */
	private String parentCode; 
    /** 层级 */
	private Integer level; 
    /** 顺序 */
	private Integer orders; 
    /** 图标 */
	private String icon; 
    /** 平台区分 */
	private String platfrom; 
    /** 按钮区分 */
	private Integer buttonflag; 
    /** 默认是否展开 */
	private Boolean open;
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

    private Integer openFlag ;

    private String userCode ;

    /**
     * 是否有子菜单
     */
    private Integer parentFlag ;

    /**
     * 资源编码集合
     */
    private List<String> list_resourceCode ;

    /**
     * 资源类型，和用户类型一致
     */
    private Integer resourceType ;
	
    public Long getId(){
		return id;
	}
	    
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getResourceName(){
		return resourceName;
	}
	    
    public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
    public String getResourceCode(){
		return resourceCode;
	}
	    
    public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
    public String getUrl(){
		return url;
	}
	    
    public void setUrl(String url) {
		this.url = url;
	}
	
    public String getParentCode(){
		return parentCode;
	}
	    
    public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
    public Integer getLevel(){
		return level;
	}
	    
    public void setLevel(Integer level) {
		this.level = level;
	}
	
    public Integer getOrders(){
		return orders;
	}
	    
    public void setOrders(Integer orders) {
		this.orders = orders;
	}
	
    public String getIcon(){
		return icon;
	}
	    
    public void setIcon(String icon) {
		this.icon = icon;
	}
	
    public String getPlatfrom(){
		return platfrom;
	}
	    
    public void setPlatfrom(String platfrom) {
		this.platfrom = platfrom;
	}
	
    public Integer getButtonflag(){
		return buttonflag;
	}
	    
    public void setButtonflag(Integer buttonflag) {
		this.buttonflag = buttonflag;
	}

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
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

    public List<String> getList_resourceCode() {
        return list_resourceCode;
    }

    public void setList_resourceCode(List<String> list_resourceCode) {
        this.list_resourceCode = list_resourceCode;
    }

    public Integer getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(Integer openFlag) {
        this.openFlag = openFlag;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getParentFlag() {
        return parentFlag;
    }

    public void setParentFlag(Integer parentFlag) {
        this.parentFlag = parentFlag;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }
}
