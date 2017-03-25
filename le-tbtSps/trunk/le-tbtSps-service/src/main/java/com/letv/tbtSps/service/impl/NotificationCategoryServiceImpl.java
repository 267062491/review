package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.NotificationCategory;
import com.letv.tbtSps.domain.query.NotificationCategoryQuery;
import com.letv.tbtSps.manager.NotificationCategoryManager;
import com.letv.tbtSps.service.NotificationCategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * NotificationCategoryService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class NotificationCategoryServiceImpl implements NotificationCategoryService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(NotificationCategoryServiceImpl.class);

    @Autowired
    private NotificationCategoryManager notificationCategoryManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationCategoryService.batchInsert")
    public boolean insert(List<NotificationCategory> notificationCategoryList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(notificationCategoryList)) {
                resultFlag = notificationCategoryManager.insert(notificationCategoryList);
            } else {
                LOG.warn("NotificationCategoryServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationCategoryServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationCategoryService.insert")
    public boolean insert(NotificationCategory notificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != notificationCategory) {
                if (notificationCategoryManager.exist(notificationCategory)) {
                    throw new ExistedException();
                }
                resultFlag = notificationCategoryManager.insert(notificationCategory);
            } else {
                LOG.warn("NotificationCategoryServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("NotificationCategoryServiceImpl#insert failed, notificationCategory has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("NotificationCategoryServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationCategoryService.update")
    public boolean update(NotificationCategory notificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != notificationCategory && null != notificationCategory.getId()) {
                resultFlag = notificationCategoryManager.update(notificationCategory);
            } else {
                LOG.warn("NotificationCategoryServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationCategoryServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationCategoryService.queryNotificationCategoryList")
    public List<NotificationCategory> queryNotificationCategoryList(NotificationCategoryQuery queryBean) {
        List<NotificationCategory> notificationCategoryList = null;
        try {
            notificationCategoryList = notificationCategoryManager.queryNotificationCategoryList(queryBean);
        } catch (Exception e) {
            LOG.error("NotificationCategoryServiceImpl#queryNotificationCategoryList has error.", e);
        }
        return notificationCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationCategoryService.queryNotificationCategoryListWithPage")
    public List<NotificationCategory> queryNotificationCategoryListWithPage(NotificationCategoryQuery queryBean, PageUtil pageUtil) {
        List<NotificationCategory> notificationCategoryList = null;
        try {
            notificationCategoryList = notificationCategoryManager.queryNotificationCategoryListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("NotificationCategoryServiceImpl#queryNotificationCategoryListWithPage has error.", e);
        }
        return notificationCategoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationCategoryService.delete")
    public boolean delete(NotificationCategory notificationCategory) {
        boolean resultFlag = false;
        try {
            if (null != notificationCategory && null != notificationCategory.getId()) {
                resultFlag = notificationCategoryManager.delete(notificationCategory);
            } else {
                LOG.warn("NotificationCategoryServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationCategoryServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationCategoryService.batchDelete")
    public boolean delete(NotificationCategory[] notificationCategorys) {
        boolean resultFlag = false;
        try {
            if (null != notificationCategorys && notificationCategorys.length > 0) {
                resultFlag = notificationCategoryManager.delete(notificationCategorys);
            } else {
                LOG.warn("NotificationCategoryServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationCategoryServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "NotificationCategoryService.getNotificationCategoryById")
    public NotificationCategory getNotificationCategoryById(Long id) {
        NotificationCategory notificationCategory = null;
        try {
            if (null != id) {
                notificationCategory = notificationCategoryManager.getNotificationCategoryById(id);
            } else {
                LOG.warn("NotificationCategoryServiceImpl#getNotificationCategoryById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("NotificationCategoryServiceImpl#getNotificationCategoryById has error.", e);
        }
        return notificationCategory;
    }
}

