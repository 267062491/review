package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.TbinfoLogDao;
import com.letv.tbtSps.domain.TbinfoLog;
import com.letv.tbtSps.domain.query.TbinfoLogQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * TbinfoLogDAO实现类<br/>
 * 对'tbt信息操作日志表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class TbinfoLogDaoImpl extends BaseDao implements TbinfoLogDao {
    /** namespace */
    private final String namespace = TbinfoLogDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<TbinfoLog> queryTbinfoLogList(TbinfoLogQuery queryBean) {
        return (List<TbinfoLog>) queryForList(namespace +".queryTbinfoLogList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(TbinfoLog tbinfoLog) {
        return insert(namespace +".insert", tbinfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(TbinfoLog tbinfoLog) {
        return update(namespace +".update", tbinfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public int queryTbinfoLogCount(TbinfoLogQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryTbinfoLogCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<TbinfoLog> queryTbinfoLogListWithPage(TbinfoLogQuery queryBean) {
        return (List<TbinfoLog>) queryForList(namespace +".queryTbinfoLogListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(TbinfoLog configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public TbinfoLog getTbinfoLogById(Long id) {
        return (TbinfoLog) queryForObject(namespace +".getTbinfoLogById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(TbinfoLog tbinfoLog) {
        int count = (Integer) queryForObject(namespace +".exist", tbinfoLog);
        return count > 0;
    }
}
