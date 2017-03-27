package com.letv.tbtSps.service;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.tbtSps.domain.query.TreeDomain;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.wmscommon.dto.PagedQueryDto;
import com.letv.wmscommon.dto.PagedResultDto;
import java.util.List;

/**
 * ResourceService接口
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface ResourceService {

    /**
     * 批量增加对象信息
     * 
     * @param resourceList
     * @return
     */
    public boolean batchInsert(List<Resource> resourceList);

    /**
     * 单个增加对象信息
     * 
     * @param resource
     * @return
     */
    public LetvResponse<Resource> insert(Resource resource);

    /**
     * 更新 对象信息
     * 
     * @param resource
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(Resource resource);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Resource> queryResourceList(ResourceQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     *
     * @return
     */
    public PagedResultDto<Resource> queryResourceListWithPage(PagedQueryDto<ResourceQuery> pagedQuery);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param resource
     *            　
     * @return
     */
    public boolean delete(Resource resource);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public Resource getResourceById( Long id);


    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param resources
     *            Resource集合
     * @return
     */
    public boolean batchDelete(Resource[] resources);


    /*************************************以上是生成代码****************************************************/

    /**
     * 根据用户名称，查询用户拥有的资源列表
     * @param userQuery
     * @return
     */
    public LetvResponse<List<Resource>> searchResourceByUserName(UserQuery userQuery);


    /**
     * 根据资源编码集合查询资源
     * @param queryBean
     * @return
     */
    public LetvResponse<List<TreeDomain>> queryResourceListByCodes(ResourceQuery queryBean);

    /**
     * 根据用户编码和资源，验证用户是否拥有该资源的权限
     * @param resourceQuery
     * @return
     */
    public LetvResponse<Boolean> isPermitted(ResourceQuery resourceQuery) ;


    /**
     * 查询某个节点以及节点的子节点
     * @param queryBean
     * @return
     */
    public LetvResponse<List<TreeDomain>> queryResourceListByLikeCode(ResourceQuery queryBean);

    /**
     * 删除资源节点及其下所有子节点
     * @param resource
     * @return
     */
    public LetvResponse<Boolean> deleteTreeNode(Resource resource) ;

    /**
     *根据资源层级查询资源信息
     * @return
     */
    public LetvResponse<List<Resource>>  queryResourceByLevel(ResourceQuery resourceQuery);

    /**
     * 插入资源以及资源对应的角色
     * @param resource
     * @return
     */
    public LetvResponse<Boolean> addResourceAndBelongRole(Resource resource);

    public LetvResponse<Boolean> insertRequestURL(Resource resource) ;

    public LetvResponse<Resource> getResourceByCode(String resourceCode);
}
