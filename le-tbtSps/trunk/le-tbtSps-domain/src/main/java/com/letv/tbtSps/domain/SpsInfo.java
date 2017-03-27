package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * SpsInfo：sps信息表实体类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class SpsInfo implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 通报编号(作为唯一键) */
    private String spsCode; 
    /** 通报成员,一般是国家 */
    private String countryCode; 
    /** 发布日期 */
    private Date publishDate; 
    /** 负责机构 */
    private String spsOrg; 
    /** 覆盖产品 */
    private String coverProduct; 
    /** 文件标题 */
    private String fileTitle; 
    /** 内容描述 */
    private String contentDes; 
    /** 是否符合标准,1符合，0不符合 */
    private Integer standardYn; 
    /** 原文链接 */
    private String oraLink; 
    /** 文件编号 */
    private String fileCode; 
    /** 受影响的地区或国际 */
    private String affectCountry; 
    /** 拟批注日期,是否有用待定 */
    private String expectApprovalDate; 
    /** 拟生效日期,是否有用待定 */
    private String expectEffectiveDate; 
    /** 拟公布日期,是否有用待定 */
    private String expectPublishDate; 
    /** 意见反馈截止日期,是否有用待定 */
    private String feedbackEndDate; 
    /** 文件获得机构,是否有用待定 */
    private String fileGetOrg; 
    /** 父通报号，如果没有默认是1 */
    private String parSpsCode; 
    /** 不符合标准原因 */
    private String errorReason; 
    /** 当前状态 */
    private String state; 
    /** 备用字段1 */
    private String back1; 
    /** 备用字段2 */
    private String back2; 
    /** 备用字段3 */
    private String back3; 
    /** 备用字段4 */
    private String back4; 
    /** 备用字段5 */
    private String versions;
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
    
    public String getCountryCode(){
        return countryCode;
    }
        
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public Date getPublishDate(){
        return publishDate;
    }
        
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    
    public String getSpsOrg(){
        return spsOrg;
    }
        
    public void setSpsOrg(String spsOrg) {
        this.spsOrg = spsOrg;
    }
    
    public String getCoverProduct(){
        return coverProduct;
    }
        
    public void setCoverProduct(String coverProduct) {
        this.coverProduct = coverProduct;
    }
    
    public String getFileTitle(){
        return fileTitle;
    }
        
    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }
    
    public String getContentDes(){
        return contentDes;
    }
        
    public void setContentDes(String contentDes) {
        this.contentDes = contentDes;
    }
    
    public Integer getStandardYn(){
        return standardYn;
    }
        
    public void setStandardYn(Integer standardYn) {
        this.standardYn = standardYn;
    }
    
    public String getOraLink(){
        return oraLink;
    }
        
    public void setOraLink(String oraLink) {
        this.oraLink = oraLink;
    }
    
    public String getFileCode(){
        return fileCode;
    }
        
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }
    
    public String getAffectCountry(){
        return affectCountry;
    }
        
    public void setAffectCountry(String affectCountry) {
        this.affectCountry = affectCountry;
    }
    
    public String getExpectApprovalDate(){
        return expectApprovalDate;
    }
        
    public void setExpectApprovalDate(String expectApprovalDate) {
        this.expectApprovalDate = expectApprovalDate;
    }
    
    public String getExpectEffectiveDate(){
        return expectEffectiveDate;
    }
        
    public void setExpectEffectiveDate(String expectEffectiveDate) {
        this.expectEffectiveDate = expectEffectiveDate;
    }
    
    public String getExpectPublishDate(){
        return expectPublishDate;
    }
        
    public void setExpectPublishDate(String expectPublishDate) {
        this.expectPublishDate = expectPublishDate;
    }
    
    public String getFeedbackEndDate(){
        return feedbackEndDate;
    }
        
    public void setFeedbackEndDate(String feedbackEndDate) {
        this.feedbackEndDate = feedbackEndDate;
    }
    
    public String getFileGetOrg(){
        return fileGetOrg;
    }
        
    public void setFileGetOrg(String fileGetOrg) {
        this.fileGetOrg = fileGetOrg;
    }
    
    public String getParSpsCode(){
        return parSpsCode;
    }
        
    public void setParSpsCode(String parSpsCode) {
        this.parSpsCode = parSpsCode;
    }
    
    public String getErrorReason(){
        return errorReason;
    }
        
    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }
    
    public String getState(){
        return state;
    }
        
    public void setState(String state) {
        this.state = state;
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
    
    public String getBack3(){
        return back3;
    }
        
    public void setBack3(String back3) {
        this.back3 = back3;
    }
    
    public String getBack4(){
        return back4;
    }
        
    public void setBack4(String back4) {
        this.back4 = back4;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
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
