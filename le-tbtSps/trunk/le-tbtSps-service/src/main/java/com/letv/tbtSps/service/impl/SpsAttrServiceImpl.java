package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.SpsAttr;
import com.letv.tbtSps.domain.query.SpsAttrQuery;
import com.letv.tbtSps.manager.SpsAttrManager;
import com.letv.tbtSps.service.SpsAttrService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * SpsAttrService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class SpsAttrServiceImpl implements SpsAttrService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(SpsAttrServiceImpl.class);

    @Autowired
    private SpsAttrManager spsAttrManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsAttrService.batchInsert")
    public boolean insert(List<SpsAttr> spsAttrList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(spsAttrList)) {
                resultFlag = spsAttrManager.insert(spsAttrList);
            } else {
                LOG.warn("SpsAttrServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsAttrServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsAttrService.insert")
    public boolean insert(SpsAttr spsAttr) {
        boolean resultFlag = false;
        try {
            if (null != spsAttr) {
                if (spsAttrManager.exist(spsAttr)) {
                    throw new ExistedException();
                }
                resultFlag = spsAttrManager.insert(spsAttr);
            } else {
                LOG.warn("SpsAttrServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("SpsAttrServiceImpl#insert failed, spsAttr has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("SpsAttrServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsAttrService.update")
    public boolean update(SpsAttr spsAttr) {
        boolean resultFlag = false;
        try {
            if (null != spsAttr && null != spsAttr.getId()) {
                resultFlag = spsAttrManager.update(spsAttr);
            } else {
                LOG.warn("SpsAttrServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsAttrServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsAttrService.querySpsAttrList")
    public List<SpsAttr> querySpsAttrList(SpsAttrQuery queryBean) {
        List<SpsAttr> spsAttrList = null;
        try {
            spsAttrList = spsAttrManager.querySpsAttrList(queryBean);
        } catch (Exception e) {
            LOG.error("SpsAttrServiceImpl#querySpsAttrList has error.", e);
        }
        return spsAttrList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsAttrService.querySpsAttrListWithPage")
    public List<SpsAttr> querySpsAttrListWithPage(SpsAttrQuery queryBean, PageUtil pageUtil) {
        List<SpsAttr> spsAttrList = null;
        try {
            spsAttrList = spsAttrManager.querySpsAttrListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("SpsAttrServiceImpl#querySpsAttrListWithPage has error.", e);
        }
        return spsAttrList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsAttrService.delete")
    public boolean delete(SpsAttr spsAttr) {
        boolean resultFlag = false;
        try {
            if (null != spsAttr && null != spsAttr.getId()) {
                resultFlag = spsAttrManager.delete(spsAttr);
            } else {
                LOG.warn("SpsAttrServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsAttrServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsAttrService.batchDelete")
    public boolean delete(SpsAttr[] spsAttrs) {
        boolean resultFlag = false;
        try {
            if (null != spsAttrs && spsAttrs.length > 0) {
                resultFlag = spsAttrManager.delete(spsAttrs);
            } else {
                LOG.warn("SpsAttrServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsAttrServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SpsAttrService.getSpsAttrById")
    public SpsAttr getSpsAttrById(Long id) {
        SpsAttr spsAttr = null;
        try {
            if (null != id) {
                spsAttr = spsAttrManager.getSpsAttrById(id);
            } else {
                LOG.warn("SpsAttrServiceImpl#getSpsAttrById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SpsAttrServiceImpl#getSpsAttrById has error.", e);
        }
        return spsAttr;
    }
}

