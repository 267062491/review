package com.letv.tbtSps.domain.dto;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Description 索引对应的bean
 * Created by ygd on 2017/11/13.
 */
public class SolrDto implements Serializable{

    public SolrDto() {
        super();
    }

    public SolrDto(String id,String spsCode, List<String> notificationTypeCode, List<String> notificationTypeContent, String countryCode
            , String countryContent, String relationMedicineProductCode, String relationMedicineProductEn
            , String relationMedicineProductZhAlias, String relationMedicineProductZh, String relationMedicineCode
            , String relationMedicineZh, String relationMedicineEm, String relationMedicineZhAlias, String publishDate
            , String publishDateYear, String fileTitle, String contentDes,Integer zhiDingCLL , Integer xiuGaiCLL ,Integer shanChuCLL) {
        this.id = id ;
        this.spsCode = spsCode;
        this.notificationTypeCode = notificationTypeCode;
        this.notificationTypeContent = notificationTypeContent;
        this.countryCode = countryCode;
        this.countryContent = countryContent;
        this.relationMedicineProductCode = relationMedicineProductCode;
        this.relationMedicineProductEn = relationMedicineProductEn;
        this.relationMedicineProductZhAlias = relationMedicineProductZhAlias;
        this.relationMedicineProductZh = relationMedicineProductZh;
        this.relationMedicineCode = relationMedicineCode;
        this.relationMedicineZh = relationMedicineZh;
        this.relationMedicineEm = relationMedicineEm;
        this.relationMedicineZhAlias = relationMedicineZhAlias;
        this.publishDate = publishDate;
        this.publishDateYear = publishDateYear;
        this.fileTitle = fileTitle;
        this.contentDes = contentDes;
        this.zhiDingCLL = zhiDingCLL;
        this.xiuGaiCLL = xiuGaiCLL;
        this.shanChuCLL = shanChuCLL;
    }

    public SolrDto(String spsCode, List<String> notificationTypeCode, List<String> notificationTypeContent) {
        this.spsCode = spsCode;
        this.notificationTypeCode = notificationTypeCode;
        this.notificationTypeContent = notificationTypeContent;
    }

    /**
     * 唯一标识 ，内容也是spsCode ,只是solr里的类型是string
     */
    private String id ;
    /**
     * 通报编号
     */
    private String spsCode;
    /** 通报类型编码 */
    private List<String> notificationTypeCode;
    /** 通报类型内容 */
    private List<String> notificationTypeContent;

    /** 通报成员 */
    private String countryCode;
    /** 通报成员内容 */
    private String countryContent;

    /** 相关农产品 */
    private String relationMedicineProductCode;
    /** 相关农产品英文 */
    private String relationMedicineProductEn;
    /** 相关农产品中文别名 */
    private String relationMedicineProductZhAlias;
    /** 相关农产品中文 */
    private String relationMedicineProductZh;


    /** 相关农药编码 */
    private String relationMedicineCode;
    /** 相关农药中文 */
    private String relationMedicineZh;
    /** 相关农药英文 */
    private String relationMedicineEm;
    /** 相关农药中文别名 */
    private String relationMedicineZhAlias;

    /**
     * 发布时间
     */
    private String publishDate ;
    /**
     * 发布年份，发布时间截取年存储
     */
    private String publishDateYear ;


    /** 文件标题 */
    private String fileTitle;
    /** 内容描述 */
    private String contentDes;

    /**
     * 制定残留量数量
     */
    private Integer zhiDingCLL ;

    /**
     * 修改残留量数量
     */
    private Integer xiuGaiCLL ;

    /**
     * 删除残留量数量
     */
    private Integer shanChuCLL;


    public String getSpsCode() {
        return spsCode;
    }
    @Field
    public void setSpsCode(String spsCode) {
        this.spsCode = spsCode;
    }


    public String getId() {
        return id;
    }
    @Field
    public void setId(String id) {
        this.id = id;
    }

