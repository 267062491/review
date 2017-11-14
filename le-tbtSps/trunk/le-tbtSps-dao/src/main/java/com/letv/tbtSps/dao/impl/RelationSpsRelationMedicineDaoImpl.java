package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationSpsRelationMedicineDao;
import com.letv.tbtSps.domain.RelationSpsRelationMedicine;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationSpsRelationMedicineDAO实现类<br/>
 * 对'sps信息表-相关农药关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationSpsRelationMedicineDaoImpl extends BaseDao implements RelationSpsRelationMedicineDao {
    /** namespace */
    private final String namespace = RelationSpsRelationMedicineDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineList(RelationSpsRelationMedicineQuery queryBean) {
        return (List<RelationSpsRelationMedicine>) queryForList(namespace +".queryRelationSpsRelationMedicineList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        return insert(namespace +".insert", relationSpsRelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        return update(namespace +".update", relationSpsRelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsRelationMedicineCount(RelationSpsRelationMedicineQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationSpsRelationMedicineCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineListWithPage(RelationSpsRelationMedicineQuery queryBean) {
        return (List<RelationSpsRelationMedicine>) queryForList(namespace +".queryRelationSpsRelationMedicineListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsRelationMedicine configuration) {
        return delete(namespace +".delete", configuration);
    }
    public boolean deleteByCode(RelationSpsRelationMedicine configuration) {
        return delete(namespace +".deleteByCode", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsRelationMedicine getRelationSpsRelationMedicineById(Long id) {
        return (RelationSpsRelationMedicine) queryForObject(namespace +".getRelationSpsRelationMedicineById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        int count = (Integer) queryForObject(namespace +".exist", relationSpsRelationMedicine);
        return count > 0;
    }
}
