package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.CountryDao;
import com.letv.tbtSps.domain.Country;
import com.letv.tbtSps.domain.query.CountryQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * CountryDAO实现类<br/>
 * 对'通报成员'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class CountryDaoImpl extends BaseDao implements CountryDao {
    /** namespace */
    private final String namespace = CountryDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<Country> queryCountryList(CountryQuery queryBean) {
        return (List<Country>) queryForList(namespace +".queryCountryList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(Country country) {
        return insert(namespace +".insert", country);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(Country country) {
        return update(namespace +".update", country);
    }

    /**
     * {@inheritDoc}
     */
    public int queryCountryCount(CountryQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryCountryCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Country> queryCountryListWithPage(CountryQuery queryBean) {
        return (List<Country>) queryForList(namespace +".queryCountryListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Country configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public Country getCountryById(Long id) {
        return (Country) queryForObject(namespace +".getCountryById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(Country country) {
        int count = (Integer) queryForObject(namespace +".exist", country);
        return count > 0;
    }
}
