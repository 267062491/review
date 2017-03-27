package com.letv.tbtSps.service;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.wmscommon.dto.PagedQueryDto;
import com.letv.wmscommon.dto.PagedResultDto;
import java.util.List;

/**
 * RoleService接口
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface RoleService {

    /**
     * 批量增加对象信息
     * 
     * @param roleList
     * @return
     */
    public boolean batchInsert(List<Role> roleList);

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
     * @return
     */
    public PagedResultDto<Role> queryRoleListWithPage(PagedQueryDto<RoleQuery> pagedQuery);

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
    public Role getRoleById( Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param roles
     *            Role集合
     * @return
     */
    public boolean batchDelete(Role[] roles);


    /**
     * 根据用户编码查询用户拥有的角色
     * @param userRoleQuery
     * @return
     */
    public LetvResponse<List<Role>> queryRoleListByUserCode(UserRoleQuery userRoleQuery);

    /**
     * 根据用编码， 查询用户可选择的角色
     * @param userRoleQuery
     * @return
     */
    public  LetvResponse<List<Role>> queryChooseRoleListByUserCode(UserRoleQuery userRoleQuery) ;

    /**
     * 根据角色编码查询角色信息
     * @param roleQuery
     * @return
     */
    public LetvResponse<Role> getRoleByCode(RoleQuery roleQuery);

    /**
     * 插入角色
     * @param role
     * @return
     */
    public LetvResponse<Boolean> insertRole(Role role);

    /**
     * 更新角色
     * @param role
     * @return
     */
    public LetvResponse<Boolean> updateRole(Role role);

    /**
     * 根据角色编码删除角色
     * @param role
     * @return
     */
    public LetvResponse<Boolean> deleteRole(Role role);

    /**
     * 查询角色列表
     * @param roleQuery
     * @return
     */
    public LetvResponse<List<Role>> queryRoleListLe(RoleQuery roleQuery);

    /**
     * 启用、禁用 角色
     * @param role
     * @return
     */
    public LetvResponse<Boolean> enableOrDisable(Role role);

}
