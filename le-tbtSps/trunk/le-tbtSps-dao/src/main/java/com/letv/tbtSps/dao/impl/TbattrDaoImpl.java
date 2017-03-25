package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.TbattrDao;
import com.letv.tbtSps.domain.Tbattr;
import com.letv.tbtSps.domain.query.TbattrQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * TbattrDAO实现类<br/>
 * 对'tbt信息表对应的附件'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class TbattrDaoImpl extends BaseDao implements TbattrDao {
    /** namespace */
    private final String namespace = TbattrDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<Tbattr> queryTbattrList(TbattrQuery queryBean) {
        return (List<Tbattr>) queryForList(namespace +".queryTbattrList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(Tbattr tbattr) {
        return insert(namespace +".insert", tbattr);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(Tbattr tbattr) {
        return update(namespace +".update", tbattr);
    }

    /**
     * {@inheritDoc}
     */
    public int queryTbattrCount(TbattrQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryTbattrCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Tbattr> queryTbattrListWithPage(TbattrQuery queryBean) {
        return (List<Tbattr>) queryForList(namespace +".queryTbattrListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Tbattr configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public Tbattr getTbattrById(Long id) {
        return (Tbattr) queryForObject(namespace +".getTbattrById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(Tbattr tbattr) {
        int count = (Integer) queryForObject(namespace +".exist", tbattr);
        return count > 0;
    }
}
