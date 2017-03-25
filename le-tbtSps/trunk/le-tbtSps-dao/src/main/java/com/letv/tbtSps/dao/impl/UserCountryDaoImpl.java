package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.UserCountryDao;
import com.letv.tbtSps.domain.UserCountry;
import com.letv.tbtSps.domain.query.UserCountryQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * UserCountryDAO实现类<br/>
 * 对'用户通报成员关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class UserCountryDaoImpl extends BaseDao implements UserCountryDao {
    /** namespace */
    private final String namespace = UserCountryDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<UserCountry> queryUserCountryList(UserCountryQuery queryBean) {
        return (List<UserCountry>) queryForList(namespace +".queryUserCountryList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(UserCountry userCountry) {
        return insert(namespace +".insert", userCountry);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(UserCountry userCountry) {
        return update(namespace +".update", userCountry);
    }

    /**
     * {@inheritDoc}
     */
    public int queryUserCountryCount(UserCountryQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryUserCountryCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<UserCountry> queryUserCountryListWithPage(UserCountryQuery queryBean) {
        return (List<UserCountry>) queryForList(namespace +".queryUserCountryListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(UserCountry configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public UserCountry getUserCountryById(Long id) {
        return (UserCountry) queryForObject(namespace +".getUserCountryById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(UserCountry userCountry) {
        int count = (Integer) queryForObject(namespace +".exist", userCountry);
        return count > 0;
    }
}
