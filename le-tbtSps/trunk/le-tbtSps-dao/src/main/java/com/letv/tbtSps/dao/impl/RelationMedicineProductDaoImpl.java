package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationMedicineProductDao;
import com.letv.tbtSps.domain.RelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationMedicineProductQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationMedicineProductDAO实现类<br/>
 * 对'相关农产品'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationMedicineProductDaoImpl extends BaseDao implements RelationMedicineProductDao {
    /** namespace */
    private final String namespace = RelationMedicineProductDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationMedicineProduct> queryRelationMedicineProductList(RelationMedicineProductQuery queryBean) {
        return (List<RelationMedicineProduct>) queryForList(namespace +".queryRelationMedicineProductList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationMedicineProduct relationMedicineProduct) {
        return insert(namespace +".insert", relationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationMedicineProduct relationMedicineProduct) {
        return update(namespace +".update", relationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationMedicineProductCount(RelationMedicineProductQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationMedicineProductCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationMedicineProduct> queryRelationMedicineProductListWithPage(RelationMedicineProductQuery queryBean) {
        return (List<RelationMedicineProduct>) queryForList(namespace +".queryRelationMedicineProductListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationMedicineProduct configuration) {
        return delete(namespace +".delete", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationMedicineProduct getRelationMedicineProductById(Long id) {
        return (RelationMedicineProduct) queryForObject(namespace +".getRelationMedicineProductById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationMedicineProduct relationMedicineProduct) {
        int count = (Integer) queryForObject(namespace +".exist", relationMedicineProduct);
        return count > 0;
    }
}
