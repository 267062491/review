package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.SpsInfoLogDao;
import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.query.SpsInfoLogQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * SpsInfoLogDAO实现类<br/>
 * 对'sps信息操作日志表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class SpsInfoLogDaoImpl extends BaseDao implements SpsInfoLogDao {
    /** namespace */
    private final String namespace = SpsInfoLogDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<SpsInfoLog> querySpsInfoLogList(SpsInfoLogQuery queryBean) {
        return (List<SpsInfoLog>) queryForList(namespace +".querySpsInfoLogList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(SpsInfoLog spsInfoLog) {
        return insert(namespace +".insert", spsInfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(SpsInfoLog spsInfoLog) {
        return update(namespace +".update", spsInfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsInfoLogCount(SpsInfoLogQuery queryBean) {
        return (Integer) queryForObject(namespace +".querySpsInfoLogCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsInfoLog> querySpsInfoLogListWithPage(SpsInfoLogQuery queryBean) {
        return (List<SpsInfoLog>) queryForList(namespace +".querySpsInfoLogListWithPage", queryBean);
    }

    public List<SpsInfoLog> queryExperts(SpsInfoLogQuery queryBean) {
        return (List<SpsInfoLog>) queryForList(namespace +".queryExperts", queryBean);
    }

    public List<SpsInfoLog> queryUnReviewLog(SpsInfoLogQuery queryBean) {
        return (List<SpsInfoLog>) queryForList(namespace +".queryUnReviewLog", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsInfoLog configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public SpsInfoLog getSpsInfoLogById(Long id) {
        return (SpsInfoLog) queryForObject(namespace +".getSpsInfoLogById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(SpsInfoLog spsInfoLog) {
        int count = (Integer) queryForObject(namespace +".exist", spsInfoLog);
        return count > 0;
    }
    /**
     * 根据spsCode修改canEdit状态
     * @param spsInfoLog
     * @return
     */
    public boolean updateCanEditBySpsCode(SpsInfoLog spsInfoLog) {
        return update(namespace +".updateCanEditBySpsCode", spsInfoLog);
    }
}
