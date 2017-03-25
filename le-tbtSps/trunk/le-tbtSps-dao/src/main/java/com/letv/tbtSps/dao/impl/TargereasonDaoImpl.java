package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.TargereasonDao;
import com.letv.tbtSps.domain.Targereason;
import com.letv.tbtSps.domain.query.TargereasonQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * TargereasonDAO实现类<br/>
 * 对'目标理由'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class TargereasonDaoImpl extends BaseDao implements TargereasonDao {
    /** namespace */
    private final String namespace = TargereasonDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<Targereason> queryTargereasonList(TargereasonQuery queryBean) {
        return (List<Targereason>) queryForList(namespace +".queryTargereasonList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(Targereason targereason) {
        return insert(namespace +".insert", targereason);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(Targereason targereason) {
        return update(namespace +".update", targereason);
    }

    /**
     * {@inheritDoc}
     */
    public int queryTargereasonCount(TargereasonQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryTargereasonCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Targereason> queryTargereasonListWithPage(TargereasonQuery queryBean) {
        return (List<Targereason>) queryForList(namespace +".queryTargereasonListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Targereason configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public Targereason getTargereasonById(Long id) {
        return (Targereason) queryForObject(namespace +".getTargereasonById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(Targereason targereason) {
        int count = (Integer) queryForObject(namespace +".exist", targereason);
        return count > 0;
    }
}
