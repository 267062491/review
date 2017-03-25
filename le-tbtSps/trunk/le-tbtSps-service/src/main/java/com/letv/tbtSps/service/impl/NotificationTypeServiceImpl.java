package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.NotificationType;
import com.letv.tbtSps.domain.query.NotificationTypeQuery;
import com.letv.tbtSps.manager.NotificationTypeManager;
import com.letv.tbtSps.service.NotificationTypeService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * NotificationTypeService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(NotificationTypeServiceImpl.class);

    @Autowired
    private NotificationTypeManager notificationTypeManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationTypeService.batchInsert")
    public boolean insert(List<NotificationType> notificationTypeList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(notificationTypeList)) {
                resultFlag = notificationTypeManager.insert(notificationTypeList);
            } else {
                LOG.warn("NotificationTypeServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationTypeServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationTypeService.insert")
    public boolean insert(NotificationType notificationType) {
        boolean resultFlag = false;
        try {
            if (null != notificationType) {
                if (notificationTypeManager.exist(notificationType)) {
                    throw new ExistedException();
                }
                resultFlag = notificationTypeManager.insert(notificationType);
            } else {
                LOG.warn("NotificationTypeServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("NotificationTypeServiceImpl#insert failed, notificationType has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("NotificationTypeServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationTypeService.update")
    public boolean update(NotificationType notificationType) {
        boolean resultFlag = false;
        try {
            if (null != notificationType && null != notificationType.getId()) {
                resultFlag = notificationTypeManager.update(notificationType);
            } else {
                LOG.warn("NotificationTypeServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationTypeServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationTypeService.queryNotificationTypeList")
    public List<NotificationType> queryNotificationTypeList(NotificationTypeQuery queryBean) {
        List<NotificationType> notificationTypeList = null;
        try {
            notificationTypeList = notificationTypeManager.queryNotificationTypeList(queryBean);
        } catch (Exception e) {
            LOG.error("NotificationTypeServiceImpl#queryNotificationTypeList has error.", e);
        }
        return notificationTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationTypeService.queryNotificationTypeListWithPage")
    public List<NotificationType> queryNotificationTypeListWithPage(NotificationTypeQuery queryBean, PageUtil pageUtil) {
        List<NotificationType> notificationTypeList = null;
        try {
            notificationTypeList = notificationTypeManager.queryNotificationTypeListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("NotificationTypeServiceImpl#queryNotificationTypeListWithPage has error.", e);
        }
        return notificationTypeList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationTypeService.delete")
    public boolean delete(NotificationType notificationType) {
        boolean resultFlag = false;
        try {
            if (null != notificationType && null != notificationType.getId()) {
                resultFlag = notificationTypeManager.delete(notificationType);
            } else {
                LOG.warn("NotificationTypeServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationTypeServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationTypeService.batchDelete")
    public boolean delete(NotificationType[] notificationTypes) {
        boolean resultFlag = false;
        try {
            if (null != notificationTypes && notificationTypes.length > 0) {
                resultFlag = notificationTypeManager.delete(notificationTypes);
            } else {
                LOG.warn("NotificationTypeServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationTypeServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationTypeService.getNotificationTypeById")
    public NotificationType getNotificationTypeById(Long id) {
        NotificationType notificationType = null;
        try {
            if (null != id) {
                notificationType = notificationTypeManager.getNotificationTypeById(id);
            } else {
                LOG.warn("NotificationTypeServiceImpl#getNotificationTypeById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationTypeServiceImpl#getNotificationTypeById has error.", e);
        }
        return notificationType;
    }
}

