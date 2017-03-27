package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.UserRoleDao;
import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * UserRoleDAO实现类<br/>
 * 对'用户-角色'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class UserRoleDaoImpl extends BaseDao implements UserRoleDao {
    /** namespace */
    private final String namespace = UserRoleDaoImpl.class.getName();

    /**
     * {@inheritDoc}
     */
    public List<UserRole> queryUserRoleList(UserRoleQuery queryBean) {
        return (List<UserRole>) queryForList(namespace +".queryUserRoleList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(UserRole userRole) {
        return insert(namespace +".insert", userRole);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(UserRole userRole) {
        return update(namespace +".update", userRole);
    }

    /**
     * {@inheritDoc}
     */
    public int queryUserRoleCount(UserRoleQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryUserRoleCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<UserRole> queryUserRoleListWithPage(UserRoleQuery queryBean) {
        return (List<UserRole>) queryForList(namespace +".queryUserRoleListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(UserRole configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public UserRole getUserRoleById(Long id) {
        return (UserRole) queryForObject(namespace +".getUserRoleById", id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exist(UserRole userRole) {
        int count = (Integer) queryForObject(namespace +".exist", userRole);
        return count > 0;
    }

    /**
     * 根据用编码查询用户拥有的角色
     * @param queryBean
     * @return
     */
    @Override
    public List<UserRole> queryUserRoleListByUserCode(UserRoleQuery queryBean) {
        return (List<UserRole>) queryForList(namespace +".queryUserRoleListByUserCode", queryBean);
    }

    /**
     * 根据用户编码删除用户角色
     * @param queryBean
     * @return
     */
    @Override
    public boolean deleteUserRoleByUserCode(UserRoleQuery queryBean) {
        return update(namespace +".deleteUserRoleByUserCode", queryBean);
    }
}
