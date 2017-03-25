package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.SpsResidualInfo;
import com.letv.tbtSps.domain.query.SpsResidualInfoQuery;
import com.letv.tbtSps.dao.SpsResidualInfoDao;
import com.letv.tbtSps.manager.SpsResidualInfoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SpsResidualInfoManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class SpsResidualInfoManagerImpl extends BaseManager implements SpsResidualInfoManager {
	
    @Autowired
    private SpsResidualInfoDao spsResidualInfoDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<SpsResidualInfo> spsResidualInfoList) {
        boolean resultFlag = false;
        if (null != spsResidualInfoList && spsResidualInfoList.size() > 0) {
            for (SpsResidualInfo spsResidualInfo : spsResidualInfoList) {
                resultFlag = spsResidualInfoDao.insert(spsResidualInfo);
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
    public boolean insert(SpsResidualInfo spsResidualInfo) {
        return spsResidualInfoDao.insert(spsResidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final SpsResidualInfo spsResidualInfo) {
        return spsResidualInfoDao.update(spsResidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsResidualInfo> querySpsResidualInfoList(SpsResidualInfoQuery queryBean) {
        return spsResidualInfoDao.querySpsResidualInfoList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsResidualInfo> querySpsResidualInfoListWithPage(SpsResidualInfoQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new SpsResidualInfoQuery();
        }

        // 查询总数
        int totalItem = querySpsResidualInfoCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return spsResidualInfoDao.querySpsResidualInfoListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsResidualInfoCount(SpsResidualInfoQuery queryBean) {
        return spsResidualInfoDao.querySpsResidualInfoCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsResidualInfo spsResidualInfo) {
        return spsResidualInfoDao.delete(spsResidualInfo);
    }

    /**
     * {@inheritDoc}
     */
    public SpsResidualInfo getSpsResidualInfoById(Long id) {
        return spsResidualInfoDao.getSpsResidualInfoById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final SpsResidualInfo[] spsResidualInfos) {
        boolean resultFlag = false;
        if (null != spsResidualInfos && spsResidualInfos.length > 0) {
            for (int i = 0; i < spsResidualInfos.length; i++) {
                resultFlag = delete(spsResidualInfos[i]);
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
    public boolean exist(SpsResidualInfo spsResidualInfo) {
        return spsResidualInfoDao.exist(spsResidualInfo);
    }
}
