package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.query.SpsInfoLogQuery;
import com.letv.tbtSps.dao.SpsInfoLogDao;
import com.letv.tbtSps.manager.SpsInfoLogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SpsInfoLogManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class SpsInfoLogManagerImpl extends BaseManager implements SpsInfoLogManager {
	
    @Autowired
    private SpsInfoLogDao spsInfoLogDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<SpsInfoLog> spsInfoLogList) {
        boolean resultFlag = false;
        if (null != spsInfoLogList && spsInfoLogList.size() > 0) {
            for (SpsInfoLog spsInfoLog : spsInfoLogList) {
                resultFlag = spsInfoLogDao.insert(spsInfoLog);
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
    public boolean insert(SpsInfoLog spsInfoLog) {
        return spsInfoLogDao.insert(spsInfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final SpsInfoLog spsInfoLog) {
        return spsInfoLogDao.update(spsInfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsInfoLog> querySpsInfoLogList(SpsInfoLogQuery queryBean) {
        return spsInfoLogDao.querySpsInfoLogList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SpsInfoLog> querySpsInfoLogListWithPage(SpsInfoLogQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new SpsInfoLogQuery();
        }

        // 查询总数
        int totalItem = querySpsInfoLogCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return spsInfoLogDao.querySpsInfoLogListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int querySpsInfoLogCount(SpsInfoLogQuery queryBean) {
        return spsInfoLogDao.querySpsInfoLogCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SpsInfoLog spsInfoLog) {
        return spsInfoLogDao.delete(spsInfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public SpsInfoLog getSpsInfoLogById(Long id) {
        return spsInfoLogDao.getSpsInfoLogById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final SpsInfoLog[] spsInfoLogs) {
        boolean resultFlag = false;
        if (null != spsInfoLogs && spsInfoLogs.length > 0) {
            for (int i = 0; i < spsInfoLogs.length; i++) {
                resultFlag = delete(spsInfoLogs[i]);
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
    public boolean exist(SpsInfoLog spsInfoLog) {
        return spsInfoLogDao.exist(spsInfoLog);
    }
}
