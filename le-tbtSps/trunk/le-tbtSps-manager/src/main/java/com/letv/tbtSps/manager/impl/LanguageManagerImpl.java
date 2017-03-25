package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.Language;
import com.letv.tbtSps.domain.query.LanguageQuery;
import com.letv.tbtSps.dao.LanguageDao;
import com.letv.tbtSps.manager.LanguageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * LanguageManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class LanguageManagerImpl extends BaseManager implements LanguageManager {
	
    @Autowired
    private LanguageDao languageDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<Language> languageList) {
        boolean resultFlag = false;
        if (null != languageList && languageList.size() > 0) {
            for (Language language : languageList) {
                resultFlag = languageDao.insert(language);
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
    public boolean insert(Language language) {
        return languageDao.insert(language);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final Language language) {
        return languageDao.update(language);
    }

    /**
     * {@inheritDoc}
     */
    public List<Language> queryLanguageList(LanguageQuery queryBean) {
        return languageDao.queryLanguageList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Language> queryLanguageListWithPage(LanguageQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new LanguageQuery();
        }

        // 查询总数
        int totalItem = queryLanguageCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return languageDao.queryLanguageListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryLanguageCount(LanguageQuery queryBean) {
        return languageDao.queryLanguageCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Language language) {
        return languageDao.delete(language);
    }

    /**
     * {@inheritDoc}
     */
    public Language getLanguageById(Long id) {
        return languageDao.getLanguageById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final Language[] languages) {
        boolean resultFlag = false;
        if (null != languages && languages.length > 0) {
            for (int i = 0; i < languages.length; i++) {
                resultFlag = delete(languages[i]);
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
    public boolean exist(Language language) {
        return languageDao.exist(language);
    }
}
