package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationSpsNotificationType;
import com.letv.tbtSps.domain.query.RelationSpsNotificationTypeQuery;
import com.letv.tbtSps.dao.RelationSpsNotificationTypeDao;
import com.letv.tbtSps.manager.RelationSpsNotificationTypeManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationSpsNotificationTypeManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationSpsNotificationTypeManagerImpl extends BaseManager implements RelationSpsNotificationTypeManager {
	
    @Autowired
    private RelationSpsNotificationTypeDao relationSpsNotificationTypeDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationSpsNotificationType> relationSpsNotificationTypeList) {
        boolean resultFlag = false;
        if (null != relationSpsNotificationTypeList && relationSpsNotificationTypeList.size() > 0) {
            for (RelationSpsNotificationType relationSpsNotificationType : relationSpsNotificationTypeList) {
                resultFlag = relationSpsNotificationTypeDao.insert(relationSpsNotificationType);
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
    public boolean insert(RelationSpsNotificationType relationSpsNotificationType) {
        return relationSpsNotificationTypeDao.insert(relationSpsNotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationSpsNotificationType relationSpsNotificationType) {
        return relationSpsNotificationTypeDao.update(relationSpsNotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeList(RelationSpsNotificationTypeQuery queryBean) {
        return relationSpsNotificationTypeDao.queryRelationSpsNotificationTypeList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeListWithPage(RelationSpsNotificationTypeQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationSpsNotificationTypeQuery();
        }

        // 查询总数
        int totalItem = queryRelationSpsNotificationTypeCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationSpsNotificationTypeDao.queryRelationSpsNotificationTypeListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsNotificationTypeCount(RelationSpsNotificationTypeQuery queryBean) {
        return relationSpsNotificationTypeDao.queryRelationSpsNotificationTypeCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsNotificationType relationSpsNotificationType) {
        return relationSpsNotificationTypeDao.delete(relationSpsNotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsNotificationType getRelationSpsNotificationTypeById(Long id) {
        return relationSpsNotificationTypeDao.getRelationSpsNotificationTypeById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationSpsNotificationType[] relationSpsNotificationTypes) {
        boolean resultFlag = false;
        if (null != relationSpsNotificationTypes && relationSpsNotificationTypes.length > 0) {
            for (int i = 0; i < relationSpsNotificationTypes.length; i++) {
                resultFlag = delete(relationSpsNotificationTypes[i]);
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
    public boolean exist(RelationSpsNotificationType relationSpsNotificationType) {
        return relationSpsNotificationTypeDao.exist(relationSpsNotificationType);
    }
}
