package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationTbnotificationType;
import com.letv.tbtSps.domain.query.RelationTbnotificationTypeQuery;
import com.letv.tbtSps.dao.RelationTbnotificationTypeDao;
import com.letv.tbtSps.manager.RelationTbnotificationTypeManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationTbnotificationTypeManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationTbnotificationTypeManagerImpl extends BaseManager implements RelationTbnotificationTypeManager {
	
    @Autowired
    private RelationTbnotificationTypeDao relationTbnotificationTypeDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationTbnotificationType> relationTbnotificationTypeList) {
        boolean resultFlag = false;
        if (null != relationTbnotificationTypeList && relationTbnotificationTypeList.size() > 0) {
            for (RelationTbnotificationType relationTbnotificationType : relationTbnotificationTypeList) {
                resultFlag = relationTbnotificationTypeDao.insert(relationTbnotificationType);
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
    public boolean insert(RelationTbnotificationType relationTbnotificationType) {
        return relationTbnotificationTypeDao.insert(relationTbnotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationTbnotificationType relationTbnotificationType) {
        return relationTbnotificationTypeDao.update(relationTbnotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbnotificationType> queryRelationTbnotificationTypeList(RelationTbnotificationTypeQuery queryBean) {
        return relationTbnotificationTypeDao.queryRelationTbnotificationTypeList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbnotificationType> queryRelationTbnotificationTypeListWithPage(RelationTbnotificationTypeQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationTbnotificationTypeQuery();
        }

        // 查询总数
        int totalItem = queryRelationTbnotificationTypeCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationTbnotificationTypeDao.queryRelationTbnotificationTypeListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTbnotificationTypeCount(RelationTbnotificationTypeQuery queryBean) {
        return relationTbnotificationTypeDao.queryRelationTbnotificationTypeCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTbnotificationType relationTbnotificationType) {
        return relationTbnotificationTypeDao.delete(relationTbnotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTbnotificationType getRelationTbnotificationTypeById(Long id) {
        return relationTbnotificationTypeDao.getRelationTbnotificationTypeById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationTbnotificationType[] relationTbnotificationTypes) {
        boolean resultFlag = false;
        if (null != relationTbnotificationTypes && relationTbnotificationTypes.length > 0) {
            for (int i = 0; i < relationTbnotificationTypes.length; i++) {
                resultFlag = delete(relationTbnotificationTypes[i]);
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
    public boolean exist(RelationTbnotificationType relationTbnotificationType) {
        return relationTbnotificationTypeDao.exist(relationTbnotificationType);
    }
}
