package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.LanguageDao;
import com.letv.tbtSps.domain.Language;
import com.letv.tbtSps.domain.query.LanguageQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * LanguageDAO实现类<br/>
 * 对'原文语种'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class LanguageDaoImpl extends BaseDao implements LanguageDao {
    /** namespace */
    private final String namespace = LanguageDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<Language> queryLanguageList(LanguageQuery queryBean) {
        return (List<Language>) queryForList(namespace +".queryLanguageList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(Language language) {
        return insert(namespace +".insert", language);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(Language language) {
        return update(namespace +".update", language);
    }

    /**
     * {@inheritDoc}
     */
    public int queryLanguageCount(LanguageQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryLanguageCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Language> queryLanguageListWithPage(LanguageQuery queryBean) {
        return (List<Language>) queryForList(namespace +".queryLanguageListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Language configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public Language getLanguageById(Long id) {
        return (Language) queryForObject(namespace +".getLanguageById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(Language language) {
        int count = (Integer) queryForObject(namespace +".exist", language);
        return count > 0;
    }
}
