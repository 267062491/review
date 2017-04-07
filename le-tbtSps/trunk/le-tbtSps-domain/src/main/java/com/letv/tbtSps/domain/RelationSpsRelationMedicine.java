package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * RelationSpsRelationMedicine：sps信息表-相关农药关联表实体类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class RelationSpsRelationMedicine implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;


    public RelationSpsRelationMedicine(){}

    public RelationSpsRelationMedicine(String spsCode , String relationMedicineCode , String userName){
        this.spsCode = spsCode ;
        this.relationMedicineCode = relationMedicineCode ;
        this.createUser = userName ;
        this.createTime = new Date() ;
        this.updateUser = userName ;
        this.updateTime = new Date();
    }
    /** id */
    private Long id; 
    /** 相关农药 */
    private String relationMedicineCode; 
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
    
    public String getRelationMedicineCode(){
        return relationMedicineCode;
    }
        
    public void setRelationMedicineCode(String relationMedicineCode) {
        this.relationMedicineCode = relationMedicineCode;
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
