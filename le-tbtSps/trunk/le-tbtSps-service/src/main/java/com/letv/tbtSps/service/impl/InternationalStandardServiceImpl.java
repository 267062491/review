package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.InternationalStandard;
import com.letv.tbtSps.domain.query.InternationalStandardQuery;
import com.letv.tbtSps.manager.InternationalStandardManager;
import com.letv.tbtSps.service.InternationalStandardService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * InternationalStandardService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class InternationalStandardServiceImpl implements InternationalStandardService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(InternationalStandardServiceImpl.class);

    @Autowired
    private InternationalStandardManager internationalStandardManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "InternationalStandardService.batchInsert")
    public boolean insert(List<InternationalStandard> internationalStandardList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(internationalStandardList)) {
                resultFlag = internationalStandardManager.insert(internationalStandardList);
            } else {
                LOG.warn("InternationalStandardServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("InternationalStandardServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "InternationalStandardService.insert")
    public boolean insert(InternationalStandard internationalStandard) {
        boolean resultFlag = false;
        try {
            if (null != internationalStandard) {
                if (internationalStandardManager.exist(internationalStandard)) {
                    throw new ExistedException();
                }
                resultFlag = internationalStandardManager.insert(internationalStandard);
            } else {
                LOG.warn("InternationalStandardServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("InternationalStandardServiceImpl#insert failed, internationalStandard has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("InternationalStandardServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "InternationalStandardService.update")
    public boolean update(InternationalStandard internationalStandard) {
        boolean resultFlag = false;
        try {
            if (null != internationalStandard && null != internationalStandard.getId()) {
                resultFlag = internationalStandardManager.update(internationalStandard);
            } else {
                LOG.warn("InternationalStandardServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("InternationalStandardServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "InternationalStandardService.queryInternationalStandardList")
    public List<InternationalStandard> queryInternationalStandardList(InternationalStandardQuery queryBean) {
        List<InternationalStandard> internationalStandardList = null;
        try {
            internationalStandardList = internationalStandardManager.queryInternationalStandardList(queryBean);
        } catch (Exception e) {
            LOG.error("InternationalStandardServiceImpl#queryInternationalStandardList has error.", e);
        }
        return internationalStandardList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "InternationalStandardService.queryInternationalStandardListWithPage")
    public List<InternationalStandard> queryInternationalStandardListWithPage(InternationalStandardQuery queryBean, PageUtil pageUtil) {
        List<InternationalStandard> internationalStandardList = null;
        try {
            internationalStandardList = internationalStandardManager.queryInternationalStandardListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("InternationalStandardServiceImpl#queryInternationalStandardListWithPage has error.", e);
        }
        return internationalStandardList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "InternationalStandardService.delete")
    public boolean delete(InternationalStandard internationalStandard) {
        boolean resultFlag = false;
        try {
            if (null != internationalStandard && null != internationalStandard.getId()) {
                resultFlag = internationalStandardManager.delete(internationalStandard);
            } else {
                LOG.warn("InternationalStandardServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("InternationalStandardServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "InternationalStandardService.batchDelete")
    public boolean delete(InternationalStandard[] internationalStandards) {
        boolean resultFlag = false;
        try {
            if (null != internationalStandards && internationalStandards.length > 0) {
                resultFlag = internationalStandardManager.delete(internationalStandards);
            } else {
                LOG.warn("InternationalStandardServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("InternationalStandardServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "InternationalStandardService.getInternationalStandardById")
    public InternationalStandard getInternationalStandardById(Long id) {
        InternationalStandard internationalStandard = null;
        try {
            if (null != id) {
                internationalStandard = internationalStandardManager.getInternationalStandardById(id);
            } else {
                LOG.warn("InternationalStandardServiceImpl#getInternationalStandardById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("InternationalStandardServiceImpl#getInternationalStandardById has error.", e);
        }
        return internationalStandard;
    }
}

