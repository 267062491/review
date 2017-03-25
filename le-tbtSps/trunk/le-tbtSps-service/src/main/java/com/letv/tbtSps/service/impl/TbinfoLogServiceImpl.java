package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.TbinfoLog;
import com.letv.tbtSps.domain.query.TbinfoLogQuery;
import com.letv.tbtSps.manager.TbinfoLogManager;
import com.letv.tbtSps.service.TbinfoLogService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * TbinfoLogService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class TbinfoLogServiceImpl implements TbinfoLogService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(TbinfoLogServiceImpl.class);

    @Autowired
    private TbinfoLogManager tbinfoLogManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoLogService.batchInsert")
    public boolean insert(List<TbinfoLog> tbinfoLogList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(tbinfoLogList)) {
                resultFlag = tbinfoLogManager.insert(tbinfoLogList);
            } else {
                LOG.warn("TbinfoLogServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoLogServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoLogService.insert")
    public boolean insert(TbinfoLog tbinfoLog) {
        boolean resultFlag = false;
        try {
            if (null != tbinfoLog) {
                if (tbinfoLogManager.exist(tbinfoLog)) {
                    throw new ExistedException();
                }
                resultFlag = tbinfoLogManager.insert(tbinfoLog);
            } else {
                LOG.warn("TbinfoLogServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("TbinfoLogServiceImpl#insert failed, tbinfoLog has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("TbinfoLogServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoLogService.update")
    public boolean update(TbinfoLog tbinfoLog) {
        boolean resultFlag = false;
        try {
            if (null != tbinfoLog && null != tbinfoLog.getId()) {
                resultFlag = tbinfoLogManager.update(tbinfoLog);
            } else {
                LOG.warn("TbinfoLogServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoLogServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoLogService.queryTbinfoLogList")
    public List<TbinfoLog> queryTbinfoLogList(TbinfoLogQuery queryBean) {
        List<TbinfoLog> tbinfoLogList = null;
        try {
            tbinfoLogList = tbinfoLogManager.queryTbinfoLogList(queryBean);
        } catch (Exception e) {
            LOG.error("TbinfoLogServiceImpl#queryTbinfoLogList has error.", e);
        }
        return tbinfoLogList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoLogService.queryTbinfoLogListWithPage")
    public List<TbinfoLog> queryTbinfoLogListWithPage(TbinfoLogQuery queryBean, PageUtil pageUtil) {
        List<TbinfoLog> tbinfoLogList = null;
        try {
            tbinfoLogList = tbinfoLogManager.queryTbinfoLogListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("TbinfoLogServiceImpl#queryTbinfoLogListWithPage has error.", e);
        }
        return tbinfoLogList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoLogService.delete")
    public boolean delete(TbinfoLog tbinfoLog) {
        boolean resultFlag = false;
        try {
            if (null != tbinfoLog && null != tbinfoLog.getId()) {
                resultFlag = tbinfoLogManager.delete(tbinfoLog);
            } else {
                LOG.warn("TbinfoLogServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoLogServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoLogService.batchDelete")
    public boolean delete(TbinfoLog[] tbinfoLogs) {
        boolean resultFlag = false;
        try {
            if (null != tbinfoLogs && tbinfoLogs.length > 0) {
                resultFlag = tbinfoLogManager.delete(tbinfoLogs);
            } else {
                LOG.warn("TbinfoLogServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoLogServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoLogService.getTbinfoLogById")
    public TbinfoLog getTbinfoLogById(Long id) {
        TbinfoLog tbinfoLog = null;
        try {
            if (null != id) {
                tbinfoLog = tbinfoLogManager.getTbinfoLogById(id);
            } else {
                LOG.warn("TbinfoLogServiceImpl#getTbinfoLogById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoLogServiceImpl#getTbinfoLogById has error.", e);
        }
        return tbinfoLog;
    }
}

