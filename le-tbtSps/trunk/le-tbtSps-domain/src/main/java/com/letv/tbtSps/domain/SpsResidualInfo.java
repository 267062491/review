package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * SpsResidualInfo：sps残留量信息实体类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class SpsResidualInfo implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;


    public SpsResidualInfo(){}

    public SpsResidualInfo(String spsCode , String medicineCode , String medicineProductCode
            ,String updateType , Double residuaAmount , String unit ,Double safeDay , Date endDate , String userName){
        this.spsCode = spsCode ;
        this.medicineCode = medicineCode ;
        this.medicineProductCode = medicineProductCode ;
        this.updateType = updateType ;
        this.residuaAmount = residuaAmount ;
        this.unit = unit ;
        this.safeDay = safeDay ;
        this.endDate = endDate ;
        this.createUser = userName ;
        this.createTime = new Date();
        this.updateUser = userName ;
        this.updateTime = new Date() ;
    }

    /** id */
    private Long id; 
    /** 关联sps_info表 */
    private String spsCode; 
    /** 农药 */
    private String medicineCode; 
    /** 农产品 */
    private String medicineProductCode; 
    /** 修改类型 1:制定残留限量 ; 2:修改残留限量 ;3:删除残留量*/
    private String updateType; 
    /** 残留量 */
    private Double residuaAmount; 
    /** 单位 */
    private String unit; 
    /** 安全期 */
    private Double safeDay; 
    /** 撤销截止日期 */
    private Date endDate; 
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

    private String endDateIn;

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
    
    public String getMedicineCode(){
        return medicineCode;
    }
        
    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }
    
    public String getMedicineProductCode(){
        return medicineProductCode;
    }
        
    public void setMedicineProductCode(String medicineProductCode) {
        this.medicineProductCode = medicineProductCode;
    }
    
    public String getUpdateType(){
        return updateType;
    }
        
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
    
    public Double getResiduaAmount(){
        return residuaAmount;
    }
        
    public void setResiduaAmount(Double residuaAmount) {
        this.residuaAmount = residuaAmount;
    }
    
    public String getUnit(){
        return unit;
    }
        
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public Double getSafeDay(){
        return safeDay;
    }
        
    public void setSafeDay(Double safeDay) {
        this.safeDay = safeDay;
    }
    
    public Date getEndDate(){
        return endDate;
    }
        
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getEndDateIn() {
        return endDateIn;
    }

    public void setEndDateIn(String endDateIn) {
        this.endDateIn = endDateIn;
    }
}
