package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationTblanguage;
import com.letv.tbtSps.domain.query.RelationTblanguageQuery;
import com.letv.tbtSps.manager.RelationTblanguageManager;
import com.letv.tbtSps.service.RelationTblanguageService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationTblanguageService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationTblanguageServiceImpl implements RelationTblanguageService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationTblanguageServiceImpl.class);

    @Autowired
    private RelationTblanguageManager relationTblanguageManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTblanguageService.batchInsert")
    public boolean insert(List<RelationTblanguage> relationTblanguageList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationTblanguageList)) {
                resultFlag = relationTblanguageManager.insert(relationTblanguageList);
            } else {
                LOG.warn("RelationTblanguageServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTblanguageServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTblanguageService.insert")
    public boolean insert(RelationTblanguage relationTblanguage) {
        boolean resultFlag = false;
        try {
            if (null != relationTblanguage) {
                if (relationTblanguageManager.exist(relationTblanguage)) {
                    throw new ExistedException();
                }
                resultFlag = relationTblanguageManager.insert(relationTblanguage);
            } else {
                LOG.warn("RelationTblanguageServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationTblanguageServiceImpl#insert failed, relationTblanguage has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationTblanguageServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTblanguageService.update")
    public boolean update(RelationTblanguage relationTblanguage) {
        boolean resultFlag = false;
        try {
            if (null != relationTblanguage && null != relationTblanguage.getId()) {
                resultFlag = relationTblanguageManager.update(relationTblanguage);
            } else {
                LOG.warn("RelationTblanguageServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTblanguageServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTblanguageService.queryRelationTblanguageList")
    public List<RelationTblanguage> queryRelationTblanguageList(RelationTblanguageQuery queryBean) {
        List<RelationTblanguage> relationTblanguageList = null;
        try {
            relationTblanguageList = relationTblanguageManager.queryRelationTblanguageList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationTblanguageServiceImpl#queryRelationTblanguageList has error.", e);
        }
        return relationTblanguageList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTblanguageService.queryRelationTblanguageListWithPage")
    public List<RelationTblanguage> queryRelationTblanguageListWithPage(RelationTblanguageQuery queryBean, PageUtil pageUtil) {
        List<RelationTblanguage> relationTblanguageList = null;
        try {
            relationTblanguageList = relationTblanguageManager.queryRelationTblanguageListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationTblanguageServiceImpl#queryRelationTblanguageListWithPage has error.", e);
        }
        return relationTblanguageList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTblanguageService.delete")
    public boolean delete(RelationTblanguage relationTblanguage) {
        boolean resultFlag = false;
        try {
            if (null != relationTblanguage && null != relationTblanguage.getId()) {
                resultFlag = relationTblanguageManager.delete(relationTblanguage);
            } else {
                LOG.warn("RelationTblanguageServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTblanguageServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTblanguageService.batchDelete")
    public boolean delete(RelationTblanguage[] relationTblanguages) {
        boolean resultFlag = false;
        try {
            if (null != relationTblanguages && relationTblanguages.length > 0) {
                resultFlag = relationTblanguageManager.delete(relationTblanguages);
            } else {
                LOG.warn("RelationTblanguageServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTblanguageServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTblanguageService.getRelationTblanguageById")
    public RelationTblanguage getRelationTblanguageById(Long id) {
        RelationTblanguage relationTblanguage = null;
        try {
            if (null != id) {
                relationTblanguage = relationTblanguageManager.getRelationTblanguageById(id);
            } else {
                LOG.warn("RelationTblanguageServiceImpl#getRelationTblanguageById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTblanguageServiceImpl#getRelationTblanguageById has error.", e);
        }
        return relationTblanguage;
    }
}

