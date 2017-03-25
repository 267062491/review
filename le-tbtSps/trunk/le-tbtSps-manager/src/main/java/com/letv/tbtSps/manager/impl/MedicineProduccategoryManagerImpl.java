package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.MedicineProduccategory;
import com.letv.tbtSps.domain.query.MedicineProduccategoryQuery;
import com.letv.tbtSps.dao.MedicineProduccategoryDao;
import com.letv.tbtSps.manager.MedicineProduccategoryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MedicineProduccategoryManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class MedicineProduccategoryManagerImpl extends BaseManager implements MedicineProduccategoryManager {
	
    @Autowired
    private MedicineProduccategoryDao medicineProduccategoryDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<MedicineProduccategory> medicineProduccategoryList) {
        boolean resultFlag = false;
        if (null != medicineProduccategoryList && medicineProduccategoryList.size() > 0) {
            for (MedicineProduccategory medicineProduccategory : medicineProduccategoryList) {
                resultFlag = medicineProduccategoryDao.insert(medicineProduccategory);
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
    public boolean insert(MedicineProduccategory medicineProduccategory) {
        return medicineProduccategoryDao.insert(medicineProduccategory);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final MedicineProduccategory medicineProduccategory) {
        return medicineProduccategoryDao.update(medicineProduccategory);
    }

    /**
     * {@inheritDoc}
     */
    public List<MedicineProduccategory> queryMedicineProduccategoryList(MedicineProduccategoryQuery queryBean) {
        return medicineProduccategoryDao.queryMedicineProduccategoryList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<MedicineProduccategory> queryMedicineProduccategoryListWithPage(MedicineProduccategoryQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new MedicineProduccategoryQuery();
        }

        // 查询总数
        int totalItem = queryMedicineProduccategoryCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return medicineProduccategoryDao.queryMedicineProduccategoryListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryMedicineProduccategoryCount(MedicineProduccategoryQuery queryBean) {
        return medicineProduccategoryDao.queryMedicineProduccategoryCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(MedicineProduccategory medicineProduccategory) {
        return medicineProduccategoryDao.delete(medicineProduccategory);
    }

    /**
     * {@inheritDoc}
     */
    public MedicineProduccategory getMedicineProduccategoryById(Long id) {
        return medicineProduccategoryDao.getMedicineProduccategoryById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final MedicineProduccategory[] medicineProduccategorys) {
        boolean resultFlag = false;
        if (null != medicineProduccategorys && medicineProduccategorys.length > 0) {
            for (int i = 0; i < medicineProduccategorys.length; i++) {
                resultFlag = delete(medicineProduccategorys[i]);
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
    public boolean exist(MedicineProduccategory medicineProduccategory) {
        return medicineProduccategoryDao.exist(medicineProduccategory);
    }
}
