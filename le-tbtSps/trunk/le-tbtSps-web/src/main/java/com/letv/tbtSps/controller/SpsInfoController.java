package com.letv.tbtSps.controller;


import com.google.common.collect.Lists;
import com.letv.common.utils.DateHelper;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.*;
import com.letv.tbtSps.domain.dto.*;
import com.letv.tbtSps.domain.query.*;
import com.letv.tbtSps.service.*;
import com.letv.tbtSps.utils.DateHelperImpl;
import com.letv.tbtSps.utils.JsonHelperImpl;
import com.letv.tbtSps.utils.ParameterLoad;
import com.letv.tbtSps.utils.constant.SystemConstant;
import com.letv.tbtSps.utils.enums.RoleEnum;
import com.letv.tbtSps.utils.enums.ScopeEnum;
import com.letv.tbtSps.utils.enums.Sps_Tbt_InfoStatus;
import com.letv.tbtSps.utils.enums.SystemCodeEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SpsInfoController ：sps信息表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("spsInfo")
public class SpsInfoController extends ReviewBaseController {

    @Autowired
    private SpsInfoService spsInfoService;
    @Autowired
    private SpsInfoLogService spsInfoLogService;
    @Autowired
    private SpsLogAttrService spsLogAttrService;
    @Autowired
    private SpsResidualInfoService spsResidualInfoService;
    @Autowired
    private ParameterLoad parameterLoad;
    @Autowired
    private RelationSpsLanguageService relationSpsLanguageService;
    @Autowired
    private RelationSpsNotificationTypeService relationSpsNotificationTypeService;
    @Autowired
    private RelationSpsTargereasonService relationSpsTargereasonService;
    @Autowired
    private RelationSpsInternationalStandardService relationSpsInternationalStandardService;
    @Autowired
    private RelationSpsRelationMedicineService relationSpsRelationMedicineService;
    @Autowired
    private RelationSpsRelationMedicineProductService relationSpsRelationMedicineProductService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService ;

    /** 视图前缀 */
    private static final String viewPrefix ="spsInfo";
    
    private static final Log LOG = LogFactory.getLog(SpsInfoController.class);

    /**
     * 首页sps
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        List<SpsBtbState> list_spsBtbState = parameterLoad.getList_spsBtbState();
        List<Country> list_country = parameterLoad.getList_country();
        model.addAttribute("list_country",list_country); // 通报成员
        model.addAttribute("list_spsBtbState",list_spsBtbState) ;// 通报状态
        model.addAttribute("list_date",parameterLoad.getListYear()) ;// 年份
        return viewPrefix + "/index";
    }
    /**
     * 首页tbt
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "tbt")
    public String indexTbt(Model model) {
        List<SpsBtbState> list_spsBtbState = parameterLoad.getList_spsBtbState();
        List<Country> list_country = parameterLoad.getList_country();
        model.addAttribute("list_country",list_country); // 通报成员
        model.addAttribute("list_spsBtbState",list_spsBtbState) ;// 通报状态
        return viewPrefix + "/indexTBT";
    }


    /**
     * 分页 查询数据
     * 
     * @param model
     * @param page
     * @param query
     * @return
     */
    @RequestMapping(value = "queryByPage")
    public String queryByPage(Model model, PageUtil page, SpsInfoQuery query) {
        try {
            if(!StringUtils.isEmpty(query.getPublishDateIn1())){
                query.setPublishDateIn(query.getPublishDateIn1());
            }
            query.setPublishDate(DateHelper.parseDate(query.getPublishDateIn(),"yyyy"));
            List<SpsInfo> dataList = spsInfoService.querySpsInfoListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 通报状态
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
            model.addAttribute("list_spsBtbState",parameterLoad.getList_spsBtbState()) ;// 通报状态
            model.addAttribute("list_country", parameterLoad.getList_country()); // 通报成员
            model.addAttribute("list_date",parameterLoad.getListYear()) ;// 年份
        } catch (Exception e) {
            LOG.error("spsInfo queryByPage has error.", e);
        }
        if("tbt".equals(query.getType())){
            return viewPrefix + "/indexTBT";
        }
        return viewPrefix + "/index";
    }

