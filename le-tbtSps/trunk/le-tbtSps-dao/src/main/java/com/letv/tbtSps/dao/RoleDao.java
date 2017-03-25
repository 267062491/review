package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.query.RoleQuery;
/**
 * RoleDao接口<br/>
 * 对'角色表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RoleDao {
    
    /**
     * 新增对象
     * 
     * @param role 
     * @return
     */
    public boolean insert(Role role);

    /**
     * 更新对象
     * 
     * @param role
     * @return
     */
    public boolean update(Role role);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Role> queryRoleList(RoleQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRoleCount(RoleQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Role> queryRoleListWithPage(RoleQuery queryBean);

    /**
     * 删除记录
     * 
     * @param role
     * @return
     */
    public boolean delete(Role role);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public Role getRoleById(Long id);

    /**
     * 判断是否存在
     * 
     * @param role
     * @return
     */
    public boolean exist(Role role);

}
