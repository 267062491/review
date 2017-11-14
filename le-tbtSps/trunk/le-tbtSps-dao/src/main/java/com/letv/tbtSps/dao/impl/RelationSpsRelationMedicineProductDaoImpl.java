package com.letv.tbtSps.dao.impl;

import com.letv.common.dao.mybatis.BaseDao;
import com.letv.tbtSps.dao.RelationSpsRelationMedicineProductDao;
import com.letv.tbtSps.domain.RelationSpsRelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineProductQuery;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * RelationSpsRelationMedicineProductDAO实现类<br/>
 * 对'sps信息表-相关农产品关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Repository
public class RelationSpsRelationMedicineProductDaoImpl extends BaseDao implements RelationSpsRelationMedicineProductDao {
    /** namespace */
    private final String namespace = RelationSpsRelationMedicineProductDaoImpl.class.getName();
    
    /**
     * {@inheritDoc}
     */
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductList(RelationSpsRelationMedicineProductQuery queryBean) {
        return (List<RelationSpsRelationMedicineProduct>) queryForList(namespace +".queryRelationSpsRelationMedicineProductList", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        return insert(namespace +".insert", relationSpsRelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        return update(namespace +".update", relationSpsRelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsRelationMedicineProductCount(RelationSpsRelationMedicineProductQuery queryBean) {
        return (Integer) queryForObject(namespace +".queryRelationSpsRelationMedicineProductCount", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductListWithPage(RelationSpsRelationMedicineProductQuery queryBean) {
        return (List<RelationSpsRelationMedicineProduct>) queryForList(namespace +".queryRelationSpsRelationMedicineProductListWithPage", queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsRelationMedicineProduct configuration) {
        return delete(namespace +".delete", configuration);
    }
    public boolean deleteByCode(RelationSpsRelationMedicineProduct configuration) {
        return delete(namespace +".deleteByCode", configuration);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsRelationMedicineProduct getRelationSpsRelationMedicineProductById(Long id) {
        return (RelationSpsRelationMedicineProduct) queryForObject(namespace +".getRelationSpsRelationMedicineProductById", id);
    } 
    
    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        int count = (Integer) queryForObject(namespace +".exist", relationSpsRelationMedicineProduct);
        return count > 0;
    }
}
