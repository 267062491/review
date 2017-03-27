package com.letv.tbtSps.dao;


import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.UserRoleQuery;

import java.util.List;

/**
 * RoleDao接口<br/>
 * 对'角色表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
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


    /**
     * 根据用户编码查询用户拥有的角色
     * @param userRoleQuery
     * @return
     */
    public List<Role> queryRoleListByUserCode(UserRoleQuery userRoleQuery);

    /**
     * 根据用编码， 查询用户可选择的角色
     * @param userRoleQuery
     * @return
     */
    public List<Role> queryChooseRoleListByUserCode(UserRoleQuery userRoleQuery);

    /**
     * 根据角色编码删除
     * @param configuration
     * @return
     */
    public boolean deleteByCode(Role configuration);

    /**
     * 按照角色角色编码集合查询角色信息
     * @param roleQuery
     * @return
     */
    public List<Role> queryRoleListByUserCodes(RoleQuery roleQuery);

    /**
     * 根据角色编码查询角色信息
     * @param list
     * @return
     */
    public List<Role> queryRoleListByRoleCodes(List<String> list);

    /**
     * 启用、禁用角色
     * @param role
     * @return
     */
    public boolean enableOrDisable(Role role);

}
