package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.Tbattr;
import com.letv.tbtSps.domain.query.TbattrQuery;
import com.letv.tbtSps.dao.TbattrDao;
import com.letv.tbtSps.manager.TbattrManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TbattrManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class TbattrManagerImpl extends BaseManager implements TbattrManager {
	
    @Autowired
    private TbattrDao tbattrDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<Tbattr> tbattrList) {
        boolean resultFlag = false;
        if (null != tbattrList && tbattrList.size() > 0) {
            for (Tbattr tbattr : tbattrList) {
                resultFlag = tbattrDao.insert(tbattr);
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
    public boolean insert(Tbattr tbattr) {
        return tbattrDao.insert(tbattr);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final Tbattr tbattr) {
        return tbattrDao.update(tbattr);
    }

    /**
     * {@inheritDoc}
     */
    public List<Tbattr> queryTbattrList(TbattrQuery queryBean) {
        return tbattrDao.queryTbattrList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Tbattr> queryTbattrListWithPage(TbattrQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new TbattrQuery();
        }

        // 查询总数
        int totalItem = queryTbattrCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return tbattrDao.queryTbattrListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryTbattrCount(TbattrQuery queryBean) {
        return tbattrDao.queryTbattrCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Tbattr tbattr) {
        return tbattrDao.delete(tbattr);
    }

    /**
     * {@inheritDoc}
     */
    public Tbattr getTbattrById(Long id) {
        return tbattrDao.getTbattrById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final Tbattr[] tbattrs) {
        boolean resultFlag = false;
        if (null != tbattrs && tbattrs.length > 0) {
            for (int i = 0; i < tbattrs.length; i++) {
                resultFlag = delete(tbattrs[i]);
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
    public boolean exist(Tbattr tbattr) {
        return tbattrDao.exist(tbattr);
    }
}
