package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationSpsInternationalStandard;
import com.letv.tbtSps.domain.query.RelationSpsInternationalStandardQuery;
import com.letv.tbtSps.dao.RelationSpsInternationalStandardDao;
import com.letv.tbtSps.manager.RelationSpsInternationalStandardManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationSpsInternationalStandardManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationSpsInternationalStandardManagerImpl extends BaseManager implements RelationSpsInternationalStandardManager {
	
    @Autowired
    private RelationSpsInternationalStandardDao relationSpsInternationalStandardDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationSpsInternationalStandard> relationSpsInternationalStandardList) {
        boolean resultFlag = false;
        if (null != relationSpsInternationalStandardList && relationSpsInternationalStandardList.size() > 0) {
            for (RelationSpsInternationalStandard relationSpsInternationalStandard : relationSpsInternationalStandardList) {
                resultFlag = relationSpsInternationalStandardDao.insert(relationSpsInternationalStandard);
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
    public boolean insert(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        return relationSpsInternationalStandardDao.insert(relationSpsInternationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationSpsInternationalStandard relationSpsInternationalStandard) {
        return relationSpsInternationalStandardDao.update(relationSpsInternationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardList(RelationSpsInternationalStandardQuery queryBean) {
        return relationSpsInternationalStandardDao.queryRelationSpsInternationalStandardList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardListWithPage(RelationSpsInternationalStandardQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationSpsInternationalStandardQuery();
        }

        // 查询总数
        int totalItem = queryRelationSpsInternationalStandardCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationSpsInternationalStandardDao.queryRelationSpsInternationalStandardListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsInternationalStandardCount(RelationSpsInternationalStandardQuery queryBean) {
        return relationSpsInternationalStandardDao.queryRelationSpsInternationalStandardCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        return relationSpsInternationalStandardDao.delete(relationSpsInternationalStandard);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsInternationalStandard getRelationSpsInternationalStandardById(Long id) {
        return relationSpsInternationalStandardDao.getRelationSpsInternationalStandardById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationSpsInternationalStandard[] relationSpsInternationalStandards) {
        boolean resultFlag = false;
        if (null != relationSpsInternationalStandards && relationSpsInternationalStandards.length > 0) {
            for (int i = 0; i < relationSpsInternationalStandards.length; i++) {
                resultFlag = delete(relationSpsInternationalStandards[i]);
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
    public boolean exist(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        return relationSpsInternationalStandardDao.exist(relationSpsInternationalStandard);
    }
}
