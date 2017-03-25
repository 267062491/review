package com.letv.tbtSps.sdk.api.request;

import com.letv.common.sdk.api.request.LetvRequest;
import java.util.Date;

/**
 * TbinfoLogRequest：tbt信息操作日志表请求参数
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class TbinfoLogRequest extends LetvRequest {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    /** 通报编码 */
    private String tbtCode; 
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
    /** 预留字段 */
    private String back1; 
    /** 预留字段2 */
    private String back2; 
    /** 预留字段3 */
    private String back3; 
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
