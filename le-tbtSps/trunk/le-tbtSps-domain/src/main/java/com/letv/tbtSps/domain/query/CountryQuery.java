package com.letv.tbtSps.domain.query;

import java.util.Date;

import com.letv.common.utils.page.Query;

/**
 * CountryQuery：通报成员查询类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class CountryQuery extends Query implements java.io.Serializable  {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
	private Long id; 
    /** 通报成员 */
	private String countryCode; 
    /** 通报成员内容 */
	private String countryContent; 
    /** 简拼 */
	private String simpleName; 
    /** 全品 */
	private String fullName; 
    /** 英文 */
	private String enName; 
    /** 创建时间 */
	private Date createTime; 
    /** 修改时间 */
	private Date updateTime; 
    /** 创建人 */
	private String createUser; 
    /** 修改人 */
	private String updateUser; 
    /** yn */
	private Integer yn; 
    /** 开始时间 */
    private Date startTime; 
    /** 结束时间 */
    private Date endTime; 
	
    public Long getId(){
		return id;
	}
	    
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getCountryCode(){
		return countryCode;
	}
	    
    public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
    public String getCountryContent(){
		return countryContent;
	}
	    
    public void setCountryContent(String countryContent) {
		this.countryContent = countryContent;
	}
	
    public String getSimpleName(){
		return simpleName;
	}
	    
    public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	
    public String getFullName(){
		return fullName;
	}
	    
    public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
    public String getEnName(){
		return enName;
	}
	    
    public void setEnName(String enName) {
		this.enName = enName;
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
}
