package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationSpsLanguage;
import com.letv.tbtSps.domain.query.RelationSpsLanguageQuery;
import com.letv.tbtSps.dao.RelationSpsLanguageDao;
import com.letv.tbtSps.manager.RelationSpsLanguageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationSpsLanguageManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationSpsLanguageManagerImpl extends BaseManager implements RelationSpsLanguageManager {
	
    @Autowired
    private RelationSpsLanguageDao relationSpsLanguageDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationSpsLanguage> relationSpsLanguageList) {
        boolean resultFlag = false;
        if (null != relationSpsLanguageList && relationSpsLanguageList.size() > 0) {
            for (RelationSpsLanguage relationSpsLanguage : relationSpsLanguageList) {
                resultFlag = relationSpsLanguageDao.insert(relationSpsLanguage);
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
    public boolean insert(RelationSpsLanguage relationSpsLanguage) {
        return relationSpsLanguageDao.insert(relationSpsLanguage);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationSpsLanguage relationSpsLanguage) {
        return relationSpsLanguageDao.update(relationSpsLanguage);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsLanguage> queryRelationSpsLanguageList(RelationSpsLanguageQuery queryBean) {
        return relationSpsLanguageDao.queryRelationSpsLanguageList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsLanguage> queryRelationSpsLanguageListWithPage(RelationSpsLanguageQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationSpsLanguageQuery();
        }

        // 查询总数
        int totalItem = queryRelationSpsLanguageCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationSpsLanguageDao.queryRelationSpsLanguageListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsLanguageCount(RelationSpsLanguageQuery queryBean) {
        return relationSpsLanguageDao.queryRelationSpsLanguageCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsLanguage relationSpsLanguage) {
        return relationSpsLanguageDao.delete(relationSpsLanguage);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsLanguage getRelationSpsLanguageById(Long id) {
        return relationSpsLanguageDao.getRelationSpsLanguageById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationSpsLanguage[] relationSpsLanguages) {
        boolean resultFlag = false;
        if (null != relationSpsLanguages && relationSpsLanguages.length > 0) {
            for (int i = 0; i < relationSpsLanguages.length; i++) {
                resultFlag = delete(relationSpsLanguages[i]);
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
    public boolean exist(RelationSpsLanguage relationSpsLanguage) {
        return relationSpsLanguageDao.exist(relationSpsLanguage);
    }
}
