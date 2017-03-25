package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.TbresidualInfo;
import com.letv.tbtSps.domain.query.TbresidualInfoQuery;
import com.letv.tbtSps.dao.TbresidualInfoDao;
import com.letv.tbtSps.manager.TbresidualInfoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TbresidualInfoManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class TbresidualInfoManagerImpl extends BaseManager implements TbresidualInfoManager {
	
    @Autowired
    private TbresidualInfoDao tbresidualInfoDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<TbresidualInfo> tbresidualInfoList) {
        boolean resultFlag = false;
        if (null != tbresidualInfoList && tbresidualInfoList.size() > 0) {
            for (TbresidualInfo tbresidualInfo : tbresidualInfoList) {
                resultFlag = tbresidualInfoDao.insert(tbresidualInfo);
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
    public boolean insert(TbresidualInfo tbresidualInfo) {
        return tbresidualInfoDao.insert(tbresidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final TbresidualInfo tbresidualInfo) {
        return tbresidualInfoDao.update(tbresidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public List<TbresidualInfo> queryTbresidualInfoList(TbresidualInfoQuery queryBean) {
        return tbresidualInfoDao.queryTbresidualInfoList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<TbresidualInfo> queryTbresidualInfoListWithPage(TbresidualInfoQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new TbresidualInfoQuery();
        }

        // 查询总数
        int totalItem = queryTbresidualInfoCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return tbresidualInfoDao.queryTbresidualInfoListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryTbresidualInfoCount(TbresidualInfoQuery queryBean) {
        return tbresidualInfoDao.queryTbresidualInfoCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(TbresidualInfo tbresidualInfo) {
        return tbresidualInfoDao.delete(tbresidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public TbresidualInfo getTbresidualInfoById(Long id) {
        return tbresidualInfoDao.getTbresidualInfoById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final TbresidualInfo[] tbresidualInfos) {
        boolean resultFlag = false;
        if (null != tbresidualInfos && tbresidualInfos.length > 0) {
            for (int i = 0; i < tbresidualInfos.length; i++) {
                resultFlag = delete(tbresidualInfos[i]);
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
    public boolean exist(TbresidualInfo tbresidualInfo) {
        return tbresidualInfoDao.exist(tbresidualInfo);
    }
}
