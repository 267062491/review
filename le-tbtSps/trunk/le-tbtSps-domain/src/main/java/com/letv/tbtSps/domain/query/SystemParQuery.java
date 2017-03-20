package com.letv.tbtSps.domain.query;

import java.util.Date;

import com.letv.common.utils.page.Query;

/**
 * SystemParQuery：系统参数表查询类
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
public class SystemParQuery extends Query implements java.io.Serializable  {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
	private Long id; 
    /** 仓库编码 */
	private String warehouseNo; 
    /** 参数编码 */
	private String parCode; 
    /** 所属模块 */
	private String partModule; 
    /** 值 */
	private String parValue; 
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
	
    public Long getId(){
		return id;
	}
	    
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getWarehouseNo(){
		return warehouseNo;
	}
	    
    public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	
    public String getParCode(){
		return parCode;
	}
	    
    public void setParCode(String parCode) {
		this.parCode = parCode;
	}
	
    public String getPartModule(){
		return partModule;
	}
	    
    public void setPartModule(String partModule) {
		this.partModule = partModule;
	}
	
    public String getParValue(){
		return parValue;
	}
	    
    public void setParValue(String parValue) {
		this.parValue = parValue;
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
}
