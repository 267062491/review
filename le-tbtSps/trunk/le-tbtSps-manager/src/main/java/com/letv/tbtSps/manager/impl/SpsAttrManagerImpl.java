package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.SpsAttr;
import com.letv.tbtSps.domain.query.SpsAttrQuery;
import com.letv.tbtSps.dao.SpsAttrDao;
import com.letv.tbtSps.manager.SpsAttrManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SpsAttrManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class SpsAttrManagerImpl extends BaseManager implements SpsAttrManager {
	
    @Autowired
    private SpsAttrDao spsAttrDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<SpsAttr> spsAttrList) {
        boolean resultFlag = false;
        if (null != spsAttrList && spsAttrList.size() > 0) {
            for (SpsAttr spsAttr : spsAttrList) {
                resultFlag = spsAttrDao.insert(spsAttr);
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
    public boolean insert(SpsAttr spsAttr) {
        return spsAttrDao.insert(spsAttr);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final SpsAttr spsAttr) {
        return spsAttrDao.update(spsAttr);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsAttr> querySpsAttrList(SpsAttrQuery queryBean) {
        return spsAttrDao.querySpsAttrList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsAttr> querySpsAttrListWithPage(SpsAttrQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new SpsAttrQuery();
        }

        // 查询总数
        int totalItem = querySpsAttrCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return spsAttrDao.querySpsAttrListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsAttrCount(SpsAttrQuery queryBean) {
        return spsAttrDao.querySpsAttrCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsAttr spsAttr) {
        return spsAttrDao.delete(spsAttr);
    }

    /**
     * {@inheritDoc}
     */
    public SpsAttr getSpsAttrById(Long id) {
        return spsAttrDao.getSpsAttrById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final SpsAttr[] spsAttrs) {
        boolean resultFlag = false;
        if (null != spsAttrs && spsAttrs.length > 0) {
            for (int i = 0; i < spsAttrs.length; i++) {
                resultFlag = delete(spsAttrs[i]);
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
    public boolean exist(SpsAttr spsAttr) {
        return spsAttrDao.exist(spsAttr);
    }
}
