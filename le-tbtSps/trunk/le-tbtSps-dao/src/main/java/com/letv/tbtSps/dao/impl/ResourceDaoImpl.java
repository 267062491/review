package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.ResourceDao;
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * ResourceDAO实现类<br/>
 * 对'资源表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class ResourceDaoImpl extends BaseDao implements ResourceDao {
    /** namespace */
    private final String namespace = ResourceDaoImpl.class.getName();
    
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
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(Resource resource) {
        int count = (Integer) queryForObject(namespace +".exist", resource);
        return count > 0;
    }
}
