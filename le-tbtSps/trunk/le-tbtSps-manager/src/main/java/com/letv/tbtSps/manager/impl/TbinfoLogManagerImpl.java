package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.TbinfoLog;
import com.letv.tbtSps.domain.query.TbinfoLogQuery;
import com.letv.tbtSps.dao.TbinfoLogDao;
import com.letv.tbtSps.manager.TbinfoLogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TbinfoLogManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class TbinfoLogManagerImpl extends BaseManager implements TbinfoLogManager {
	
    @Autowired
    private TbinfoLogDao tbinfoLogDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<TbinfoLog> tbinfoLogList) {
        boolean resultFlag = false;
        if (null != tbinfoLogList && tbinfoLogList.size() > 0) {
            for (TbinfoLog tbinfoLog : tbinfoLogList) {
                resultFlag = tbinfoLogDao.insert(tbinfoLog);
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
    public boolean insert(TbinfoLog tbinfoLog) {
        return tbinfoLogDao.insert(tbinfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final TbinfoLog tbinfoLog) {
        return tbinfoLogDao.update(tbinfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public List<TbinfoLog> queryTbinfoLogList(TbinfoLogQuery queryBean) {
        return tbinfoLogDao.queryTbinfoLogList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<TbinfoLog> queryTbinfoLogListWithPage(TbinfoLogQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new TbinfoLogQuery();
        }

        // 查询总数
        int totalItem = queryTbinfoLogCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return tbinfoLogDao.queryTbinfoLogListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryTbinfoLogCount(TbinfoLogQuery queryBean) {
        return tbinfoLogDao.queryTbinfoLogCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(TbinfoLog tbinfoLog) {
        return tbinfoLogDao.delete(tbinfoLog);
    }

    /**
     * {@inheritDoc}
     */
    public TbinfoLog getTbinfoLogById(Long id) {
        return tbinfoLogDao.getTbinfoLogById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final TbinfoLog[] tbinfoLogs) {
        boolean resultFlag = false;
        if (null != tbinfoLogs && tbinfoLogs.length > 0) {
            for (int i = 0; i < tbinfoLogs.length; i++) {
                resultFlag = delete(tbinfoLogs[i]);
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
    public boolean exist(TbinfoLog tbinfoLog) {
        return tbinfoLogDao.exist(tbinfoLog);
    }
}
