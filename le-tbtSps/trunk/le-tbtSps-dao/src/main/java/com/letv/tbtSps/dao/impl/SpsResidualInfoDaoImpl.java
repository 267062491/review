package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.SpsResidualInfoDao;
import com.letv.tbtSps.domain.SpsResidualInfo;
import com.letv.tbtSps.domain.query.SpsResidualInfoQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * SpsResidualInfoDAO实现类<br/>
 * 对'sps残留量信息'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class SpsResidualInfoDaoImpl extends BaseDao implements SpsResidualInfoDao {
    /** namespace */
    private final String namespace = SpsResidualInfoDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<SpsResidualInfo> querySpsResidualInfoList(SpsResidualInfoQuery queryBean) {
        return (List<SpsResidualInfo>) queryForList(namespace +".querySpsResidualInfoList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(SpsResidualInfo spsResidualInfo) {
        return insert(namespace +".insert", spsResidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(SpsResidualInfo spsResidualInfo) {
        return update(namespace +".update", spsResidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsResidualInfoCount(SpsResidualInfoQuery queryBean) {
        return (Integer) queryForObject(namespace +".querySpsResidualInfoCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsResidualInfo> querySpsResidualInfoListWithPage(SpsResidualInfoQuery queryBean) {
        return (List<SpsResidualInfo>) queryForList(namespace +".querySpsResidualInfoListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsResidualInfo configuration) {
        return delete(namespace +".delete", configuration);
    }
    public boolean deleteByCode(SpsResidualInfo configuration) {
        return delete(namespace +".deleteByCode", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public SpsResidualInfo getSpsResidualInfoById(Long id) {
        return (SpsResidualInfo) queryForObject(namespace +".getSpsResidualInfoById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(SpsResidualInfo spsResidualInfo) {
        int count = (Integer) queryForObject(namespace +".exist", spsResidualInfo);
        return count > 0;
    }
}
