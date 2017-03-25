package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.InternationalStandard;
import com.letv.tbtSps.domain.query.InternationalStandardQuery;
import com.letv.tbtSps.dao.InternationalStandardDao;
import com.letv.tbtSps.manager.InternationalStandardManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * InternationalStandardManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class InternationalStandardManagerImpl extends BaseManager implements InternationalStandardManager {
	
    @Autowired
    private InternationalStandardDao internationalStandardDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<InternationalStandard> internationalStandardList) {
        boolean resultFlag = false;
        if (null != internationalStandardList && internationalStandardList.size() > 0) {
            for (InternationalStandard internationalStandard : internationalStandardList) {
                resultFlag = internationalStandardDao.insert(internationalStandard);
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
    public boolean insert(InternationalStandard internationalStandard) {
        return internationalStandardDao.insert(internationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final InternationalStandard internationalStandard) {
        return internationalStandardDao.update(internationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public List<InternationalStandard> queryInternationalStandardList(InternationalStandardQuery queryBean) {
        return internationalStandardDao.queryInternationalStandardList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<InternationalStandard> queryInternationalStandardListWithPage(InternationalStandardQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new InternationalStandardQuery();
        }

        // 查询总数
        int totalItem = queryInternationalStandardCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return internationalStandardDao.queryInternationalStandardListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryInternationalStandardCount(InternationalStandardQuery queryBean) {
        return internationalStandardDao.queryInternationalStandardCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(InternationalStandard internationalStandard) {
        return internationalStandardDao.delete(internationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public InternationalStandard getInternationalStandardById(Long id) {
        return internationalStandardDao.getInternationalStandardById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final InternationalStandard[] internationalStandards) {
        boolean resultFlag = false;
        if (null != internationalStandards && internationalStandards.length > 0) {
            for (int i = 0; i < internationalStandards.length; i++) {
                resultFlag = delete(internationalStandards[i]);
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
    public boolean exist(InternationalStandard internationalStandard) {
        return internationalStandardDao.exist(internationalStandard);
    }
}
