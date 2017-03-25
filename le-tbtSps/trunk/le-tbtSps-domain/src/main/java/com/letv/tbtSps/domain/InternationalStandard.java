package com.letv.tbtSps.domain;

import java.util.Date;

/**
 * InternationalStandard：国际标准实体类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public class InternationalStandard implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 国际标准 */
    private String internationalStandardCode; 
    /** 国际标准内容 */
    private String internationalStandardContent; 
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
    
    public String getInternationalStandardContent(){
        return internationalStandardContent;
    }
        
    public void setInternationalStandardContent(String internationalStandardContent) {
        this.internationalStandardContent = internationalStandardContent;
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
