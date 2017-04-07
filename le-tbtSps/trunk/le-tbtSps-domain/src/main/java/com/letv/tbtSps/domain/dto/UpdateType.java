package com.letv.tbtSps.domain.dto;

/**
 * 修改类型
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-3-31
 * Time: 下午9:24
 * To change this template use File | Settings | File Templates.
 */
public class UpdateType {
    private String updateTypeCode ;
    private String updateTypeContent ;

    public String getUpdateTypeCode() {
        return updateTypeCode;
    }

    public void setUpdateTypeCode(String updateTypeCode) {
        this.updateTypeCode = updateTypeCode;
    }

    public String getUpdateTypeContent() {
        return updateTypeContent;
    }

    public void setUpdateTypeContent(String updateTypeContent) {
        this.updateTypeContent = updateTypeContent;
    }
}
