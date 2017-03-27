package com.letv.tbtSps.service;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.domain.query.TreeDomain;
import com.letv.wmscommon.dto.PagedQueryDto;
import com.letv.wmscommon.dto.PagedResultDto;
import java.util.List;

/**
 * RoleResourceService接口
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface RoleResourceService {

    /**
     * 批量增加对象信息
     * 
     * @param roleResourceList
     * @return
     */
    public boolean batchInsert(List<RoleResource> roleResourceList);

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
     * @return
     */
    public PagedResultDto<RoleResource> queryRoleResourceListWithPage(PagedQueryDto<RoleResourceQuery> pagedQuery);

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
    public RoleResource getRoleResourceById( Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param roleResources
     *            RoleResource集合
     * @return
     */
    public boolean batchDelete(RoleResource[] roleResources);


    /*****************************以上是生成代码*************************************************************/
    /**
     * 根据用编码获取用户拥有的资源
     * @param queryBean
     * @return
     */
    public LetvResponse<List<RoleResource>> queryRoleResourceListByUserCode(RoleResourceQuery queryBean);

    /**
     * 根据角色编码， 获取角色拥有的资源 ,按照rf和pc分开返回
     * @param queryBean
     * @return
     */
    public LetvResponse<List<TreeDomain>> queryRoleResourceByRoleCode(RoleResourceQuery queryBean);

    /**
     * 修改某个角色拥有的所有资源
     * @param roleResource
     * @return
     */
    public LetvResponse<Boolean> updateRoleResource(RoleResource roleResource) ;
}
