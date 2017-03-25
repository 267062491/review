package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.SpsAttrDao;
import com.letv.tbtSps.domain.SpsAttr;
import com.letv.tbtSps.domain.query.SpsAttrQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * SpsAttrDAO实现类<br/>
 * 对'sps信息表对应的附件'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class SpsAttrDaoImpl extends BaseDao implements SpsAttrDao {
    /** namespace */
    private final String namespace = SpsAttrDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<SpsAttr> querySpsAttrList(SpsAttrQuery queryBean) {
        return (List<SpsAttr>) queryForList(namespace +".querySpsAttrList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(SpsAttr spsAttr) {
        return insert(namespace +".insert", spsAttr);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(SpsAttr spsAttr) {
        return update(namespace +".update", spsAttr);
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsAttrCount(SpsAttrQuery queryBean) {
        return (Integer) queryForObject(namespace +".querySpsAttrCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsAttr> querySpsAttrListWithPage(SpsAttrQuery queryBean) {
        return (List<SpsAttr>) queryForList(namespace +".querySpsAttrListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsAttr configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public SpsAttr getSpsAttrById(Long id) {
        return (SpsAttr) queryForObject(namespace +".getSpsAttrById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(SpsAttr spsAttr) {
        int count = (Integer) queryForObject(namespace +".exist", spsAttr);
        return count > 0;
    }
}
