package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationSpsRelationMedicine;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineQuery;
import com.letv.tbtSps.dao.RelationSpsRelationMedicineDao;
import com.letv.tbtSps.manager.RelationSpsRelationMedicineManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationSpsRelationMedicineManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationSpsRelationMedicineManagerImpl extends BaseManager implements RelationSpsRelationMedicineManager {
	
    @Autowired
    private RelationSpsRelationMedicineDao relationSpsRelationMedicineDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationSpsRelationMedicine> relationSpsRelationMedicineList) {
        boolean resultFlag = false;
        if (null != relationSpsRelationMedicineList && relationSpsRelationMedicineList.size() > 0) {
            for (RelationSpsRelationMedicine relationSpsRelationMedicine : relationSpsRelationMedicineList) {
                resultFlag = relationSpsRelationMedicineDao.insert(relationSpsRelationMedicine);
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
    public boolean insert(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        return relationSpsRelationMedicineDao.insert(relationSpsRelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationSpsRelationMedicine relationSpsRelationMedicine) {
        return relationSpsRelationMedicineDao.update(relationSpsRelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineList(RelationSpsRelationMedicineQuery queryBean) {
        return relationSpsRelationMedicineDao.queryRelationSpsRelationMedicineList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineListWithPage(RelationSpsRelationMedicineQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationSpsRelationMedicineQuery();
        }

        // 查询总数
        int totalItem = queryRelationSpsRelationMedicineCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationSpsRelationMedicineDao.queryRelationSpsRelationMedicineListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsRelationMedicineCount(RelationSpsRelationMedicineQuery queryBean) {
        return relationSpsRelationMedicineDao.queryRelationSpsRelationMedicineCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        return relationSpsRelationMedicineDao.delete(relationSpsRelationMedicine);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsRelationMedicine getRelationSpsRelationMedicineById(Long id) {
        return relationSpsRelationMedicineDao.getRelationSpsRelationMedicineById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationSpsRelationMedicine[] relationSpsRelationMedicines) {
        boolean resultFlag = false;
        if (null != relationSpsRelationMedicines && relationSpsRelationMedicines.length > 0) {
            for (int i = 0; i < relationSpsRelationMedicines.length; i++) {
                resultFlag = delete(relationSpsRelationMedicines[i]);
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
    public boolean exist(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        return relationSpsRelationMedicineDao.exist(relationSpsRelationMedicine);
    }
}
