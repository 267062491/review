package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationTbnotificationCategoryDao;
import com.letv.tbtSps.domain.RelationTbnotificationCategory;
import com.letv.tbtSps.domain.query.RelationTbnotificationCategoryQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationTbnotificationCategoryDAO实现类<br/>
 * 对'tbt信息表通报分类关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationTbnotificationCategoryDaoImpl extends BaseDao implements RelationTbnotificationCategoryDao {
    /** namespace */
    private final String namespace = RelationTbnotificationCategoryDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryList(RelationTbnotificationCategoryQuery queryBean) {
        return (List<RelationTbnotificationCategory>) queryForList(namespace +".queryRelationTbnotificationCategoryList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationTbnotificationCategory relationTbnotificationCategory) {
        return insert(namespace +".insert", relationTbnotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationTbnotificationCategory relationTbnotificationCategory) {
        return update(namespace +".update", relationTbnotificationCategory);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTbnotificationCategoryCount(RelationTbnotificationCategoryQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationTbnotificationCategoryCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryListWithPage(RelationTbnotificationCategoryQuery queryBean) {
        return (List<RelationTbnotificationCategory>) queryForList(namespace +".queryRelationTbnotificationCategoryListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTbnotificationCategory configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTbnotificationCategory getRelationTbnotificationCategoryById(Long id) {
        return (RelationTbnotificationCategory) queryForObject(namespace +".getRelationTbnotificationCategoryById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationTbnotificationCategory relationTbnotificationCategory) {
        int count = (Integer) queryForObject(namespace +".exist", relationTbnotificationCategory);
        return count > 0;
    }
}
