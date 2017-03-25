package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationSpsNotificationCategory;
import com.letv.tbtSps.domain.query.RelationSpsNotificationCategoryQuery;
import com.letv.tbtSps.dao.RelationSpsNotificationCategoryDao;
import com.letv.tbtSps.manager.RelationSpsNotificationCategoryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationSpsNotificationCategoryManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationSpsNotificationCategoryManagerImpl extends BaseManager implements RelationSpsNotificationCategoryManager {
	
    @Autowired
    private RelationSpsNotificationCategoryDao relationSpsNotificationCategoryDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationSpsNotificationCategory> relationSpsNotificationCategoryList) {
        boolean resultFlag = false;
        if (null != relationSpsNotificationCategoryList && relationSpsNotificationCategoryList.size() > 0) {
            for (RelationSpsNotificationCategory relationSpsNotificationCategory : relationSpsNotificationCategoryList) {
                resultFlag = relationSpsNotificationCategoryDao.insert(relationSpsNotificationCategory);
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
    public boolean insert(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        return relationSpsNotificationCategoryDao.insert(relationSpsNotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationSpsNotificationCategory relationSpsNotificationCategory) {
        return relationSpsNotificationCategoryDao.update(relationSpsNotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryList(RelationSpsNotificationCategoryQuery queryBean) {
        return relationSpsNotificationCategoryDao.queryRelationSpsNotificationCategoryList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryListWithPage(RelationSpsNotificationCategoryQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationSpsNotificationCategoryQuery();
        }

        // 查询总数
        int totalItem = queryRelationSpsNotificationCategoryCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationSpsNotificationCategoryDao.queryRelationSpsNotificationCategoryListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsNotificationCategoryCount(RelationSpsNotificationCategoryQuery queryBean) {
        return relationSpsNotificationCategoryDao.queryRelationSpsNotificationCategoryCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        return relationSpsNotificationCategoryDao.delete(relationSpsNotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsNotificationCategory getRelationSpsNotificationCategoryById(Long id) {
        return relationSpsNotificationCategoryDao.getRelationSpsNotificationCategoryById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationSpsNotificationCategory[] relationSpsNotificationCategorys) {
        boolean resultFlag = false;
        if (null != relationSpsNotificationCategorys && relationSpsNotificationCategorys.length > 0) {
            for (int i = 0; i < relationSpsNotificationCategorys.length; i++) {
                resultFlag = delete(relationSpsNotificationCategorys[i]);
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
    public boolean exist(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        return relationSpsNotificationCategoryDao.exist(relationSpsNotificationCategory);
    }
}
