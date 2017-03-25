package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationTbnotificationCategory;
import com.letv.tbtSps.domain.query.RelationTbnotificationCategoryQuery;
import com.letv.tbtSps.dao.RelationTbnotificationCategoryDao;
import com.letv.tbtSps.manager.RelationTbnotificationCategoryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationTbnotificationCategoryManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationTbnotificationCategoryManagerImpl extends BaseManager implements RelationTbnotificationCategoryManager {
	
    @Autowired
    private RelationTbnotificationCategoryDao relationTbnotificationCategoryDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationTbnotificationCategory> relationTbnotificationCategoryList) {
        boolean resultFlag = false;
        if (null != relationTbnotificationCategoryList && relationTbnotificationCategoryList.size() > 0) {
            for (RelationTbnotificationCategory relationTbnotificationCategory : relationTbnotificationCategoryList) {
                resultFlag = relationTbnotificationCategoryDao.insert(relationTbnotificationCategory);
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
    public boolean insert(RelationTbnotificationCategory relationTbnotificationCategory) {
        return relationTbnotificationCategoryDao.insert(relationTbnotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationTbnotificationCategory relationTbnotificationCategory) {
        return relationTbnotificationCategoryDao.update(relationTbnotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryList(RelationTbnotificationCategoryQuery queryBean) {
        return relationTbnotificationCategoryDao.queryRelationTbnotificationCategoryList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryListWithPage(RelationTbnotificationCategoryQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationTbnotificationCategoryQuery();
        }

        // 查询总数
        int totalItem = queryRelationTbnotificationCategoryCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationTbnotificationCategoryDao.queryRelationTbnotificationCategoryListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTbnotificationCategoryCount(RelationTbnotificationCategoryQuery queryBean) {
        return relationTbnotificationCategoryDao.queryRelationTbnotificationCategoryCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTbnotificationCategory relationTbnotificationCategory) {
        return relationTbnotificationCategoryDao.delete(relationTbnotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTbnotificationCategory getRelationTbnotificationCategoryById(Long id) {
        return relationTbnotificationCategoryDao.getRelationTbnotificationCategoryById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationTbnotificationCategory[] relationTbnotificationCategorys) {
        boolean resultFlag = false;
        if (null != relationTbnotificationCategorys && relationTbnotificationCategorys.length > 0) {
            for (int i = 0; i < relationTbnotificationCategorys.length; i++) {
                resultFlag = delete(relationTbnotificationCategorys[i]);
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
    public boolean exist(RelationTbnotificationCategory relationTbnotificationCategory) {
        return relationTbnotificationCategoryDao.exist(relationTbnotificationCategory);
    }
}
