package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationTbrelationMedicineDao;
import com.letv.tbtSps.domain.RelationTbrelationMedicine;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationTbrelationMedicineDAO实现类<br/>
 * 对'tbt信息表-相关农药关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationTbrelationMedicineDaoImpl extends BaseDao implements RelationTbrelationMedicineDao {
    /** namespace */
    private final String namespace = RelationTbrelationMedicineDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationTbrelationMedicine> queryRelationTbrelationMedicineList(RelationTbrelationMedicineQuery queryBean) {
        return (List<RelationTbrelationMedicine>) queryForList(namespace +".queryRelationTbrelationMedicineList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationTbrelationMedicine relationTbrelationMedicine) {
        return insert(namespace +".insert", relationTbrelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationTbrelationMedicine relationTbrelationMedicine) {
        return update(namespace +".update", relationTbrelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTbrelationMedicineCount(RelationTbrelationMedicineQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationTbrelationMedicineCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbrelationMedicine> queryRelationTbrelationMedicineListWithPage(RelationTbrelationMedicineQuery queryBean) {
        return (List<RelationTbrelationMedicine>) queryForList(namespace +".queryRelationTbrelationMedicineListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTbrelationMedicine configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTbrelationMedicine getRelationTbrelationMedicineById(Long id) {
        return (RelationTbrelationMedicine) queryForObject(namespace +".getRelationTbrelationMedicineById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationTbrelationMedicine relationTbrelationMedicine) {
        int count = (Integer) queryForObject(namespace +".exist", relationTbrelationMedicine);
        return count > 0;
    }
}
