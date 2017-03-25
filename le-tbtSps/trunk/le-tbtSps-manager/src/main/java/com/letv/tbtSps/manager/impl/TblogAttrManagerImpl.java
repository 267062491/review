package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.TblogAttr;
import com.letv.tbtSps.domain.query.TblogAttrQuery;
import com.letv.tbtSps.dao.TblogAttrDao;
import com.letv.tbtSps.manager.TblogAttrManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TblogAttrManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class TblogAttrManagerImpl extends BaseManager implements TblogAttrManager {
	
    @Autowired
    private TblogAttrDao tblogAttrDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<TblogAttr> tblogAttrList) {
        boolean resultFlag = false;
        if (null != tblogAttrList && tblogAttrList.size() > 0) {
            for (TblogAttr tblogAttr : tblogAttrList) {
                resultFlag = tblogAttrDao.insert(tblogAttr);
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
    public boolean insert(TblogAttr tblogAttr) {
        return tblogAttrDao.insert(tblogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final TblogAttr tblogAttr) {
        return tblogAttrDao.update(tblogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public List<TblogAttr> queryTblogAttrList(TblogAttrQuery queryBean) {
        return tblogAttrDao.queryTblogAttrList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<TblogAttr> queryTblogAttrListWithPage(TblogAttrQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new TblogAttrQuery();
        }

        // 查询总数
        int totalItem = queryTblogAttrCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return tblogAttrDao.queryTblogAttrListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryTblogAttrCount(TblogAttrQuery queryBean) {
        return tblogAttrDao.queryTblogAttrCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(TblogAttr tblogAttr) {
        return tblogAttrDao.delete(tblogAttr);
    }

    /**
     * {@inheritDoc}
     */
    public TblogAttr getTblogAttrById(Long id) {
        return tblogAttrDao.getTblogAttrById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final TblogAttr[] tblogAttrs) {
        boolean resultFlag = false;
        if (null != tblogAttrs && tblogAttrs.length > 0) {
            for (int i = 0; i < tblogAttrs.length; i++) {
                resultFlag = delete(tblogAttrs[i]);
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
    public boolean exist(TblogAttr tblogAttr) {
        return tblogAttrDao.exist(tblogAttr);
    }
}
