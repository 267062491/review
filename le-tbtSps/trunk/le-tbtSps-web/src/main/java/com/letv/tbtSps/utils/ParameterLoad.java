package com.letv.tbtSps.utils;

import com.letv.tbtSps.domain.*;
import com.letv.tbtSps.domain.dto.SpsBtbState;
import com.letv.tbtSps.domain.dto.UpdateType;
import com.letv.tbtSps.domain.query.*;
import com.letv.tbtSps.service.*;
import com.letv.tbtSps.utils.enums.LevelsEnum;
import com.letv.tbtSps.utils.enums.RoleEnum;
import com.letv.tbtSps.utils.enums.Sps_Tbt_InfoStatus;
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
    /**
     * 修改类型
     */
    private List<UpdateType> list_updateType = new ArrayList<UpdateType>();
    /**
     * 状态
     */
    private List<SpsBtbState> list_spsBtbState = new ArrayList<SpsBtbState>();
    /**
     * 部分状态
     */
    private List<SpsBtbState> list_spsBtbStatePart = new ArrayList<SpsBtbState>();
    /**
     * 重要等级
     */
    private List<SpsBtbState> list_leves = new ArrayList<SpsBtbState>();
    /**
     * 可以评议角色
     */
    private List<String> list_role_review = new ArrayList<String>();

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
            list_updateType = this.initUpdateType();
            list_spsBtbState = this.initSpsBtbState();
            list_spsBtbStatePart = this.initSpsBtbStatePart();
            list_leves = this.initLeves();
            list_role_review = this.reviewRoles();
        }catch (Exception e){
            LOG.error("初始化后select列表项异常：",e);
        }
    }


    private List<UpdateType> initUpdateType(){
        List<UpdateType> list_updateType = new ArrayList<UpdateType>();
        UpdateType updateType = new UpdateType() ;
        updateType.setUpdateTypeCode("001");
        updateType.setUpdateTypeContent("制定残留限量");
        list_updateType.add(updateType);
        updateType = new UpdateType() ;
        updateType.setUpdateTypeCode("002");
        updateType.setUpdateTypeContent("修改残留限量");
        list_updateType.add(updateType);
        updateType = new UpdateType() ;
        updateType.setUpdateTypeCode("003");
        updateType.setUpdateTypeContent("撤销残留限量");
        list_updateType.add(updateType);
        return list_updateType ;
    }
    private List<SpsBtbState> initSpsBtbState(){
        List<SpsBtbState> list_spsBtbState = new ArrayList<SpsBtbState>();
        SpsBtbState spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
        spsBtbState.setContent(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode());
        spsBtbState.setContent(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode());
        spsBtbState.setContent(Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusCode());
        spsBtbState.setContent(Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        return list_spsBtbState ;
    }
    private List<SpsBtbState> initSpsBtbStatePart(){
        List<SpsBtbState> list_spsBtbState = new ArrayList<SpsBtbState>();
        SpsBtbState spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
        spsBtbState.setContent(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode());
        spsBtbState.setContent(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        return list_spsBtbState ;
    }
    private List<SpsBtbState> initLeves(){
        List<SpsBtbState> list_spsBtbState = new ArrayList<SpsBtbState>();
        SpsBtbState spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(LevelsEnum.HIGH.getStatusCode());
        spsBtbState.setContent(LevelsEnum.HIGH.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(LevelsEnum.MIDDLE.getStatusCode());
        spsBtbState.setContent(LevelsEnum.MIDDLE.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        spsBtbState = new SpsBtbState() ;
        spsBtbState.setState(LevelsEnum.LOW.getStatusCode());
        spsBtbState.setContent(LevelsEnum.LOW.getStatusContent());
        list_spsBtbState.add(spsBtbState);
        return list_spsBtbState ;
    }
    private List<String> reviewRoles(){
        List<String> list_roleReview = new ArrayList<String>();
        list_roleReview.add(RoleEnum.CCPR.getStatusCode());
        list_roleReview.add(RoleEnum.outExports.getStatusCode());
        list_roleReview.add(RoleEnum.notice_manager.getStatusCode());
        list_roleReview.add(RoleEnum.limit_manager.getStatusCode());
        list_roleReview.add(RoleEnum.sys_manager.getStatusCode());
        return list_roleReview ;
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

    public List<UpdateType> getList_updateType() {
        return list_updateType;
    }

    public void setList_updateType(List<UpdateType> list_updateType) {
        this.list_updateType = list_updateType;
    }

    public List<SpsBtbState> getList_spsBtbState() {
        return list_spsBtbState;
    }

    public void setList_spsBtbState(List<SpsBtbState> list_spsBtbState) {
        this.list_spsBtbState = list_spsBtbState;
    }

    public List<SpsBtbState> getList_spsBtbStatePart() {
        return list_spsBtbStatePart;
    }

    public void setList_spsBtbStatePart(List<SpsBtbState> list_spsBtbStatePart) {
        this.list_spsBtbStatePart = list_spsBtbStatePart;
    }

    public List<SpsBtbState> getList_leves() {
        return list_leves;
    }

    public void setList_leves(List<SpsBtbState> list_leves) {
        this.list_leves = list_leves;
    }

    public List<String> getList_role_review() {
        return list_role_review;
    }

    public void setList_role_review(List<String> list_role_review) {
        this.list_role_review = list_role_review;
    }
}
