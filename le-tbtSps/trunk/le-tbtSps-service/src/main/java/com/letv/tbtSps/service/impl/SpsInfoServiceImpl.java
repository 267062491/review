package com.letv.tbtSps.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.letv.common.utils.DateHelper;
import com.letv.common.utils.config.PropertiesHelper;
import com.letv.tbtSps.domain.*;
import com.letv.tbtSps.domain.dto.SendDto;
import com.letv.tbtSps.domain.dto.SpsBtbMulityDto;
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
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
import org.springframework.util.CollectionUtils;
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
            spsInfo.setSpsInfoCommonValues(spsInfo,spsCode,userName,Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode(),Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode());
            /**
             * 拼装残留量信息
             */
            List<Object> list_spsResidualInfo = JsonHelperImpl.jsonFormatArrayToListBean(SpsResidualInfo.class , contents);
            /**
             * 拼装sps信息操作日志表
             */
            SpsInfoLog spsInfoLog = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode(),Sps_Tbt_InfoStatus.UN_FENPEI.getStatusCode(),Sps_Tbt_InfoStatus.UN_FENPEI.getStatusContent(),
                    attrRelation,1, SystemConstant.YES+"",userName);
            /**
             * 拼装原始语言
             */
            List<RelationSpsLanguage> list_relationSpsLanguage = new ArrayList<RelationSpsLanguage>();
            for(String language : spsBtbMulityDto.getLanguage()){
                list_relationSpsLanguage.add(new RelationSpsLanguage(spsCode,language,userName));
            }
            /**
             * 拼装通报类型
             */
            List<RelationSpsNotificationType> list_relationSpsNotificationType = new ArrayList<RelationSpsNotificationType>();
            for(String notificationType : spsBtbMulityDto.getNotificationType()){
                list_relationSpsNotificationType.add(new RelationSpsNotificationType(spsCode,notificationType,userName));
            }
            /**
             * 拼装目标理由
             */
            List<RelationSpsTargereason> list_relationSpsTargereason = new ArrayList<RelationSpsTargereason>();
            for(String relationSpsTargereason : spsBtbMulityDto.getLanguage()){
                list_relationSpsTargereason.add(new RelationSpsTargereason(spsCode,relationSpsTargereason,userName));
            }
            /**
             * 拼装国际标准
             */
            List<RelationSpsInternationalStandard> list_relationSpsInternationalStandard = new ArrayList<RelationSpsInternationalStandard>();
            for(String relationSpsInternationalStandard : spsBtbMulityDto.getInternationalStandard()){
                list_relationSpsInternationalStandard.add(new RelationSpsInternationalStandard(spsCode,relationSpsInternationalStandard,userName));
            }
            /**
             * 拼装农药相关
             */
            RelationSpsRelationMedicine relationSpsRelationMedicine = new RelationSpsRelationMedicine(spsCode,spsBtbMulityDto.getRelationMedicine(),userName) ;
            /**
             * 拼装农药商品相关
             */
            RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct(spsCode,spsBtbMulityDto.getRelationMedicineProduct(),userName);

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

            }catch (MySQLIntegrityConstraintViolationException e){
                if(e.getMessage().contains("Duplicate")){
                    ret[0]=SystemCodeEnum.SPS_CODE_HAVE_EXISTS.getCode();
                    ret[1]=SystemCodeEnum.SPS_CODE_HAVE_EXISTS.getContent();
                }else{

                }
                return ret;
            }catch (Exception e){
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
            /**
             * 拼装spsInfo信息
             */
            SpsInfo spsInfo = new SpsInfo();
            spsInfo.setOraState(Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode());
            spsInfo.setState(Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode());
            spsInfo.setUpdateTime(new Date());
            spsInfo.setUpdateUser(userName);
            /**
             * 拼装sps信息操作日志表,这的之前状态可能有问题  TODO
             */
            SpsInfoLog spsInfoLog_new = new SpsInfoLog(spsCode, Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode(),Sps_Tbt_InfoStatus.HAVE_FENPEI.getStatusCode(),spsInfoLog.getContent(),
                    attrRelation,1, SystemConstant.YES+"",userName);
            spsInfoLog_new.setOverReview(spsInfoLog.getOverReview());

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
}

