package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.InternationalStandardDao;
import com.letv.tbtSps.domain.InternationalStandard;
import com.letv.tbtSps.domain.query.InternationalStandardQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * InternationalStandardDAO实现类<br/>
 * 对'国际标准'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class InternationalStandardDaoImpl extends BaseDao implements InternationalStandardDao {
    /** namespace */
    private final String namespace = InternationalStandardDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<InternationalStandard> queryInternationalStandardList(InternationalStandardQuery queryBean) {
        return (List<InternationalStandard>) queryForList(namespace +".queryInternationalStandardList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(InternationalStandard internationalStandard) {
        return insert(namespace +".insert", internationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(InternationalStandard internationalStandard) {
        return update(namespace +".update", internationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public int queryInternationalStandardCount(InternationalStandardQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryInternationalStandardCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<InternationalStandard> queryInternationalStandardListWithPage(InternationalStandardQuery queryBean) {
        return (List<InternationalStandard>) queryForList(namespace +".queryInternationalStandardListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(InternationalStandard configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public InternationalStandard getInternationalStandardById(Long id) {
        return (InternationalStandard) queryForObject(namespace +".getInternationalStandardById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(InternationalStandard internationalStandard) {
        int count = (Integer) queryForObject(namespace +".exist", internationalStandard);
        return count > 0;
    }
}