    /**
     * sps信息表----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward(Model model,SpsInfoQuery spsInfoQuery) {
        addForwardInfo(model, spsInfoQuery);
        if("tbt".equals(spsInfoQuery.getType())){
            return viewPrefix + "/addTBT";
        }
        return viewPrefix + "/add";
    }

    private void addForwardInfo(Model model, SpsInfoQuery spsInfoQuery) {
        /**
         * 组装页面显示的数据  ,当修改的时候走if逻辑
         */
        if(null!=spsInfoQuery.getId() ){
            /**
             * spsInfo基础信息
              */
            List<SpsInfo> listSpsInfo = spsInfoService.querySpsInfoList(spsInfoQuery) ;
            SpsInfo spsInfo = listSpsInfo.get(0);
            String spsCode = spsInfo.getSpsCode();
            // 原文语种信息
            RelationSpsLanguageQuery relationSpsLanguageQuery = new RelationSpsLanguageQuery();
            relationSpsLanguageQuery.setSpsCode(spsCode);
            List<RelationSpsLanguage> listRelationSpsLanguage = relationSpsLanguageService.queryRelationSpsLanguageList(relationSpsLanguageQuery);
            RelationSpsNotificationTypeQuery relationSpsNotificationTypeQuery = new RelationSpsNotificationTypeQuery();
            relationSpsNotificationTypeQuery.setSpsCode(spsCode);
            List<RelationSpsNotificationType> listRelationSpsNotificationType = relationSpsNotificationTypeService.queryRelationSpsNotificationTypeList(relationSpsNotificationTypeQuery);
            RelationSpsTargereasonQuery relationSpsTargereasonQuery = new RelationSpsTargereasonQuery();
            relationSpsTargereasonQuery.setSpsCode(spsCode);
            List<RelationSpsTargereason> listRelationSpsTargetreason = relationSpsTargereasonService.queryRelationSpsTargereasonList(relationSpsTargereasonQuery);
            RelationSpsInternationalStandardQuery relationSpsInternationalStandardQuery = new RelationSpsInternationalStandardQuery();
            relationSpsInternationalStandardQuery.setSpsCode(spsCode);
            List<RelationSpsInternationalStandard> listRelationSpsInternationlStandard = relationSpsInternationalStandardService.queryRelationSpsInternationalStandardList(relationSpsInternationalStandardQuery);
            RelationSpsRelationMedicineQuery relationSpsRelationMedicineQuery = new RelationSpsRelationMedicineQuery();
            relationSpsRelationMedicineQuery.setSpsCode(spsCode);
            List<RelationSpsRelationMedicine> listRelationSpsRelationMedicine = relationSpsRelationMedicineService.queryRelationSpsRelationMedicineList(relationSpsRelationMedicineQuery);
            RelationSpsRelationMedicineProductQuery relationSpsRelationMedicineProductQuery = new RelationSpsRelationMedicineProductQuery();
            relationSpsRelationMedicineProductQuery.setSpsCode(spsCode);
            List<RelationSpsRelationMedicineProduct> listRelationSpsRelationMedicineProduct = relationSpsRelationMedicineProductService.queryRelationSpsRelationMedicineProductList(relationSpsRelationMedicineProductQuery);
            model.addAttribute("spsInfo",spsInfo);
            model.addAttribute("listRelationSpsLanguage",listRelationSpsLanguage);
            model.addAttribute("listRelationSpsNotificationType",listRelationSpsNotificationType);
            model.addAttribute("listRelationSpsTargetreason",listRelationSpsTargetreason);
            model.addAttribute("listRelationSpsInternationlStandard",listRelationSpsInternationlStandard);
            model.addAttribute("listRelationSpsRelationMedicine",listRelationSpsRelationMedicine);
            model.addAttribute("listRelationSpsRelationMedicineProduct",listRelationSpsRelationMedicineProduct);

            /**
             * spsInfo 附件信息
             */
            SpsInfoLogQuery spsInfoLogQuery = new SpsInfoLogQuery();
            spsInfoLogQuery.setSpsCode(spsCode);
            spsInfoLogQuery.setState(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
            List<SpsInfoLog> listSpsInfoLog = spsInfoLogService.querySpsInfoLogList(spsInfoLogQuery)  ;
            if(!CollectionUtils.isEmpty(listSpsInfoLog)){
                SpsInfoLog spsInfoLog = listSpsInfoLog.get(0);
                SpsLogAttrQuery spsLogAttrQuery = new SpsLogAttrQuery();
                spsLogAttrQuery.setSpsCode(spsCode);
                spsLogAttrQuery.setLogAttrRelation(spsInfoLog.getLogAttrRelation());
                List<SpsLogAttr> listSpsLogAttr = spsLogAttrService.querySpsLogAttrList(spsLogAttrQuery) ;
                model.addAttribute("listSpsLogAttr",listSpsLogAttr);
            }
            /**
             * spsInfo 农药农产品信息
             */
            SpsResidualInfoQuery spsResidualInfoQuery = new SpsResidualInfoQuery();
            spsResidualInfoQuery.setSpsCode(spsCode);

            List<SpsResidualInfo> listSpsResidualInfo = spsResidualInfoService.querySpsResidualInfoList(spsResidualInfoQuery) ;
            for(SpsResidualInfo spsResidualInfo : listSpsResidualInfo){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(spsResidualInfo.getEndDate());
                spsResidualInfo.setEndDateIn(dateString);
            }

            model.addAttribute("listSpsResidualInfo",listSpsResidualInfo);
        }else{
            model.addAttribute("standardYnFlag","standardYnFlag");
        }

        List<Country> list_country = parameterLoad.getList_country();
        List<Language> list_language = parameterLoad.getList_language();
        List<NotificationType> list_notificationType = parameterLoad.getList_notificationType();
        List<Targereason> list_targereason = parameterLoad.getList_targereason();
        List<InternationalStandard> list_internationalStandard =  parameterLoad.getList_internationalStandard();
        List<RelationMedicine> list_relationMedicine = parameterLoad.getList_relationMedicine();
        List<RelationMedicineProduct> list_relationMedicineProduct = parameterLoad.getList_relationMedicineProduct();
        List<UpdateType> list_updateType = parameterLoad.getList_updateType();
        List<String> listDate = parameterLoad.getListDate();
        model.addAttribute("list_country",list_country);
        model.addAttribute("list_language",list_language);
        model.addAttribute("list_notificationType",list_notificationType);
        model.addAttribute("list_targereason",list_targereason);
        model.addAttribute("list_internationalStandard",list_internationalStandard);
        model.addAttribute("list_relationMedicine",list_relationMedicine);
        model.addAttribute("list_relationMedicineProduct",list_relationMedicineProduct);
        model.addAttribute("list_updateType",list_updateType);
        model.addAttribute("listDate",listDate);
    }


