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
    /**
     * 根据用户登录名或者中文名称查询用户信息
     * @param queryBean
     * @return
     */
    @Override
    public List<User> searchUserByCodeOrName(UserQuery queryBean) {
        return (List<User>) queryForList(namespace +".searchUserByCodeOrName", queryBean);
    }

    /**
     * 根据角色编码联合查询用户数
     *
     * @param queryBean
     * @return
     */
    @Override
    public int queryUserCountByRoleCode(UserQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryUserCountByRoleCode", queryBean);
    }

    /**
     * 根据角色编码联合查询用户，翻页
     *
     * @param queryBean
     * @return
     */
    @Override
    public List<User> queryUserByRoleCode(UserQuery queryBean) {
        return (List<User>) queryForList(namespace +".queryUserByRoleCode", queryBean);
    }

    public List<User> queryUserByRoleCodeNoPage(UserQuery queryBean) {
        return (List<User>) queryForList(namespace +".queryUserByRoleCodeNoPage", queryBean);
    }

    /**
     * 启用、禁用用户
     * @param user
     * @return
     */
    @Override
    public boolean enableOrDisable(User user) {
        return delete(namespace +".enableOrDisable", user);
    }
    /**
     * 根据用编码删除用户
     * @param user
     * @return
     */
    @Override
    public boolean deleteByUserCode(User user) {
        return delete(namespace +".deleteByUserCode", user);
    }
    /**
     * 根据用户编码删除
     * @param user
     * @return
     */
    public boolean updateByUserCode(User user) {
        return update(namespace +".updateByUserCode", user);
    }
    /**
     * 根据用户名集合查询用户信息
     * @param list
     * @return
     */
    @Override
    public List<User> searchUserByUserNames(List<String> list) {
        return (List<User>) queryForList(namespace +".searchUserByUserNames", list);
    }

    /**
     * 根据用户编码查询用户信息 ， 批量接口
     * @param queryBean
     * @return
     */
    @Override
    public List<User> searchUserByCodes(UserQuery queryBean) {
        return (List<User>) queryForList(namespace +".searchUserByCodes", queryBean);
    }
}
