package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.NotificationType;
import com.letv.tbtSps.domain.query.NotificationTypeQuery;
import com.letv.tbtSps.dao.NotificationTypeDao;
import com.letv.tbtSps.manager.NotificationTypeManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * NotificationTypeManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class NotificationTypeManagerImpl extends BaseManager implements NotificationTypeManager {
	
    @Autowired
    private NotificationTypeDao notificationTypeDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<NotificationType> notificationTypeList) {
        boolean resultFlag = false;
        if (null != notificationTypeList && notificationTypeList.size() > 0) {
            for (NotificationType notificationType : notificationTypeList) {
                resultFlag = notificationTypeDao.insert(notificationType);
                if (!resultFlag) {
                    throw new RuntimeException("批量新增表信息异常");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(NotificationType notificationType) {
        return notificationTypeDao.insert(notificationType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final NotificationType notificationType) {
        return notificationTypeDao.update(notificationType);
    }

    /**
     * {@inheritDoc}
     */
    public List<NotificationType> queryNotificationTypeList(NotificationTypeQuery queryBean) {
        return notificationTypeDao.queryNotificationTypeList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<NotificationType> queryNotificationTypeListWithPage(NotificationTypeQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new NotificationTypeQuery();
        }

        // 查询总数
        int totalItem = queryNotificationTypeCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return notificationTypeDao.queryNotificationTypeListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryNotificationTypeCount(NotificationTypeQuery queryBean) {
        return notificationTypeDao.queryNotificationTypeCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(NotificationType notificationType) {
        return notificationTypeDao.delete(notificationType);
    }

    /**
     * {@inheritDoc}
     */
    public NotificationType getNotificationTypeById(Long id) {
        return notificationTypeDao.getNotificationTypeById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final NotificationType[] notificationTypes) {
        boolean resultFlag = false;
        if (null != notificationTypes && notificationTypes.length > 0) {
            for (int i = 0; i < notificationTypes.length; i++) {
                resultFlag = delete(notificationTypes[i]);
                if (!resultFlag) {
                    throw new RuntimeException("批量删除表信息异常!");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean exist(NotificationType notificationType) {
        return notificationTypeDao.exist(notificationType);
    }
}
