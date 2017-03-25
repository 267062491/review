package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationSpsNotificationType;
import com.letv.tbtSps.domain.query.RelationSpsNotificationTypeQuery;
import com.letv.tbtSps.manager.RelationSpsNotificationTypeManager;
import com.letv.tbtSps.service.RelationSpsNotificationTypeService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationSpsNotificationTypeService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationSpsNotificationTypeServiceImpl implements RelationSpsNotificationTypeService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationSpsNotificationTypeServiceImpl.class);

    @Autowired
    private RelationSpsNotificationTypeManager relationSpsNotificationTypeManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationTypeService.batchInsert")
    public boolean insert(List<RelationSpsNotificationType> relationSpsNotificationTypeList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationSpsNotificationTypeList)) {
                resultFlag = relationSpsNotificationTypeManager.insert(relationSpsNotificationTypeList);
            } else {
                LOG.warn("RelationSpsNotificationTypeServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationTypeServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationTypeService.insert")
    public boolean insert(RelationSpsNotificationType relationSpsNotificationType) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsNotificationType) {
                if (relationSpsNotificationTypeManager.exist(relationSpsNotificationType)) {
                    throw new ExistedException();
                }
                resultFlag = relationSpsNotificationTypeManager.insert(relationSpsNotificationType);
            } else {
                LOG.warn("RelationSpsNotificationTypeServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationSpsNotificationTypeServiceImpl#insert failed, relationSpsNotificationType has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationTypeServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationTypeService.update")
    public boolean update(RelationSpsNotificationType relationSpsNotificationType) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsNotificationType && null != relationSpsNotificationType.getId()) {
                resultFlag = relationSpsNotificationTypeManager.update(relationSpsNotificationType);
            } else {
                LOG.warn("RelationSpsNotificationTypeServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationTypeServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationTypeService.queryRelationSpsNotificationTypeList")
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeList(RelationSpsNotificationTypeQuery queryBean) {
        List<RelationSpsNotificationType> relationSpsNotificationTypeList = null;
        try {
            relationSpsNotificationTypeList = relationSpsNotificationTypeManager.queryRelationSpsNotificationTypeList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationTypeServiceImpl#queryRelationSpsNotificationTypeList has error.", e);
        }
        return relationSpsNotificationTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationTypeService.queryRelationSpsNotificationTypeListWithPage")
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeListWithPage(RelationSpsNotificationTypeQuery queryBean, PageUtil pageUtil) {
        List<RelationSpsNotificationType> relationSpsNotificationTypeList = null;
        try {
            relationSpsNotificationTypeList = relationSpsNotificationTypeManager.queryRelationSpsNotificationTypeListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationTypeServiceImpl#queryRelationSpsNotificationTypeListWithPage has error.", e);
        }
        return relationSpsNotificationTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationTypeService.delete")
    public boolean delete(RelationSpsNotificationType relationSpsNotificationType) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsNotificationType && null != relationSpsNotificationType.getId()) {
                resultFlag = relationSpsNotificationTypeManager.delete(relationSpsNotificationType);
            } else {
                LOG.warn("RelationSpsNotificationTypeServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationTypeServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationTypeService.batchDelete")
    public boolean delete(RelationSpsNotificationType[] relationSpsNotificationTypes) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsNotificationTypes && relationSpsNotificationTypes.length > 0) {
                resultFlag = relationSpsNotificationTypeManager.delete(relationSpsNotificationTypes);
            } else {
                LOG.warn("RelationSpsNotificationTypeServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationTypeServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsNotificationTypeService.getRelationSpsNotificationTypeById")
    public RelationSpsNotificationType getRelationSpsNotificationTypeById(Long id) {
        RelationSpsNotificationType relationSpsNotificationType = null;
        try {
            if (null != id) {
                relationSpsNotificationType = relationSpsNotificationTypeManager.getRelationSpsNotificationTypeById(id);
            } else {
                LOG.warn("RelationSpsNotificationTypeServiceImpl#getRelationSpsNotificationTypeById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsNotificationTypeServiceImpl#getRelationSpsNotificationTypeById has error.", e);
        }
        return relationSpsNotificationType;
    }
}

