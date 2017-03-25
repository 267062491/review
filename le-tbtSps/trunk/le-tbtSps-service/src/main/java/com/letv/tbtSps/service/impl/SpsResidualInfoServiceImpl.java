package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.SpsResidualInfo;
import com.letv.tbtSps.domain.query.SpsResidualInfoQuery;
import com.letv.tbtSps.manager.SpsResidualInfoManager;
import com.letv.tbtSps.service.SpsResidualInfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * SpsResidualInfoService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class SpsResidualInfoServiceImpl implements SpsResidualInfoService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(SpsResidualInfoServiceImpl.class);

    @Autowired
    private SpsResidualInfoManager spsResidualInfoManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsResidualInfoService.batchInsert")
    public boolean insert(List<SpsResidualInfo> spsResidualInfoList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(spsResidualInfoList)) {
                resultFlag = spsResidualInfoManager.insert(spsResidualInfoList);
            } else {
                LOG.warn("SpsResidualInfoServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsResidualInfoServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsResidualInfoService.insert")
    public boolean insert(SpsResidualInfo spsResidualInfo) {
        boolean resultFlag = false;
        try {
            if (null != spsResidualInfo) {
                if (spsResidualInfoManager.exist(spsResidualInfo)) {
                    throw new ExistedException();
                }
                resultFlag = spsResidualInfoManager.insert(spsResidualInfo);
            } else {
                LOG.warn("SpsResidualInfoServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("SpsResidualInfoServiceImpl#insert failed, spsResidualInfo has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("SpsResidualInfoServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsResidualInfoService.update")
    public boolean update(SpsResidualInfo spsResidualInfo) {
        boolean resultFlag = false;
        try {
            if (null != spsResidualInfo && null != spsResidualInfo.getId()) {
                resultFlag = spsResidualInfoManager.update(spsResidualInfo);
            } else {
                LOG.warn("SpsResidualInfoServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsResidualInfoServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsResidualInfoService.querySpsResidualInfoList")
    public List<SpsResidualInfo> querySpsResidualInfoList(SpsResidualInfoQuery queryBean) {
        List<SpsResidualInfo> spsResidualInfoList = null;
        try {
            spsResidualInfoList = spsResidualInfoManager.querySpsResidualInfoList(queryBean);
        } catch (Exception e) {
            LOG.error("SpsResidualInfoServiceImpl#querySpsResidualInfoList has error.", e);
        }
        return spsResidualInfoList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsResidualInfoService.querySpsResidualInfoListWithPage")
    public List<SpsResidualInfo> querySpsResidualInfoListWithPage(SpsResidualInfoQuery queryBean, PageUtil pageUtil) {
        List<SpsResidualInfo> spsResidualInfoList = null;
        try {
            spsResidualInfoList = spsResidualInfoManager.querySpsResidualInfoListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("SpsResidualInfoServiceImpl#querySpsResidualInfoListWithPage has error.", e);
        }
        return spsResidualInfoList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsResidualInfoService.delete")
    public boolean delete(SpsResidualInfo spsResidualInfo) {
        boolean resultFlag = false;
        try {
            if (null != spsResidualInfo && null != spsResidualInfo.getId()) {
                resultFlag = spsResidualInfoManager.delete(spsResidualInfo);
            } else {
                LOG.warn("SpsResidualInfoServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsResidualInfoServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsResidualInfoService.batchDelete")
    public boolean delete(SpsResidualInfo[] spsResidualInfos) {
        boolean resultFlag = false;
        try {
            if (null != spsResidualInfos && spsResidualInfos.length > 0) {
                resultFlag = spsResidualInfoManager.delete(spsResidualInfos);
            } else {
                LOG.warn("SpsResidualInfoServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsResidualInfoServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsResidualInfoService.getSpsResidualInfoById")
    public SpsResidualInfo getSpsResidualInfoById(Long id) {
        SpsResidualInfo spsResidualInfo = null;
        try {
            if (null != id) {
                spsResidualInfo = spsResidualInfoManager.getSpsResidualInfoById(id);
            } else {
                LOG.warn("SpsResidualInfoServiceImpl#getSpsResidualInfoById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsResidualInfoServiceImpl#getSpsResidualInfoById has error.", e);
        }
        return spsResidualInfo;
    }
}

