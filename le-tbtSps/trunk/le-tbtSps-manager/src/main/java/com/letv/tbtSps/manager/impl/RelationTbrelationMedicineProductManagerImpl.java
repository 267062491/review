package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationTbrelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineProductQuery;
import com.letv.tbtSps.dao.RelationTbrelationMedicineProductDao;
import com.letv.tbtSps.manager.RelationTbrelationMedicineProductManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationTbrelationMedicineProductManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationTbrelationMedicineProductManagerImpl extends BaseManager implements RelationTbrelationMedicineProductManager {
	
    @Autowired
    private RelationTbrelationMedicineProductDao relationTbrelationMedicineProductDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationTbrelationMedicineProduct> relationTbrelationMedicineProductList) {
        boolean resultFlag = false;
        if (null != relationTbrelationMedicineProductList && relationTbrelationMedicineProductList.size() > 0) {
            for (RelationTbrelationMedicineProduct relationTbrelationMedicineProduct : relationTbrelationMedicineProductList) {
                resultFlag = relationTbrelationMedicineProductDao.insert(relationTbrelationMedicineProduct);
                if (!resultFlag) {
                    throw new RuntimeException("批量新增表信息异常");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        return relationTbrelationMedicineProductDao.insert(relationTbrelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        return relationTbrelationMedicineProductDao.update(relationTbrelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductList(RelationTbrelationMedicineProductQuery queryBean) {
        return relationTbrelationMedicineProductDao.queryRelationTbrelationMedicineProductList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductListWithPage(RelationTbrelationMedicineProductQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationTbrelationMedicineProductQuery();
        }

        // 查询总数
        int totalItem = queryRelationTbrelationMedicineProductCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationTbrelationMedicineProductDao.queryRelationTbrelationMedicineProductListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTbrelationMedicineProductCount(RelationTbrelationMedicineProductQuery queryBean) {
        return relationTbrelationMedicineProductDao.queryRelationTbrelationMedicineProductCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        return relationTbrelationMedicineProductDao.delete(relationTbrelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTbrelationMedicineProduct getRelationTbrelationMedicineProductById(Long id) {
        return relationTbrelationMedicineProductDao.getRelationTbrelationMedicineProductById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationTbrelationMedicineProduct[] relationTbrelationMedicineProducts) {
        boolean resultFlag = false;
        if (null != relationTbrelationMedicineProducts && relationTbrelationMedicineProducts.length > 0) {
            for (int i = 0; i < relationTbrelationMedicineProducts.length; i++) {
                resultFlag = delete(relationTbrelationMedicineProducts[i]);
                if (!resultFlag) {
                    throw new RuntimeException("批量删除表信息异常!");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean exist(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct) {
        return relationTbrelationMedicineProductDao.exist(relationTbrelationMedicineProduct);
    }
}
