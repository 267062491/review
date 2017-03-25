package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationSpsLanguage;
import com.letv.tbtSps.domain.query.RelationSpsLanguageQuery;
import com.letv.tbtSps.manager.RelationSpsLanguageManager;
import com.letv.tbtSps.service.RelationSpsLanguageService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationSpsLanguageService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationSpsLanguageServiceImpl implements RelationSpsLanguageService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationSpsLanguageServiceImpl.class);

    @Autowired
    private RelationSpsLanguageManager relationSpsLanguageManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsLanguageService.batchInsert")
    public boolean insert(List<RelationSpsLanguage> relationSpsLanguageList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationSpsLanguageList)) {
                resultFlag = relationSpsLanguageManager.insert(relationSpsLanguageList);
            } else {
                LOG.warn("RelationSpsLanguageServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsLanguageServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsLanguageService.insert")
    public boolean insert(RelationSpsLanguage relationSpsLanguage) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsLanguage) {
                if (relationSpsLanguageManager.exist(relationSpsLanguage)) {
                    throw new ExistedException();
                }
                resultFlag = relationSpsLanguageManager.insert(relationSpsLanguage);
            } else {
                LOG.warn("RelationSpsLanguageServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationSpsLanguageServiceImpl#insert failed, relationSpsLanguage has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationSpsLanguageServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsLanguageService.update")
    public boolean update(RelationSpsLanguage relationSpsLanguage) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsLanguage && null != relationSpsLanguage.getId()) {
                resultFlag = relationSpsLanguageManager.update(relationSpsLanguage);
            } else {
                LOG.warn("RelationSpsLanguageServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsLanguageServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsLanguageService.queryRelationSpsLanguageList")
    public List<RelationSpsLanguage> queryRelationSpsLanguageList(RelationSpsLanguageQuery queryBean) {
        List<RelationSpsLanguage> relationSpsLanguageList = null;
        try {
            relationSpsLanguageList = relationSpsLanguageManager.queryRelationSpsLanguageList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationSpsLanguageServiceImpl#queryRelationSpsLanguageList has error.", e);
        }
        return relationSpsLanguageList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsLanguageService.queryRelationSpsLanguageListWithPage")
    public List<RelationSpsLanguage> queryRelationSpsLanguageListWithPage(RelationSpsLanguageQuery queryBean, PageUtil pageUtil) {
        List<RelationSpsLanguage> relationSpsLanguageList = null;
        try {
            relationSpsLanguageList = relationSpsLanguageManager.queryRelationSpsLanguageListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationSpsLanguageServiceImpl#queryRelationSpsLanguageListWithPage has error.", e);
        }
        return relationSpsLanguageList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsLanguageService.delete")
    public boolean delete(RelationSpsLanguage relationSpsLanguage) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsLanguage && null != relationSpsLanguage.getId()) {
                resultFlag = relationSpsLanguageManager.delete(relationSpsLanguage);
            } else {
                LOG.warn("RelationSpsLanguageServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsLanguageServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsLanguageService.batchDelete")
    public boolean delete(RelationSpsLanguage[] relationSpsLanguages) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsLanguages && relationSpsLanguages.length > 0) {
                resultFlag = relationSpsLanguageManager.delete(relationSpsLanguages);
            } else {
                LOG.warn("RelationSpsLanguageServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsLanguageServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsLanguageService.getRelationSpsLanguageById")
    public RelationSpsLanguage getRelationSpsLanguageById(Long id) {
        RelationSpsLanguage relationSpsLanguage = null;
        try {
            if (null != id) {
                relationSpsLanguage = relationSpsLanguageManager.getRelationSpsLanguageById(id);
            } else {
                LOG.warn("RelationSpsLanguageServiceImpl#getRelationSpsLanguageById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsLanguageServiceImpl#getRelationSpsLanguageById has error.", e);
        }
        return relationSpsLanguage;
    }
}

