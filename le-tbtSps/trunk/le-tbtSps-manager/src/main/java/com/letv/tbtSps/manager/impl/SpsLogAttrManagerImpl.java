package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.domain.query.SpsLogAttrQuery;
import com.letv.tbtSps.dao.SpsLogAttrDao;
import com.letv.tbtSps.manager.SpsLogAttrManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SpsLogAttrManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class SpsLogAttrManagerImpl extends BaseManager implements SpsLogAttrManager {
	
    @Autowired
    private SpsLogAttrDao spsLogAttrDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<SpsLogAttr> spsLogAttrList) {
        boolean resultFlag = false;
        if (null != spsLogAttrList && spsLogAttrList.size() > 0) {
            for (SpsLogAttr spsLogAttr : spsLogAttrList) {
                resultFlag = spsLogAttrDao.insert(spsLogAttr);
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
    public boolean insert(SpsLogAttr spsLogAttr) {
        return spsLogAttrDao.insert(spsLogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final SpsLogAttr spsLogAttr) {
        return spsLogAttrDao.update(spsLogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsLogAttr> querySpsLogAttrList(SpsLogAttrQuery queryBean) {
        return spsLogAttrDao.querySpsLogAttrList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsLogAttr> querySpsLogAttrListWithPage(SpsLogAttrQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new SpsLogAttrQuery();
        }

        // 查询总数
        int totalItem = querySpsLogAttrCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return spsLogAttrDao.querySpsLogAttrListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsLogAttrCount(SpsLogAttrQuery queryBean) {
        return spsLogAttrDao.querySpsLogAttrCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsLogAttr spsLogAttr) {
        return spsLogAttrDao.delete(spsLogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public SpsLogAttr getSpsLogAttrById(Long id) {
        return spsLogAttrDao.getSpsLogAttrById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final SpsLogAttr[] spsLogAttrs) {
        boolean resultFlag = false;
        if (null != spsLogAttrs && spsLogAttrs.length > 0) {
            for (int i = 0; i < spsLogAttrs.length; i++) {
                resultFlag = delete(spsLogAttrs[i]);
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
    public boolean exist(SpsLogAttr spsLogAttr) {
        return spsLogAttrDao.exist(spsLogAttr);
    }
}