    public List<String> getNotificationTypeCode() {
        return notificationTypeCode;
    }
    @Field
    public void setNotificationTypeCode(List<String> notificationTypeCode) {
        this.notificationTypeCode = notificationTypeCode;
    }

    public Integer getZhiDingCLL() {
        return zhiDingCLL;
    }
    @Field
    public void setZhiDingCLL(Integer zhiDingCLL) {
        this.zhiDingCLL = zhiDingCLL;
    }

    public Integer getXiuGaiCLL() {
        return xiuGaiCLL;
    }
    @Field
    public void setXiuGaiCLL(Integer xiuGaiCLL) {
        this.xiuGaiCLL = xiuGaiCLL;
    }

    public Integer getShanChuCLL() {
        return shanChuCLL;
    }
    @Field
    public void setShanChuCLL(Integer shanChuCLL) {
        this.shanChuCLL = shanChuCLL;
    }

    public List<String> getNotificationTypeContent() {
        return notificationTypeContent;
    }
    @Field
    public void setNotificationTypeContent(List<String> notificationTypeContent) {
        this.notificationTypeContent = notificationTypeContent;
    }

    public String getCountryCode() {
        return countryCode;
    }
    @Field
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryContent() {
        return countryContent;
    }
    @Field
    public void setCountryContent(String countryContent) {
        this.countryContent = countryContent;
    }

    public String getRelationMedicineProductCode() {
        return relationMedicineProductCode;
    }
    @Field
    public void setRelationMedicineProductCode(String relationMedicineProductCode) {
        this.relationMedicineProductCode = relationMedicineProductCode;
    }

    public String getRelationMedicineProductEn() {
        return relationMedicineProductEn;
    }
    @Field
    public void setRelationMedicineProductEn(String relationMedicineProductEn) {
        this.relationMedicineProductEn = relationMedicineProductEn;
    }

    public String getRelationMedicineProductZhAlias() {
        return relationMedicineProductZhAlias;
    }
    @Field
    public void setRelationMedicineProductZhAlias(String relationMedicineProductZhAlias) {
        this.relationMedicineProductZhAlias = relationMedicineProductZhAlias;
    }

    public String getRelationMedicineProductZh() {
        return relationMedicineProductZh;
    }
    @Field
    public void setRelationMedicineProductZh(String relationMedicineProductZh) {
        this.relationMedicineProductZh = relationMedicineProductZh;
    }

    public String getRelationMedicineCode() {
        return relationMedicineCode;
    }
    @Field
    public void setRelationMedicineCode(String relationMedicineCode) {
        this.relationMedicineCode = relationMedicineCode;
    }

    public String getRelationMedicineZh() {
        return relationMedicineZh;
    }
    @Field
    public void setRelationMedicineZh(String relationMedicineZh) {
        this.relationMedicineZh = relationMedicineZh;
    }

    public String getRelationMedicineEm() {
        return relationMedicineEm;
    }
    @Field
    public void setRelationMedicineEm(String relationMedicineEm) {
        this.relationMedicineEm = relationMedicineEm;
    }

    public String getRelationMedicineZhAlias() {
        return relationMedicineZhAlias;
    }
    @Field
    public void setRelationMedicineZhAlias(String relationMedicineZhAlias) {
        this.relationMedicineZhAlias = relationMedicineZhAlias;
    }

    public String getPublishDate() {
        return publishDate;
    }
    @Field
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishDateYear() {
        return publishDateYear;
    }
    @Field
    public void setPublishDateYear(String publishDateYear) {
        this.publishDateYear = publishDateYear;
    }

    public String getFileTitle() {
        return fileTitle;
    }
    @Field
    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getContentDes() {
        return contentDes;
    }
    @Field
    public void setContentDes(String contentDes) {
        this.contentDes = contentDes;
    }

    @Override
    public String toString() {
        return "";
    }

}
