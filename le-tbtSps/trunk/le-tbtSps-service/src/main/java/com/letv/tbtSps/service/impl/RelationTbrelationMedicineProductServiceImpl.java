package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationTbrelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineProductQuery;
import com.letv.tbtSps.manager.RelationTbrelationMedicineProductManager;
import com.letv.tbtSps.service.RelationTbrelationMedicineProductService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationTbrelationMedicineProductService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationTbrelationMedicineProductServiceImpl implements RelationTbrelationMedicineProductService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationTbrelationMedicineProductServiceImpl.class);

    @Autowired
    private RelationTbrelationMedicineProductManager relationTbrelationMedicineProductManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineProductService.batchInsert")
    public boolean insert(List<RelationTbrelationMedicineProduct> relationTbrelationMedicineProductList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationTbrelationMedicineProductList)) {
                resultFlag = relationTbrelationMedicineProductManager.insert(relationTbrelationMedicineProductList);
            } else {
                LOG.warn("RelationTbrelationMedicineProductServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineProductServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineProductService.insert")
    public boolean insert(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationTbrelationMedicineProduct) {
                if (relationTbrelationMedicineProductManager.exist(relationTbrelationMedicineProduct)) {
                    throw new ExistedException();
                }
                resultFlag = relationTbrelationMedicineProductManager.insert(relationTbrelationMedicineProduct);
            } else {
                LOG.warn("RelationTbrelationMedicineProductServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationTbrelationMedicineProductServiceImpl#insert failed, relationTbrelationMedicineProduct has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineProductServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineProductService.update")
    public boolean update(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationTbrelationMedicineProduct && null != relationTbrelationMedicineProduct.getId()) {
                resultFlag = relationTbrelationMedicineProductManager.update(relationTbrelationMedicineProduct);
            } else {
                LOG.warn("RelationTbrelationMedicineProductServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineProductServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineProductService.queryRelationTbrelationMedicineProductList")
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductList(RelationTbrelationMedicineProductQuery queryBean) {
        List<RelationTbrelationMedicineProduct> relationTbrelationMedicineProductList = null;
        try {
            relationTbrelationMedicineProductList = relationTbrelationMedicineProductManager.queryRelationTbrelationMedicineProductList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineProductServiceImpl#queryRelationTbrelationMedicineProductList has error.", e);
        }
        return relationTbrelationMedicineProductList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineProductService.queryRelationTbrelationMedicineProductListWithPage")
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductListWithPage(RelationTbrelationMedicineProductQuery queryBean, PageUtil pageUtil) {
        List<RelationTbrelationMedicineProduct> relationTbrelationMedicineProductList = null;
        try {
            relationTbrelationMedicineProductList = relationTbrelationMedicineProductManager.queryRelationTbrelationMedicineProductListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineProductServiceImpl#queryRelationTbrelationMedicineProductListWithPage has error.", e);
        }
        return relationTbrelationMedicineProductList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineProductService.delete")
    public boolean delete(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationTbrelationMedicineProduct && null != relationTbrelationMedicineProduct.getId()) {
                resultFlag = relationTbrelationMedicineProductManager.delete(relationTbrelationMedicineProduct);
            } else {
                LOG.warn("RelationTbrelationMedicineProductServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineProductServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineProductService.batchDelete")
    public boolean delete(RelationTbrelationMedicineProduct[] relationTbrelationMedicineProducts) {
        boolean resultFlag = false;
        try {
            if (null != relationTbrelationMedicineProducts && relationTbrelationMedicineProducts.length > 0) {
                resultFlag = relationTbrelationMedicineProductManager.delete(relationTbrelationMedicineProducts);
            } else {
                LOG.warn("RelationTbrelationMedicineProductServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineProductServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineProductService.getRelationTbrelationMedicineProductById")
    public RelationTbrelationMedicineProduct getRelationTbrelationMedicineProductById(Long id) {
        RelationTbrelationMedicineProduct relationTbrelationMedicineProduct = null;
        try {
            if (null != id) {
                relationTbrelationMedicineProduct = relationTbrelationMedicineProductManager.getRelationTbrelationMedicineProductById(id);
            } else {
                LOG.warn("RelationTbrelationMedicineProductServiceImpl#getRelationTbrelationMedicineProductById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineProductServiceImpl#getRelationTbrelationMedicineProductById has error.", e);
        }
        return relationTbrelationMedicineProduct;
    }
}

