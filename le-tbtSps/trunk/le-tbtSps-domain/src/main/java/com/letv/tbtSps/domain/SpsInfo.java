package com.letv.tbtSps.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.letv.common.utils.DateHelper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
    /** 发布日期 spring mvc 接收日期参数 没弄好， 先用别的字段string类型的接收吧 */
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
    /** 重要等级  高、中、低 */
    private String levels;
    /** 通报下发日期 */
    private Date sendDate;
    /** 专家评议截止日期 */
    private Date expertsEndDate;
    /** 上次状态 */
    private String oraState;
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

    /*****************************/
    private String publishDateIn;

    private String standardYnShow ;

    private List<String> list_experts ;

    private List<SpsInfoLog> list_spsInfoLog ;

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

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getExpertsEndDate() {
        return expertsEndDate;
    }

    public void setExpertsEndDate(Date expertsEndDate) {
        this.expertsEndDate = expertsEndDate;
    }

    public String getOraState() {
        return oraState;
    }

    public void setOraState(String oraState) {
        this.oraState = oraState;
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

    public String getPublishDateIn() {
        return publishDateIn;
    }

    public void setPublishDateIn(String publishDateIn) {
        this.publishDateIn = publishDateIn;
    }

    public String getStandardYnShow() {
        return standardYnShow;
    }

    public void setStandardYnShow(String standardYnShow) {
        this.standardYnShow = standardYnShow;
    }

    public List<String> getList_experts() {
        return list_experts;
    }

    public void setList_experts(List<String> list_experts) {
        this.list_experts = list_experts;
    }

    public List<SpsInfoLog> getList_spsInfoLog() {
        return list_spsInfoLog;
    }

    public void setList_spsInfoLog(List<SpsInfoLog> list_spsInfoLog) {
        this.list_spsInfoLog = list_spsInfoLog;
    }

    public void setSpsInfoCommonValues(SpsInfo spsInfo , String spsCode , String userName,String state,String oraState){
        spsInfo.setSpsCode(spsCode);
        spsInfo.setCreateTime(new Date());
        spsInfo.setCreateUser(userName);
        spsInfo.setUpdateTime(new Date());
        spsInfo.setUpdateUser(userName);
        spsInfo.setState(state);
        spsInfo.setOraState(oraState);
        if(!StringUtils.isEmpty(spsInfo.getPublishDateIn())){
            spsInfo.setPublishDate(DateHelper.parseDate(spsInfo.getPublishDateIn()));
        }
        spsInfo.setVersions(UUID.randomUUID().toString());
    }


}
