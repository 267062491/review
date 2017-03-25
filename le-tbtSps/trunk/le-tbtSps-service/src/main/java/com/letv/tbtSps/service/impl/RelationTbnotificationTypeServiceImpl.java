package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationTbnotificationType;
import com.letv.tbtSps.domain.query.RelationTbnotificationTypeQuery;
import com.letv.tbtSps.manager.RelationTbnotificationTypeManager;
import com.letv.tbtSps.service.RelationTbnotificationTypeService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationTbnotificationTypeService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationTbnotificationTypeServiceImpl implements RelationTbnotificationTypeService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationTbnotificationTypeServiceImpl.class);

    @Autowired
    private RelationTbnotificationTypeManager relationTbnotificationTypeManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationTypeService.batchInsert")
    public boolean insert(List<RelationTbnotificationType> relationTbnotificationTypeList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationTbnotificationTypeList)) {
                resultFlag = relationTbnotificationTypeManager.insert(relationTbnotificationTypeList);
            } else {
                LOG.warn("RelationTbnotificationTypeServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationTypeServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationTypeService.insert")
    public boolean insert(RelationTbnotificationType relationTbnotificationType) {
        boolean resultFlag = false;
        try {
            if (null != relationTbnotificationType) {
                if (relationTbnotificationTypeManager.exist(relationTbnotificationType)) {
                    throw new ExistedException();
                }
                resultFlag = relationTbnotificationTypeManager.insert(relationTbnotificationType);
            } else {
                LOG.warn("RelationTbnotificationTypeServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationTbnotificationTypeServiceImpl#insert failed, relationTbnotificationType has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationTbnotificationTypeServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationTypeService.update")
    public boolean update(RelationTbnotificationType relationTbnotificationType) {
        boolean resultFlag = false;
        try {
            if (null != relationTbnotificationType && null != relationTbnotificationType.getId()) {
                resultFlag = relationTbnotificationTypeManager.update(relationTbnotificationType);
            } else {
                LOG.warn("RelationTbnotificationTypeServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationTypeServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationTypeService.queryRelationTbnotificationTypeList")
    public List<RelationTbnotificationType> queryRelationTbnotificationTypeList(RelationTbnotificationTypeQuery queryBean) {
        List<RelationTbnotificationType> relationTbnotificationTypeList = null;
        try {
            relationTbnotificationTypeList = relationTbnotificationTypeManager.queryRelationTbnotificationTypeList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationTbnotificationTypeServiceImpl#queryRelationTbnotificationTypeList has error.", e);
        }
        return relationTbnotificationTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationTypeService.queryRelationTbnotificationTypeListWithPage")
    public List<RelationTbnotificationType> queryRelationTbnotificationTypeListWithPage(RelationTbnotificationTypeQuery queryBean, PageUtil pageUtil) {
        List<RelationTbnotificationType> relationTbnotificationTypeList = null;
        try {
            relationTbnotificationTypeList = relationTbnotificationTypeManager.queryRelationTbnotificationTypeListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationTbnotificationTypeServiceImpl#queryRelationTbnotificationTypeListWithPage has error.", e);
        }
        return relationTbnotificationTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationTypeService.delete")
    public boolean delete(RelationTbnotificationType relationTbnotificationType) {
        boolean resultFlag = false;
        try {
            if (null != relationTbnotificationType && null != relationTbnotificationType.getId()) {
                resultFlag = relationTbnotificationTypeManager.delete(relationTbnotificationType);
            } else {
                LOG.warn("RelationTbnotificationTypeServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationTypeServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationTypeService.batchDelete")
    public boolean delete(RelationTbnotificationType[] relationTbnotificationTypes) {
        boolean resultFlag = false;
        try {
            if (null != relationTbnotificationTypes && relationTbnotificationTypes.length > 0) {
                resultFlag = relationTbnotificationTypeManager.delete(relationTbnotificationTypes);
            } else {
                LOG.warn("RelationTbnotificationTypeServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationTypeServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbnotificationTypeService.getRelationTbnotificationTypeById")
    public RelationTbnotificationType getRelationTbnotificationTypeById(Long id) {
        RelationTbnotificationType relationTbnotificationType = null;
        try {
            if (null != id) {
                relationTbnotificationType = relationTbnotificationTypeManager.getRelationTbnotificationTypeById(id);
            } else {
                LOG.warn("RelationTbnotificationTypeServiceImpl#getRelationTbnotificationTypeById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbnotificationTypeServiceImpl#getRelationTbnotificationTypeById has error.", e);
        }
        return relationTbnotificationType;
    }
}

