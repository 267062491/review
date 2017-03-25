package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;
/**
 * UserRoleDao接口<br/>
 * 对'用户-角色'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface UserRoleDao {
    
    /**
     * 新增对象
     * 
     * @param userRole 
     * @return
     */
    public boolean insert(UserRole userRole);

    /**
     * 更新对象
     * 
     * @param userRole
     * @return
     */
    public boolean update(UserRole userRole);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<UserRole> queryUserRoleList(UserRoleQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryUserRoleCount(UserRoleQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<UserRole> queryUserRoleListWithPage(UserRoleQuery queryBean);

    /**
     * 删除记录
     * 
     * @param userRole
     * @return
     */
    public boolean delete(UserRole userRole);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public UserRole getUserRoleById(Long id);

    /**
     * 判断是否存在
     * 
     * @param userRole
     * @return
     */
    public boolean exist(UserRole userRole);

}
