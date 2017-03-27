package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RoleResourceDao;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleResourceDAO实现类<br/>
 * 对'角色-资源'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
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

    /**
     * 根据用编码获取用户拥有的资源
     * @param queryBean
     * @return
     */
    @Override
    public List<RoleResource> queryRoleResourceListByUserCode(RoleResourceQuery queryBean) {
        return (List<RoleResource>) queryForList(namespace +".queryRoleResourceListByUserCode", queryBean);
    }
    /**
     * 根据角色编码删除角色下的资源
     * @param roleResource
     * @return
     */
    @Override
    public boolean deleteByRoleCode(RoleResource roleResource) {
        return delete(namespace +".deleteByRoleCode", roleResource);
    }

    /**
     * 根据资源编码和角色批量查询角色资源
     * @param queryBean
     * @return
     */
    @Override
    public List<RoleResource> queryRoleResourceByRoleAndResourceCode(RoleResourceQuery queryBean) {
        return (List<RoleResource>) queryForList(namespace +".queryRoleResourceByRoleAndResourceCode", queryBean);
    }

    /**
     * 批量插入
     * @param list_roleResource
     * @return
     */
    @Override
    public boolean insertBatch(List<RoleResource> list_roleResource) {
        return this.getSqlSession().insert(namespace + ".insertBatch", list_roleResource) == list_roleResource.size();
    }
}
