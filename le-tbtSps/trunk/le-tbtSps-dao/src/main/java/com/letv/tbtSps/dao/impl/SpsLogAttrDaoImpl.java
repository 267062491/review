package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.SpsLogAttrDao;
import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.domain.query.SpsLogAttrQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * SpsLogAttrDAO实现类<br/>
 * 对'sps评审信息表对应的附件'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class SpsLogAttrDaoImpl extends BaseDao implements SpsLogAttrDao {
    /** namespace */
    private final String namespace = SpsLogAttrDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<SpsLogAttr> querySpsLogAttrList(SpsLogAttrQuery queryBean) {
        return (List<SpsLogAttr>) queryForList(namespace +".querySpsLogAttrList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(SpsLogAttr spsLogAttr) {
        return insert(namespace +".insert", spsLogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(SpsLogAttr spsLogAttr) {
        return update(namespace +".update", spsLogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsLogAttrCount(SpsLogAttrQuery queryBean) {
        return (Integer) queryForObject(namespace +".querySpsLogAttrCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsLogAttr> querySpsLogAttrListWithPage(SpsLogAttrQuery queryBean) {
        return (List<SpsLogAttr>) queryForList(namespace +".querySpsLogAttrListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsLogAttr configuration) {
        return delete(namespace +".delete", configuration);
    }
    public boolean deleteByCode(SpsLogAttr configuration) {
        return delete(namespace +".deleteByCode", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public SpsLogAttr getSpsLogAttrById(Long id) {
        return (SpsLogAttr) queryForObject(namespace +".getSpsLogAttrById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(SpsLogAttr spsLogAttr) {
        int count = (Integer) queryForObject(namespace +".exist", spsLogAttr);
        return count > 0;
    }
}
