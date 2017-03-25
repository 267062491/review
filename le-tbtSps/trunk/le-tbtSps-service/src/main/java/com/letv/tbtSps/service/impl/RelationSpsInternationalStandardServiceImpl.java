package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationSpsInternationalStandard;
import com.letv.tbtSps.domain.query.RelationSpsInternationalStandardQuery;
import com.letv.tbtSps.manager.RelationSpsInternationalStandardManager;
import com.letv.tbtSps.service.RelationSpsInternationalStandardService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationSpsInternationalStandardService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationSpsInternationalStandardServiceImpl implements RelationSpsInternationalStandardService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationSpsInternationalStandardServiceImpl.class);

    @Autowired
    private RelationSpsInternationalStandardManager relationSpsInternationalStandardManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsInternationalStandardService.batchInsert")
    public boolean insert(List<RelationSpsInternationalStandard> relationSpsInternationalStandardList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationSpsInternationalStandardList)) {
                resultFlag = relationSpsInternationalStandardManager.insert(relationSpsInternationalStandardList);
            } else {
                LOG.warn("RelationSpsInternationalStandardServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsInternationalStandardServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsInternationalStandardService.insert")
    public boolean insert(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsInternationalStandard) {
                if (relationSpsInternationalStandardManager.exist(relationSpsInternationalStandard)) {
                    throw new ExistedException();
                }
                resultFlag = relationSpsInternationalStandardManager.insert(relationSpsInternationalStandard);
            } else {
                LOG.warn("RelationSpsInternationalStandardServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationSpsInternationalStandardServiceImpl#insert failed, relationSpsInternationalStandard has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationSpsInternationalStandardServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsInternationalStandardService.update")
    public boolean update(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsInternationalStandard && null != relationSpsInternationalStandard.getId()) {
                resultFlag = relationSpsInternationalStandardManager.update(relationSpsInternationalStandard);
            } else {
                LOG.warn("RelationSpsInternationalStandardServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsInternationalStandardServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsInternationalStandardService.queryRelationSpsInternationalStandardList")
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardList(RelationSpsInternationalStandardQuery queryBean) {
        List<RelationSpsInternationalStandard> relationSpsInternationalStandardList = null;
        try {
            relationSpsInternationalStandardList = relationSpsInternationalStandardManager.queryRelationSpsInternationalStandardList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationSpsInternationalStandardServiceImpl#queryRelationSpsInternationalStandardList has error.", e);
        }
        return relationSpsInternationalStandardList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsInternationalStandardService.queryRelationSpsInternationalStandardListWithPage")
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardListWithPage(RelationSpsInternationalStandardQuery queryBean, PageUtil pageUtil) {
        List<RelationSpsInternationalStandard> relationSpsInternationalStandardList = null;
        try {
            relationSpsInternationalStandardList = relationSpsInternationalStandardManager.queryRelationSpsInternationalStandardListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationSpsInternationalStandardServiceImpl#queryRelationSpsInternationalStandardListWithPage has error.", e);
        }
        return relationSpsInternationalStandardList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsInternationalStandardService.delete")
    public boolean delete(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsInternationalStandard && null != relationSpsInternationalStandard.getId()) {
                resultFlag = relationSpsInternationalStandardManager.delete(relationSpsInternationalStandard);
            } else {
                LOG.warn("RelationSpsInternationalStandardServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsInternationalStandardServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsInternationalStandardService.batchDelete")
    public boolean delete(RelationSpsInternationalStandard[] relationSpsInternationalStandards) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsInternationalStandards && relationSpsInternationalStandards.length > 0) {
                resultFlag = relationSpsInternationalStandardManager.delete(relationSpsInternationalStandards);
            } else {
                LOG.warn("RelationSpsInternationalStandardServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsInternationalStandardServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsInternationalStandardService.getRelationSpsInternationalStandardById")
    public RelationSpsInternationalStandard getRelationSpsInternationalStandardById(Long id) {
        RelationSpsInternationalStandard relationSpsInternationalStandard = null;
        try {
            if (null != id) {
                relationSpsInternationalStandard = relationSpsInternationalStandardManager.getRelationSpsInternationalStandardById(id);
            } else {
                LOG.warn("RelationSpsInternationalStandardServiceImpl#getRelationSpsInternationalStandardById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsInternationalStandardServiceImpl#getRelationSpsInternationalStandardById has error.", e);
        }
        return relationSpsInternationalStandard;
    }
}

