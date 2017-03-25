package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.query.SpsInfoLogQuery;
import com.letv.tbtSps.manager.SpsInfoLogManager;
import com.letv.tbtSps.service.SpsInfoLogService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * SpsInfoLogService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class SpsInfoLogServiceImpl implements SpsInfoLogService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(SpsInfoLogServiceImpl.class);

    @Autowired
    private SpsInfoLogManager spsInfoLogManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoLogService.batchInsert")
    public boolean insert(List<SpsInfoLog> spsInfoLogList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(spsInfoLogList)) {
                resultFlag = spsInfoLogManager.insert(spsInfoLogList);
            } else {
                LOG.warn("SpsInfoLogServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoLogServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoLogService.insert")
    public boolean insert(SpsInfoLog spsInfoLog) {
        boolean resultFlag = false;
        try {
            if (null != spsInfoLog) {
                if (spsInfoLogManager.exist(spsInfoLog)) {
                    throw new ExistedException();
                }
                resultFlag = spsInfoLogManager.insert(spsInfoLog);
            } else {
                LOG.warn("SpsInfoLogServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("SpsInfoLogServiceImpl#insert failed, spsInfoLog has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("SpsInfoLogServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoLogService.update")
    public boolean update(SpsInfoLog spsInfoLog) {
        boolean resultFlag = false;
        try {
            if (null != spsInfoLog && null != spsInfoLog.getId()) {
                resultFlag = spsInfoLogManager.update(spsInfoLog);
            } else {
                LOG.warn("SpsInfoLogServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoLogServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoLogService.querySpsInfoLogList")
    public List<SpsInfoLog> querySpsInfoLogList(SpsInfoLogQuery queryBean) {
        List<SpsInfoLog> spsInfoLogList = null;
        try {
            spsInfoLogList = spsInfoLogManager.querySpsInfoLogList(queryBean);
        } catch (Exception e) {
            LOG.error("SpsInfoLogServiceImpl#querySpsInfoLogList has error.", e);
        }
        return spsInfoLogList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoLogService.querySpsInfoLogListWithPage")
    public List<SpsInfoLog> querySpsInfoLogListWithPage(SpsInfoLogQuery queryBean, PageUtil pageUtil) {
        List<SpsInfoLog> spsInfoLogList = null;
        try {
            spsInfoLogList = spsInfoLogManager.querySpsInfoLogListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("SpsInfoLogServiceImpl#querySpsInfoLogListWithPage has error.", e);
        }
        return spsInfoLogList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoLogService.delete")
    public boolean delete(SpsInfoLog spsInfoLog) {
        boolean resultFlag = false;
        try {
            if (null != spsInfoLog && null != spsInfoLog.getId()) {
                resultFlag = spsInfoLogManager.delete(spsInfoLog);
            } else {
                LOG.warn("SpsInfoLogServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoLogServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoLogService.batchDelete")
    public boolean delete(SpsInfoLog[] spsInfoLogs) {
        boolean resultFlag = false;
        try {
            if (null != spsInfoLogs && spsInfoLogs.length > 0) {
                resultFlag = spsInfoLogManager.delete(spsInfoLogs);
            } else {
                LOG.warn("SpsInfoLogServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoLogServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsInfoLogService.getSpsInfoLogById")
    public SpsInfoLog getSpsInfoLogById(Long id) {
        SpsInfoLog spsInfoLog = null;
        try {
            if (null != id) {
                spsInfoLog = spsInfoLogManager.getSpsInfoLogById(id);
            } else {
                LOG.warn("SpsInfoLogServiceImpl#getSpsInfoLogById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsInfoLogServiceImpl#getSpsInfoLogById has error.", e);
        }
        return spsInfoLog;
    }
}

