package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.Tbinfo;
import com.letv.tbtSps.domain.query.TbinfoQuery;
import com.letv.tbtSps.dao.TbinfoDao;
import com.letv.tbtSps.manager.TbinfoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TbinfoManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class TbinfoManagerImpl extends BaseManager implements TbinfoManager {
	
    @Autowired
    private TbinfoDao tbinfoDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<Tbinfo> tbinfoList) {
        boolean resultFlag = false;
        if (null != tbinfoList && tbinfoList.size() > 0) {
            for (Tbinfo tbinfo : tbinfoList) {
                resultFlag = tbinfoDao.insert(tbinfo);
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
    public boolean insert(Tbinfo tbinfo) {
        return tbinfoDao.insert(tbinfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final Tbinfo tbinfo) {
        return tbinfoDao.update(tbinfo);
    }

    /**
     * {@inheritDoc}
     */
    public List<Tbinfo> queryTbinfoList(TbinfoQuery queryBean) {
        return tbinfoDao.queryTbinfoList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Tbinfo> queryTbinfoListWithPage(TbinfoQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new TbinfoQuery();
        }

        // 查询总数
        int totalItem = queryTbinfoCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return tbinfoDao.queryTbinfoListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryTbinfoCount(TbinfoQuery queryBean) {
        return tbinfoDao.queryTbinfoCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Tbinfo tbinfo) {
        return tbinfoDao.delete(tbinfo);
    }

    /**
     * {@inheritDoc}
     */
    public Tbinfo getTbinfoById(Long id) {
        return tbinfoDao.getTbinfoById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final Tbinfo[] tbinfos) {
        boolean resultFlag = false;
        if (null != tbinfos && tbinfos.length > 0) {
            for (int i = 0; i < tbinfos.length; i++) {
                resultFlag = delete(tbinfos[i]);
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
    public boolean exist(Tbinfo tbinfo) {
        return tbinfoDao.exist(tbinfo);
    }
}
