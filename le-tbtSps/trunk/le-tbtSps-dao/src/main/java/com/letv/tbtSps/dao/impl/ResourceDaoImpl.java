package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.dao.ResourceDao;
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ResourceDAO实现类<br/>
 * 对'资源表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Repository
public class ResourceDaoImpl extends BaseDao implements ResourceDao {
    /** namespace */
    private final String namespace = ResourceDaoImpl.class.getName();

    public static int count = 0 ;
    /**
     * {@inheritDoc}
     */
    public List<Resource> queryResourceList(ResourceQuery queryBean) {
        return (List<Resource>) queryForList(namespace +".queryResourceList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(Resource resource) {
        return insert(namespace +".insert", resource);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(Resource resource) {
        return update(namespace +".update", resource);
    }

    /**
     * {@inheritDoc}
     */
    public int queryResourceCount(ResourceQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryResourceCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Resource> queryResourceListWithPage(ResourceQuery queryBean) {
        return (List<Resource>) queryForList(namespace +".queryResourceListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Resource configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public Resource getResourceById(Long id) {
        return (Resource) queryForObject(namespace +".getResourceById", id);
    } 
    public Resource getResourceByCode(String resourceCode) {
        return (Resource) queryForObject(namespace +".getResourceByCode", resourceCode);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exist(Resource resource) {
        int count = (Integer) queryForObject(namespace +".exist", resource);
        return count > 0;
    }

    public List<Resource> iterative(String pId){
        ResourceQuery resourceQuery = new ResourceQuery();
        List<Resource> dataList = queryResourceList(resourceQuery);
        System.out.print(JsonHelper.toJson(dataList));
        return dataList ;
    }
    /**
     * 根据资源编码集合查询资源
     * @param queryBean
     * @return
     */
    @Override
    public List<Resource> queryResourceListByCodes(ResourceQuery queryBean) {
        return (List<Resource>) queryForList(namespace +".queryResourceListByCodes", queryBean);
    }
    /**
     * 查询某个节点以及节点的子节点
     * @param queryBean
     * @return
     */
    @Override
    public List<Resource> queryResourceListByLikeCode(ResourceQuery queryBean) {
        return (List<Resource>) queryForList(namespace +".queryResourceListByLikeCode", queryBean);
    }

    /**
     * 根据父节点查询父节点的一级子节点
     * @param queryBean
     * @return
     */
    @Override
    public List<Resource> queryResourceListByParentCode(ResourceQuery queryBean) {
        return (List<Resource>) queryForList(namespace +".queryResourceListByParentCode", queryBean);
    }

    /**
     * 删除资源节点及其下所有子节点
     * @param resource
     * @return
     */
    @Override
    public boolean deleteTreeNode(Resource resource) {
        return delete(namespace +".deleteTreeNode", resource);
    }
}
