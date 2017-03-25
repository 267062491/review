package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.domain.query.SpsLogAttrQuery;
import com.letv.tbtSps.manager.SpsLogAttrManager;
import com.letv.tbtSps.service.SpsLogAttrService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * SpsLogAttrService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class SpsLogAttrServiceImpl implements SpsLogAttrService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(SpsLogAttrServiceImpl.class);

    @Autowired
    private SpsLogAttrManager spsLogAttrManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsLogAttrService.batchInsert")
    public boolean insert(List<SpsLogAttr> spsLogAttrList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(spsLogAttrList)) {
                resultFlag = spsLogAttrManager.insert(spsLogAttrList);
            } else {
                LOG.warn("SpsLogAttrServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsLogAttrServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsLogAttrService.insert")
    public boolean insert(SpsLogAttr spsLogAttr) {
        boolean resultFlag = false;
        try {
            if (null != spsLogAttr) {
                if (spsLogAttrManager.exist(spsLogAttr)) {
                    throw new ExistedException();
                }
                resultFlag = spsLogAttrManager.insert(spsLogAttr);
            } else {
                LOG.warn("SpsLogAttrServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("SpsLogAttrServiceImpl#insert failed, spsLogAttr has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("SpsLogAttrServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsLogAttrService.update")
    public boolean update(SpsLogAttr spsLogAttr) {
        boolean resultFlag = false;
        try {
            if (null != spsLogAttr && null != spsLogAttr.getId()) {
                resultFlag = spsLogAttrManager.update(spsLogAttr);
            } else {
                LOG.warn("SpsLogAttrServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsLogAttrServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsLogAttrService.querySpsLogAttrList")
    public List<SpsLogAttr> querySpsLogAttrList(SpsLogAttrQuery queryBean) {
        List<SpsLogAttr> spsLogAttrList = null;
        try {
            spsLogAttrList = spsLogAttrManager.querySpsLogAttrList(queryBean);
        } catch (Exception e) {
            LOG.error("SpsLogAttrServiceImpl#querySpsLogAttrList has error.", e);
        }
        return spsLogAttrList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsLogAttrService.querySpsLogAttrListWithPage")
    public List<SpsLogAttr> querySpsLogAttrListWithPage(SpsLogAttrQuery queryBean, PageUtil pageUtil) {
        List<SpsLogAttr> spsLogAttrList = null;
        try {
            spsLogAttrList = spsLogAttrManager.querySpsLogAttrListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("SpsLogAttrServiceImpl#querySpsLogAttrListWithPage has error.", e);
        }
        return spsLogAttrList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsLogAttrService.delete")
    public boolean delete(SpsLogAttr spsLogAttr) {
        boolean resultFlag = false;
        try {
            if (null != spsLogAttr && null != spsLogAttr.getId()) {
                resultFlag = spsLogAttrManager.delete(spsLogAttr);
            } else {
                LOG.warn("SpsLogAttrServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsLogAttrServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsLogAttrService.batchDelete")
    public boolean delete(SpsLogAttr[] spsLogAttrs) {
        boolean resultFlag = false;
        try {
            if (null != spsLogAttrs && spsLogAttrs.length > 0) {
                resultFlag = spsLogAttrManager.delete(spsLogAttrs);
            } else {
                LOG.warn("SpsLogAttrServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsLogAttrServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsLogAttrService.getSpsLogAttrById")
    public SpsLogAttr getSpsLogAttrById(Long id) {
        SpsLogAttr spsLogAttr = null;
        try {
            if (null != id) {
                spsLogAttr = spsLogAttrManager.getSpsLogAttrById(id);
            } else {
                LOG.warn("SpsLogAttrServiceImpl#getSpsLogAttrById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsLogAttrServiceImpl#getSpsLogAttrById has error.", e);
        }
        return spsLogAttr;
    }
}

