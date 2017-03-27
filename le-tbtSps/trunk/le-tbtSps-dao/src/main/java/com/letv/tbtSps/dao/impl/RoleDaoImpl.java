package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RoleDao;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleDAO实现类<br/>
 * 对'角色表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Repository
public class RoleDaoImpl extends BaseDao implements RoleDao {
    /** namespace */
    private final String namespace = RoleDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<Role> queryRoleList(RoleQuery queryBean) {
        return (List<Role>) queryForList(namespace +".queryRoleList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(Role role) {
        return insert(namespace +".insert", role);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(Role role) {
        return update(namespace +".update", role);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRoleCount(RoleQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRoleCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Role> queryRoleListWithPage(RoleQuery queryBean) {
        return (List<Role>) queryForList(namespace +".queryRoleListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Role configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public Role getRoleById(Long id) {
        return (Role) queryForObject(namespace +".getRoleById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(Role role) {
        int count = (Integer) queryForObject(namespace +".exist", role);
        return count > 0;
    }

    /**
     * 根据用户编码查询用户拥有的角色
     * @param userRoleQuery
     * @return
     */
    public List<Role> queryRoleListByUserCode(UserRoleQuery userRoleQuery){
        return (List<Role>) queryForList(namespace +".queryRoleListByUserCode", userRoleQuery);
    }

    /**
     * 根据用编码， 查询用户可选择的角色
     * @param userRoleQuery
     * @return
     */
    public List<Role> queryChooseRoleListByUserCode(UserRoleQuery userRoleQuery){
        return (List<Role>) queryForList(namespace +".queryChooseRoleListByUserCode", userRoleQuery);
    }

    /**
     * 根据角色编码删除
     * @param configuration
     * @return
     */
    public boolean deleteByCode(Role configuration) {
        return delete(namespace +".deleteByCode", configuration);
    }
    /**
     * 按照角色角色编码集合查询角色信息
     * @param roleQuery
     * @return
     */
    @Override
    public List<Role> queryRoleListByUserCodes(RoleQuery roleQuery) {
        return (List<Role>) queryForList(namespace +".queryRoleListByUserCodes", roleQuery);
    }
    /**
     * 根据角色编码查询角色信息
     * @param list
     * @return
     */
    @Override
    public List<Role> queryRoleListByRoleCodes(List<String> list) {
        return (List<Role>) queryForList(namespace +".queryRoleListByRoleCodes", list);
    }
    /**
     * 启用、禁用角色
     * @param role
     * @return
     */
    @Override
    public boolean enableOrDisable(Role role) {
        return delete(namespace +".enableOrDisable", role);
    }

}
