package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.NotificationTypeDao;
import com.letv.tbtSps.domain.NotificationType;
import com.letv.tbtSps.domain.query.NotificationTypeQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * NotificationTypeDAO实现类<br/>
 * 对'通报类型'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class NotificationTypeDaoImpl extends BaseDao implements NotificationTypeDao {
    /** namespace */
    private final String namespace = NotificationTypeDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<NotificationType> queryNotificationTypeList(NotificationTypeQuery queryBean) {
        return (List<NotificationType>) queryForList(namespace +".queryNotificationTypeList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(NotificationType notificationType) {
        return insert(namespace +".insert", notificationType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(NotificationType notificationType) {
        return update(namespace +".update", notificationType);
    }

    /**
     * {@inheritDoc}
     */
    public int queryNotificationTypeCount(NotificationTypeQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryNotificationTypeCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<NotificationType> queryNotificationTypeListWithPage(NotificationTypeQuery queryBean) {
        return (List<NotificationType>) queryForList(namespace +".queryNotificationTypeListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(NotificationType configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public NotificationType getNotificationTypeById(Long id) {
        return (NotificationType) queryForObject(namespace +".getNotificationTypeById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(NotificationType notificationType) {
        int count = (Integer) queryForObject(namespace +".exist", notificationType);
        return count > 0;
    }
}
