package com.letv.test.tbtSps.rest.request;

import com.letv.common.sdk.api.request.LetvRequest;
import java.util.Date;

/**
 * NotificationCategoryRequest：通报内部分类请求参数
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class NotificationCategoryRequest extends LetvRequest {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    /** 通报分类编码 */
    private String notificationCategoryCode; 
    /** 通报分类文字 */
    private String notificationCategoryContent; 
    /** 父编码 */
    private String notificationPCode; 
    /** 顺序 */
    private Integer notificationCaOrder; 
    /** 分类编码数字 */
    private Long notificationCaCodeNum; 
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
    
    public String getNotificationCategoryCode(){
        return notificationCategoryCode;
    }
        
    public void setNotificationCategoryCode(String notificationCategoryCode) {
        this.notificationCategoryCode = notificationCategoryCode;
    }
    
    public String getNotificationCategoryContent(){
        return notificationCategoryContent;
    }
        
    public void setNotificationCategoryContent(String notificationCategoryContent) {
        this.notificationCategoryContent = notificationCategoryContent;
    }
    
    public String getNotificationPCode(){
        return notificationPCode;
    }
        
    public void setNotificationPCode(String notificationPCode) {
        this.notificationPCode = notificationPCode;
    }
    
    public Integer getNotificationCaOrder(){
        return notificationCaOrder;
    }
        
    public void setNotificationCaOrder(Integer notificationCaOrder) {
        this.notificationCaOrder = notificationCaOrder;
    }
    
    public Long getNotificationCaCodeNum(){
        return notificationCaCodeNum;
    }
        
    public void setNotificationCaCodeNum(Long notificationCaCodeNum) {
        this.notificationCaCodeNum = notificationCaCodeNum;
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
