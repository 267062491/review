package com.letv.tbtSps.manager;

import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.wmscommon.dto.PageUtil;

import java.util.List;

/**
 * RoleResourceManager接口
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface RoleResourceManager {

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
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryRoleResourceCount(RoleResourceQuery queryBean);

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
    public List<RoleResource> queryRoleResourceListByUserCode(RoleResourceQuery queryBean) ;

    /**
     * 修改角色拥有的资源
     * @param roleResource
     * @return
     */
    public boolean updateRoleResource(RoleResource roleResource);
}
