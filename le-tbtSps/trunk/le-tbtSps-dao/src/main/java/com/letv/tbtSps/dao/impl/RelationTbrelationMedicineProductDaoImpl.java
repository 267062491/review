package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationTbrelationMedicineProductDao;
import com.letv.tbtSps.domain.RelationTbrelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineProductQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationTbrelationMedicineProductDAO实现类<br/>
 * 对'tbt信息表-相关农产品关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationTbrelationMedicineProductDaoImpl extends BaseDao implements RelationTbrelationMedicineProductDao {
    /** namespace */
    private final String namespace = RelationTbrelationMedicineProductDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductList(RelationTbrelationMedicineProductQuery queryBean) {
        return (List<RelationTbrelationMedicineProduct>) queryForList(namespace +".queryRelationTbrelationMedicineProductList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        return insert(namespace +".insert", relationTbrelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        return update(namespace +".update", relationTbrelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTbrelationMedicineProductCount(RelationTbrelationMedicineProductQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationTbrelationMedicineProductCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductListWithPage(RelationTbrelationMedicineProductQuery queryBean) {
        return (List<RelationTbrelationMedicineProduct>) queryForList(namespace +".queryRelationTbrelationMedicineProductListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTbrelationMedicineProduct configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTbrelationMedicineProduct getRelationTbrelationMedicineProductById(Long id) {
        return (RelationTbrelationMedicineProduct) queryForObject(namespace +".getRelationTbrelationMedicineProductById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        int count = (Integer) queryForObject(namespace +".exist", relationTbrelationMedicineProduct);
        return count > 0;
    }
}
