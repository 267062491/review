package com.letv.tbtSps.domain.query;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-27
 * Time: 下午9:54
 * To change this template use File | Settings | File Templates.
 */
public class TreeDomain implements Serializable {

    /** 序列化标识 */
    private static final long serialVersionUID = 1L;

    /**
     * 节点id
     */
    private String id ;

    /**
     * 节点名称
     */
    private String name ;

    /**
     * 节点父ID
     */
    private String pId ;

    /**
     * 节点是否打开
     */
    private Boolean open ;

    /**
     * 节点路径
     */
    private String url ;

    /**
     * 节点图标
     */
    private String iconSkin ;

    private String target ;

    /**
     * 平台区分
     */
    private String platfrom ;

    private Integer orders ;

    private String note ;

    /**
     * 是否父节点， 1 是父节点， 0 叶子节点
     */
    private Integer parentFlag ;

    /**
     * 是否按钮
     */
    private String buttonFlag ;

    /**
     * 是否选中
     */
    private Boolean checked ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(String platfrom) {
        this.platfrom = platfrom;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getButtonFlag() {
        return buttonFlag;
    }

    public void setButtonFlag(String buttonFlag) {
        this.buttonFlag = buttonFlag;
    }

    public Integer getParentFlag() {
        return parentFlag;
    }

    public void setParentFlag(Integer parentFlag) {
        this.parentFlag = parentFlag;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
