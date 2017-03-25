package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.UserDao;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.UserQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * UserDAO实现类<br/>
 * 对'用户表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    /** namespace */
    private final String namespace = UserDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<User> queryUserList(UserQuery queryBean) {
        return (List<User>) queryForList(namespace +".queryUserList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(User user) {
        return insert(namespace +".insert", user);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(User user) {
        return update(namespace +".update", user);
    }

    /**
     * {@inheritDoc}
     */
    public int queryUserCount(UserQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryUserCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<User> queryUserListWithPage(UserQuery queryBean) {
        return (List<User>) queryForList(namespace +".queryUserListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(User configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public User getUserById(Long id) {
        return (User) queryForObject(namespace +".getUserById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(User user) {
        int count = (Integer) queryForObject(namespace +".exist", user);
        return count > 0;
    }
}
