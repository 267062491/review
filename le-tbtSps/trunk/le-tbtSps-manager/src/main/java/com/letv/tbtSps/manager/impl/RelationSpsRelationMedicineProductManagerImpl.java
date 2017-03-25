package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationSpsRelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineProductQuery;
import com.letv.tbtSps.dao.RelationSpsRelationMedicineProductDao;
import com.letv.tbtSps.manager.RelationSpsRelationMedicineProductManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationSpsRelationMedicineProductManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationSpsRelationMedicineProductManagerImpl extends BaseManager implements RelationSpsRelationMedicineProductManager {
	
    @Autowired
    private RelationSpsRelationMedicineProductDao relationSpsRelationMedicineProductDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationSpsRelationMedicineProduct> relationSpsRelationMedicineProductList) {
        boolean resultFlag = false;
        if (null != relationSpsRelationMedicineProductList && relationSpsRelationMedicineProductList.size() > 0) {
            for (RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct : relationSpsRelationMedicineProductList) {
                resultFlag = relationSpsRelationMedicineProductDao.insert(relationSpsRelationMedicineProduct);
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
    public boolean insert(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        return relationSpsRelationMedicineProductDao.insert(relationSpsRelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        return relationSpsRelationMedicineProductDao.update(relationSpsRelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductList(RelationSpsRelationMedicineProductQuery queryBean) {
        return relationSpsRelationMedicineProductDao.queryRelationSpsRelationMedicineProductList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductListWithPage(RelationSpsRelationMedicineProductQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationSpsRelationMedicineProductQuery();
        }

        // 查询总数
        int totalItem = queryRelationSpsRelationMedicineProductCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationSpsRelationMedicineProductDao.queryRelationSpsRelationMedicineProductListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsRelationMedicineProductCount(RelationSpsRelationMedicineProductQuery queryBean) {
        return relationSpsRelationMedicineProductDao.queryRelationSpsRelationMedicineProductCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        return relationSpsRelationMedicineProductDao.delete(relationSpsRelationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsRelationMedicineProduct getRelationSpsRelationMedicineProductById(Long id) {
        return relationSpsRelationMedicineProductDao.getRelationSpsRelationMedicineProductById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationSpsRelationMedicineProduct[] relationSpsRelationMedicineProducts) {
        boolean resultFlag = false;
        if (null != relationSpsRelationMedicineProducts && relationSpsRelationMedicineProducts.length > 0) {
            for (int i = 0; i < relationSpsRelationMedicineProducts.length; i++) {
                resultFlag = delete(relationSpsRelationMedicineProducts[i]);
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
    public boolean exist(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct) {
        return relationSpsRelationMedicineProductDao.exist(relationSpsRelationMedicineProduct);
    }
}
