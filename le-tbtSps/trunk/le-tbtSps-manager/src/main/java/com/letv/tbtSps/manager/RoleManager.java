package com.letv.tbtSps.manager;

import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.wmscommon.dto.PageUtil;

import java.util.List;

/**
 * RoleManager接口
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface RoleManager {

    /**
     * 批量增加对象信息
     * 
     * @param roleList
     * @return
     */
    public boolean insert(List<Role> roleList);

    /**
     * 单个增加对象信息
     * 
     * @param role
     * @return
     */
    public boolean insert(Role role);

    /**
     * 更新 对象信息
     * 
     * @param role
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(Role role);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Role> queryRoleList(RoleQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<Role> queryRoleListWithPage(RoleQuery queryBean,
                                            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryRoleCount(RoleQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param role
     *            　
     * @return
     */
    public boolean delete(Role role);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public Role getRoleById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param roles
     *            Role集合
     * @return
     */
    public boolean delete(Role[] roles);

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
