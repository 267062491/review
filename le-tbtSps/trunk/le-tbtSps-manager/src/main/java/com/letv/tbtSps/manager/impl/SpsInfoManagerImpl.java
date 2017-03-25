package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.tbtSps.dao.SpsInfoDao;
import com.letv.tbtSps.manager.SpsInfoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SpsInfoManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class SpsInfoManagerImpl extends BaseManager implements SpsInfoManager {
	
    @Autowired
    private SpsInfoDao spsInfoDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<SpsInfo> spsInfoList) {
        boolean resultFlag = false;
        if (null != spsInfoList && spsInfoList.size() > 0) {
            for (SpsInfo spsInfo : spsInfoList) {
                resultFlag = spsInfoDao.insert(spsInfo);
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
    public boolean insert(SpsInfo spsInfo) {
        return spsInfoDao.insert(spsInfo);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final SpsInfo spsInfo) {
        return spsInfoDao.update(spsInfo);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsInfo> querySpsInfoList(SpsInfoQuery queryBean) {
        return spsInfoDao.querySpsInfoList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsInfo> querySpsInfoListWithPage(SpsInfoQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new SpsInfoQuery();
        }

        // 查询总数
        int totalItem = querySpsInfoCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return spsInfoDao.querySpsInfoListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsInfoCount(SpsInfoQuery queryBean) {
        return spsInfoDao.querySpsInfoCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsInfo spsInfo) {
        return spsInfoDao.delete(spsInfo);
    }

    /**
     * {@inheritDoc}
     */
    public SpsInfo getSpsInfoById(Long id) {
        return spsInfoDao.getSpsInfoById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final SpsInfo[] spsInfos) {
        boolean resultFlag = false;
        if (null != spsInfos && spsInfos.length > 0) {
            for (int i = 0; i < spsInfos.length; i++) {
                resultFlag = delete(spsInfos[i]);
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
    public boolean exist(SpsInfo spsInfo) {
        return spsInfoDao.exist(spsInfo);
    }
}
