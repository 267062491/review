package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.Tbattr;
import com.letv.tbtSps.domain.query.TbattrQuery;
import com.letv.tbtSps.manager.TbattrManager;
import com.letv.tbtSps.service.TbattrService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * TbattrService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class TbattrServiceImpl implements TbattrService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(TbattrServiceImpl.class);

    @Autowired
    private TbattrManager tbattrManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbattrService.batchInsert")
    public boolean insert(List<Tbattr> tbattrList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(tbattrList)) {
                resultFlag = tbattrManager.insert(tbattrList);
            } else {
                LOG.warn("TbattrServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbattrServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbattrService.insert")
    public boolean insert(Tbattr tbattr) {
        boolean resultFlag = false;
        try {
            if (null != tbattr) {
                if (tbattrManager.exist(tbattr)) {
                    throw new ExistedException();
                }
                resultFlag = tbattrManager.insert(tbattr);
            } else {
                LOG.warn("TbattrServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("TbattrServiceImpl#insert failed, tbattr has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("TbattrServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbattrService.update")
    public boolean update(Tbattr tbattr) {
        boolean resultFlag = false;
        try {
            if (null != tbattr && null != tbattr.getId()) {
                resultFlag = tbattrManager.update(tbattr);
            } else {
                LOG.warn("TbattrServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbattrServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbattrService.queryTbattrList")
    public List<Tbattr> queryTbattrList(TbattrQuery queryBean) {
        List<Tbattr> tbattrList = null;
        try {
            tbattrList = tbattrManager.queryTbattrList(queryBean);
        } catch (Exception e) {
            LOG.error("TbattrServiceImpl#queryTbattrList has error.", e);
        }
        return tbattrList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbattrService.queryTbattrListWithPage")
    public List<Tbattr> queryTbattrListWithPage(TbattrQuery queryBean, PageUtil pageUtil) {
        List<Tbattr> tbattrList = null;
        try {
            tbattrList = tbattrManager.queryTbattrListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("TbattrServiceImpl#queryTbattrListWithPage has error.", e);
        }
        return tbattrList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbattrService.delete")
    public boolean delete(Tbattr tbattr) {
        boolean resultFlag = false;
        try {
            if (null != tbattr && null != tbattr.getId()) {
                resultFlag = tbattrManager.delete(tbattr);
            } else {
                LOG.warn("TbattrServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbattrServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbattrService.batchDelete")
    public boolean delete(Tbattr[] tbattrs) {
        boolean resultFlag = false;
        try {
            if (null != tbattrs && tbattrs.length > 0) {
                resultFlag = tbattrManager.delete(tbattrs);
            } else {
                LOG.warn("TbattrServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbattrServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbattrService.getTbattrById")
    public Tbattr getTbattrById(Long id) {
        Tbattr tbattr = null;
        try {
            if (null != id) {
                tbattr = tbattrManager.getTbattrById(id);
            } else {
                LOG.warn("TbattrServiceImpl#getTbattrById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbattrServiceImpl#getTbattrById has error.", e);
        }
        return tbattr;
    }
}

