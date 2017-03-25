package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * RoleResourceService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RoleResourceService {

    /**
     * 批量增加对象信息
     * 
     * @param roleResourceList
     * @return
     */
    public boolean insert(List<RoleResource> roleResourceList);

    /**
     * 单个增加对象信息
     * 
     * @param roleResource
     * @return
     */
    public boolean insert(RoleResource roleResource);

    /**
     * 更新 对象信息
     * 
     * @param roleResource
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RoleResource roleResource);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RoleResource> queryRoleResourceList(RoleResourceQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RoleResource> queryRoleResourceListWithPage(RoleResourceQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param roleResource
     *            　
     * @return
     */
    public boolean delete(RoleResource roleResource);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RoleResource getRoleResourceById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param roleResources
     *            RoleResource集合
     * @return
     */
    public boolean delete(RoleResource[] roleResources);
}
