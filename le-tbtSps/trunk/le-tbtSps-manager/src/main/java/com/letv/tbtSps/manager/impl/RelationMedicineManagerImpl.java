package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationMedicine;
import com.letv.tbtSps.domain.query.RelationMedicineQuery;
import com.letv.tbtSps.dao.RelationMedicineDao;
import com.letv.tbtSps.manager.RelationMedicineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationMedicineManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationMedicineManagerImpl extends BaseManager implements RelationMedicineManager {
	
    @Autowired
    private RelationMedicineDao relationMedicineDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationMedicine> relationMedicineList) {
        boolean resultFlag = false;
        if (null != relationMedicineList && relationMedicineList.size() > 0) {
            for (RelationMedicine relationMedicine : relationMedicineList) {
                resultFlag = relationMedicineDao.insert(relationMedicine);
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
    public boolean insert(RelationMedicine relationMedicine) {
        return relationMedicineDao.insert(relationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationMedicine relationMedicine) {
        return relationMedicineDao.update(relationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationMedicine> queryRelationMedicineList(RelationMedicineQuery queryBean) {
        return relationMedicineDao.queryRelationMedicineList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationMedicine> queryRelationMedicineListWithPage(RelationMedicineQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationMedicineQuery();
        }

        // 查询总数
        int totalItem = queryRelationMedicineCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationMedicineDao.queryRelationMedicineListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationMedicineCount(RelationMedicineQuery queryBean) {
        return relationMedicineDao.queryRelationMedicineCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationMedicine relationMedicine) {
        return relationMedicineDao.delete(relationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public RelationMedicine getRelationMedicineById(Long id) {
        return relationMedicineDao.getRelationMedicineById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationMedicine[] relationMedicines) {
        boolean resultFlag = false;
        if (null != relationMedicines && relationMedicines.length > 0) {
            for (int i = 0; i < relationMedicines.length; i++) {
                resultFlag = delete(relationMedicines[i]);
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
    public boolean exist(RelationMedicine relationMedicine) {
        return relationMedicineDao.exist(relationMedicine);
    }
}
