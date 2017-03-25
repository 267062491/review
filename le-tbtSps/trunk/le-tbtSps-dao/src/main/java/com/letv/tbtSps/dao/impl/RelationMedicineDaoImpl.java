package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationMedicineDao;
import com.letv.tbtSps.domain.RelationMedicine;
import com.letv.tbtSps.domain.query.RelationMedicineQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationMedicineDAO实现类<br/>
 * 对'相关农药'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationMedicineDaoImpl extends BaseDao implements RelationMedicineDao {
    /** namespace */
    private final String namespace = RelationMedicineDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationMedicine> queryRelationMedicineList(RelationMedicineQuery queryBean) {
        return (List<RelationMedicine>) queryForList(namespace +".queryRelationMedicineList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationMedicine relationMedicine) {
        return insert(namespace +".insert", relationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationMedicine relationMedicine) {
        return update(namespace +".update", relationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationMedicineCount(RelationMedicineQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationMedicineCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationMedicine> queryRelationMedicineListWithPage(RelationMedicineQuery queryBean) {
        return (List<RelationMedicine>) queryForList(namespace +".queryRelationMedicineListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationMedicine configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationMedicine getRelationMedicineById(Long id) {
        return (RelationMedicine) queryForObject(namespace +".getRelationMedicineById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationMedicine relationMedicine) {
        int count = (Integer) queryForObject(namespace +".exist", relationMedicine);
        return count > 0;
    }
}
