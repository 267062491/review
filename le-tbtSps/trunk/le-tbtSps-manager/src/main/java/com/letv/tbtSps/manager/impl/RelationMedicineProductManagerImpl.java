package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationMedicineProductQuery;
import com.letv.tbtSps.dao.RelationMedicineProductDao;
import com.letv.tbtSps.manager.RelationMedicineProductManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationMedicineProductManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationMedicineProductManagerImpl extends BaseManager implements RelationMedicineProductManager {
	
    @Autowired
    private RelationMedicineProductDao relationMedicineProductDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationMedicineProduct> relationMedicineProductList) {
        boolean resultFlag = false;
        if (null != relationMedicineProductList && relationMedicineProductList.size() > 0) {
            for (RelationMedicineProduct relationMedicineProduct : relationMedicineProductList) {
                resultFlag = relationMedicineProductDao.insert(relationMedicineProduct);
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
    public boolean insert(RelationMedicineProduct relationMedicineProduct) {
        return relationMedicineProductDao.insert(relationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationMedicineProduct relationMedicineProduct) {
        return relationMedicineProductDao.update(relationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationMedicineProduct> queryRelationMedicineProductList(RelationMedicineProductQuery queryBean) {
        return relationMedicineProductDao.queryRelationMedicineProductList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationMedicineProduct> queryRelationMedicineProductListWithPage(RelationMedicineProductQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationMedicineProductQuery();
        }

        // 查询总数
        int totalItem = queryRelationMedicineProductCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationMedicineProductDao.queryRelationMedicineProductListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationMedicineProductCount(RelationMedicineProductQuery queryBean) {
        return relationMedicineProductDao.queryRelationMedicineProductCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationMedicineProduct relationMedicineProduct) {
        return relationMedicineProductDao.delete(relationMedicineProduct);
    }

    /**
     * {@inheritDoc}
     */
    public RelationMedicineProduct getRelationMedicineProductById(Long id) {
        return relationMedicineProductDao.getRelationMedicineProductById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationMedicineProduct[] relationMedicineProducts) {
        boolean resultFlag = false;
        if (null != relationMedicineProducts && relationMedicineProducts.length > 0) {
            for (int i = 0; i < relationMedicineProducts.length; i++) {
                resultFlag = delete(relationMedicineProducts[i]);
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
    public boolean exist(RelationMedicineProduct relationMedicineProduct) {
        return relationMedicineProductDao.exist(relationMedicineProduct);
    }
}
