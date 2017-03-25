package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.NotificationCategory;
import com.letv.tbtSps.domain.query.NotificationCategoryQuery;
import com.letv.tbtSps.dao.NotificationCategoryDao;
import com.letv.tbtSps.manager.NotificationCategoryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * NotificationCategoryManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class NotificationCategoryManagerImpl extends BaseManager implements NotificationCategoryManager {
	
    @Autowired
    private NotificationCategoryDao notificationCategoryDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<NotificationCategory> notificationCategoryList) {
        boolean resultFlag = false;
        if (null != notificationCategoryList && notificationCategoryList.size() > 0) {
            for (NotificationCategory notificationCategory : notificationCategoryList) {
                resultFlag = notificationCategoryDao.insert(notificationCategory);
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
    public boolean insert(NotificationCategory notificationCategory) {
        return notificationCategoryDao.insert(notificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final NotificationCategory notificationCategory) {
        return notificationCategoryDao.update(notificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public List<NotificationCategory> queryNotificationCategoryList(NotificationCategoryQuery queryBean) {
        return notificationCategoryDao.queryNotificationCategoryList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<NotificationCategory> queryNotificationCategoryListWithPage(NotificationCategoryQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new NotificationCategoryQuery();
        }

        // 查询总数
        int totalItem = queryNotificationCategoryCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return notificationCategoryDao.queryNotificationCategoryListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryNotificationCategoryCount(NotificationCategoryQuery queryBean) {
        return notificationCategoryDao.queryNotificationCategoryCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(NotificationCategory notificationCategory) {
        return notificationCategoryDao.delete(notificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public NotificationCategory getNotificationCategoryById(Long id) {
        return notificationCategoryDao.getNotificationCategoryById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final NotificationCategory[] notificationCategorys) {
        boolean resultFlag = false;
        if (null != notificationCategorys && notificationCategorys.length > 0) {
            for (int i = 0; i < notificationCategorys.length; i++) {
                resultFlag = delete(notificationCategorys[i]);
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
    public boolean exist(NotificationCategory notificationCategory) {
        return notificationCategoryDao.exist(notificationCategory);
    }
}
