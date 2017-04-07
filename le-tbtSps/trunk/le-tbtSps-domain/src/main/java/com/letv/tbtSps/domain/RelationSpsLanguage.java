package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * RelationSpsLanguage：sps信息表-原文语种关联表实体类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class RelationSpsLanguage implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;

    public RelationSpsLanguage(){}

    public RelationSpsLanguage(String spsCode , String languageCode , String userName){
        this.spsCode = spsCode ;
        this.languageCode = languageCode ;
        this.createUser = userName ;
        this.createTime = new Date() ;
        this.updateUser = userName ;
        this.updateTime = new Date();
    }
	
    /** id */
    private Long id; 
    /** 语言编码 */
    private String languageCode; 
    /** sps编码 */
    private String spsCode; 
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
    
    public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLanguageCode(){
        return languageCode;
    }
        
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
    
    public String getSpsCode(){
        return spsCode;
    }
        
    public void setSpsCode(String spsCode) {
        this.spsCode = spsCode;
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
