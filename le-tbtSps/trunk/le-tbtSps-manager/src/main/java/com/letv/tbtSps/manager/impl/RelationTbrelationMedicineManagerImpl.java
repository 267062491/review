package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationTbrelationMedicine;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineQuery;
import com.letv.tbtSps.dao.RelationTbrelationMedicineDao;
import com.letv.tbtSps.manager.RelationTbrelationMedicineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationTbrelationMedicineManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationTbrelationMedicineManagerImpl extends BaseManager implements RelationTbrelationMedicineManager {
	
    @Autowired
    private RelationTbrelationMedicineDao relationTbrelationMedicineDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationTbrelationMedicine> relationTbrelationMedicineList) {
        boolean resultFlag = false;
        if (null != relationTbrelationMedicineList && relationTbrelationMedicineList.size() > 0) {
            for (RelationTbrelationMedicine relationTbrelationMedicine : relationTbrelationMedicineList) {
                resultFlag = relationTbrelationMedicineDao.insert(relationTbrelationMedicine);
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
    public boolean insert(RelationTbrelationMedicine relationTbrelationMedicine) {
        return relationTbrelationMedicineDao.insert(relationTbrelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationTbrelationMedicine relationTbrelationMedicine) {
        return relationTbrelationMedicineDao.update(relationTbrelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbrelationMedicine> queryRelationTbrelationMedicineList(RelationTbrelationMedicineQuery queryBean) {
        return relationTbrelationMedicineDao.queryRelationTbrelationMedicineList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTbrelationMedicine> queryRelationTbrelationMedicineListWithPage(RelationTbrelationMedicineQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationTbrelationMedicineQuery();
        }

        // 查询总数
        int totalItem = queryRelationTbrelationMedicineCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationTbrelationMedicineDao.queryRelationTbrelationMedicineListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTbrelationMedicineCount(RelationTbrelationMedicineQuery queryBean) {
        return relationTbrelationMedicineDao.queryRelationTbrelationMedicineCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTbrelationMedicine relationTbrelationMedicine) {
        return relationTbrelationMedicineDao.delete(relationTbrelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTbrelationMedicine getRelationTbrelationMedicineById(Long id) {
        return relationTbrelationMedicineDao.getRelationTbrelationMedicineById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationTbrelationMedicine[] relationTbrelationMedicines) {
        boolean resultFlag = false;
        if (null != relationTbrelationMedicines && relationTbrelationMedicines.length > 0) {
            for (int i = 0; i < relationTbrelationMedicines.length; i++) {
                resultFlag = delete(relationTbrelationMedicines[i]);
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
    public boolean exist(RelationTbrelationMedicine relationTbrelationMedicine) {
        return relationTbrelationMedicineDao.exist(relationTbrelationMedicine);
    }
}
