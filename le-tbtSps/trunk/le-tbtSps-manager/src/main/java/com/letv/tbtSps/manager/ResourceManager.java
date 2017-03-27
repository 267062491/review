package com.letv.tbtSps.manager;

import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.wmscommon.dto.PageUtil;

import java.util.List;

/**
 * ResourceManager接口
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface ResourceManager {

    /**
     * 批量增加对象信息
     * 
     * @param resourceList
     * @return
     */
    public boolean insert(List<Resource> resourceList);

    /**
     * 单个增加对象信息
     * 
     * @param resource
     * @return
     */
    public boolean insert(Resource resource);

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
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<Resource> queryResourceListWithPage(ResourceQuery queryBean,
                                                    PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryResourceCount(ResourceQuery queryBean);

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
    public Resource getResourceById(Long id);

    public Resource getResourceByCode(String resourceCode);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param resources
     *            Resource集合
     * @return
     */
    public boolean delete(Resource[] resources);

    /**
     * 判断是否存在
     * 
     * @param resource
     * @return
     */
    public boolean exist(Resource resource);

    /**
     * 根据资源编码集合查询资源
     * @param queryBean
     * @return
     */
    public List<Resource> queryResourceListByCodes(ResourceQuery queryBean) ;

    /**
     * 查询某个节点以及节点的子节点
     * @param queryBean
     * @return
     */
    public List<Resource> queryResourceListByLikeCode(ResourceQuery queryBean) ;

    /**
     * 删除资源节点及其下所有子节点
     * @param resource
     * @return
     */
    public boolean deleteTreeNode(Resource resource);

    /**
     * 插入资源以及资源对应的角色
     * @param resource
     * @return
     */
    public boolean insertResourceAndBelongRole(Resource resource);

    public boolean insertRequestURL(Resource resource);
}
