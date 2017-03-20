package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.SystemParDao;
import com.letv.tbtSps.domain.SystemPar;
import com.letv.tbtSps.domain.query.SystemParQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * SystemParDAO实现类<br/>
 * 对'系统参数表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
@Repository
public class SystemParDaoImpl extends BaseDao implements SystemParDao {
    /** namespace */
    private final String namespace = SystemParDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<SystemPar> querySystemParList(SystemParQuery queryBean) {
        return (List<SystemPar>) queryForList(namespace +".querySystemParList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(SystemPar systemPar) {
        return insert(namespace +".insert", systemPar);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(SystemPar systemPar) {
        return update(namespace +".update", systemPar);
    }

    /**
     * {@inheritDoc}
     */
    public int querySystemParCount(SystemParQuery queryBean) {
        return (Integer) queryForObject(namespace +".querySystemParCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SystemPar> querySystemParListWithPage(SystemParQuery queryBean) {
        return (List<SystemPar>) queryForList(namespace +".querySystemParListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SystemPar configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public SystemPar getSystemParById(Long id) {
        return (SystemPar) queryForObject(namespace +".getSystemParById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(SystemPar systemPar) {
        int count = (Integer) queryForObject(namespace +".exist", systemPar);
        return count > 0;
    }
}
