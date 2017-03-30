package com.letv.tbtSps.utils;

import com.letv.tbtSps.domain.*;
import com.letv.tbtSps.domain.query.*;
import com.letv.tbtSps.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-3-30
 * Time: 下午8:49
 * To change this template use File | Settings | File Templates.
 */
public class ParameterLoad implements InitializingBean {
    private static final Log LOG = LogFactory.getLog(ParameterLoad.class);
    @Autowired
    private CountryService countryService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private NotificationTypeService notificationTypeService;
    @Autowired
    private TargereasonService targereasonService;
    @Autowired
    private InternationalStandardService internationalStandardService;
    @Autowired
    private RelationMedicineService relationMedicineService;
    @Autowired
    private RelationMedicineProductService relationMedicineProductService;

    /**
     * 通报成员
     */
    private List<Country> list_country = new ArrayList<Country>();
    /**
     * 原文语种
     */
    private List<Language> list_language = new ArrayList<Language>();
    /**
     * 通报类型
     */
    private List<NotificationType> list_notificationType = new ArrayList<NotificationType>();
    /**
     * 目标理由
     */
    private List<Targereason> list_targereason = new ArrayList<Targereason>();
    /**
     * 国际标准
     */
    private List<InternationalStandard> list_internationalStandard = new ArrayList<InternationalStandard>();
    /**
     * 农药相关
     */
    private List<RelationMedicine> list_relationMedicine = new ArrayList<RelationMedicine>();
    /**
     * 农产品相关
     */
    private List<RelationMedicineProduct> list_relationMedicineProduct = new ArrayList<RelationMedicineProduct>();

    @Override
    public void afterPropertiesSet() throws Exception {

        try{
            list_country = countryService.queryCountryList(new CountryQuery());
            list_language = languageService.queryLanguageList(new LanguageQuery());
            list_notificationType = notificationTypeService.queryNotificationTypeList(new NotificationTypeQuery());
            list_targereason = targereasonService.queryTargereasonList(new TargereasonQuery());
            list_internationalStandard = internationalStandardService.queryInternationalStandardList(new InternationalStandardQuery());
            list_relationMedicine = relationMedicineService.queryRelationMedicineList(new RelationMedicineQuery());
            list_relationMedicineProduct = relationMedicineProductService.queryRelationMedicineProductList(new RelationMedicineProductQuery());
        }catch (Exception e){
            LOG.error("初始化后select列表项异常：",e);
        }
    }


    public List<RelationMedicineProduct> getList_relationMedicineProduct() {
        return list_relationMedicineProduct;
    }

    public void setList_relationMedicineProduct(List<RelationMedicineProduct> list_relationMedicineProduct) {
        this.list_relationMedicineProduct = list_relationMedicineProduct;
    }

    public List<Country> getList_country() {
        return list_country;
    }

    public void setList_country(List<Country> list_country) {
        this.list_country = list_country;
    }

    public List<Language> getList_language() {
        return list_language;
    }

    public void setList_language(List<Language> list_language) {
        this.list_language = list_language;
    }

    public List<NotificationType> getList_notificationType() {
        return list_notificationType;
    }

    public void setList_notificationType(List<NotificationType> list_notificationType) {
        this.list_notificationType = list_notificationType;
    }

    public List<Targereason> getList_targereason() {
        return list_targereason;
    }

    public void setList_targereason(List<Targereason> list_targereason) {
        this.list_targereason = list_targereason;
    }

    public List<InternationalStandard> getList_internationalStandard() {
        return list_internationalStandard;
    }

    public void setList_internationalStandard(List<InternationalStandard> list_internationalStandard) {
        this.list_internationalStandard = list_internationalStandard;
    }

    public List<RelationMedicine> getList_relationMedicine() {
        return list_relationMedicine;
    }

    public void setList_relationMedicine(List<RelationMedicine> list_relationMedicine) {
        this.list_relationMedicine = list_relationMedicine;
    }
}
