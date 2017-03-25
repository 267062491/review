package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RoleResourceDao;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RoleResourceDAO实现类<br/>
 * 对'角色-资源'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RoleResourceDaoImpl extends BaseDao implements RoleResourceDao {
    /** namespace */
    private final String namespace = RoleResourceDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RoleResource> queryRoleResourceList(RoleResourceQuery queryBean) {
        return (List<RoleResource>) queryForList(namespace +".queryRoleResourceList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RoleResource roleResource) {
        return insert(namespace +".insert", roleResource);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RoleResource roleResource) {
        return update(namespace +".update", roleResource);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRoleResourceCount(RoleResourceQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRoleResourceCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RoleResource> queryRoleResourceListWithPage(RoleResourceQuery queryBean) {
        return (List<RoleResource>) queryForList(namespace +".queryRoleResourceListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RoleResource configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RoleResource getRoleResourceById(Long id) {
        return (RoleResource) queryForObject(namespace +".getRoleResourceById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RoleResource roleResource) {
        int count = (Integer) queryForObject(namespace +".exist", roleResource);
        return count > 0;
    }
}
