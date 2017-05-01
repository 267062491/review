package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.SpsInfoDao;
import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.query.SpsInfoQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * SpsInfoDAO实现类<br/>
 * 对'sps信息表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class SpsInfoDaoImpl extends BaseDao implements SpsInfoDao {
    /** namespace */
    private final String namespace = SpsInfoDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<SpsInfo> querySpsInfoList(SpsInfoQuery queryBean) {
        return (List<SpsInfo>) queryForList(namespace +".querySpsInfoList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(SpsInfo spsInfo) {
        return insert(namespace +".insert", spsInfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(SpsInfo spsInfo) {
        return update(namespace +".update", spsInfo);
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsInfoCount(SpsInfoQuery queryBean) {
        return (Integer) queryForObject(namespace +".querySpsInfoCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsInfo> querySpsInfoListWithPage(SpsInfoQuery queryBean) {
        return (List<SpsInfo>) queryForList(namespace +".querySpsInfoListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsInfo configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public SpsInfo getSpsInfoById(Long id) {
        return (SpsInfo) queryForObject(namespace +".getSpsInfoById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(SpsInfo spsInfo) {
        int count = (Integer) queryForObject(namespace +".exist", spsInfo);
        return count > 0;
    }
    /**
     * 根据通报成员分组查询通报成员拥有的年份
     * @param queryBean
     * @return
     */
    public List<SpsInfo> queryCountryDateByCountry(SpsInfoQuery queryBean) {
        return (List<SpsInfo>) queryForList(namespace +".queryCountryDateByCountry", queryBean);
    }
    /**
     * 根据spsCode 修改spsInfo
     * @param spsInfo
     * @return
     */
    public boolean updateSpsInfoBySpsCode(SpsInfo spsInfo) {
        return update(namespace +".updateSpsInfoBySpsCode", spsInfo);
    }

    /**
     * 修改sps状态
     * @param spsInfo
     * @return
     */
    public boolean updateSpsStateBySpsCode(SpsInfo spsInfo) {
        return update(namespace +".updateSpsStateBySpsCode", spsInfo);
    }

    /**
     * 查询专家未评议和已评议的内容
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoListExperts(SpsInfoQuery queryBean){
        return (List<SpsInfo>) queryForList(namespace +".querySpsInfoListExperts", queryBean);
    }
    /**
     * 查询专家未评议和已评议的内容数量
     * @param queryBean
     * @return
     */
    public int querySpsInfoCountExperts(SpsInfoQuery queryBean){
        return (Integer) queryForObject(namespace +".querySpsInfoCountExperts", queryBean);
    }

    public List<SpsInfo> querySpsInfoExpertsAll(SpsInfoQuery queryBean) {
        return (List<SpsInfo>) queryForList(namespace +".querySpsInfoExpertsAll", queryBean);
    }

    public int querySpsInfoCountExpertsAll(SpsInfoQuery queryBean) {
        return (Integer) queryForObject(namespace +".querySpsInfoCountExpertsAll", queryBean);
    }
}
