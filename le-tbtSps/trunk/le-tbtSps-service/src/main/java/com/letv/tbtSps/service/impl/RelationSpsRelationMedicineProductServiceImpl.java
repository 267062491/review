package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationSpsRelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineProductQuery;
import com.letv.tbtSps.manager.RelationSpsRelationMedicineProductManager;
import com.letv.tbtSps.service.RelationSpsRelationMedicineProductService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationSpsRelationMedicineProductService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationSpsRelationMedicineProductServiceImpl implements RelationSpsRelationMedicineProductService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationSpsRelationMedicineProductServiceImpl.class);

    @Autowired
    private RelationSpsRelationMedicineProductManager relationSpsRelationMedicineProductManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineProductService.batchInsert")
    public boolean insert(List<RelationSpsRelationMedicineProduct> relationSpsRelationMedicineProductList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationSpsRelationMedicineProductList)) {
                resultFlag = relationSpsRelationMedicineProductManager.insert(relationSpsRelationMedicineProductList);
            } else {
                LOG.warn("RelationSpsRelationMedicineProductServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineProductServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineProductService.insert")
    public boolean insert(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsRelationMedicineProduct) {
                if (relationSpsRelationMedicineProductManager.exist(relationSpsRelationMedicineProduct)) {
                    throw new ExistedException();
                }
                resultFlag = relationSpsRelationMedicineProductManager.insert(relationSpsRelationMedicineProduct);
            } else {
                LOG.warn("RelationSpsRelationMedicineProductServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationSpsRelationMedicineProductServiceImpl#insert failed, relationSpsRelationMedicineProduct has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineProductServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineProductService.update")
    public boolean update(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsRelationMedicineProduct && null != relationSpsRelationMedicineProduct.getId()) {
                resultFlag = relationSpsRelationMedicineProductManager.update(relationSpsRelationMedicineProduct);
            } else {
                LOG.warn("RelationSpsRelationMedicineProductServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineProductServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineProductService.queryRelationSpsRelationMedicineProductList")
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductList(RelationSpsRelationMedicineProductQuery queryBean) {
        List<RelationSpsRelationMedicineProduct> relationSpsRelationMedicineProductList = null;
        try {
            relationSpsRelationMedicineProductList = relationSpsRelationMedicineProductManager.queryRelationSpsRelationMedicineProductList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineProductServiceImpl#queryRelationSpsRelationMedicineProductList has error.", e);
        }
        return relationSpsRelationMedicineProductList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineProductService.queryRelationSpsRelationMedicineProductListWithPage")
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductListWithPage(RelationSpsRelationMedicineProductQuery queryBean, PageUtil pageUtil) {
        List<RelationSpsRelationMedicineProduct> relationSpsRelationMedicineProductList = null;
        try {
            relationSpsRelationMedicineProductList = relationSpsRelationMedicineProductManager.queryRelationSpsRelationMedicineProductListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineProductServiceImpl#queryRelationSpsRelationMedicineProductListWithPage has error.", e);
        }
        return relationSpsRelationMedicineProductList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineProductService.delete")
    public boolean delete(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsRelationMedicineProduct && null != relationSpsRelationMedicineProduct.getId()) {
                resultFlag = relationSpsRelationMedicineProductManager.delete(relationSpsRelationMedicineProduct);
            } else {
                LOG.warn("RelationSpsRelationMedicineProductServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineProductServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineProductService.batchDelete")
    public boolean delete(RelationSpsRelationMedicineProduct[] relationSpsRelationMedicineProducts) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsRelationMedicineProducts && relationSpsRelationMedicineProducts.length > 0) {
                resultFlag = relationSpsRelationMedicineProductManager.delete(relationSpsRelationMedicineProducts);
            } else {
                LOG.warn("RelationSpsRelationMedicineProductServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineProductServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineProductService.getRelationSpsRelationMedicineProductById")
    public RelationSpsRelationMedicineProduct getRelationSpsRelationMedicineProductById(Long id) {
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = null;
        try {
            if (null != id) {
                relationSpsRelationMedicineProduct = relationSpsRelationMedicineProductManager.getRelationSpsRelationMedicineProductById(id);
            } else {
                LOG.warn("RelationSpsRelationMedicineProductServiceImpl#getRelationSpsRelationMedicineProductById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineProductServiceImpl#getRelationSpsRelationMedicineProductById has error.", e);
        }
        return relationSpsRelationMedicineProduct;
    }
}

