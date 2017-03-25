package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * UserRoleService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface UserRoleService {

    /**
     * 批量增加对象信息
     * 
     * @param userRoleList
     * @return
     */
    public boolean insert(List<UserRole> userRoleList);

    /**
     * 单个增加对象信息
     * 
     * @param userRole
     * @return
     */
    public boolean insert(UserRole userRole);

    /**
     * 更新 对象信息
     * 
     * @param userRole
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(UserRole userRole);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<UserRole> queryUserRoleList(UserRoleQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<UserRole> queryUserRoleListWithPage(UserRoleQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param userRole
     *            　
     * @return
     */
    public boolean delete(UserRole userRole);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public UserRole getUserRoleById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param userRoles
     *            UserRole集合
     * @return
     */
    public boolean delete(UserRole[] userRoles);
}
