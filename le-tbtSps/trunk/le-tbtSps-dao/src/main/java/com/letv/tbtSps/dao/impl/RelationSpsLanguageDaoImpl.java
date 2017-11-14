package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationSpsLanguageDao;
import com.letv.tbtSps.domain.RelationSpsLanguage;
import com.letv.tbtSps.domain.query.RelationSpsLanguageQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationSpsLanguageDAO实现类<br/>
 * 对'sps信息表-原文语种关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationSpsLanguageDaoImpl extends BaseDao implements RelationSpsLanguageDao {
    /** namespace */
    private final String namespace = RelationSpsLanguageDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationSpsLanguage> queryRelationSpsLanguageList(RelationSpsLanguageQuery queryBean) {
        return (List<RelationSpsLanguage>) queryForList(namespace +".queryRelationSpsLanguageList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationSpsLanguage relationSpsLanguage) {
        return insert(namespace +".insert", relationSpsLanguage);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationSpsLanguage relationSpsLanguage) {
        return update(namespace +".update", relationSpsLanguage);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsLanguageCount(RelationSpsLanguageQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationSpsLanguageCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsLanguage> queryRelationSpsLanguageListWithPage(RelationSpsLanguageQuery queryBean) {
        return (List<RelationSpsLanguage>) queryForList(namespace +".queryRelationSpsLanguageListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsLanguage configuration) {
        return delete(namespace +".delete", configuration);
    }
    public boolean deleteByCode(RelationSpsLanguage configuration) {
        return delete(namespace +".deleteByCode", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsLanguage getRelationSpsLanguageById(Long id) {
        return (RelationSpsLanguage) queryForObject(namespace +".getRelationSpsLanguageById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationSpsLanguage relationSpsLanguage) {
        int count = (Integer) queryForObject(namespace +".exist", relationSpsLanguage);
        return count > 0;
    }
}
