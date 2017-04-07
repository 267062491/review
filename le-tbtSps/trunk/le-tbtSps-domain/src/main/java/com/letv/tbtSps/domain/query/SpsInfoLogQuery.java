package com.letv.tbtSps.domain.query;

import java.util.Date;

import com.letv.common.utils.page.Query;

/**
 * SpsInfoLogQuery：sps信息操作日志表查询类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class SpsInfoLogQuery extends Query implements java.io.Serializable  {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
	private Long id; 
    /** 通报编码 */
	private String spsCode; 
    /** 当前状态 */
	private String state; 
    /** 操作前状态 */
	private String oraState; 
    /** 内容 */
	private String content; 
    /** 和附件关联字段 */
	private String logAttrRelation; 
    /** 操作顺序，用来记录操作的先后顺序 */
	private Integer opertOrder;
    /** 是否可以编辑(激活之后，原来评论的内容则不可以编辑，1可以编辑；0不可以) */
    private String canEdit;
    /** 评议专家 */
	private String exports;
    /** 是否为终评 1 是 0否 */
	private String overReview;
    /** 预留字段4 */
	private String back4; 
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
	
    public String getSpsCode(){
		return spsCode;
	}
	    
    public void setSpsCode(String spsCode) {
		this.spsCode = spsCode;
	}
	
    public String getState(){
		return state;
	}
	    
    public void setState(String state) {
		this.state = state;
	}
	
    public String getOraState(){
		return oraState;
	}
	    
    public void setOraState(String oraState) {
		this.oraState = oraState;
	}
	
    public String getContent(){
		return content;
	}
	    
    public void setContent(String content) {
		this.content = content;
	}
	
    public String getLogAttrRelation(){
		return logAttrRelation;
	}
	    
    public void setLogAttrRelation(String logAttrRelation) {
		this.logAttrRelation = logAttrRelation;
	}
	
    public Integer getOpertOrder(){
		return opertOrder;
	}
	    
    public void setOpertOrder(Integer opertOrder) {
		this.opertOrder = opertOrder;
	}

    public String getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(String canEdit) {
        this.canEdit = canEdit;
    }

    public String getExports() {
        return exports;
    }

    public void setExports(String exports) {
        this.exports = exports;
    }

    public String getOverReview() {
        return overReview;
    }

    public void setOverReview(String overReview) {
        this.overReview = overReview;
    }

    public String getBack4(){
		return back4;
	}
	    
    public void setBack4(String back4) {
		this.back4 = back4;
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
