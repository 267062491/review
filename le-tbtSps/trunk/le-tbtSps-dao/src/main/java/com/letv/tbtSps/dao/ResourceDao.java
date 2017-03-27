package com.letv.tbtSps.dao;


import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;

import java.util.List;

/**
 * ResourceDao接口<br/>
 * 对'资源表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface ResourceDao {
    
    /**
     * 新增对象
     * 
     * @param resource 
     * @return
     */
    public boolean insert(Resource resource);

    /**
     * 更新对象
     * 
     * @param resource
     * @return
     */
    public boolean update(Resource resource);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Resource> queryResourceList(ResourceQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryResourceCount(ResourceQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Resource> queryResourceListWithPage(ResourceQuery queryBean);

    /**
     * 删除记录
     * 
     * @param resource
     * @return
     */
    public boolean delete(Resource resource);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public Resource getResourceById(Long id);


    public Resource getResourceByCode(String resourceCode);

    /**
     * 判断是否存在
     * 
     * @param resource
     * @return
     */
    public boolean exist(Resource resource);


    public  List<Resource> iterative(String parentCode_);

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
     * 根据父节点查询父节点的一级子节点
     * @param queryBean
     * @return
     */
    public List<Resource> queryResourceListByParentCode(ResourceQuery queryBean) ;


    /**
     * 删除资源节点及其下所有子节点
     * @param resource
     * @return
     */
    public boolean deleteTreeNode(Resource resource);

}
