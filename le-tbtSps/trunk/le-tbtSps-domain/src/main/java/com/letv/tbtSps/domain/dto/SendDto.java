package com.letv.tbtSps.domain.dto;

/**
 *下发接收数据DTO
 *  Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-7
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
public class SendDto {

    private String spsCode ;
    private String fileTitle ;
    private String country ;
    private String levels ;
    private String expertsEndDate ;
    private String exports ;


    public String getSpsCode() {
        return spsCode;
    }

    public void setSpsCode(String spsCode) {
        this.spsCode = spsCode;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getExpertsEndDate() {
        return expertsEndDate;
    }

    public void setExpertsEndDate(String expertsEndDate) {
        this.expertsEndDate = expertsEndDate;
    }

    public String getExports() {
        return exports;
    }

    public void setExports(String exports) {
        this.exports = exports;
    }
}
