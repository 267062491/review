package com.letv.tbtSps.dao;


import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;

import java.util.List;

/**
 * RoleResourceDao接口<br/>
 * 对'角色-资源'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface RoleResourceDao {
    
    /**
     * 新增对象
     * 
     * @param roleResource 
     * @return
     */
    public boolean insert(RoleResource roleResource);

    /**
     * 更新对象
     * 
     * @param roleResource
     * @return
     */
    public boolean update(RoleResource roleResource);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RoleResource> queryRoleResourceList(RoleResourceQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRoleResourceCount(RoleResourceQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RoleResource> queryRoleResourceListWithPage(RoleResourceQuery queryBean);

    /**
     * 删除记录
     * 
     * @param roleResource
     * @return
     */
    public boolean delete(RoleResource roleResource);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RoleResource getRoleResourceById(Long id);

    /**
     * 判断是否存在
     * 
     * @param roleResource
     * @return
     */
    public boolean exist(RoleResource roleResource);

    /**
     * 根据用编码获取用户拥有的资源
     * @param queryBean
     * @return
     */
    public List<RoleResource> queryRoleResourceListByUserCode(RoleResourceQuery queryBean);

    /**
     * 根据角色编码删除角色下的资源
     * @param roleResource
     * @return
     */
    public boolean deleteByRoleCode(RoleResource roleResource);

    /**
     * 根据资源编码和角色批量查询角色资源
     * @param queryBean
     * @return
     */
    public List<RoleResource> queryRoleResourceByRoleAndResourceCode(RoleResourceQuery queryBean);

    /**
     * 批量插入
     * @param list_roleResource
     * @return
     */
    public boolean insertBatch(List<RoleResource> list_roleResource);


}
