package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationTblanguage;
import com.letv.tbtSps.domain.query.RelationTblanguageQuery;
import com.letv.tbtSps.dao.RelationTblanguageDao;
import com.letv.tbtSps.manager.RelationTblanguageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationTblanguageManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationTblanguageManagerImpl extends BaseManager implements RelationTblanguageManager {
	
    @Autowired
    private RelationTblanguageDao relationTblanguageDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationTblanguage> relationTblanguageList) {
        boolean resultFlag = false;
        if (null != relationTblanguageList && relationTblanguageList.size() > 0) {
            for (RelationTblanguage relationTblanguage : relationTblanguageList) {
                resultFlag = relationTblanguageDao.insert(relationTblanguage);
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
    public boolean insert(RelationTblanguage relationTblanguage) {
        return relationTblanguageDao.insert(relationTblanguage);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationTblanguage relationTblanguage) {
        return relationTblanguageDao.update(relationTblanguage);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTblanguage> queryRelationTblanguageList(RelationTblanguageQuery queryBean) {
        return relationTblanguageDao.queryRelationTblanguageList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationTblanguage> queryRelationTblanguageListWithPage(RelationTblanguageQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationTblanguageQuery();
        }

        // 查询总数
        int totalItem = queryRelationTblanguageCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationTblanguageDao.queryRelationTblanguageListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationTblanguageCount(RelationTblanguageQuery queryBean) {
        return relationTblanguageDao.queryRelationTblanguageCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationTblanguage relationTblanguage) {
        return relationTblanguageDao.delete(relationTblanguage);
    }

    /**
     * {@inheritDoc}
     */
    public RelationTblanguage getRelationTblanguageById(Long id) {
        return relationTblanguageDao.getRelationTblanguageById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationTblanguage[] relationTblanguages) {
        boolean resultFlag = false;
        if (null != relationTblanguages && relationTblanguages.length > 0) {
            for (int i = 0; i < relationTblanguages.length; i++) {
                resultFlag = delete(relationTblanguages[i]);
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
    public boolean exist(RelationTblanguage relationTblanguage) {
        return relationTblanguageDao.exist(relationTblanguage);
    }
}
