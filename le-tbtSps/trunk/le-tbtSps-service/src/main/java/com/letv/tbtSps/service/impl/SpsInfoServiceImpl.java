package com.letv.tbtSps.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.collect.Lists;
import com.letv.common.utils.DateHelper;
import com.letv.common.utils.config.PropertiesHelper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.domain.*;
import com.letv.tbtSps.domain.dto.SendDto;
import com.letv.tbtSps.domain.dto.SolrDto;
import com.letv.tbtSps.domain.dto.SolrDtoQuery;
import com.letv.tbtSps.domain.dto.SpsBtbMulityDto;
import com.letv.tbtSps.domain.query.SpsInfoLogQuery;
import com.letv.tbtSps.manager.SpsInfoLogManager;
import com.letv.tbtSps.manager.SpsLogAttrManager;
import com.letv.tbtSps.service.SolrService;
import com.letv.tbtSps.utils.DateHelperImpl;
import com.letv.tbtSps.utils.JsonHelperImpl;
import com.letv.tbtSps.utils.constant.SystemConstant;
import com.letv.tbtSps.utils.enums.Sps_Tbt_InfoStatus;
import com.letv.tbtSps.utils.enums.SystemCodeEnum;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.tbtSps.manager.SpsInfoManager;
import com.letv.tbtSps.service.SpsInfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * SpsInfoService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class SpsInfoServiceImpl implements SpsInfoService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(SpsInfoServiceImpl.class);

    @Autowired
    private SpsInfoManager spsInfoManager;
    @Autowired
    private SpsLogAttrManager spsLogAttrManager;
    @Autowired
    private SpsInfoLogManager spsInfoLogManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoService.batchInsert")
    public boolean insert(List<SpsInfo> spsInfoList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isEmpty(spsInfoList)) {
                resultFlag = spsInfoManager.insert(spsInfoList);
            } else {
                LOG.warn("SpsInfoServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoService.insert")
    public boolean insert(SpsInfo spsInfo) {
        boolean resultFlag = false;
        try {
            if (null != spsInfo) {
                if (spsInfoManager.exist(spsInfo)) {
                    throw new ExistedException();
                }
                resultFlag = spsInfoManager.insert(spsInfo);
            } else {
                LOG.warn("SpsInfoServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("SpsInfoServiceImpl#insert failed, spsInfo has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("SpsInfoServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoService.update")
    public boolean update(SpsInfo spsInfo) {
        boolean resultFlag = false;
        try {
            if (null != spsInfo && null != spsInfo.getId()) {
                resultFlag = spsInfoManager.update(spsInfo);
            } else {
                LOG.warn("SpsInfoServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoService.querySpsInfoList")
    public List<SpsInfo> querySpsInfoList(SpsInfoQuery queryBean) {
        List<SpsInfo> spsInfoList = null;
        try {
            spsInfoList = spsInfoManager.querySpsInfoList(queryBean);
        } catch (Exception e) {
            LOG.error("SpsInfoServiceImpl#querySpsInfoList has error.", e);
        }
        return spsInfoList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoService.querySpsInfoListWithPage")
    public List<SpsInfo> querySpsInfoListWithPage(SpsInfoQuery queryBean, PageUtil pageUtil) {
        List<SpsInfo> spsInfoList = null;
        try {
            spsInfoList = spsInfoManager.querySpsInfoListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("SpsInfoServiceImpl#querySpsInfoListWithPage has error.", e);
        }
        return spsInfoList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoService.delete")
    public boolean delete(SpsInfo spsInfo) {
        boolean resultFlag = false;
        try {
            if (null != spsInfo && null != spsInfo.getId()) {
                resultFlag = spsInfoManager.delete(spsInfo);
            } else {
                LOG.warn("SpsInfoServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoService.batchDelete")
    public boolean delete(SpsInfo[] spsInfos) {
        boolean resultFlag = false;
        try {
            if (null != spsInfos && spsInfos.length > 0) {
                resultFlag = spsInfoManager.delete(spsInfos);
            } else {
                LOG.warn("SpsInfoServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoService.getSpsInfoById")
    public SpsInfo getSpsInfoById(Long id) {
        SpsInfo spsInfo = null;
        try {
            if (null != id) {
                spsInfo = spsInfoManager.getSpsInfoById(id);
            } else {
                LOG.warn("SpsInfoServiceImpl#getSpsInfoById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoServiceImpl#getSpsInfoById has error.", e);
        }
        return spsInfo;
    }


    /**
     * 创建sps、btb数据
     * @param spsInfo
     * @param files
     * @param contents
     * @param userName
     * @param spsBtbMulityDto
     * @return
     */
    public String[] createOrderInfo(SpsInfo spsInfo , List<MultipartFile> files ,String contents , String userName,SpsBtbMulityDto spsBtbMulityDto){
        String[] ret = new String[]{SystemCodeEnum.SUCCESS.getCode(),SystemCodeEnum.SUCCESS.getContent()};
        Map<String,Object> map = new HashMap<String,Object>();

        boolean attrFlag = true ;
        String spsCode =spsInfo.getSpsCode() ;
        String attrRelation =  "SPS-"+spsCode+UUID.randomUUID().toString() ;
        SpsInfoQuery spsInfoQuery = new SpsInfoQuery();
        spsInfoQuery.setSpsCode(spsCode);
        List<SpsInfo>  spsInfoList = spsInfoManager.querySpsInfoList(spsInfoQuery);

        if(!CollectionUtils.isEmpty(spsInfoList)){
            ret[0]=SystemCodeEnum.SPS_CODE_HAVE_EXISTS.getCode();
            ret[1]=SystemCodeEnum.SPS_CODE_HAVE_EXISTS.getContent();
            return ret;
        }
        /**
         * 保存附件
         * 拼装附件结构
         */
        List<SpsLogAttr> list_spsAttr = new ArrayList<SpsLogAttr>();
        if(!org.springframework.util.CollectionUtils.isEmpty(files)){
            String path = PropertiesHelper.newInstance().getValue("sps_attr_addr")+File.separator+spsCode+File.separator;
            SpsLogAttr spsAttr = null ;
            for(MultipartFile file : files){
                String fileName = file.getOriginalFilename();
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存 附件
                try {
                    file.transferTo(targetFile);
                    // 组件附件数据结构
                    spsAttr = new SpsLogAttr(spsCode,fileName,path,fileName,attrRelation,userName);
                    list_spsAttr.add(spsAttr);
                } catch (IOException e) {
                    // 这里出异常了应该把这个文件夹删除，图省事先不删除吧
                    LOG.error("sps新建，保存附件失败",e);
                    attrFlag = false ;
                    break ;
                }
            }
        }

        if(attrFlag){
            /**
             *  拼装sps信息
             */
            spsInfo.setSpsInfoCommonValues(spsInfo,spsCode,userName,Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode()
                    ,Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
            /**
             * 拼装残留量信息
             */
            Integer zhiDingCLL=0 ,  xiuGaiCLL=0 , shanChuCLL=0 ;
            List<Object> list_spsResidualInfo = JsonHelperImpl.jsonFormatArrayToListBean(SpsResidualInfo.class , contents);
            for(Object obj : list_spsResidualInfo){
                SpsResidualInfo spsResidualInfo = (SpsResidualInfo) obj;
                spsResidualInfo.setCreateUser(userName);
                spsResidualInfo.setCreateTime(new Date());
                spsResidualInfo.setUpdateTime(new Date());
                spsResidualInfo.setUpdateUser(userName);
                spsResidualInfo.setSpsCode(spsCode);
                if("1".equals(spsResidualInfo.getUpdateType())){
                    zhiDingCLL++ ;
                }else if("2".equals(spsResidualInfo.getUpdateType())){
                    xiuGaiCLL++ ;
                }else if("3".equals(spsResidualInfo.getUpdateType())){
                    shanChuCLL++;
                }
            }
            /**
             * 拼装sps信息操作日志表
             */
            SpsInfoLog spsInfoLog = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode(),Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode(),Sps_Tbt_InfoStatus.UN_FENPEI.getStatusContent(),
                    attrRelation,1, SystemConstant.YES+"",userName);
            /**
             * 拼装原始语言
             */
            List<RelationSpsLanguage> list_relationSpsLanguage = new ArrayList<RelationSpsLanguage>();
            if(null!=spsBtbMulityDto.getLanguage()){
                for(String language : spsBtbMulityDto.getLanguage()){
                    list_relationSpsLanguage.add(new RelationSpsLanguage(spsCode,language,userName));
                }
            }
            /**
             * 拼装通报类型
             */
            List<String> notificationTypeCodeList = Lists.newArrayList();
            List<String> notificationTypeContentList = Lists.newArrayList();
            List<RelationSpsNotificationType> list_relationSpsNotificationType = new ArrayList<RelationSpsNotificationType>();
            if(null!=spsBtbMulityDto.getNotificationType()){
                for(String notificationType : spsBtbMulityDto.getNotificationType()){
                    list_relationSpsNotificationType.add(new RelationSpsNotificationType(spsCode,notificationType,userName));
                    notificationTypeCodeList.add(notificationType);
                    notificationTypeContentList.add(notificationType);
                }
            }
            /**
             * 拼装目标理由
             */
            List<RelationSpsTargereason> list_relationSpsTargereason = new ArrayList<RelationSpsTargereason>();
            if(null!=spsBtbMulityDto.getTargetReason()){
                for(String relationSpsTargereason : spsBtbMulityDto.getTargetReason()){
                    list_relationSpsTargereason.add(new RelationSpsTargereason(spsCode,relationSpsTargereason,userName));
                }
            }
            /**
             * 拼装国际标准
             */
            List<RelationSpsInternationalStandard> list_relationSpsInternationalStandard = new ArrayList<RelationSpsInternationalStandard>();
            if(null!=spsBtbMulityDto.getInternationalStandard()){
                for(String relationSpsInternationalStandard : spsBtbMulityDto.getInternationalStandard()){
                    list_relationSpsInternationalStandard.add(new RelationSpsInternationalStandard(spsCode,relationSpsInternationalStandard,userName));
                }
            }
            /**
             * 拼装农药相关
             */
            RelationSpsRelationMedicine relationSpsRelationMedicine = new RelationSpsRelationMedicine() ;
            if(!StringUtils.isEmpty(spsBtbMulityDto.getRelationMedicine())){
                relationSpsRelationMedicine = new RelationSpsRelationMedicine(spsCode,spsBtbMulityDto.getRelationMedicine(),userName) ;
            }
            /**
             * 拼装农药商品相关
             */
            RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct(); ;
            if(!StringUtils.isEmpty(spsBtbMulityDto.getRelationMedicineProduct())){
                relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct(spsCode,spsBtbMulityDto.getRelationMedicineProduct(),userName);
            }

            /**
             * 把传递给manager层的数据封装到map中，统一传递
             */
            map.put("list_spsAttr",list_spsAttr);
            map.put("spsInfo",spsInfo);
            map.put("list_spsResidualInfo",list_spsResidualInfo);
            map.put("spsInfoLog",spsInfoLog);
            map.put("list_relationSpsLanguage",list_relationSpsLanguage);
            map.put("list_relationSpsNotificationType",list_relationSpsNotificationType);
            map.put("list_relationSpsTargereason",list_relationSpsTargereason);
            map.put("list_relationSpsInternationalStandard",list_relationSpsInternationalStandard);
            map.put("relationSpsRelationMedicine",relationSpsRelationMedicine);
            map.put("relationSpsRelationMedicineProduct",relationSpsRelationMedicineProduct);
            try{
                boolean result = spsInfoManager.insertOrderInfo(map);
                // 保存成功之后， 创建全文检索  TODO
                try{
                    // 2.通过bean添加document
                    List<SolrDto> solrDtoList = new ArrayList<SolrDto>();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
                    SolrDto solrDto = new SolrDto(spsCode,spsCode, notificationTypeCodeList, notificationTypeContentList, spsInfo.getCountryCode()
                            , spsInfo.getCountryCode(), relationSpsRelationMedicineProduct.getRelationMedicineProductCode(), relationSpsRelationMedicineProduct.getRelationMedicineProductCode()
                            , relationSpsRelationMedicineProduct.getRelationMedicineProductCode(), relationSpsRelationMedicineProduct.getRelationMedicineProductCode(), relationSpsRelationMedicine.getRelationMedicineCode()
                            , relationSpsRelationMedicine.getRelationMedicineCode(), relationSpsRelationMedicine.getRelationMedicineCode(), relationSpsRelationMedicine.getRelationMedicineCode(), formatter.format(spsInfo.getPublishDate())
                            , formatterYear.format(spsInfo.getPublishDate()), spsInfo.getFileTitle(),spsInfo.getContentDes(),zhiDingCLL , xiuGaiCLL , shanChuCLL);
                    solrDtoList.add(solrDto);
                    SolrServiceImpl.addDocumentByBean(solrDtoList,SolrServiceImpl.SOLR_CORE);
                }catch (Exception e){
                    LOG.error("创建sps/tbt信息，创建索引失败:",e);
                }
            }catch (DuplicateKeyException e){
                LOG.error("创建sps信息DuplicateKeyException异常：",e);
                ret[0]=SystemCodeEnum.SPS_CODE_HAVE_EXISTS.getCode();
                ret[1]=SystemCodeEnum.SPS_CODE_HAVE_EXISTS.getContent();
                return ret;
            }catch (Exception e){
                LOG.error("创建sps信息异常：",e);
                ret[0]= String.valueOf(Wrapper.ERROR_CODE);
                ret[1]=Wrapper.ERROR_MESSAGE;
                return ret;
            }
        } else{
            ret[0]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getCode();
            ret[1]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getContent();
            return ret;
        }
        return ret ;
    }
    /**
     * 修改sps、btb数据
     * @param spsInfo
     * @param files
     * @param contents
     * @param userName
     * @param spsBtbMulityDto
     * @return
     */
    public String[] updateOrderInfo(SpsInfo spsInfo , List<MultipartFile> files ,String contents
            , String userName,SpsBtbMulityDto spsBtbMulityDto ,String fileIds){
        String[] ret = new String[]{SystemCodeEnum.SUCCESS.getCode(),SystemCodeEnum.SUCCESS.getContent()};
        Map<String,Object> map = new HashMap<String,Object>();

        boolean attrFlag = true ;
        String spsCode =spsInfo.getSpsCode() ;
        String attrRelation =  "SPS-"+spsCode+UUID.randomUUID().toString() ;
        /**
         * 保存附件
         * 拼装附件结构
         */
        List<SpsLogAttr> list_spsAttr = new ArrayList<SpsLogAttr>();
        if(!org.springframework.util.CollectionUtils.isEmpty(files)){
            String path = PropertiesHelper.newInstance().getValue("sps_attr_addr")+File.separator+spsCode+File.separator;
            SpsLogAttr spsAttr = null ;
            for(MultipartFile file : files){
                String fileName = file.getOriginalFilename();
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存 附件
                try {
                    file.transferTo(targetFile);
                    // 组装附件数据结构
                    spsAttr = new SpsLogAttr(spsCode,fileName,path,fileName,attrRelation,userName);
                    list_spsAttr.add(spsAttr);
                } catch (IOException e) {
                    // 这里出异常了应该把这个文件夹删除，图省事先不删除吧
                    LOG.error("sps新建，保存附件失败",e);
                    attrFlag = false ;
                    break ;
                }
            }
        }
        if(!StringUtils.isEmpty(fileIds)){
            String[] fileId = fileIds.split(",");
            for(String str : fileId){
                SpsLogAttr spsLogAttr = spsLogAttrManager.getSpsLogAttrById(Long.valueOf(str));
                spsLogAttr.setLogAttrRelation(attrRelation);
                list_spsAttr.add(spsLogAttr);
            }
        }

        if(attrFlag){
            /**
             *  拼装sps信息
             */
            spsInfo.setSpsInfoCommonValues(spsInfo,spsCode,userName,Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode(),Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
            /**
             * 拼装残留量信息
             */
            List<Object> list_spsResidualInfo = JsonHelperImpl.jsonFormatArrayToListBean(SpsResidualInfo.class , contents);
            for(Object obj : list_spsResidualInfo){
                SpsResidualInfo spsResidualInfo = (SpsResidualInfo) obj;
                spsResidualInfo.setCreateUser(userName);
                spsResidualInfo.setCreateTime(new Date());
                spsResidualInfo.setUpdateTime(new Date());
                spsResidualInfo.setUpdateUser(userName);
                spsResidualInfo.setSpsCode(spsCode);
            }
            SpsInfoLogQuery spsInfoLogQuery = new SpsInfoLogQuery();
            spsInfoLogQuery.setSpsCode(spsCode);
            spsInfoLogQuery.setOraState(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
            spsInfoLogQuery.setState(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
            SpsInfoLog spsInfoLog = spsInfoLogManager.querySpsInfoLogList(spsInfoLogQuery).get(0);
            spsInfoLog.setLogAttrRelation(attrRelation);
            /**
             * 拼装原始语言
             */
            List<RelationSpsLanguage> list_relationSpsLanguage = new ArrayList<RelationSpsLanguage>();
            if(null!=spsBtbMulityDto.getLanguage()){
                for(String language : spsBtbMulityDto.getLanguage()){
                    list_relationSpsLanguage.add(new RelationSpsLanguage(spsCode,language,userName));
                }
            }
            /**
             * 拼装通报类型
             */
            List<RelationSpsNotificationType> list_relationSpsNotificationType = new ArrayList<RelationSpsNotificationType>();
            if(null!=spsBtbMulityDto.getNotificationType()){
                for(String notificationType : spsBtbMulityDto.getNotificationType()){
                    list_relationSpsNotificationType.add(new RelationSpsNotificationType(spsCode,notificationType,userName));
                }
            }
            /**
             * 拼装目标理由
             */
            List<RelationSpsTargereason> list_relationSpsTargereason = new ArrayList<RelationSpsTargereason>();
            if(null!=spsBtbMulityDto.getTargetReason()){
                for(String relationSpsTargereason : spsBtbMulityDto.getTargetReason()){
                    list_relationSpsTargereason.add(new RelationSpsTargereason(spsCode,relationSpsTargereason,userName));
                }
            }
            /**
             * 拼装国际标准
             */
            List<RelationSpsInternationalStandard> list_relationSpsInternationalStandard = new ArrayList<RelationSpsInternationalStandard>();
            if(null!=spsBtbMulityDto.getInternationalStandard()){
                for(String relationSpsInternationalStandard : spsBtbMulityDto.getInternationalStandard()){
                    list_relationSpsInternationalStandard.add(new RelationSpsInternationalStandard(spsCode,relationSpsInternationalStandard,userName));
                }
            }
            /**
             * 拼装农药相关
             */
            RelationSpsRelationMedicine relationSpsRelationMedicine = null ;
            if(!StringUtils.isEmpty(spsBtbMulityDto.getRelationMedicine())){
                relationSpsRelationMedicine = new RelationSpsRelationMedicine(spsCode,spsBtbMulityDto.getRelationMedicine(),userName) ;
            }
            /**
             * 拼装农药商品相关
             */
            RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = null ;
            if(!StringUtils.isEmpty(spsBtbMulityDto.getRelationMedicineProduct())){
                relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct(spsCode,spsBtbMulityDto.getRelationMedicineProduct(),userName);
            }

            /**
             * 把传递给manager层的数据封装到map中，统一传递
             */
            map.put("list_spsAttr",list_spsAttr);
            map.put("spsInfo",spsInfo);
            map.put("list_spsResidualInfo",list_spsResidualInfo);
            map.put("spsInfoLog",spsInfoLog);
            map.put("list_relationSpsLanguage",list_relationSpsLanguage);
            map.put("list_relationSpsNotificationType",list_relationSpsNotificationType);
            map.put("list_relationSpsTargereason",list_relationSpsTargereason);
            map.put("list_relationSpsInternationalStandard",list_relationSpsInternationalStandard);
            map.put("relationSpsRelationMedicine",relationSpsRelationMedicine);
            map.put("relationSpsRelationMedicineProduct",relationSpsRelationMedicineProduct);
            try{
                boolean result = spsInfoManager.updateOrderInfo(map);
                // 保存成功之后， 创建全文检索  TODO

            }catch (DuplicateKeyException e){
                LOG.error("创建sps信息DuplicateKeyException异常：",e);
                ret[0]=SystemCodeEnum.SPS_CODE_HAVE_EXISTS.getCode();
                ret[1]=SystemCodeEnum.SPS_CODE_HAVE_EXISTS.getContent();
                return ret;
            }catch (Exception e){
                LOG.error("创建sps信息异常：",e);
                ret[0]= String.valueOf(Wrapper.ERROR_CODE);
                ret[1]=Wrapper.ERROR_MESSAGE;
                return ret;
            }
        } else{
            ret[0]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getCode();
            ret[1]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getContent();
            return ret;
        }

        return ret ;
    }

    /**
     * 根据通报成员分组查询通报成员拥有的年份
     * @param queryBean
     * @return
     */
    public List<SpsInfo> queryCountryDateByCountry(SpsInfoQuery queryBean) {
        return spsInfoManager.queryCountryDateByCountry(queryBean);
    }

    /**
     * 下发
     * @param tableContent
     * @return
     */
    public String[] sendNotice(String tableContent,String userName){
        String[] ret = new String[]{SystemCodeEnum.SUCCESS.getCode(),SystemCodeEnum.SUCCESS.getContent()};
        List<Object> list_sendDto = JsonHelperImpl.jsonFormatArrayToListBean(SendDto.class,tableContent);
        if(!CollectionUtils.isEmpty(list_sendDto)){
            /**
             * 对信息进行验证
             */
            for(Object obj :list_sendDto ){
                SendDto sendDto = (SendDto) obj;
                if(StringUtils.isEmpty(sendDto.getExports())){
                    ret[0]=SystemCodeEnum.EXPERTS_CANNOT_NULL.getCode();
                    ret[1]=SystemCodeEnum.EXPERTS_CANNOT_NULL.getContent();
                    return  ret ;
                }
            }

            for(Object obj :list_sendDto ){   // 这里的处理方式是， 每个单子保存一遍 ，如果有一个失败， 则后面的单子不执行
                SendDto sendDto = (SendDto) obj;
                String spsCode = sendDto.getSpsCode();
                // 根据spsCode 查询出来对应的spsInfo信息
                SpsInfoQuery queryBean = new SpsInfoQuery () ;
                queryBean.setSpsCode(spsCode);
                List<SpsInfo> list_spsInfo = spsInfoManager.querySpsInfoList(queryBean) ;
                if(!org.springframework.util.CollectionUtils.isEmpty(list_spsInfo) && list_spsInfo.size()==1){
                    /**
                     * 拼装spsInfo信息
                     */
                    SpsInfo spsInfo = list_spsInfo.get(0);
                    spsInfo.setOraState(Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
                    spsInfo.setState(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode());
                    spsInfo.setSendDate(new Date());
                    spsInfo.setLevels(sendDto.getLevels());
                    spsInfo.setExpertsEndDate(DateHelper.parseDate(sendDto.getExpertsEndDate()));
                    spsInfo.setUpdateTime(new Date());
                    spsInfo.setUpdateUser(userName);
                    /**
                     * 拼装spsInfoLog信息
                     */
                    List<SpsInfoLog> list_spsInfoLog = new ArrayList<SpsInfoLog>();
                    String[] exports = sendDto.getExports().split(",");
                    for(String str : exports){
                        SpsInfoLog spsInfoLog = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode(),Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode(),Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusContent(),
                                "",2, SystemConstant.YES+"",userName);
                        spsInfoLog.setExports(str);
                        list_spsInfoLog.add(spsInfoLog);
                    }
                    try{
                        boolean result = spsInfoManager.updateSpsInfoSend(spsInfo,list_spsInfoLog);
                    }catch (RuntimeException e){
                        LOG.error("下发sps信息失败",e);
                        ret[0]=SystemCodeEnum.SYSTEM_RUNTIME_EXCEPTION.getCode();
                        ret[1]=e.getMessage();
                        break ;
                    }
                } else{
                    ret[0]=SystemCodeEnum.SPS_CODE_NOT_EXISTS.getCode();
                    ret[1]=SystemCodeEnum.SPS_CODE_NOT_EXISTS.getContent();
                }
            }
        }
        return ret ;
    }

    /**
     * 专家评议
     * @param spsInfoLog
     * @param files
     * @param userName
     * @return
     */
    public String[] doReview(SpsInfoLog spsInfoLog , List<MultipartFile> files, String userName){
        String[] ret = new String[]{SystemCodeEnum.SUCCESS.getCode(),SystemCodeEnum.SUCCESS.getContent()};

        boolean attrFlag = true ;
        String spsCode =spsInfoLog.getSpsCode() ;
        String attrRelation =  "SPS-"+spsCode+UUID.randomUUID().toString() ;
        /**
         * 保存附件
         * 拼装附件结构
         */
        List<SpsLogAttr> list_spsAttr = new ArrayList<SpsLogAttr>();
        if(!org.springframework.util.CollectionUtils.isEmpty(files)){
            String path = PropertiesHelper.newInstance().getValue("sps_attr_addr")+File.separator+spsCode+File.separator+userName+File.separator;
            SpsLogAttr spsAttr = null ;
            for(MultipartFile file : files){
                String fileName = file.getOriginalFilename();
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存 附件
                try {
                    file.transferTo(targetFile);
                    // 组件附件数据结构
                    spsAttr = new SpsLogAttr(spsCode,fileName,path,fileName,attrRelation,userName);
                    list_spsAttr.add(spsAttr);
                } catch (IOException e) {
                    // 这里出异常了应该把这个文件夹删除，图省事先不删除吧
                    LOG.error("sps新建，保存附件失败",e);
                    attrFlag = false ;
                    break ;
                }
            }
        }
        if(attrFlag){
            /**
             * 拼装sps信息操作日志表
             */
            SpsInfoLog spsInfoLog_new = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.HAVE_FENPEI_HUIDA.getStatusCode(),Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode(),spsInfoLog.getContent(),
                    attrRelation,1, SystemConstant.YES+"",userName);
            spsInfoLog_new.setOverReview(spsInfoLog.getOverReview());
            spsInfoLog_new.setExports(userName);
            spsInfoLog_new.setFileNameId(spsInfoLog.getFileNameId());
            try{
                boolean result = spsInfoManager.insertDoReivew(spsInfoLog_new,list_spsAttr);
            }catch (RuntimeException e){
                LOG.error("下发sps信息失败",e);
                ret[0]=SystemCodeEnum.SYSTEM_RUNTIME_EXCEPTION.getCode();
                ret[1]=e.getMessage();
            }
        } else{
            ret[0]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getCode();
            ret[1]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getContent();
            return ret;
        }
        return ret ;
    }
    /**
     * 评议汇总保存  通报管理员
     * @param spsInfoLog
     * @param files
     * @param userName
     * @return
     */
    public String[] summaryReview(SpsInfoLog spsInfoLog , List<MultipartFile> files, String userName){
        String[] ret = new String[]{SystemCodeEnum.SUCCESS.getCode(),SystemCodeEnum.SUCCESS.getContent()};

        boolean attrFlag = true ;
        String spsCode =spsInfoLog.getSpsCode() ;
        String attrRelation =  "SPS-"+spsCode+UUID.randomUUID().toString() ;
        /**
         * 保存附件
         * 拼装附件结构
         */
        List<SpsLogAttr> list_spsAttr = new ArrayList<SpsLogAttr>();
        if(!org.springframework.util.CollectionUtils.isEmpty(files)){
            String path = PropertiesHelper.newInstance().getValue("sps_attr_addr")+File.separator+spsCode+File.separator+Sps_Tbt_InfoStatus.HAVE_FENPEI_HUIZONG.getStatusCode()+File.separator;
            SpsLogAttr spsAttr = null ;
            for(MultipartFile file : files){
                String fileName = file.getOriginalFilename();
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存 附件
                try {
                    file.transferTo(targetFile);
                    // 组件附件数据结构
                    spsAttr = new SpsLogAttr(spsCode,fileName,path,fileName,attrRelation,userName);
                    list_spsAttr.add(spsAttr);
                } catch (IOException e) {
                    // 这里出异常了应该把这个文件夹删除，图省事先不删除吧
                    LOG.error("sps新建，保存附件失败",e);
                    attrFlag = false ;
                    break ;
                }
            }
        }
        if(attrFlag){
            /**
             * 拼装sps信息操作日志表
             */
            SpsInfoLog spsInfoLog_new = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.HAVE_FENPEI_HUIZONG.getStatusCode(),Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode(),spsInfoLog.getContent(),
                    attrRelation,1, SystemConstant.YES+"",userName);
            spsInfoLog_new.setOverReview(spsInfoLog.getOverReview());

            try{
                boolean result = spsInfoManager.insertSummaryReview( spsInfoLog_new, list_spsAttr);
            }catch (RuntimeException e){
                LOG.error("评议汇总保存失败",e);
                ret[0]=SystemCodeEnum.SYSTEM_RUNTIME_EXCEPTION.getCode();
                ret[1]=e.getMessage();
            }
        } else{
            ret[0]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getCode();
            ret[1]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getContent();
            return ret;
        }
        return ret ;
    }
    /**
     * 评议汇总提交  通报管理员
     * @param spsInfoLog
     * @param files
     * @param userName
     * @return
     */
    public String[] summaryReviewSubmit(SpsInfoLog spsInfoLog , List<MultipartFile> files, String userName){
        String[] ret = new String[]{SystemCodeEnum.SUCCESS.getCode(),SystemCodeEnum.SUCCESS.getContent()};

        boolean attrFlag = true ;
        String spsCode =spsInfoLog.getSpsCode() ;
        String attrRelation =  "SPS-"+spsCode+UUID.randomUUID().toString() ;
        /**
         * 保存附件
         * 拼装附件结构
         */
        List<SpsLogAttr> list_spsAttr = new ArrayList<SpsLogAttr>();
        if(!org.springframework.util.CollectionUtils.isEmpty(files)){
            String path = PropertiesHelper.newInstance().getValue("sps_attr_addr")+File.separator+spsCode+File.separator+Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode()+File.separator;
            SpsLogAttr spsAttr = null ;
            for(MultipartFile file : files){
                String fileName = file.getOriginalFilename();
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存 附件
                try {
                    file.transferTo(targetFile);
                    // 组件附件数据结构
                    spsAttr = new SpsLogAttr(spsCode,fileName,path,fileName,attrRelation,userName);
                    list_spsAttr.add(spsAttr);
                } catch (IOException e) {
                    // 这里出异常了应该把这个文件夹删除，图省事先不删除吧
                    LOG.error("sps新建，保存附件失败",e);
                    attrFlag = false ;
                    break ;
                }
            }
        }
        if(attrFlag){
            SpsInfoQuery spsInfoQuery = new SpsInfoQuery();
            spsInfoQuery.setSpsCode(spsCode);
            SpsInfo spsInfoResult = spsInfoManager.querySpsInfoList(spsInfoQuery).get(0);
            if(!Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode().equals(spsInfoResult.getState())
                    && !Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode().equals(spsInfoResult.getState())){
                ret[0]=SystemCodeEnum.STATUS_ERROR.getCode();
                ret[1]=SystemCodeEnum.STATUS_ERROR.getContent();
                return ret;
            }
            /**
             * 拼装spsInfo信息
             */
            SpsInfo spsInfo = new SpsInfo();
            spsInfo.setSpsCode(spsCode);
            spsInfo.setOraState(spsInfoResult.getState());
            spsInfo.setState(Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode());
            spsInfo.setUpdateTime(new Date());
            spsInfo.setUpdateUser(userName);
            /**
             * 拼装sps信息操作日志表,这的之前状态可能有问题  TODO
             */
            SpsInfoLog spsInfoLog_new = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode(),Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode(),spsInfoLog.getContent(),
                    attrRelation,1, SystemConstant.YES+"",userName);
            spsInfoLog_new.setOverReview(spsInfoLog.getOverReview());
            spsInfoLog_new.setFileNameId(spsInfoLog.getFileNameId());
            try{
                boolean result = spsInfoManager.insertSummaryReviewSubmit(spsInfo, spsInfoLog_new, list_spsAttr);
            }catch (RuntimeException e){
                LOG.error("评议汇总保存失败",e);
                ret[0]=SystemCodeEnum.SYSTEM_RUNTIME_EXCEPTION.getCode();
                ret[1]=e.getMessage();
            }
        } else{
            ret[0]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getCode();
            ret[1]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getContent();
            return ret;
        }
        return ret ;
    }
    /**
     * 反馈
     * @param spsInfoLog
     * @param files
     * @param userName
     * @return
     */
    public String[] feedBackSubmit(SpsInfoLog spsInfoLog , List<MultipartFile> files, String userName){
        String[] ret = new String[]{SystemCodeEnum.SUCCESS.getCode(),SystemCodeEnum.SUCCESS.getContent()};

        boolean attrFlag = true ;
        String spsCode =spsInfoLog.getSpsCode() ;
        String attrRelation =  "SPS-"+spsCode+UUID.randomUUID().toString() ;
        /**
         * 保存附件
         * 拼装附件结构
         */
        List<SpsLogAttr> list_spsAttr = new ArrayList<SpsLogAttr>();
        if(!org.springframework.util.CollectionUtils.isEmpty(files)){
            String path = PropertiesHelper.newInstance().getValue("sps_attr_addr")+File.separator+spsCode+File.separator+Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusCode()+File.separator;
            SpsLogAttr spsAttr = null ;
            for(MultipartFile file : files){
                String fileName = file.getOriginalFilename();
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                // 保存 附件
                try {
                    file.transferTo(targetFile);
                    // 组件附件数据结构
                    spsAttr = new SpsLogAttr(spsCode,fileName,path,fileName,attrRelation,userName);
                    list_spsAttr.add(spsAttr);
                } catch (IOException e) {
                    // 这里出异常了应该把这个文件夹删除，图省事先不删除吧
                    LOG.error("sps新建，保存附件失败",e);
                    attrFlag = false ;
                    break ;
                }
            }
        }
        if(attrFlag){
            SpsInfoQuery spsInfoQuery = new SpsInfoQuery();
            spsInfoQuery.setSpsCode(spsCode);
            List<SpsInfo> listSpsInfo = spsInfoManager.querySpsInfoList(spsInfoQuery) ;
            SpsInfo spsInfoResult = null ;
            if(!CollectionUtils.isEmpty(listSpsInfo)){
                spsInfoResult = listSpsInfo.get(0);
                if(!Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusCode().equals(spsInfoResult.getState())
                        && !Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode().equals(spsInfoResult.getState())){
                    ret[0]=SystemCodeEnum.STATUS_ERROR.getCode();
                    ret[1]=SystemCodeEnum.STATUS_ERROR.getContent();
                    return ret;
                }
            }
            /**
             * 拼装spsInfo信息
             */
            SpsInfo spsInfo = new SpsInfo();
            spsInfo.setSpsCode(spsCode);
            spsInfo.setOraState(spsInfoResult.getState());
            spsInfo.setState(Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusCode());
            spsInfo.setUpdateTime(new Date());
            spsInfo.setUpdateUser(userName);
            SpsInfoLog spsInfoLog_new = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.HAVE_FANKUI.getStatusCode(),Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode(),spsInfoLog.getContent(),
                    attrRelation,1, SystemConstant.YES+"",userName);
            spsInfoLog_new.setOverReview(spsInfoLog.getOverReview());
            spsInfoLog_new.setFileNameId(spsInfoLog.getFileNameId());
            try{
                boolean result = spsInfoManager.insertFeedBackSubmit(spsInfo, spsInfoLog_new, list_spsAttr);
            }catch (RuntimeException e){
                LOG.error("反馈保存失败",e);
                ret[0]=SystemCodeEnum.SYSTEM_RUNTIME_EXCEPTION.getCode();
                ret[1]=e.getMessage();
            }
        } else{
            ret[0]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getCode();
            ret[1]=SystemCodeEnum.ATTR_UPLOAD_FAIL.getContent();
            return ret;
        }
        return ret ;
    }

    /**
     * 查询专家未评议和已评议的内容
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoListExperts(SpsInfoQuery queryBean ,PageUtil pageUtil){
        return spsInfoManager.querySpsInfoListExperts(queryBean,pageUtil);
    }
    /**
     * 查询专家未评议和已评议的内容
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoExpertsAll(SpsInfoQuery queryBean ,PageUtil pageUtil){
        return spsInfoManager.querySpsInfoExpertsAll(queryBean,pageUtil);
    }

    /**
     * 激活
     * @param tableContent
     * @return
     */
    public String[] jihuo(String tableContent,String userName){
        String[] ret = new String[]{SystemCodeEnum.SUCCESS.getCode(),SystemCodeEnum.SUCCESS.getContent()};
        List<Object> list_jiHuoDto = JsonHelperImpl.jsonFormatArrayToListBean(SendDto.class,tableContent);
        if(!CollectionUtils.isEmpty(list_jiHuoDto)){
            /**
             * 对信息进行验证
             */
            for(Object obj :list_jiHuoDto ){
                SendDto sendDto = (SendDto) obj;
                if(StringUtils.isEmpty(sendDto.getExports())){
                    ret[0]=SystemCodeEnum.EXPERTS_CANNOT_NULL.getCode();
                    ret[1]=SystemCodeEnum.EXPERTS_CANNOT_NULL.getContent();
                    return  ret ;
                }
            }

            for(Object obj :list_jiHuoDto ){   // 这里的处理方式是， 每个单子保存一遍 ，如果有一个失败， 则后面的单子不执行
                SendDto sendDto = (SendDto) obj;
                String spsCode = sendDto.getSpsCode();
                // 根据spsCode 查询出来对应的spsInfo信息
                SpsInfoQuery queryBean = new SpsInfoQuery () ;
                queryBean.setSpsCode(spsCode);
                List<SpsInfo> list_spsInfo = spsInfoManager.querySpsInfoList(queryBean) ;
                if(!org.springframework.util.CollectionUtils.isEmpty(list_spsInfo) && list_spsInfo.size()==1){
                    /**
                     * 拼装spsInfo信息
                     */
                    SpsInfo spsInfo = list_spsInfo.get(0);
                    String oraStatus = spsInfo.getState();
                    // 计算出从下发到评议截止之间的天数
                    int pingYiDays = DateHelperImpl.subDate(spsInfo.getExpertsEndDate(),spsInfo.getSendDate());
                    // 计算出激活之后的专家评议截止日期
                    Date expertsEndDate = DateHelperImpl.addDate(new Date(),pingYiDays);
                    // 修改状态为已经分配
                    spsInfo.setState(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode());
                    spsInfo.setExpertsEndDate(expertsEndDate);
                    spsInfo.setUpdateTime(new Date());
                    spsInfo.setUpdateUser(userName);
                    /**
                     * 拼装spsInfoLog信息
                     */
                    List<SpsInfoLog> list_spsInfoLog = new ArrayList<SpsInfoLog>();
                    String[] exports = sendDto.getExports().split(",");
                    for(String str : exports){
                        SpsInfoLog spsInfoLog = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode(),oraStatus,Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusContent(),
                                "",2, SystemConstant.YES+"",userName);
                        spsInfoLog.setExports(str);
                        list_spsInfoLog.add(spsInfoLog);
                    }
                    try{
                        boolean result = spsInfoManager.updateSpsInfoSend(spsInfo,list_spsInfoLog);
                    }catch (RuntimeException e){
                        LOG.error("激活sps信息失败",e);
                        ret[0]=SystemCodeEnum.SYSTEM_RUNTIME_EXCEPTION.getCode();
                        ret[1]=e.getMessage();
                        break ;
                    }
                } else{
                    ret[0]=SystemCodeEnum.SPS_CODE_NOT_EXISTS.getCode();
                    ret[1]=SystemCodeEnum.SPS_CODE_NOT_EXISTS.getContent();
                }
            }
        }
        return ret ;
    }

    /**
     * solr查询
     * @param solrDtoQuery
     * @param pageUtil
     * @return
     */
    public List<SolrDto> sorlQuery(SolrDtoQuery solrDtoQuery , PageUtil pageUtil) throws Exception {
        String qc = "" ;
        StringBuilder queryCondition = new StringBuilder();
        if (null == solrDtoQuery) {// 如果没有传递条件，则查询所有
            qc = String.valueOf(queryCondition.append("*:*"));
        }else{
            if(StringUtils.isEmpty(solrDtoQuery.getPublishDateYearBegin()) || StringUtils.isEmpty(solrDtoQuery.getPublishDateYearEnd())){
                throw new Exception("请输入开始年和结束年");
            }
            queryCondition.append("( ");
            if(!StringUtils.isEmpty(solrDtoQuery.getSpsCode())){// 通报编码
                queryCondition.append("AND spsCode:");
                queryCondition.append(solrDtoQuery.getSpsCode());
            }
            if(!StringUtils.isEmpty(solrDtoQuery.getNotificationTypeCodeQuery())){// 通报类型
                queryCondition.append(" AND ( ");
                String[] strs = solrDtoQuery.getNotificationTypeCodeQuery().split(",");
                for(int i = 0 ; i< strs.length ; i++){
                    if(i==0){
                        queryCondition.append("notificationTypeCode:");
                        queryCondition.append(strs[i]);
                    }else{
                        queryCondition.append(" OR notificationTypeCode:");
                        queryCondition.append(strs[i]);
                    }
                }
                queryCondition.append(" )");
            }
            if(!StringUtils.isEmpty(solrDtoQuery.getCountryCode())){// 通报成员
                queryCondition.append(" AND ( ");
                String[] strs = solrDtoQuery.getCountryCode().split(",");
                for(int i = 0 ; i< strs.length ; i++){
                    if(i==0){
                        queryCondition.append("countryCode:");
                        queryCondition.append(strs[i]);
                    }else{
                        queryCondition.append(" OR countryCode:");
                        queryCondition.append(strs[i]);
                    }
                }
                queryCondition.append(" )");
            }
            if(!StringUtils.isEmpty(solrDtoQuery.getRelationMedicineProductCode())){// 农产品
                queryCondition.append(" AND ( ");
                String[] strs = solrDtoQuery.getRelationMedicineProductCode().split(",");
                for(int i = 0 ; i< strs.length ; i++){
                    if(i==0){
                        queryCondition.append("relationMedicineProductCode:");
                        queryCondition.append(strs[i]);
                    }else{
                        queryCondition.append(" OR relationMedicineProductCode:");
                        queryCondition.append(strs[i]);
                    }
                }
                queryCondition.append(" )");
            }
            if(!StringUtils.isEmpty(solrDtoQuery.getRelationMedicineCode())){// 农药
                queryCondition.append(" AND ( ");
                String[] strs = solrDtoQuery.getRelationMedicineCode().split(",");
                for(int i = 0 ; i< strs.length ; i++){
                    if(i==0){
                        queryCondition.append("relationMedicineCode:");
                        queryCondition.append(strs[i]);
                    }else{
                        queryCondition.append(" OR relationMedicineCode:");
                        queryCondition.append(strs[i]);
                    }
                }
                queryCondition.append(" )");
            }
            queryCondition.append(" )");
            qc = queryCondition.toString().replaceFirst("AND","");
        }
        // 开始年和结束年是必填的
        String fq = "publishDateYear:["+solrDtoQuery.getPublishDateYearBegin()+" TO "+solrDtoQuery.getPublishDateYearEnd()+"]" ;

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        int totalItem = pageUtil.getTotalRow();
        int start = 0 ;// 从第几条开始检索
        int row = 10 ;// 每次检索多少条
        if(pageUtil.getCurPage()>1){// 说明不是第一页，是翻页查询
             start = pageUtil.getCurPage()*pageUtil.getPageSize();// 计算从哪条开始检索
        }
        Map<String , Object> map = SolrServiceImpl.solrQuery(SolrServiceImpl.SOLR_CORE ,qc,"id", "ASC"
                , start , row, true, "countryCode",fq);
        pageUtil.setTotalRow((Integer) map.get("totalItem"));
        pageUtil.init();
        List<SolrDto> solrDtoList = (List<SolrDto>) map.get("solrDtoList");
        return solrDtoList ;
    }
}

