package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationSpsNotificationCategory;
import com.letv.tbtSps.domain.query.RelationSpsNotificationCategoryQuery;
import com.letv.tbtSps.manager.RelationSpsNotificationCategoryManager;
import com.letv.tbtSps.service.RelationSpsNotificationCategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationSpsNotificationCategoryService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationSpsNotificationCategoryServiceImpl implements RelationSpsNotificationCategoryService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationSpsNotificationCategoryServiceImpl.class);

    @Autowired
    private RelationSpsNotificationCategoryManager relationSpsNotificationCategoryManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationCategoryService.batchInsert")
    public boolean insert(List<RelationSpsNotificationCategory> relationSpsNotificationCategoryList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationSpsNotificationCategoryList)) {
                resultFlag = relationSpsNotificationCategoryManager.insert(relationSpsNotificationCategoryList);
            } else {
                LOG.warn("RelationSpsNotificationCategoryServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationCategoryServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationCategoryService.insert")
    public boolean insert(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsNotificationCategory) {
                if (relationSpsNotificationCategoryManager.exist(relationSpsNotificationCategory)) {
                    throw new ExistedException();
                }
                resultFlag = relationSpsNotificationCategoryManager.insert(relationSpsNotificationCategory);
            } else {
                LOG.warn("RelationSpsNotificationCategoryServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationSpsNotificationCategoryServiceImpl#insert failed, relationSpsNotificationCategory has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationCategoryServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationCategoryService.update")
    public boolean update(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsNotificationCategory && null != relationSpsNotificationCategory.getId()) {
                resultFlag = relationSpsNotificationCategoryManager.update(relationSpsNotificationCategory);
            } else {
                LOG.warn("RelationSpsNotificationCategoryServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationCategoryServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationCategoryService.queryRelationSpsNotificationCategoryList")
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryList(RelationSpsNotificationCategoryQuery queryBean) {
        List<RelationSpsNotificationCategory> relationSpsNotificationCategoryList = null;
        try {
            relationSpsNotificationCategoryList = relationSpsNotificationCategoryManager.queryRelationSpsNotificationCategoryList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationCategoryServiceImpl#queryRelationSpsNotificationCategoryList has error.", e);
        }
        return relationSpsNotificationCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationCategoryService.queryRelationSpsNotificationCategoryListWithPage")
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryListWithPage(RelationSpsNotificationCategoryQuery queryBean, PageUtil pageUtil) {
        List<RelationSpsNotificationCategory> relationSpsNotificationCategoryList = null;
        try {
            relationSpsNotificationCategoryList = relationSpsNotificationCategoryManager.queryRelationSpsNotificationCategoryListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationCategoryServiceImpl#queryRelationSpsNotificationCategoryListWithPage has error.", e);
        }
        return relationSpsNotificationCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationCategoryService.delete")
    public boolean delete(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsNotificationCategory && null != relationSpsNotificationCategory.getId()) {
                resultFlag = relationSpsNotificationCategoryManager.delete(relationSpsNotificationCategory);
            } else {
                LOG.warn("RelationSpsNotificationCategoryServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationCategoryServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationCategoryService.batchDelete")
    public boolean delete(RelationSpsNotificationCategory[] relationSpsNotificationCategorys) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsNotificationCategorys && relationSpsNotificationCategorys.length > 0) {
                resultFlag = relationSpsNotificationCategoryManager.delete(relationSpsNotificationCategorys);
            } else {
                LOG.warn("RelationSpsNotificationCategoryServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationCategoryServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationCategoryService.getRelationSpsNotificationCategoryById")
    public RelationSpsNotificationCategory getRelationSpsNotificationCategoryById(Long id) {
        RelationSpsNotificationCategory relationSpsNotificationCategory = null;
        try {
            if (null != id) {
                relationSpsNotificationCategory = relationSpsNotificationCategoryManager.getRelationSpsNotificationCategoryById(id);
            } else {
                LOG.warn("RelationSpsNotificationCategoryServiceImpl#getRelationSpsNotificationCategoryById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationCategoryServiceImpl#getRelationSpsNotificationCategoryById has error.", e);
        }
        return relationSpsNotificationCategory;
    }
}

