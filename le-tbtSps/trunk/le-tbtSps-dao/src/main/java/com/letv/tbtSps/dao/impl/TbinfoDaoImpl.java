package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.TbinfoDao;
import com.letv.tbtSps.domain.Tbinfo;
import com.letv.tbtSps.domain.query.TbinfoQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * TbinfoDAO实现类<br/>
 * 对'tbt信息表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class TbinfoDaoImpl extends BaseDao implements TbinfoDao {
    /** namespace */
    private final String namespace = TbinfoDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<Tbinfo> queryTbinfoList(TbinfoQuery queryBean) {
        return (List<Tbinfo>) queryForList(namespace +".queryTbinfoList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(Tbinfo tbinfo) {
        return insert(namespace +".insert", tbinfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(Tbinfo tbinfo) {
        return update(namespace +".update", tbinfo);
    }

    /**
     * {@inheritDoc}
     */
    public int queryTbinfoCount(TbinfoQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryTbinfoCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Tbinfo> queryTbinfoListWithPage(TbinfoQuery queryBean) {
        return (List<Tbinfo>) queryForList(namespace +".queryTbinfoListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Tbinfo configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public Tbinfo getTbinfoById(Long id) {
        return (Tbinfo) queryForObject(namespace +".getTbinfoById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(Tbinfo tbinfo) {
        int count = (Integer) queryForObject(namespace +".exist", tbinfo);
        return count > 0;
    }
}
