package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.TblogAttr;
import com.letv.tbtSps.domain.query.TblogAttrQuery;
import com.letv.tbtSps.manager.TblogAttrManager;
import com.letv.tbtSps.service.TblogAttrService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * TblogAttrService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class TblogAttrServiceImpl implements TblogAttrService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(TblogAttrServiceImpl.class);

    @Autowired
    private TblogAttrManager tblogAttrManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TblogAttrService.batchInsert")
    public boolean insert(List<TblogAttr> tblogAttrList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(tblogAttrList)) {
                resultFlag = tblogAttrManager.insert(tblogAttrList);
            } else {
                LOG.warn("TblogAttrServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TblogAttrServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TblogAttrService.insert")
    public boolean insert(TblogAttr tblogAttr) {
        boolean resultFlag = false;
        try {
            if (null != tblogAttr) {
                if (tblogAttrManager.exist(tblogAttr)) {
                    throw new ExistedException();
                }
                resultFlag = tblogAttrManager.insert(tblogAttr);
            } else {
                LOG.warn("TblogAttrServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("TblogAttrServiceImpl#insert failed, tblogAttr has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("TblogAttrServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TblogAttrService.update")
    public boolean update(TblogAttr tblogAttr) {
        boolean resultFlag = false;
        try {
            if (null != tblogAttr && null != tblogAttr.getId()) {
                resultFlag = tblogAttrManager.update(tblogAttr);
            } else {
                LOG.warn("TblogAttrServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TblogAttrServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TblogAttrService.queryTblogAttrList")
    public List<TblogAttr> queryTblogAttrList(TblogAttrQuery queryBean) {
        List<TblogAttr> tblogAttrList = null;
        try {
            tblogAttrList = tblogAttrManager.queryTblogAttrList(queryBean);
        } catch (Exception e) {
            LOG.error("TblogAttrServiceImpl#queryTblogAttrList has error.", e);
        }
        return tblogAttrList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TblogAttrService.queryTblogAttrListWithPage")
    public List<TblogAttr> queryTblogAttrListWithPage(TblogAttrQuery queryBean, PageUtil pageUtil) {
        List<TblogAttr> tblogAttrList = null;
        try {
            tblogAttrList = tblogAttrManager.queryTblogAttrListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("TblogAttrServiceImpl#queryTblogAttrListWithPage has error.", e);
        }
        return tblogAttrList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TblogAttrService.delete")
    public boolean delete(TblogAttr tblogAttr) {
        boolean resultFlag = false;
        try {
            if (null != tblogAttr && null != tblogAttr.getId()) {
                resultFlag = tblogAttrManager.delete(tblogAttr);
            } else {
                LOG.warn("TblogAttrServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TblogAttrServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TblogAttrService.batchDelete")
    public boolean delete(TblogAttr[] tblogAttrs) {
        boolean resultFlag = false;
        try {
            if (null != tblogAttrs && tblogAttrs.length > 0) {
                resultFlag = tblogAttrManager.delete(tblogAttrs);
            } else {
                LOG.warn("TblogAttrServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TblogAttrServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TblogAttrService.getTblogAttrById")
    public TblogAttr getTblogAttrById(Long id) {
        TblogAttr tblogAttr = null;
        try {
            if (null != id) {
                tblogAttr = tblogAttrManager.getTblogAttrById(id);
            } else {
                LOG.warn("TblogAttrServiceImpl#getTblogAttrById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TblogAttrServiceImpl#getTblogAttrById has error.", e);
        }
        return tblogAttr;
    }
}

