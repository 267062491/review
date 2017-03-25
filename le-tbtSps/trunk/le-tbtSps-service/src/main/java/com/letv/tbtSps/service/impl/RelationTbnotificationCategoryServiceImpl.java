package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationTbnotificationCategory;
import com.letv.tbtSps.domain.query.RelationTbnotificationCategoryQuery;
import com.letv.tbtSps.manager.RelationTbnotificationCategoryManager;
import com.letv.tbtSps.service.RelationTbnotificationCategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationTbnotificationCategoryService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationTbnotificationCategoryServiceImpl implements RelationTbnotificationCategoryService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationTbnotificationCategoryServiceImpl.class);

    @Autowired
    private RelationTbnotificationCategoryManager relationTbnotificationCategoryManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationCategoryService.batchInsert")
    public boolean insert(List<RelationTbnotificationCategory> relationTbnotificationCategoryList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationTbnotificationCategoryList)) {
                resultFlag = relationTbnotificationCategoryManager.insert(relationTbnotificationCategoryList);
            } else {
                LOG.warn("RelationTbnotificationCategoryServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationCategoryServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationCategoryService.insert")
    public boolean insert(RelationTbnotificationCategory relationTbnotificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != relationTbnotificationCategory) {
                if (relationTbnotificationCategoryManager.exist(relationTbnotificationCategory)) {
                    throw new ExistedException();
                }
                resultFlag = relationTbnotificationCategoryManager.insert(relationTbnotificationCategory);
            } else {
                LOG.warn("RelationTbnotificationCategoryServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationTbnotificationCategoryServiceImpl#insert failed, relationTbnotificationCategory has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationTbnotificationCategoryServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationCategoryService.update")
    public boolean update(RelationTbnotificationCategory relationTbnotificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != relationTbnotificationCategory && null != relationTbnotificationCategory.getId()) {
                resultFlag = relationTbnotificationCategoryManager.update(relationTbnotificationCategory);
            } else {
                LOG.warn("RelationTbnotificationCategoryServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationCategoryServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationCategoryService.queryRelationTbnotificationCategoryList")
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryList(RelationTbnotificationCategoryQuery queryBean) {
        List<RelationTbnotificationCategory> relationTbnotificationCategoryList = null;
        try {
            relationTbnotificationCategoryList = relationTbnotificationCategoryManager.queryRelationTbnotificationCategoryList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationTbnotificationCategoryServiceImpl#queryRelationTbnotificationCategoryList has error.", e);
        }
        return relationTbnotificationCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationCategoryService.queryRelationTbnotificationCategoryListWithPage")
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryListWithPage(RelationTbnotificationCategoryQuery queryBean, PageUtil pageUtil) {
        List<RelationTbnotificationCategory> relationTbnotificationCategoryList = null;
        try {
            relationTbnotificationCategoryList = relationTbnotificationCategoryManager.queryRelationTbnotificationCategoryListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationTbnotificationCategoryServiceImpl#queryRelationTbnotificationCategoryListWithPage has error.", e);
        }
        return relationTbnotificationCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationCategoryService.delete")
    public boolean delete(RelationTbnotificationCategory relationTbnotificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != relationTbnotificationCategory && null != relationTbnotificationCategory.getId()) {
                resultFlag = relationTbnotificationCategoryManager.delete(relationTbnotificationCategory);
            } else {
                LOG.warn("RelationTbnotificationCategoryServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationCategoryServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationCategoryService.batchDelete")
    public boolean delete(RelationTbnotificationCategory[] relationTbnotificationCategorys) {
        boolean resultFlag = false;
        try {
            if (null != relationTbnotificationCategorys && relationTbnotificationCategorys.length > 0) {
                resultFlag = relationTbnotificationCategoryManager.delete(relationTbnotificationCategorys);
            } else {
                LOG.warn("RelationTbnotificationCategoryServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationCategoryServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationCategoryService.getRelationTbnotificationCategoryById")
    public RelationTbnotificationCategory getRelationTbnotificationCategoryById(Long id) {
        RelationTbnotificationCategory relationTbnotificationCategory = null;
        try {
            if (null != id) {
                relationTbnotificationCategory = relationTbnotificationCategoryManager.getRelationTbnotificationCategoryById(id);
            } else {
                LOG.warn("RelationTbnotificationCategoryServiceImpl#getRelationTbnotificationCategoryById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationCategoryServiceImpl#getRelationTbnotificationCategoryById has error.", e);
        }
        return relationTbnotificationCategory;
    }
}

