package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.TbresidualInfo;
import com.letv.tbtSps.domain.query.TbresidualInfoQuery;
import com.letv.tbtSps.manager.TbresidualInfoManager;
import com.letv.tbtSps.service.TbresidualInfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * TbresidualInfoService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class TbresidualInfoServiceImpl implements TbresidualInfoService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(TbresidualInfoServiceImpl.class);

    @Autowired
    private TbresidualInfoManager tbresidualInfoManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbresidualInfoService.batchInsert")
    public boolean insert(List<TbresidualInfo> tbresidualInfoList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(tbresidualInfoList)) {
                resultFlag = tbresidualInfoManager.insert(tbresidualInfoList);
            } else {
                LOG.warn("TbresidualInfoServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbresidualInfoServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbresidualInfoService.insert")
    public boolean insert(TbresidualInfo tbresidualInfo) {
        boolean resultFlag = false;
        try {
            if (null != tbresidualInfo) {
                if (tbresidualInfoManager.exist(tbresidualInfo)) {
                    throw new ExistedException();
                }
                resultFlag = tbresidualInfoManager.insert(tbresidualInfo);
            } else {
                LOG.warn("TbresidualInfoServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("TbresidualInfoServiceImpl#insert failed, tbresidualInfo has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("TbresidualInfoServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbresidualInfoService.update")
    public boolean update(TbresidualInfo tbresidualInfo) {
        boolean resultFlag = false;
        try {
            if (null != tbresidualInfo && null != tbresidualInfo.getId()) {
                resultFlag = tbresidualInfoManager.update(tbresidualInfo);
            } else {
                LOG.warn("TbresidualInfoServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbresidualInfoServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbresidualInfoService.queryTbresidualInfoList")
    public List<TbresidualInfo> queryTbresidualInfoList(TbresidualInfoQuery queryBean) {
        List<TbresidualInfo> tbresidualInfoList = null;
        try {
            tbresidualInfoList = tbresidualInfoManager.queryTbresidualInfoList(queryBean);
        } catch (Exception e) {
            LOG.error("TbresidualInfoServiceImpl#queryTbresidualInfoList has error.", e);
        }
        return tbresidualInfoList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbresidualInfoService.queryTbresidualInfoListWithPage")
    public List<TbresidualInfo> queryTbresidualInfoListWithPage(TbresidualInfoQuery queryBean, PageUtil pageUtil) {
        List<TbresidualInfo> tbresidualInfoList = null;
        try {
            tbresidualInfoList = tbresidualInfoManager.queryTbresidualInfoListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("TbresidualInfoServiceImpl#queryTbresidualInfoListWithPage has error.", e);
        }
        return tbresidualInfoList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbresidualInfoService.delete")
    public boolean delete(TbresidualInfo tbresidualInfo) {
        boolean resultFlag = false;
        try {
            if (null != tbresidualInfo && null != tbresidualInfo.getId()) {
                resultFlag = tbresidualInfoManager.delete(tbresidualInfo);
            } else {
                LOG.warn("TbresidualInfoServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbresidualInfoServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbresidualInfoService.batchDelete")
    public boolean delete(TbresidualInfo[] tbresidualInfos) {
        boolean resultFlag = false;
        try {
            if (null != tbresidualInfos && tbresidualInfos.length > 0) {
                resultFlag = tbresidualInfoManager.delete(tbresidualInfos);
            } else {
                LOG.warn("TbresidualInfoServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbresidualInfoServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbresidualInfoService.getTbresidualInfoById")
    public TbresidualInfo getTbresidualInfoById(Long id) {
        TbresidualInfo tbresidualInfo = null;
        try {
            if (null != id) {
                tbresidualInfo = tbresidualInfoManager.getTbresidualInfoById(id);
            } else {
                LOG.warn("TbresidualInfoServiceImpl#getTbresidualInfoById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbresidualInfoServiceImpl#getTbresidualInfoById has error.", e);
        }
        return tbresidualInfo;
    }
}

