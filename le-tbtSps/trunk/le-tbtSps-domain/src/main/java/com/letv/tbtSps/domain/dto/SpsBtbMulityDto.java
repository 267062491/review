package com.letv.tbtSps.domain.dto;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-3
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public class SpsBtbMulityDto {

    /**
     * 原文语种
     */
    private String[] language ;
    /**
     * 通报类型
     */
    private String[] notificationType ;
    /**
     * 目标理由
     */
    private String[] targetReason ;
    /**
     * 国际标准
     */
    private String[] internationalStandard ;
    /**
     * 农药添加
     */
    private String relationMedicine ;
    /**
     * 农产品添加
     */
    private String relationMedicineProduct ;

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }

    public String[] getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String[] notificationType) {
        this.notificationType = notificationType;
    }

    public String[] getTargetReason() {
        return targetReason;
    }

    public void setTargetReason(String[] targetReason) {
        this.targetReason = targetReason;
    }

    public String[] getInternationalStandard() {
        return internationalStandard;
    }

    public void setInternationalStandard(String[] internationalStandard) {
        this.internationalStandard = internationalStandard;
    }

    public String getRelationMedicine() {
        return relationMedicine;
    }

    public void setRelationMedicine(String relationMedicine) {
        this.relationMedicine = relationMedicine;
    }

    public String getRelationMedicineProduct() {
        return relationMedicineProduct;
    }

    public void setRelationMedicineProduct(String relationMedicineProduct) {
        this.relationMedicineProduct = relationMedicineProduct;
    }
}
