package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationSpsNotificationCategoryDao;
import com.letv.tbtSps.domain.RelationSpsNotificationCategory;
import com.letv.tbtSps.domain.query.RelationSpsNotificationCategoryQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationSpsNotificationCategoryDAO实现类<br/>
 * 对'sps信息表通报分类关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationSpsNotificationCategoryDaoImpl extends BaseDao implements RelationSpsNotificationCategoryDao {
    /** namespace */
    private final String namespace = RelationSpsNotificationCategoryDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryList(RelationSpsNotificationCategoryQuery queryBean) {
        return (List<RelationSpsNotificationCategory>) queryForList(namespace +".queryRelationSpsNotificationCategoryList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        return insert(namespace +".insert", relationSpsNotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        return update(namespace +".update", relationSpsNotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsNotificationCategoryCount(RelationSpsNotificationCategoryQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationSpsNotificationCategoryCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryListWithPage(RelationSpsNotificationCategoryQuery queryBean) {
        return (List<RelationSpsNotificationCategory>) queryForList(namespace +".queryRelationSpsNotificationCategoryListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsNotificationCategory configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsNotificationCategory getRelationSpsNotificationCategoryById(Long id) {
        return (RelationSpsNotificationCategory) queryForObject(namespace +".getRelationSpsNotificationCategoryById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        int count = (Integer) queryForObject(namespace +".exist", relationSpsNotificationCategory);
        return count > 0;
    }
}
