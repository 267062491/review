package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.SystemPar;
import com.letv.tbtSps.domain.query.SystemParQuery;
import com.letv.tbtSps.dao.SystemParDao;
import com.letv.tbtSps.manager.SystemParManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SystemParManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
@Component
public class SystemParManagerImpl extends BaseManager implements SystemParManager {
	
    @Autowired
    private SystemParDao systemParDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<SystemPar> systemParList) {
        boolean resultFlag = false;
        if (null != systemParList && systemParList.size() > 0) {
            for (SystemPar systemPar : systemParList) {
                resultFlag = systemParDao.insert(systemPar);
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
    public boolean insert(SystemPar systemPar) {
        return systemParDao.insert(systemPar);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final SystemPar systemPar) {
        return systemParDao.update(systemPar);
    }

    /**
     * {@inheritDoc}
     */
    public List<SystemPar> querySystemParList(SystemParQuery queryBean) {
        return systemParDao.querySystemParList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<SystemPar> querySystemParListWithPage(SystemParQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new SystemParQuery();
        }

        // 查询总数
        int totalItem = querySystemParCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return systemParDao.querySystemParListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int querySystemParCount(SystemParQuery queryBean) {
        return systemParDao.querySystemParCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(SystemPar systemPar) {
        return systemParDao.delete(systemPar);
    }

    /**
     * {@inheritDoc}
     */
    public SystemPar getSystemParById(Long id) {
        return systemParDao.getSystemParById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final SystemPar[] systemPars) {
        boolean resultFlag = false;
        if (null != systemPars && systemPars.length > 0) {
            for (int i = 0; i < systemPars.length; i++) {
                resultFlag = delete(systemPars[i]);
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
    public boolean exist(SystemPar systemPar) {
        return systemParDao.exist(systemPar);
    }
}
