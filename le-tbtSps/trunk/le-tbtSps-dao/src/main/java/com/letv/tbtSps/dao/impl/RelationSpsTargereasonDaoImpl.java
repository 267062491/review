package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationSpsTargereasonDao;
import com.letv.tbtSps.domain.RelationSpsTargereason;
import com.letv.tbtSps.domain.query.RelationSpsTargereasonQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationSpsTargereasonDAO实现类<br/>
 * 对'sps信息表-目标理由关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationSpsTargereasonDaoImpl extends BaseDao implements RelationSpsTargereasonDao {
    /** namespace */
    private final String namespace = RelationSpsTargereasonDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationSpsTargereason> queryRelationSpsTargereasonList(RelationSpsTargereasonQuery queryBean) {
        return (List<RelationSpsTargereason>) queryForList(namespace +".queryRelationSpsTargereasonList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationSpsTargereason relationSpsTargereason) {
        return insert(namespace +".insert", relationSpsTargereason);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationSpsTargereason relationSpsTargereason) {
        return update(namespace +".update", relationSpsTargereason);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsTargereasonCount(RelationSpsTargereasonQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationSpsTargereasonCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsTargereason> queryRelationSpsTargereasonListWithPage(RelationSpsTargereasonQuery queryBean) {
        return (List<RelationSpsTargereason>) queryForList(namespace +".queryRelationSpsTargereasonListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsTargereason configuration) {
        return delete(namespace +".delete", configuration);
    }
    public boolean deleteByCode(RelationSpsTargereason configuration) {
        return delete(namespace +".deleteByCode", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsTargereason getRelationSpsTargereasonById(Long id) {
        return (RelationSpsTargereason) queryForObject(namespace +".getRelationSpsTargereasonById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationSpsTargereason relationSpsTargereason) {
        int count = (Integer) queryForObject(namespace +".exist", relationSpsTargereason);
        return count > 0;
    }
}
