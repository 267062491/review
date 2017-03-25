package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationMedicineProductQuery;
import com.letv.tbtSps.manager.RelationMedicineProductManager;
import com.letv.tbtSps.service.RelationMedicineProductService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationMedicineProductService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationMedicineProductServiceImpl implements RelationMedicineProductService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationMedicineProductServiceImpl.class);

    @Autowired
    private RelationMedicineProductManager relationMedicineProductManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineProductService.batchInsert")
    public boolean insert(List<RelationMedicineProduct> relationMedicineProductList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationMedicineProductList)) {
                resultFlag = relationMedicineProductManager.insert(relationMedicineProductList);
            } else {
                LOG.warn("RelationMedicineProductServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineProductServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineProductService.insert")
    public boolean insert(RelationMedicineProduct relationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationMedicineProduct) {
                if (relationMedicineProductManager.exist(relationMedicineProduct)) {
                    throw new ExistedException();
                }
                resultFlag = relationMedicineProductManager.insert(relationMedicineProduct);
            } else {
                LOG.warn("RelationMedicineProductServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationMedicineProductServiceImpl#insert failed, relationMedicineProduct has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationMedicineProductServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineProductService.update")
    public boolean update(RelationMedicineProduct relationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationMedicineProduct && null != relationMedicineProduct.getId()) {
                resultFlag = relationMedicineProductManager.update(relationMedicineProduct);
            } else {
                LOG.warn("RelationMedicineProductServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineProductServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineProductService.queryRelationMedicineProductList")
    public List<RelationMedicineProduct> queryRelationMedicineProductList(RelationMedicineProductQuery queryBean) {
        List<RelationMedicineProduct> relationMedicineProductList = null;
        try {
            relationMedicineProductList = relationMedicineProductManager.queryRelationMedicineProductList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationMedicineProductServiceImpl#queryRelationMedicineProductList has error.", e);
        }
        return relationMedicineProductList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineProductService.queryRelationMedicineProductListWithPage")
    public List<RelationMedicineProduct> queryRelationMedicineProductListWithPage(RelationMedicineProductQuery queryBean, PageUtil pageUtil) {
        List<RelationMedicineProduct> relationMedicineProductList = null;
        try {
            relationMedicineProductList = relationMedicineProductManager.queryRelationMedicineProductListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationMedicineProductServiceImpl#queryRelationMedicineProductListWithPage has error.", e);
        }
        return relationMedicineProductList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineProductService.delete")
    public boolean delete(RelationMedicineProduct relationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationMedicineProduct && null != relationMedicineProduct.getId()) {
                resultFlag = relationMedicineProductManager.delete(relationMedicineProduct);
            } else {
                LOG.warn("RelationMedicineProductServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineProductServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineProductService.batchDelete")
    public boolean delete(RelationMedicineProduct[] relationMedicineProducts) {
        boolean resultFlag = false;
        try {
            if (null != relationMedicineProducts && relationMedicineProducts.length > 0) {
                resultFlag = relationMedicineProductManager.delete(relationMedicineProducts);
            } else {
                LOG.warn("RelationMedicineProductServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineProductServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineProductService.getRelationMedicineProductById")
    public RelationMedicineProduct getRelationMedicineProductById(Long id) {
        RelationMedicineProduct relationMedicineProduct = null;
        try {
            if (null != id) {
                relationMedicineProduct = relationMedicineProductManager.getRelationMedicineProductById(id);
            } else {
                LOG.warn("RelationMedicineProductServiceImpl#getRelationMedicineProductById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineProductServiceImpl#getRelationMedicineProductById has error.", e);
        }
        return relationMedicineProduct;
    }
}

