package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.MedicineProduccategoryDao;
import com.letv.tbtSps.domain.MedicineProduccategory;
import com.letv.tbtSps.domain.query.MedicineProduccategoryQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * MedicineProduccategoryDAO实现类<br/>
 * 对'农产品分类'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class MedicineProduccategoryDaoImpl extends BaseDao implements MedicineProduccategoryDao {
    /** namespace */
    private final String namespace = MedicineProduccategoryDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<MedicineProduccategory> queryMedicineProduccategoryList(MedicineProduccategoryQuery queryBean) {
        return (List<MedicineProduccategory>) queryForList(namespace +".queryMedicineProduccategoryList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(MedicineProduccategory medicineProduccategory) {
        return insert(namespace +".insert", medicineProduccategory);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(MedicineProduccategory medicineProduccategory) {
        return update(namespace +".update", medicineProduccategory);
    }

    /**
     * {@inheritDoc}
     */
    public int queryMedicineProduccategoryCount(MedicineProduccategoryQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryMedicineProduccategoryCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<MedicineProduccategory> queryMedicineProduccategoryListWithPage(MedicineProduccategoryQuery queryBean) {
        return (List<MedicineProduccategory>) queryForList(namespace +".queryMedicineProduccategoryListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(MedicineProduccategory configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public MedicineProduccategory getMedicineProduccategoryById(Long id) {
        return (MedicineProduccategory) queryForObject(namespace +".getMedicineProduccategoryById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(MedicineProduccategory medicineProduccategory) {
        int count = (Integer) queryForObject(namespace +".exist", medicineProduccategory);
        return count > 0;
    }
}
