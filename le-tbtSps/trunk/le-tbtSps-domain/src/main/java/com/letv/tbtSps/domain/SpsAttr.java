package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * SpsAttr：sps信息表对应的附件实体类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class SpsAttr implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;

    public SpsAttr(){

    }

    public SpsAttr(String spsCode ,String fileName ,String filePath ,String createUser ,String updateUser ){
        this.spsCode = spsCode ;
        this.fileName = fileName ;
        this.filePath = filePath ;
        this.createUser = createUser ;
        this.createTime = new Date() ;
        this.updateUser = updateUser ;
        this.updateTime = new Date() ;
    }


    /** id */
    private Long id; 
    /** 通报编号(关联sps信息表的通报编号) */
    private String spsCode; 
    /** 文件名 */
    private String fileName; 
    /** 文件路径 */
    private String filePath; 
    /** 文件别名 */
    private String fileAliasName; 
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
    
    public String getSpsCode(){
        return spsCode;
    }
        
    public void setSpsCode(String spsCode) {
        this.spsCode = spsCode;
    }
    
    public String getFileName(){
        return fileName;
    }
        
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFilePath(){
        return filePath;
    }
        
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getFileAliasName(){
        return fileAliasName;
    }
        
    public void setFileAliasName(String fileAliasName) {
        this.fileAliasName = fileAliasName;
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