    @RequestMapping(value = "intoDetail")
    public String intoDetail(Model model,SpsInfoQuery spsInfoQuery){
        /**
         * 组装页面显示的数据  ,当修改的时候走if逻辑
         */
        if(!StringUtils.isEmpty(spsInfoQuery.getSpsCodeQuery()) ){
            spsInfoQuery.setSpsCode(spsInfoQuery.getSpsCodeQuery());
            /**
             * spsInfo基础信息
             */
            List<SpsInfo> listSpsInfo = spsInfoService.querySpsInfoList(spsInfoQuery) ;
            SpsInfo spsInfo = listSpsInfo.get(0);
            String spsCode = spsInfo.getSpsCode();
            // 原文语种信息
            RelationSpsLanguageQuery relationSpsLanguageQuery = new RelationSpsLanguageQuery();
            relationSpsLanguageQuery.setSpsCode(spsCode);
            List<RelationSpsLanguage> listRelationSpsLanguage = relationSpsLanguageService.queryRelationSpsLanguageList(relationSpsLanguageQuery);
            RelationSpsNotificationTypeQuery relationSpsNotificationTypeQuery = new RelationSpsNotificationTypeQuery();
            relationSpsNotificationTypeQuery.setSpsCode(spsCode);
            List<RelationSpsNotificationType> listRelationSpsNotificationType = relationSpsNotificationTypeService.queryRelationSpsNotificationTypeList(relationSpsNotificationTypeQuery);
            RelationSpsTargereasonQuery relationSpsTargereasonQuery = new RelationSpsTargereasonQuery();
            relationSpsTargereasonQuery.setSpsCode(spsCode);
            List<RelationSpsTargereason> listRelationSpsTargetreason = relationSpsTargereasonService.queryRelationSpsTargereasonList(relationSpsTargereasonQuery);
            RelationSpsInternationalStandardQuery relationSpsInternationalStandardQuery = new RelationSpsInternationalStandardQuery();
            relationSpsInternationalStandardQuery.setSpsCode(spsCode);
            List<RelationSpsInternationalStandard> listRelationSpsInternationlStandard = relationSpsInternationalStandardService.queryRelationSpsInternationalStandardList(relationSpsInternationalStandardQuery);
            RelationSpsRelationMedicineQuery relationSpsRelationMedicineQuery = new RelationSpsRelationMedicineQuery();
            relationSpsRelationMedicineQuery.setSpsCode(spsCode);
            List<RelationSpsRelationMedicine> listRelationSpsRelationMedicine = relationSpsRelationMedicineService.queryRelationSpsRelationMedicineList(relationSpsRelationMedicineQuery);
            RelationSpsRelationMedicineProductQuery relationSpsRelationMedicineProductQuery = new RelationSpsRelationMedicineProductQuery();
            relationSpsRelationMedicineProductQuery.setSpsCode(spsCode);
            List<RelationSpsRelationMedicineProduct> listRelationSpsRelationMedicineProduct = relationSpsRelationMedicineProductService.queryRelationSpsRelationMedicineProductList(relationSpsRelationMedicineProductQuery);
            model.addAttribute("spsInfo",spsInfo);
            model.addAttribute("listRelationSpsLanguage",listRelationSpsLanguage);
            model.addAttribute("listRelationSpsNotificationType",listRelationSpsNotificationType);
            model.addAttribute("listRelationSpsTargetreason",listRelationSpsTargetreason);
            model.addAttribute("listRelationSpsInternationlStandard",listRelationSpsInternationlStandard);
            model.addAttribute("listRelationSpsRelationMedicine",listRelationSpsRelationMedicine);
            model.addAttribute("listRelationSpsRelationMedicineProduct",listRelationSpsRelationMedicineProduct);

            /**
             * spsInfo 附件信息
             */
            SpsInfoLogQuery spsInfoLogQuery = new SpsInfoLogQuery();
            spsInfoLogQuery.setSpsCode(spsCode);
            spsInfoLogQuery.setState(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
            List<SpsInfoLog> listSpsInfoLog = spsInfoLogService.querySpsInfoLogList(spsInfoLogQuery)  ;
            if(!CollectionUtils.isEmpty(listSpsInfoLog)){
                SpsInfoLog spsInfoLog = listSpsInfoLog.get(0);
                SpsLogAttrQuery spsLogAttrQuery = new SpsLogAttrQuery();
                spsLogAttrQuery.setSpsCode(spsCode);
                spsLogAttrQuery.setLogAttrRelation(spsInfoLog.getLogAttrRelation());
                List<SpsLogAttr> listSpsLogAttr = spsLogAttrService.querySpsLogAttrList(spsLogAttrQuery) ;
                model.addAttribute("listSpsLogAttr",listSpsLogAttr);
            }
            /**
             * spsInfo 农药农产品信息
             */
            SpsResidualInfoQuery spsResidualInfoQuery = new SpsResidualInfoQuery();
            spsResidualInfoQuery.setSpsCode(spsCode);

            List<SpsResidualInfo> listSpsResidualInfo = spsResidualInfoService.querySpsResidualInfoList(spsResidualInfoQuery) ;
            for(SpsResidualInfo spsResidualInfo : listSpsResidualInfo){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(spsResidualInfo.getEndDate());
                spsResidualInfo.setEndDateIn(dateString);
            }

            model.addAttribute("listSpsResidualInfo",listSpsResidualInfo);
        }else{
            model.addAttribute("standardYnFlag","standardYnFlag");
        }

        List<Country> list_country = parameterLoad.getList_country();
        List<Language> list_language = parameterLoad.getList_language();
        List<NotificationType> list_notificationType = parameterLoad.getList_notificationType();
        List<Targereason> list_targereason = parameterLoad.getList_targereason();
        List<InternationalStandard> list_internationalStandard =  parameterLoad.getList_internationalStandard();
        List<RelationMedicine> list_relationMedicine = parameterLoad.getList_relationMedicine();
        List<RelationMedicineProduct> list_relationMedicineProduct = parameterLoad.getList_relationMedicineProduct();
        List<UpdateType> list_updateType = parameterLoad.getList_updateType();
        List<String> listDate = parameterLoad.getListDate();
        model.addAttribute("list_country",list_country);
        model.addAttribute("list_language",list_language);
        model.addAttribute("list_notificationType",list_notificationType);
        model.addAttribute("list_targereason",list_targereason);
        model.addAttribute("list_internationalStandard",list_internationalStandard);
        model.addAttribute("list_relationMedicine",list_relationMedicine);
        model.addAttribute("list_relationMedicineProduct",list_relationMedicineProduct);
        model.addAttribute("list_updateType",list_updateType);
        model.addAttribute("listDate",listDate);
        return viewPrefix + "/detail";
    }
    /**
     * sps信息表----添加
     * 
     * @param spsInfo
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(SpsInfo spsInfo,@RequestParam("file") MultipartFile file) {
        try {
            spsInfo.setCreateUser(getLoginUserCnName());
            if (spsInfoService.insert(spsInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("spsInfo add fail, exist spsInfo.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("spsInfo add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps信息表----更新跳转
     * 
     * @param model
     * @param spsInfo
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, SpsInfo spsInfo) {
        try {
            SpsInfo spsInfoResult = spsInfoService.getSpsInfoById(spsInfo.getId());
            model.addAttribute("spsInfo", spsInfoResult);
        } catch (Exception e) {
            LOG.error("spsInfo updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps信息表----更新
     * 
     * @param model
     * @param spsInfo
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, SpsInfo spsInfo) {
        try {
            spsInfo.setUpdateUser(getLoginUserCnName());
            if (spsInfoService.update(spsInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfo update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表----删除
     * 
     * @param spsInfo
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(SpsInfo spsInfo) {
        try {
            spsInfo.setUpdateUser(getLoginUserCnName());
            if (spsInfoService.delete(spsInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfo delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(SpsInfoQuery query) {
        try {
            List<SpsInfo> list = spsInfoService.querySpsInfoList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfo query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps信息表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(SpsInfoQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

//        try {
//            SpsInfo spsInfo = spsInfoService.getSpsInfoById(query.getId());
//            if (spsInfo != null) {
//                return new Wrapper<SpsInfo>().result(spsInfo);
//            } else {
//                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps信息表详情失败！");
//            }
//        } catch (Exception e) {
//            LOG.warn("detail spsInfo has error.", e);
//            return error();
//        }
        return null ;
    }

    /**
     * 创建sps基础信息
     * @param request
     * @param files 附件
     * @param spsInfo
     * @param tableContent  contnet3的内容 jsonArray
     * @return
     */
    @RequestMapping(value = "/createOrderInfo", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> createOrderInfo(HttpServletRequest request, @RequestParam("file") List<MultipartFile> files
            , SpsInfo spsInfo,String tableContent , SpsBtbMulityDto spsBtbMulityDto,String fileNameId) {
        LOG.info("进入创建sps方法，传入user="+getLoginUserName()+" , spsInfo="+ JsonHelperImpl.toJson(spsInfo)+" , tableContent="+tableContent+",spsBtbMulityDto="+JsonHelperImpl.toJson(spsBtbMulityDto));
        try{
            String[] ret = null ;

            if(StringUtils.isEmpty(spsInfo.getSpsCode())){
                Wrapper wrapper = new Wrapper<SpsInfo>();
                wrapper.setCode(400);
                wrapper.setMessage("请填写通报编码");
                return wrapper ;
            }
            if(StringUtils.isEmpty(spsInfo.getPublishDateIn())){
                Wrapper wrapper = new Wrapper<SpsInfo>();
                wrapper.setCode(400);
                wrapper.setMessage("请填写分发日期");
                return wrapper ;
            }
            if(null==spsBtbMulityDto.getLanguage() || spsBtbMulityDto.getLanguage().length==0){
                Wrapper wrapper = new Wrapper<SpsInfo>();
                wrapper.setCode(400);
                wrapper.setMessage("请选择原文语种");
                return wrapper ;
            }
            if(StringUtils.isEmpty(spsInfo.getCountryCode())){
                Wrapper wrapper = new Wrapper<SpsInfo>();
                wrapper.setCode(400);
                wrapper.setMessage("请选择通报成员");
                return wrapper ;
            }
            if(null == spsInfo.getId()){
                ret = spsInfoService.createOrderInfo(spsInfo,files,tableContent,getLoginUserName(),spsBtbMulityDto);
            } else{
                ret = spsInfoService.updateOrderInfo(spsInfo,files,tableContent,getLoginUserName(),spsBtbMulityDto,fileNameId);
            }

            if(ret[0].equals(SystemCodeEnum.SUCCESS.getCode())){
                Wrapper wrapper = new Wrapper<SpsInfo>().result(spsInfo);
                wrapper.setCode(Integer.valueOf(SystemCodeEnum.SUCCESS.getCode()));
                wrapper.setMessage("操作成功");
                return wrapper;
            } else{
                Wrapper wrapper = new Wrapper<SpsInfo>().result(spsInfo);
                wrapper.setCode(Integer.parseInt(ret[0]));
                wrapper.setMessage(ret[1]);
                return wrapper;
            }
        }catch (Exception e){
            return error();
        }
    }

    /**
     * 根据通报成员分组查询通报成员拥有的年份
     * @param queryBean
     * @return
     */
    @RequestMapping(value = "/queryCountryDateByCountry", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> queryCountryDateByCountry(SpsInfoQuery queryBean){
        try{
            if(!StringUtils.isEmpty(queryBean.getPublishDateIn1())){
                queryBean.setPublishDateIn(queryBean.getPublishDateIn1());
            }
            List<SpsInfo> list_spsInfo = spsInfoService.queryCountryDateByCountry(queryBean);
            return new Wrapper<List<SpsInfo>>().result(list_spsInfo);
        } catch (Exception e){
            return error();
        }
    }


    /***************以下是在线评议管理*******************************/

    /**
     * 通报评审首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "indexReview")
    public String indexReview(Model model) {
        List<SpsBtbState> list_spsBtbState = parameterLoad.getList_spsBtbState();
        model.addAttribute("list_spsBtbState",list_spsBtbState) ;// 通报状态
        return viewPrefix + "/index_review";
    }


    /**
     * 分页 查询数据 通报评审
     *
     * @param model
     * @param page
     * @param query
     * @return
     */
    @RequestMapping(value = "queryByPageReview")
    public String queryByPageReview(Model model, PageUtil page, SpsInfoQuery query) {
        try {
            List<SpsInfo> dataList = spsInfoService.querySpsInfoListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 查询的数据
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
            UserQuery queryBean = new UserQuery ();

            queryBean.setList_roleCode(parameterLoad.getList_role_review_ccpr_outExp());
            queryBean.setUserType(1);
            List<SpsBtbState> list_spsBtbState = parameterLoad.getList_spsBtbState();
            model.addAttribute("list_spsBtbState",list_spsBtbState) ;// 通报状态
            logger.info("queryByPageReview:"+JsonHelper.toJson(queryBean));
            if(query.getState().equals(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode())){
                List<User> list_user = userService.queryUserByRoleCodeNoPage(queryBean);
                List<SpsBtbState> list_leves = parameterLoad.getList_leves();
                model.addAttribute("list_user",list_user); // 重要程度
                model.addAttribute("list_leves",list_leves); // 重要程度
            }else if(query.getState().equals(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode())
                    || query.getState().equals(Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode())
                    || query.getState().equals(Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusCode())){
                for(SpsInfo spsInfo : dataList){
                    spsInfo.setLevels(parameterLoad.getLevelsNameByCode(spsInfo.getLevels()));

                    // 查询评议专家 状态=20 and content = "已分配"
                    SpsInfoLogQuery spsInfoLogQuery = new SpsInfoLogQuery();
                    spsInfoLogQuery.setSpsCode(spsInfo.getSpsCode());
                    spsInfoLogQuery.setState(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode());
                    spsInfoLogQuery.setContent(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusContent());
                    List<SpsInfoLog>  listSpsInfoLog = spsInfoLogService.querySpsInfoLogList(spsInfoLogQuery) ;

                    spsInfoLogQuery = new SpsInfoLogQuery();
                    spsInfoLogQuery.setSpsCode(spsInfo.getSpsCode());
                    spsInfoLogQuery.setState(Sps_Tbt_InfoStatus.HAVE_FENPEI_HUIDA.getStatusCode());
                    List<SpsInfoLog>  listSpsInfoLog21 = spsInfoLogService.querySpsInfoLogList(spsInfoLogQuery) ;
                    for(SpsInfoLog spsInfoLog : listSpsInfoLog){
                        for(SpsInfoLog spsInfoLog21 : listSpsInfoLog21){
                            if(spsInfoLog.getExports().equals(spsInfoLog21.getCreateUser())){
                                spsInfoLog.setHaveReview(SystemConstant.YES+"");
                            }
                        }
                    }
                    spsInfo.setList_spsInfoLog(listSpsInfoLog);
                }

            }
        } catch (Exception e) {
            LOG.error("spsInfo queryByPage has error.", e);
        }
        return viewPrefix + "/index_review";
    }

    /**
     * 下发 到 专家
     * @param tableJson
     * @return
     */
    @RequestMapping(value = "/sendNotice", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> sendNotice(String tableJson){
        try{
            if(StringUtils.isEmpty(tableJson)){
                Wrapper wrapper = new Wrapper<String>();
                wrapper.setCode(400);
                wrapper.setMessage("请选择需要下发的数据");
                return wrapper;
            }
            String[] ret = spsInfoService.sendNotice(tableJson,this.getLoginUserName());
            Wrapper wrapper = new Wrapper<String>().result(ret[1]);
            wrapper.setCode(Integer.parseInt(ret[0]));
            wrapper.setMessage(ret[1]);
            return wrapper;
        } catch (Exception e){
            return error();
        }
    }

    /**
     * 进入评议页面
     * @param model
     * @param spsCode
     * @param sendFalg 下发和未下发标识 ，10 未分配， 20已分配
     * @return
     */
    @RequestMapping(value = "reviewForward")
    public String reviewForward(Model model , String spsCode , String sendFalg) {
        try {
            spsCode = URLDecoder.decode(spsCode, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * 1、查询spsInfo 表 ， 查询出评议基本内容
         * 2、查询spsInfoLog 表 ， 获得附件信息管理的字段 、专家评议的信息
         *
         */
        SpsInfoQuery spsInfoQuery = new SpsInfoQuery () ;
        spsInfoQuery.setSpsCode(spsCode);
        // 查询sps基础信息
        List<SpsInfo> list_spsInfo = spsInfoService.querySpsInfoList(spsInfoQuery);
        /**
         * 对基本信息进行显示加工（通报成员、是否符合标准 进行汉子转换）
         */
        for(SpsInfo spsInfo : list_spsInfo){
            if(SystemConstant.YES==spsInfo.getStandardYn()){
                spsInfo.setStandardYnShow(SystemConstant.standardY);
            }else{
                spsInfo.setStandardYnShow(SystemConstant.standardN);
            }
            spsInfo.setCountryCode(parameterLoad.getCountryNameByCode(spsInfo.getCountryCode()));
        }
        /**
         * 原文语种 、 目标理由 查询 并 转汉子
         */
        StringBuilder languageSb = new StringBuilder() ;
        StringBuilder targetReasonSb = new StringBuilder() ;
        RelationSpsLanguageQuery relationSpsLanguageQuery = new RelationSpsLanguageQuery();
        relationSpsLanguageQuery.setSpsCode(spsCode);
        List<RelationSpsLanguage> list_relationSpsLanguage = relationSpsLanguageService.queryRelationSpsLanguageList(relationSpsLanguageQuery);
        for(RelationSpsLanguage relationSpsLanguage : list_relationSpsLanguage){
            languageSb.append(","+parameterLoad.getLanguageNameByCode(relationSpsLanguage.getLanguageCode()));
        }
        RelationSpsTargereasonQuery relationSpsTargereasonQuery = new RelationSpsTargereasonQuery();
        relationSpsTargereasonQuery.setSpsCode(spsCode);
        List<RelationSpsTargereason> list_relationSpsTargereason = relationSpsTargereasonService.queryRelationSpsTargereasonList(relationSpsTargereasonQuery);
        for(RelationSpsTargereason relationSpsTargereason : list_relationSpsTargereason){
            targetReasonSb.append(","+parameterLoad.getTargetReasonNameByCode(relationSpsTargereason.getTargetReasonCode()));
        }

        SpsInfoLogQuery spsInfoLogQuery = new SpsInfoLogQuery();
        spsInfoLogQuery.setSpsCode(spsCode);
        // 查询sps的日志信息
        List<SpsInfoLog> list_spsCodeLog = spsInfoLogService.querySpsInfoLogList(spsInfoLogQuery);
        List<SpsLogAttr> list_attr = new ArrayList<SpsLogAttr>(); // 定义附件list
        List<SpsInfoLog> list_review = new ArrayList<SpsInfoLog>(); // 定义专家评议list，不包括自己可以修改的评议内容，包括自己不可以修改的评议内容（激活后原来评议的内容就不可以在进行编辑了，需要在进行新的评议）
        List<SpsInfoLog> list_summary = new ArrayList<SpsInfoLog>(); // 定义汇总评议的list
        List<SpsInfoLog> list_feedBack = new ArrayList<SpsInfoLog>(); // 定义反馈的list
        SpsInfoLog review = new SpsInfoLog();// 定义自己可以修改的评议
        SpsInfoLog summary = new SpsInfoLog();// 定义自己可以修改的汇总
        SpsInfoLog feedBack = new SpsInfoLog();// 定义自己可以修改的反馈
        String logAttrRelation = "" ;// sps附件关联字段
        for(SpsInfoLog spsInfoLog : list_spsCodeLog){
            logAttrRelation = spsInfoLog.getLogAttrRelation();
            // 查询日志对应的附件信息
            SpsLogAttrQuery spsLogAttrQuery = new SpsLogAttrQuery();
            spsLogAttrQuery.setLogAttrRelation(logAttrRelation);
            List<SpsLogAttr> listLogAttr = spsLogAttrService.querySpsLogAttrList(spsLogAttrQuery) ;
            spsInfoLog.setListLogAttr(listLogAttr);

            if(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode().equals(spsInfoLog.getState())){    // 未分配
                list_attr = listLogAttr ;
            } else if(Sps_Tbt_InfoStatus.HAVE_FENPEI_HUIDA.getStatusCode().equals(spsInfoLog.getState())){  // 已评议
                if(!this.getLoginUserName().equals(spsInfoLog.getCreateUser())){// 不是自己的都可以看到
                    list_review.add(spsInfoLog);
                }else if(this.getLoginUserName().equals(spsInfoLog.getCreateUser()) && String.valueOf(SystemConstant.NO).equals(spsInfoLog.getCanEdit())){ //是自己的，但是不可以编辑了，也能看到
                    list_review.add(spsInfoLog);
                }else{
                    review = spsInfoLog ;// 自己的可以编辑的
                }
            } else if(Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode().equals(spsInfoLog.getState())){
                if(!this.getLoginUserName().equals(spsInfoLog.getCreateUser())){// 不是自己的都可以看到
                    list_summary.add(spsInfoLog);
                }else{
                    summary = spsInfoLog ;// 自己的可以编辑的
                }
            } else if(Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusCode().equals(spsInfoLog.getState())){
                if(!this.getLoginUserName().equals(spsInfoLog.getCreateUser())){// 不是自己的都可以看到
                    list_feedBack.add(spsInfoLog);
                }else{
                    feedBack = spsInfoLog ;// 自己的可以编辑的
                }
            }
        }

        model.addAttribute("spsInfo",list_spsInfo.get(0));
        model.addAttribute("review",review);
        model.addAttribute("list_review",list_review);
        model.addAttribute("list_attr",list_attr);
        model.addAttribute("list_summary",list_summary);
        model.addAttribute("summary",summary);
        model.addAttribute("list_feedBack",list_feedBack);
        model.addAttribute("feedBack",feedBack);
        try{
            model.addAttribute("languageSb",String.valueOf(languageSb).substring(1,String.valueOf(languageSb).length()));
            model.addAttribute("targetReasonSb",String.valueOf(targetReasonSb).substring(1,String.valueOf(targetReasonSb).length()));
        } catch (Exception e){
            this.logger.info("异常就异常吧，没关系了");
        }
        if(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode().equals(sendFalg)){ // 进入汇总操作页面
            /**
             * 把历史评议内容，拼装到一起
             */
            StringBuilder strb = new StringBuilder();
            for(SpsInfoLog spsInfoLog : list_review){
                strb.append(spsInfoLog.getContent()+"\n");
            }
            strb.append(review.getContent());
            model.addAttribute("overContent",String.valueOf(strb));
            return viewPrefix+"/summary_review" ;
        }else if(Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode().equals(sendFalg)){  // 进入反馈操作页面
            return viewPrefix+"/feedback_review" ;
        } else if(Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusCode().equals(sendFalg)){  // 进入已反馈页面
            return viewPrefix+"/feedback_have" ;
        }else if(Sps_Tbt_InfoStatus.EXPERTS_UN_REVIEW.getStatusCode().equals(sendFalg)){  // 进入反专家评议页面
            UserRoleQuery userRoleQuery = new UserRoleQuery();
            userRoleQuery.setUserCode(this.getLoginUserCode());
            List<UserRole> userRoleList = userRoleService.queryUserRoleList(userRoleQuery);
            String flag = "" ;
            for(UserRole userRole : userRoleList){// 如果是ccpr角色，则可以进行选择是否最终评议
                if(RoleEnum.CCPR.getStatusCode().equals(userRole.getRoleCode())){
                    model.addAttribute("overReview","1");
                    flag = "1" ;
                }
            }
            list_spsInfo.get(0).getExpertsEndDate();// 专家评议截止日期
            int result = compare_date(new Date(),list_spsInfo.get(0).getExpertsEndDate());
            if(StringUtils.isEmpty(flag) && result==1){// 不是ccpr，并且超过了评审日期，则不能进行评审
                model.addAttribute("pingShenButton","no");
            }
            return viewPrefix+"/experts_review" ;
        } else{ // 进入详情页面
            return viewPrefix+"/detailInfo" ;
        }
    }


    /**
     * 进行评议汇总
     * @return
     */
    @Deprecated
    @RequestMapping(value = "/summaryReview", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> summaryReview(HttpServletRequest request, @RequestParam("file") List<MultipartFile> files , SpsInfoLog spsInfoLog){
        /**
         * 1、在日志表里面插入数据， 数据的状态是30 已经汇总 ，操作顺序是4 ， 修改canedit为 0 不可以编辑
         * 2、在附件表里面插入附件数据 ， 路径里面加上专家的编码作为一级路径
         */
        try{
            String[] ret = spsInfoService.summaryReview(spsInfoLog,files,this.getLoginUserName());
            Wrapper wrapper = new Wrapper<String>().result(ret[1]);
            wrapper.setCode(Integer.parseInt(ret[0]));
            wrapper.setMessage(ret[1]);
            return wrapper;
        } catch (Exception e){
            return error();
        }
    }
    /**
     * 进行评议汇总提交
     * @return
     */
    @RequestMapping(value = "/summaryReviewSubmit", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> summaryReviewSubmit(HttpServletRequest request, @RequestParam("file") List<MultipartFile> files , SpsInfoLog spsInfoLog){
        /**
         * 1、修改spsinfo表的状态为30 评议汇总
         * 2、在日志表里面插入数据， 数据的状态是30 已经汇总 ，操作顺序是4 ， 修改canedit为 0 不可以编辑
         * 3、在附件表里面插入附件数据 ， 路径里面加上专家的编码作为一级路径
         */
        try{
            String[] ret = spsInfoService.summaryReviewSubmit(spsInfoLog,files,this.getLoginUserName());
            Wrapper wrapper = new Wrapper<String>().result(ret[1]);
            wrapper.setCode(Integer.parseInt(ret[0]));
            wrapper.setMessage(ret[1]);
            return wrapper;
        } catch (Exception e){
            return error();
        }
    }
    /**
     * 意见反馈
     * @return
     */
    @RequestMapping(value = "/feedBack", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> feedBack(HttpServletRequest request, @RequestParam("file") List<MultipartFile> files , SpsInfoLog spsInfoLog){
        /**
         * 1、修改spsinfo表的状态为40 已反馈
         * 2、在日志表里面插入数据， 数据的状态是40 已反馈 ，操作顺序是5
         * 3、在附件表里面插入附件数据 ， 路径里面加上当前登录用户编码作为一级路径
         */
        try{
            String[] ret = spsInfoService.feedBackSubmit(spsInfoLog,files,this.getLoginUserName());
            Wrapper wrapper = new Wrapper<String>().result(ret[1]);
            wrapper.setCode(Integer.parseInt(ret[0]));
            wrapper.setMessage(ret[1]);
            return wrapper;
        } catch (Exception e){
            return error();
        }
    }
    /*********************************************/
    /*****************专家评议开始****************************/
    /**
     * 专家评议首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "indexExpertsReview")
    public String indexExpertsReview(Model model) {
        List<SpsBtbState> list_spsBtbState = parameterLoad.getList_spsBtbStateExperts();
        model.addAttribute("list_spsBtbState",list_spsBtbState) ;// 通报状态
        List<SpsBtbState> list_scope = parameterLoad.getList_scope();
        model.addAttribute("list_scope",list_scope) ;// 通报范围
        return viewPrefix + "/index_exports_review";
    }


    /**
     * 分页 查询数据 专家评议
     *
     * @param model
     * @param page
     * @param query
     * @return
     */
    @RequestMapping(value = "queryByPageExpertsReview")
    public String queryByPageExpertsReview(Model model, PageUtil page, SpsInfoQuery query,String scope) {
        try {
            // 设置给页面回显用的
            if(query.getOraState().equals(Sps_Tbt_InfoStatus.EXPERTS_UN_REVIEW.getStatusCode())){
                query.setExpertsReivewFlag(Sps_Tbt_InfoStatus.EXPERTS_UN_REVIEW.getStatusCode());
            }
            query.setState(query.getOraState().substring(0,2));
            /**
             * 查询出已分配的所有单据
             */
            List<SpsInfo> list_spsInfo = Lists.newArrayList() ;
            SpsInfoLogQuery spsInfoLogQuery = new SpsInfoLogQuery();
            if(ScopeEnum.SELF.getStatusCode().equals(scope)){// 如果选择的是自己， 那么需要设置自己为查询条件
                spsInfoLogQuery.setExports(this.getLoginUserCode());
            }
            /**
             * 1、先查询出log，然后看log中同一个spsCode的状态， 如果有一个是已评议21 ， 则是已评议， 如果没有任务一个已评议21 ， 则是未评议
             * 2、用过滤出来的spsCode,传入，在查询sps_info 表 就可以了
             * 其实这里就是根据log表过滤出来，哪些code是未评议的，哪些是已经评议的
             */

            List<String> listSpsCode = new ArrayList<String>();
            List<SpsInfoLog> listSpsInfoLog = spsInfoLogService.queryUnReviewLog(spsInfoLogQuery) ;
            for(SpsInfoLog spsInfoLog : listSpsInfoLog){
                if(spsInfoLog.getState().equals(Sps_Tbt_InfoStatus.HAVE_FENPEI_HUIDA.getStatusCode())){  // 已评议的code
                    listSpsCode.add(spsInfoLog.getSpsCode());
                }
            }
            if(query.getState().equals(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode())){  // 未评议的code
                for(int i = 0 ; i<listSpsInfoLog.size();i++){
                    SpsInfoLog spsInfoLog = listSpsInfoLog.get(i);
                    for(String spsCode : listSpsCode){
                        if(spsCode.equals(spsInfoLog.getSpsCode())){
                            listSpsInfoLog.set(i,null);
                            break ;
                        }
                    }
                }
                listSpsCode.clear();
                for(SpsInfoLog spsInfoLog : listSpsInfoLog){
                    if(null!=spsInfoLog) {
                        listSpsCode.add(spsInfoLog.getSpsCode());
                    }
                }
            }
            // 调用sql 查询 spsinfo 就可以了
            if(listSpsCode.size()>0){
                query.setListSpsCode(listSpsCode);
                list_spsInfo = spsInfoService.querySpsInfoExpertsAll(query,page);
                for(SpsInfo spsInfo : list_spsInfo){
                    spsInfo.setLevels(parameterLoad.getLevelsNameByCode(spsInfo.getLevels()));

                    // 查询评议专家 状态=20 and content = "已分配"
                    spsInfoLogQuery = new SpsInfoLogQuery();
                    spsInfoLogQuery.setSpsCode(spsInfo.getSpsCode());
                    spsInfoLogQuery.setState(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode());
                    spsInfoLogQuery.setContent(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusContent());
                    listSpsInfoLog = spsInfoLogService.querySpsInfoLogList(spsInfoLogQuery) ;

                    spsInfoLogQuery = new SpsInfoLogQuery();
                    spsInfoLogQuery.setSpsCode(spsInfo.getSpsCode());
                    spsInfoLogQuery.setState(Sps_Tbt_InfoStatus.HAVE_FENPEI_HUIDA.getStatusCode());
                    List<SpsInfoLog>  listSpsInfoLog21 = spsInfoLogService.querySpsInfoLogList(spsInfoLogQuery) ;
                    for(SpsInfoLog spsInfoLog : listSpsInfoLog){
                        for(SpsInfoLog spsInfoLog21 : listSpsInfoLog21){
                            if(spsInfoLog.getExports().equals(spsInfoLog21.getCreateUser())){
                                spsInfoLog.setHaveReview(SystemConstant.YES+"");
                            }
                        }
                    }
                    spsInfo.setList_spsInfoLog(listSpsInfoLog);
                }
            }else{
                page.setTotalRow(0);
                page.setTotalPage(0);
            }

            model.addAttribute("dataList", list_spsInfo);// 查询的数据
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
            UserQuery queryBean = new UserQuery ();
            queryBean.setList_roleCode(parameterLoad.getList_role_review());
            queryBean.setUserType(1);
            List<SpsBtbState> list_spsBtbState = parameterLoad.getList_spsBtbStateExperts();
            model.addAttribute("list_spsBtbState",list_spsBtbState) ;// 通报状态
            List<SpsBtbState> list_scope = parameterLoad.getList_scope();
            model.addAttribute("list_scope",list_scope) ;// 通报范围
            model.addAttribute("scope",scope) ;
        } catch (Exception e) {
            LOG.error("spsInfo queryByPage has error.", e);
        }
        return viewPrefix + "/index_exports_review";
    }

    /**
     * 专家进行评议
     * @return
     */
    @RequestMapping(value = "/doReview", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> doReview(HttpServletRequest request, @RequestParam("file") List<MultipartFile> files , SpsInfoLog spsInfoLog){
        /**
         * 1、在日志表里面插入数据， 数据的状态是21 已经评议 ，操作顺序是3
         * 2、在附件表里面插入附件数据 ， 路径里面加上专家的编码作为一级路径
         */
        try{
            String[] ret = spsInfoService.doReview(spsInfoLog,files,this.getLoginUserName());
            Wrapper wrapper = new Wrapper<String>().result(ret[1]);
            wrapper.setCode(Integer.parseInt(ret[0]));
            wrapper.setMessage(ret[1]);
            return wrapper;
        } catch (Exception e){
            return error();
        }
    }
    /******************专家评议结束***************************/

    /**
     * 激活
     * @param tableJson
     * @return
     */
    @RequestMapping(value = "/jiHuo", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> jiHuo(String tableJson){
        try{
            if(StringUtils.isEmpty(tableJson)){
                Wrapper wrapper = new Wrapper<String>();
                wrapper.setCode(400);
                wrapper.setMessage("请选择需要激活的数据");
                return wrapper;
            }
            String[] ret = spsInfoService.jihuo(tableJson,this.getLoginUserName());
            Wrapper wrapper = new Wrapper<String>().result(ret[1]);
            wrapper.setCode(Integer.parseInt(ret[0]));
            wrapper.setMessage(ret[1]);
            return wrapper;
        } catch (Exception e){
            return error();
        }
    }

    public static int compare_date(Date DATE1, Date DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
//            Date dt1 = df.parse(DATE1);
//            Date dt2 = df.parse(DATE2);
            if (DATE1.getTime() >= DATE2.getTime()) {
                return 1;
            } else if (DATE1.getTime() < DATE2.getTime()) {
                return -1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 首页indexSolr
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "indexSolr")
    public String indexSolr(Model model,SolrDtoQuery query ) {
        query.setPublishDateYearBegin(DateHelperImpl.getNowYear());
        query.setPublishDateYearEnd(DateHelperImpl.getNowYear());
        model.addAttribute("list_country",parameterLoad.getList_country()); // 通报成员
        model.addAttribute("list_notificationType",parameterLoad.getList_notificationType()) ;// 通报类型
        model.addAttribute("list_relationMedicine",parameterLoad.getList_relationMedicine());
        model.addAttribute("list_relationMedicineProduct",parameterLoad.getList_relationMedicineProduct());
        model.addAttribute("year",parameterLoad.getListYear());
        model.addAttribute("query", query);
        return viewPrefix + "/indexSolr";
    }

    @RequestMapping(value = "sorlQuery")
    public String sorlQuery(SolrDtoQuery query , Model model, PageUtil page){
        try {
            List<SolrDto> solrDtoList = solrDtoList = spsInfoService.sorlQuery(query,page);

            if(!StringUtils.isEmpty(query.getNotificationTypeCodeQuery())){
                String[] strs = query.getNotificationTypeCodeQuery().split(",");
                List<String> ll = Lists.newArrayList();
                for(String str : strs){
                    ll.add(str);
                }
                query.setNotificationTypeCode(ll);
            }
            if(!StringUtils.isEmpty(query.getCountryCode())){
                String[] strs = query.getCountryCode().split(",");
                List<String> ll = Lists.newArrayList();
                for(String str : strs){
                    ll.add(str);
                }
                query.setCountryCodeList(ll);
            }
            if(!StringUtils.isEmpty(query.getRelationMedicineProductCode())){
                String[] strs = query.getRelationMedicineProductCode().split(",");
                List<String> ll = Lists.newArrayList();
                for(String str : strs){
                    ll.add(str);
                }
                query.setRelationMedicineProductCodeList(ll);
            }
            if(!StringUtils.isEmpty(query.getRelationMedicineCode())){
                String[] strs = query.getRelationMedicineCode().split(",");
                List<String> ll = Lists.newArrayList();
                for(String str : strs){
                    ll.add(str);
                }
                query.setRelationMedicineCodeList(ll);
            }

            model.addAttribute("dataList",solrDtoList);
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
            model.addAttribute("list_country",parameterLoad.getList_country()); // 通报成员
            model.addAttribute("list_notificationType",parameterLoad.getList_notificationType()) ;// 通报类型
            model.addAttribute("list_relationMedicine",parameterLoad.getList_relationMedicine());
            model.addAttribute("list_relationMedicineProduct",parameterLoad.getList_relationMedicineProduct());
            model.addAttribute("year",parameterLoad.getListYear());
            model.addAttribute("nowYear", DateHelperImpl.getNowYear());
            return viewPrefix + "/indexSolr" ;
        } catch (Exception e) {
            logger.info("spsInfoController.sorlQuery.e:",e);
        }
        return viewPrefix + "/indexSolr" ;
    }
}
