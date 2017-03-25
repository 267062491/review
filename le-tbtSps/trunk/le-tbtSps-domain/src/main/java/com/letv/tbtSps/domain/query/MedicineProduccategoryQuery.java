package com.letv.tbtSps.domain.query;

import java.util.Date;

import com.letv.common.utils.page.Query;

/**
 * MedicineProduccategoryQuery：农产品分类查询类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class MedicineProduccategoryQuery extends Query implements java.io.Serializable  {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
	private Long id; 
    /** 分类名称 */
	private String caName; 
    /** 分类编码 */
	private String caCode; 
    /** 父编码 */
	private String pCode; 
    /** 顺序 */
	private Integer caOrder; 
    /** 分类编码数字 */
	private Long caCodeNum; 
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
	
    public String getCaName(){
		return caName;
	}
	    
    public void setCaName(String caName) {
		this.caName = caName;
	}
	
    public String getCaCode(){
		return caCode;
	}
	    
    public void setCaCode(String caCode) {
		this.caCode = caCode;
	}
	
    public String getPCode(){
		return pCode;
	}
	    
    public void setPCode(String pCode) {
		this.pCode = pCode;
	}
	
    public Integer getCaOrder(){
		return caOrder;
	}
	    
    public void setCaOrder(Integer caOrder) {
		this.caOrder = caOrder;
	}
	
    public Long getCaCodeNum(){
		return caCodeNum;
	}
	    
    public void setCaCodeNum(Long caCodeNum) {
		this.caCodeNum = caCodeNum;
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
