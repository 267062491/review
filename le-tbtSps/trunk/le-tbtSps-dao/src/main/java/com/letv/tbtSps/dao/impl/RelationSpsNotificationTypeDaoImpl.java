package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationSpsNotificationTypeDao;
import com.letv.tbtSps.domain.RelationSpsNotificationType;
import com.letv.tbtSps.domain.query.RelationSpsNotificationTypeQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationSpsNotificationTypeDAO实现类<br/>
 * 对'sps信息表-通报类型关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationSpsNotificationTypeDaoImpl extends BaseDao implements RelationSpsNotificationTypeDao {
    /** namespace */
    private final String namespace = RelationSpsNotificationTypeDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeList(RelationSpsNotificationTypeQuery queryBean) {
        return (List<RelationSpsNotificationType>) queryForList(namespace +".queryRelationSpsNotificationTypeList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationSpsNotificationType relationSpsNotificationType) {
        return insert(namespace +".insert", relationSpsNotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationSpsNotificationType relationSpsNotificationType) {
        return update(namespace +".update", relationSpsNotificationType);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsNotificationTypeCount(RelationSpsNotificationTypeQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationSpsNotificationTypeCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeListWithPage(RelationSpsNotificationTypeQuery queryBean) {
        return (List<RelationSpsNotificationType>) queryForList(namespace +".queryRelationSpsNotificationTypeListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsNotificationType configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsNotificationType getRelationSpsNotificationTypeById(Long id) {
        return (RelationSpsNotificationType) queryForObject(namespace +".getRelationSpsNotificationTypeById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationSpsNotificationType relationSpsNotificationType) {
        int count = (Integer) queryForObject(namespace +".exist", relationSpsNotificationType);
        return count > 0;
    }
}
