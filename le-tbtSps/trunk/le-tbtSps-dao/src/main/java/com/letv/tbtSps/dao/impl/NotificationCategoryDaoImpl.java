package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.NotificationCategoryDao;
import com.letv.tbtSps.domain.NotificationCategory;
import com.letv.tbtSps.domain.query.NotificationCategoryQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * NotificationCategoryDAO实现类<br/>
 * 对'通报内部分类'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class NotificationCategoryDaoImpl extends BaseDao implements NotificationCategoryDao {
    /** namespace */
    private final String namespace = NotificationCategoryDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<NotificationCategory> queryNotificationCategoryList(NotificationCategoryQuery queryBean) {
        return (List<NotificationCategory>) queryForList(namespace +".queryNotificationCategoryList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(NotificationCategory notificationCategory) {
        return insert(namespace +".insert", notificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(NotificationCategory notificationCategory) {
        return update(namespace +".update", notificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public int queryNotificationCategoryCount(NotificationCategoryQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryNotificationCategoryCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<NotificationCategory> queryNotificationCategoryListWithPage(NotificationCategoryQuery queryBean) {
        return (List<NotificationCategory>) queryForList(namespace +".queryNotificationCategoryListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(NotificationCategory configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public NotificationCategory getNotificationCategoryById(Long id) {
        return (NotificationCategory) queryForObject(namespace +".getNotificationCategoryById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(NotificationCategory notificationCategory) {
        int count = (Integer) queryForObject(namespace +".exist", notificationCategory);
        return count > 0;
    }
}
