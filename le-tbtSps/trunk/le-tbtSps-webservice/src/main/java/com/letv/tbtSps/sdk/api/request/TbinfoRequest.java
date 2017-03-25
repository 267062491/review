package com.letv.tbtSps.sdk.api.request;

import com.letv.common.sdk.api.request.LetvRequest;
import java.util.Date;

/**
 * TbinfoRequest：tbt信息表请求参数
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class TbinfoRequest extends LetvRequest {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    /** 通报编号(作为唯一键) */
    private String tbtCode; 
    /** 发布日期 */
    private Date publishDate; 
    /** 通报成员,一般是国家 */
    private String tbtCountryCode; 
    /** 负责机构 */
    private String tbtOrg; 
    /** 覆盖产品 */
    private String coverProduct; 
    /** 文件标题 */
    private String fileTitle; 
    /** 内容描述 */
    private String contentDes; 
    /** 目标理由(和sps有区别) */
    private String targetReason; 
    /** 原文链接 */
    private String oraLink; 
    /** 文件编号 */
    private String fileCode; 
    /** 通报依据 */
    private String notificationBasis; 
    /** 拟批注日期,是否有用待定 */
    private String expectApprovalDate; 
    /** 拟生效日期,是否有用待定 */
    private String expectEffectiveDate; 
    /** 意见反馈截止日期,是否有用待定 */
    private String feedbackEndDate; 
    /** 文件获得机构,是否有用待定 */
    private String fileGetOrg; 
    /** 父通报号，如果没有默认是1 */
    private String parSpsCode; 
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
    private String back5; 
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
    
    public String getTbtCode(){
        return tbtCode;
    }
        
    public void setTbtCode(String tbtCode) {
        this.tbtCode = tbtCode;
    }
    
    public Date getPublishDate(){
        return publishDate;
    }
        
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    
    public String getTbtCountryCode(){
        return tbtCountryCode;
    }
        
    public void setTbtCountryCode(String tbtCountryCode) {
        this.tbtCountryCode = tbtCountryCode;
    }
    
    public String getTbtOrg(){
        return tbtOrg;
    }
        
    public void setTbtOrg(String tbtOrg) {
        this.tbtOrg = tbtOrg;
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
    
    public String getTargetReason(){
        return targetReason;
    }
        
    public void setTargetReason(String targetReason) {
        this.targetReason = targetReason;
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
    
    public String getNotificationBasis(){
        return notificationBasis;
    }
        
    public void setNotificationBasis(String notificationBasis) {
        this.notificationBasis = notificationBasis;
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
    
    public String getBack5(){
        return back5;
    }
        
    public void setBack5(String back5) {
        this.back5 = back5;
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
