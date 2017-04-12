package com.letv.tbtSps.manager.impl;

import java.util.List;
import java.util.Map;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.DateHelper;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.dao.*;
import com.letv.tbtSps.domain.*;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.tbtSps.manager.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SpsInfoManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class SpsInfoManagerImpl extends BaseManager implements SpsInfoManager {
	
    @Autowired
    private SpsInfoDao spsInfoDao;
    @Autowired
    private SpsInfoLogDao spsInfoLogDao;
    @Autowired
    private SpsLogAttrDao spsLogAttrDao;
    @Autowired
    private SpsResidualInfoDao spsResidualInfoDao ;
    @Autowired
    private RelationSpsLanguageDao relationspslanguageDao;
    @Autowired
    private RelationSpsNotificationTypeDao relationSpsNotificationTypeDao;
    @Autowired
    private RelationSpsTargereasonDao relationSpsTargereasonDao;
    @Autowired
    private RelationSpsInternationalStandardDao relationSpsInternationalStandardDao;
    @Autowired
    private RelationSpsRelationMedicineDao relationSpsRelationMedicineDao;
    @Autowired
    private RelationSpsRelationMedicineProductDao relationSpsRelationMedicineProductDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<SpsInfo> spsInfoList) {
        boolean resultFlag = false;
        if (null != spsInfoList && spsInfoList.size() > 0) {
            for (SpsInfo spsInfo : spsInfoList) {
                resultFlag = spsInfoDao.insert(spsInfo);
                if (!resultFlag) {
                    throw new RuntimeException("批量新增表信息异常");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(SpsInfo spsInfo) {
        return spsInfoDao.insert(spsInfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final SpsInfo spsInfo) {
        return spsInfoDao.update(spsInfo);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsInfo> querySpsInfoList(SpsInfoQuery queryBean) {
        return spsInfoDao.querySpsInfoList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsInfo> querySpsInfoListWithPage(SpsInfoQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new SpsInfoQuery();
        }

        // 查询总数
        int totalItem = querySpsInfoCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return spsInfoDao.querySpsInfoListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsInfoCount(SpsInfoQuery queryBean) {
        return spsInfoDao.querySpsInfoCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsInfo spsInfo) {
        return spsInfoDao.delete(spsInfo);
    }

    /**
     * {@inheritDoc}
     */
    public SpsInfo getSpsInfoById(Long id) {
        return spsInfoDao.getSpsInfoById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final SpsInfo[] spsInfos) {
        boolean resultFlag = false;
        if (null != spsInfos && spsInfos.length > 0) {
            for (int i = 0; i < spsInfos.length; i++) {
                resultFlag = delete(spsInfos[i]);
                if (!resultFlag) {
                    throw new RuntimeException("批量删除表信息异常!");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean exist(SpsInfo spsInfo) {
        return spsInfoDao.exist(spsInfo);
    }
    /**
     * 创建sps、tbt
     * @param map
     */
    public boolean insertOrderInfo(Map<String, Object> map) {
        /**
         * 解析出需要保存的内容
         */
        SpsInfo spsInfo = (SpsInfo) map.get("spsInfo"); // sps 信息
        List<SpsLogAttr> list_spsAttr = (List<SpsLogAttr>) map.get("list_spsAttr"); // 附件
        List<Object> list_spsResidualInfo = (List<Object>) map.get("list_spsResidualInfo"); // 农药残留
        SpsInfoLog spsInfoLog = (SpsInfoLog) map.get("spsInfoLog"); // 操作日志
        List<RelationSpsLanguage> list_relationSpsLanguage = (List<RelationSpsLanguage>) map.get("list_relationSpsLanguage"); // 原始语言
        List<RelationSpsNotificationType> list_relationSpsNotificationType = (List<RelationSpsNotificationType>) map.get("list_relationSpsNotificationType");// 通报类型
        List<RelationSpsTargereason> list_relationSpsTargereason = (List<RelationSpsTargereason>) map.get("list_relationSpsTargereason");// 目标理由
        List<RelationSpsInternationalStandard> list_relationSpsInternationalStandard = (List<RelationSpsInternationalStandard>) map.get("list_relationSpsInternationalStandard");// 国际标准
        RelationSpsRelationMedicine relationSpsRelationMedicine = (RelationSpsRelationMedicine) map.get("relationSpsRelationMedicine"); // 农药信息
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = (RelationSpsRelationMedicineProduct) map.get("relationSpsRelationMedicineProduct");// 农产品信息

        /**
         *保存到数据库
         */

        spsInfoDao.insert(spsInfo);
        for(SpsLogAttr spsLogAttr : list_spsAttr){
            spsLogAttrDao.insert(spsLogAttr);
        }
        for(Object obj : list_spsResidualInfo){
            SpsResidualInfo spsResidualInfo = (SpsResidualInfo) obj;
            spsResidualInfo.setEndDate(DateHelper.parseDate(spsResidualInfo.getEndDateIn()));// 这个转换主要是jsonHelper.toBean 方法， 对date类型不支持
            spsResidualInfoDao.insert(spsResidualInfo);
        }
        spsInfoLogDao.insert(spsInfoLog);
        for(RelationSpsLanguage relationSpsLanguage : list_relationSpsLanguage){
            relationspslanguageDao.insert(relationSpsLanguage);
        }
        for(RelationSpsNotificationType relationSpsNotificationType : list_relationSpsNotificationType){
            relationSpsNotificationTypeDao.insert(relationSpsNotificationType);
        }
        for(RelationSpsTargereason relationSpsTargereason : list_relationSpsTargereason){
            relationSpsTargereasonDao.insert(relationSpsTargereason);
        }
        for(RelationSpsInternationalStandard relationSpsInternationalStandard : list_relationSpsInternationalStandard){
            relationSpsInternationalStandardDao.insert(relationSpsInternationalStandard);
        }
        relationSpsRelationMedicineDao.insert(relationSpsRelationMedicine);
        relationSpsRelationMedicineProductDao.insert(relationSpsRelationMedicineProduct);
        return true;
    }
    /**
     * 根据通报成员分组查询通报成员拥有的年份
     * @param queryBean
     * @return
     */
    public List<SpsInfo> queryCountryDateByCountry(SpsInfoQuery queryBean) {
        return spsInfoDao.queryCountryDateByCountry(queryBean);
    }

    /**
     * 根据spsCode 修改spsInfo
     * @param spsInfo
     * @return
     */
    public boolean updateSpsInfoBySpsCode(SpsInfo spsInfo) {
        return spsInfoDao.updateSpsInfoBySpsCode(spsInfo);
    }

    /**
     * 下发任务
     * @param spsInfo
     * @return
     */
    public boolean updateSpsInfoSend(SpsInfo spsInfo, List<SpsInfoLog> list_spsInfoLog){
        boolean relult_temp1 = spsInfoDao.updateSpsInfoBySpsCode(spsInfo);
        if(relult_temp1){
            boolean result_temp2 = true ;
            for(SpsInfoLog spsInfoLog : list_spsInfoLog){
                result_temp2 = spsInfoLogDao.insert(spsInfoLog) ;
                if(!result_temp2){
                    throw new RuntimeException("添加信息spsInfoLog信息失败") ;
                }
            }
        }else{
            throw new RuntimeException("修改spsInfo信息失败") ;
        }
        return true ;
    }

    /**
     * 专家评议 TODO 这里有个漏洞， 没有判断之前的状态是已下发
     * @param spsInfoLog
     * @param list_spsAttr
     * @return
     */
    public boolean insertDoReivew(SpsInfoLog spsInfoLog, List<SpsLogAttr> list_spsAttr){
        boolean temp1 = spsInfoLogDao.insert(spsInfoLog) ;
        boolean temp2 = true ;
        if(temp1){
            for(SpsLogAttr spsLogAttr : list_spsAttr){
                temp2 = spsLogAttrDao.insert(spsLogAttr);
                if(!temp2){
                    throw new RuntimeException("添加信息spsInfo附件信息失败") ;
                }
            }
        } else{
            throw new RuntimeException("添加信息spsInfoLog信息失败") ;
        }
        return true ;
    }

    /**
     * 评议汇总保存
     * @param spsInfoLog
     * @param list_spsAttr
     * @return
     */
    public boolean insertSummaryReview(SpsInfoLog spsInfoLog, List<SpsLogAttr> list_spsAttr){
        boolean temp1 = spsInfoLogDao.insert(spsInfoLog) ;
        boolean temp2 = true ;
        if(temp1){
            for(SpsLogAttr spsLogAttr : list_spsAttr){
                temp2 = spsLogAttrDao.insert(spsLogAttr);
                if(!temp2){
                    throw new RuntimeException("添加信息spsInfo附件信息失败") ;
                }
            }
        } else{
            throw new RuntimeException("添加信息spsInfoLog信息失败") ;
        }
        return true ;
    }
    /**
     * 评议汇总提交  这要在保存的基础上，看看有没有保存的附件， 如果有，把保存的附件和信息拿过来 ； 还要看看是不是有新传入的附件  TODO 这个还得慢慢写
     * @param spsInfo
     * @param spsInfoLog
     * @param list_spsAttr
     * @return
     */
    public boolean insertSummaryReviewSubmit(SpsInfo spsInfo ,SpsInfoLog spsInfoLog, List<SpsLogAttr> list_spsAttr){
        boolean temp = spsInfoDao.updateSpsStateBySpsCode(spsInfo) ;
        if(temp){
            boolean temp1 = spsInfoLogDao.insert(spsInfoLog) ;
            spsInfoLogDao.updateCanEditBySpsCode(spsInfoLog);
            boolean temp2 = true ;
            if(temp1){
                for(SpsLogAttr spsLogAttr : list_spsAttr){
                    temp2 = spsLogAttrDao.insert(spsLogAttr);
                    if(!temp2){
                        throw new RuntimeException("添加信息spsInfo附件信息失败") ;
                    }
                }
            } else{
                throw new RuntimeException("添加信息spsInfoLog信息失败") ;
            }
        }else{
            throw new RuntimeException("修改spsInfo状态失败") ;
        }

        return true ;
    }

}
