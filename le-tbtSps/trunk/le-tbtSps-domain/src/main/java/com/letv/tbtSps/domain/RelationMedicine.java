package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * RelationMedicine：相关农药实体类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class RelationMedicine implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 相关农药编码 */
    private String relationMedicineCode; 
    /** 相关农药中文 */
    private String relationMedicineZh; 
    /** 相关农药英文 */
    private String relationMedicineEm; 
    /** 相关农药中文别名 */
    private String relationMedicineZhAlias; 
    /** 预留字段 */
    private String back1; 
    /** 预留字段2 */
    private String back2; 
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
    
    public String getRelationMedicineZh(){
        return relationMedicineZh;
    }
        
    public void setRelationMedicineZh(String relationMedicineZh) {
        this.relationMedicineZh = relationMedicineZh;
    }
    
    public String getRelationMedicineEm(){
        return relationMedicineEm;
    }
        
    public void setRelationMedicineEm(String relationMedicineEm) {
        this.relationMedicineEm = relationMedicineEm;
    }
    
    public String getRelationMedicineZhAlias(){
        return relationMedicineZhAlias;
    }
        
    public void setRelationMedicineZhAlias(String relationMedicineZhAlias) {
        this.relationMedicineZhAlias = relationMedicineZhAlias;
    }
    
    public String getBack1(){
        return back1;
    }
        
    public void setBack1(String back1) {
        this.back1 = back1;
    }
    
    public String getBack2(){
        return back2;
    }
        
    public void setBack2(String back2) {
        this.back2 = back2;
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
