package com.letv.tbtSps.sdk.api.request;

import com.letv.common.sdk.api.request.LetvRequest;
import java.util.Date;

/**
 * TargereasonRequest：目标理由请求参数
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class TargereasonRequest extends LetvRequest {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    /** 目标理由编码 */
    private String targetReasonCode; 
    /** 目标理由文字 */
    private String targetReasonContent; 
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
    
    public String getTargetReasonCode(){
        return targetReasonCode;
    }
        
    public void setTargetReasonCode(String targetReasonCode) {
        this.targetReasonCode = targetReasonCode;
    }
    
    public String getTargetReasonContent(){
        return targetReasonContent;
    }
        
    public void setTargetReasonContent(String targetReasonContent) {
        this.targetReasonContent = targetReasonContent;
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
