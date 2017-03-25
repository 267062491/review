package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationTblanguageDao;
import com.letv.tbtSps.domain.RelationTblanguage;
import com.letv.tbtSps.domain.query.RelationTblanguageQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationTblanguageDAO实现类<br/>
 * 对'tbt信息表-原文语种关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationTblanguageDaoImpl extends BaseDao implements RelationTblanguageDao {
    /** namespace */
    private final String namespace = RelationTblanguageDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationTblanguage> queryRelationTblanguageList(RelationTblanguageQuery queryBean) {
        return (List<RelationTblanguage>) queryForList(namespace +".queryRelationTblanguageList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationTblanguage relationTblanguage) {
        return insert(namespace +".insert", relationTblanguage);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationTblanguage relationTblanguage) {
        return update(namespace +".update", relationTblanguage);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTblanguageCount(RelationTblanguageQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationTblanguageCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTblanguage> queryRelationTblanguageListWithPage(RelationTblanguageQuery queryBean) {
        return (List<RelationTblanguage>) queryForList(namespace +".queryRelationTblanguageListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTblanguage configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTblanguage getRelationTblanguageById(Long id) {
        return (RelationTblanguage) queryForObject(namespace +".getRelationTblanguageById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationTblanguage relationTblanguage) {
        int count = (Integer) queryForObject(namespace +".exist", relationTblanguage);
        return count > 0;
    }
}
