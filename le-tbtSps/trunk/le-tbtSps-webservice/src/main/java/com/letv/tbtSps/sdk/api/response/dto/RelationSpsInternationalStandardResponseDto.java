package com.letv.tbtSps.sdk.api.response.dto;

import com.letv.common.sdk.api.dto.LetvDto;

import java.util.Date;

/**
 * RelationSpsInternationalStandardResponse：sps信息表-国际标准关联表返回对象<br/>
 * 提供rest接口时方法的返回对象
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationSpsInternationalStandardResponseDto extends LetvDto {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    /** 国际标准 */
    private String internationalStandardCode; 
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
    
    public String getInternationalStandardCode(){
        return internationalStandardCode;
    }
        
    public void setInternationalStandardCode(String internationalStandardCode) {
        this.internationalStandardCode = internationalStandardCode;
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
