package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.TbresidualInfoDao;
import com.letv.tbtSps.domain.TbresidualInfo;
import com.letv.tbtSps.domain.query.TbresidualInfoQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * TbresidualInfoDAO实现类<br/>
 * 对'tbt残留量信息'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class TbresidualInfoDaoImpl extends BaseDao implements TbresidualInfoDao {
    /** namespace */
    private final String namespace = TbresidualInfoDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<TbresidualInfo> queryTbresidualInfoList(TbresidualInfoQuery queryBean) {
        return (List<TbresidualInfo>) queryForList(namespace +".queryTbresidualInfoList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(TbresidualInfo tbresidualInfo) {
        return insert(namespace +".insert", tbresidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(TbresidualInfo tbresidualInfo) {
        return update(namespace +".update", tbresidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public int queryTbresidualInfoCount(TbresidualInfoQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryTbresidualInfoCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<TbresidualInfo> queryTbresidualInfoListWithPage(TbresidualInfoQuery queryBean) {
        return (List<TbresidualInfo>) queryForList(namespace +".queryTbresidualInfoListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(TbresidualInfo configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public TbresidualInfo getTbresidualInfoById(Long id) {
        return (TbresidualInfo) queryForObject(namespace +".getTbresidualInfoById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(TbresidualInfo tbresidualInfo) {
        int count = (Integer) queryForObject(namespace +".exist", tbresidualInfo);
        return count > 0;
    }
}
