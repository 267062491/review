package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.tbtSps.manager.SpsInfoManager;
import com.letv.tbtSps.service.SpsInfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
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
            if (CollectionUtils.isNotEmpty(spsInfoList)) {
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
}

