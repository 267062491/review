package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.Targereason;
import com.letv.tbtSps.domain.query.TargereasonQuery;
import com.letv.tbtSps.dao.TargereasonDao;
import com.letv.tbtSps.manager.TargereasonManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TargereasonManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class TargereasonManagerImpl extends BaseManager implements TargereasonManager {
	
    @Autowired
    private TargereasonDao targereasonDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<Targereason> targereasonList) {
        boolean resultFlag = false;
        if (null != targereasonList && targereasonList.size() > 0) {
            for (Targereason targereason : targereasonList) {
                resultFlag = targereasonDao.insert(targereason);
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
    public boolean insert(Targereason targereason) {
        return targereasonDao.insert(targereason);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final Targereason targereason) {
        return targereasonDao.update(targereason);
    }

    /**
     * {@inheritDoc}
     */
    public List<Targereason> queryTargereasonList(TargereasonQuery queryBean) {
        return targereasonDao.queryTargereasonList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Targereason> queryTargereasonListWithPage(TargereasonQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new TargereasonQuery();
        }

        // 查询总数
        int totalItem = queryTargereasonCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return targereasonDao.queryTargereasonListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryTargereasonCount(TargereasonQuery queryBean) {
        return targereasonDao.queryTargereasonCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Targereason targereason) {
        return targereasonDao.delete(targereason);
    }

    /**
     * {@inheritDoc}
     */
    public Targereason getTargereasonById(Long id) {
        return targereasonDao.getTargereasonById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final Targereason[] targereasons) {
        boolean resultFlag = false;
        if (null != targereasons && targereasons.length > 0) {
            for (int i = 0; i < targereasons.length; i++) {
                resultFlag = delete(targereasons[i]);
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
    public boolean exist(Targereason targereason) {
        return targereasonDao.exist(targereason);
    }
}
