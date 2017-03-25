package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationSpsInternationalStandardDao;
import com.letv.tbtSps.domain.RelationSpsInternationalStandard;
import com.letv.tbtSps.domain.query.RelationSpsInternationalStandardQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationSpsInternationalStandardDAO实现类<br/>
 * 对'sps信息表-国际标准关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationSpsInternationalStandardDaoImpl extends BaseDao implements RelationSpsInternationalStandardDao {
    /** namespace */
    private final String namespace = RelationSpsInternationalStandardDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardList(RelationSpsInternationalStandardQuery queryBean) {
        return (List<RelationSpsInternationalStandard>) queryForList(namespace +".queryRelationSpsInternationalStandardList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        return insert(namespace +".insert", relationSpsInternationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        return update(namespace +".update", relationSpsInternationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsInternationalStandardCount(RelationSpsInternationalStandardQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationSpsInternationalStandardCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardListWithPage(RelationSpsInternationalStandardQuery queryBean) {
        return (List<RelationSpsInternationalStandard>) queryForList(namespace +".queryRelationSpsInternationalStandardListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsInternationalStandard configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsInternationalStandard getRelationSpsInternationalStandardById(Long id) {
        return (RelationSpsInternationalStandard) queryForObject(namespace +".getRelationSpsInternationalStandardById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        int count = (Integer) queryForObject(namespace +".exist", relationSpsInternationalStandard);
        return count > 0;
    }
}
