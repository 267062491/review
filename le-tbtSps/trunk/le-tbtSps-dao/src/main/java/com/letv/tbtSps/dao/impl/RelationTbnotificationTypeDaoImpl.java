package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationTbnotificationTypeDao;
import com.letv.tbtSps.domain.RelationTbnotificationType;
import com.letv.tbtSps.domain.query.RelationTbnotificationTypeQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationTbnotificationTypeDAO实现类<br/>
 * 对'tbt信息表-通报类型关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationTbnotificationTypeDaoImpl extends BaseDao implements RelationTbnotificationTypeDao {
    /** namespace */
    private final String namespace = RelationTbnotificationTypeDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationTbnotificationType> queryRelationTbnotificationTypeList(RelationTbnotificationTypeQuery queryBean) {
        return (List<RelationTbnotificationType>) queryForList(namespace +".queryRelationTbnotificationTypeList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationTbnotificationType relationTbnotificationType) {
        return insert(namespace +".insert", relationTbnotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationTbnotificationType relationTbnotificationType) {
        return update(namespace +".update", relationTbnotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTbnotificationTypeCount(RelationTbnotificationTypeQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationTbnotificationTypeCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbnotificationType> queryRelationTbnotificationTypeListWithPage(RelationTbnotificationTypeQuery queryBean) {
        return (List<RelationTbnotificationType>) queryForList(namespace +".queryRelationTbnotificationTypeListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTbnotificationType configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTbnotificationType getRelationTbnotificationTypeById(Long id) {
        return (RelationTbnotificationType) queryForObject(namespace +".getRelationTbnotificationTypeById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationTbnotificationType relationTbnotificationType) {
        int count = (Integer) queryForObject(namespace +".exist", relationTbnotificationType);
        return count > 0;
    }
}
