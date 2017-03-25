package com.letv.tbtSps.sdk.api.request;

import com.letv.common.sdk.api.request.LetvRequest;
import java.util.Date;

/**
 * RelationMedicineProductRequest：相关农产品请求参数
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class RelationMedicineProductRequest extends LetvRequest {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    /** 相关农产品 */
    private String relationMedicineProductCode; 
    /** 相关农产品英文 */
    private String relationMedicineProductEn; 
    /** 相关农产品中文别名 */
    private String relationMedicineProductZhAlias; 
    /** 预留字段 */
    private String back1; 
    /** 预留字段2 */
    private String back2; 
    /** 相关农产品中文 */
    private String relationMedicineProductZh; 
    /** 所属农产品分类 */
    private String caCode; 
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
    
    public String getRelationMedicineProductCode(){
        return relationMedicineProductCode;
    }
        
    public void setRelationMedicineProductCode(String relationMedicineProductCode) {
        this.relationMedicineProductCode = relationMedicineProductCode;
    }
    
    public String getRelationMedicineProductEn(){
        return relationMedicineProductEn;
    }
        
    public void setRelationMedicineProductEn(String relationMedicineProductEn) {
        this.relationMedicineProductEn = relationMedicineProductEn;
    }
    
    public String getRelationMedicineProductZhAlias(){
        return relationMedicineProductZhAlias;
    }
        
    public void setRelationMedicineProductZhAlias(String relationMedicineProductZhAlias) {
        this.relationMedicineProductZhAlias = relationMedicineProductZhAlias;
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
    
    public String getRelationMedicineProductZh(){
        return relationMedicineProductZh;
    }
        
    public void setRelationMedicineProductZh(String relationMedicineProductZh) {
        this.relationMedicineProductZh = relationMedicineProductZh;
    }
    
    public String getCaCode(){
        return caCode;
    }
        
    public void setCaCode(String caCode) {
        this.caCode = caCode;
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
