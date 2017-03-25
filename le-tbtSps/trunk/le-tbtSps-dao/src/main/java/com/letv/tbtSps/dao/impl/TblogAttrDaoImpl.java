package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.TblogAttrDao;
import com.letv.tbtSps.domain.TblogAttr;
import com.letv.tbtSps.domain.query.TblogAttrQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * TblogAttrDAO实现类<br/>
 * 对'tbt评审信息表对应的附件'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class TblogAttrDaoImpl extends BaseDao implements TblogAttrDao {
    /** namespace */
    private final String namespace = TblogAttrDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<TblogAttr> queryTblogAttrList(TblogAttrQuery queryBean) {
        return (List<TblogAttr>) queryForList(namespace +".queryTblogAttrList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(TblogAttr tblogAttr) {
        return insert(namespace +".insert", tblogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(TblogAttr tblogAttr) {
        return update(namespace +".update", tblogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public int queryTblogAttrCount(TblogAttrQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryTblogAttrCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<TblogAttr> queryTblogAttrListWithPage(TblogAttrQuery queryBean) {
        return (List<TblogAttr>) queryForList(namespace +".queryTblogAttrListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(TblogAttr configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public TblogAttr getTblogAttrById(Long id) {
        return (TblogAttr) queryForObject(namespace +".getTblogAttrById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(TblogAttr tblogAttr) {
        int count = (Integer) queryForObject(namespace +".exist", tblogAttr);
        return count > 0;
    }
}
