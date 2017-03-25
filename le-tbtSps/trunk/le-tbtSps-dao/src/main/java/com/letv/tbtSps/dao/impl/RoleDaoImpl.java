package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RoleDao;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.query.RoleQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RoleDAO实现类<br/>
 * 对'角色表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
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
}
