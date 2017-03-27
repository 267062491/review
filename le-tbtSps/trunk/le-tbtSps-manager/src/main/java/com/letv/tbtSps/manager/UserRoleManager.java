package com.letv.tbtSps.manager;

import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.wmscommon.dto.PageUtil;

import java.util.List;

/**
 * UserRoleManager接口
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface UserRoleManager {

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
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryUserRoleCount(UserRoleQuery queryBean);

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

    /**
     * 判断是否存在
     * 
     * @param userRole
     * @return
     */
    public boolean exist(UserRole userRole);



    public boolean batchUpdate(String userCode, String[] roleIds, String createUser);
}
