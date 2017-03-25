package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.Language;
import com.letv.tbtSps.domain.query.LanguageQuery;
import com.letv.tbtSps.manager.LanguageManager;
import com.letv.tbtSps.service.LanguageService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * LanguageService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class LanguageServiceImpl implements LanguageService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(LanguageServiceImpl.class);

    @Autowired
    private LanguageManager languageManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LanguageService.batchInsert")
    public boolean insert(List<Language> languageList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(languageList)) {
                resultFlag = languageManager.insert(languageList);
            } else {
                LOG.warn("LanguageServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LanguageServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LanguageService.insert")
    public boolean insert(Language language) {
        boolean resultFlag = false;
        try {
            if (null != language) {
                if (languageManager.exist(language)) {
                    throw new ExistedException();
                }
                resultFlag = languageManager.insert(language);
            } else {
                LOG.warn("LanguageServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("LanguageServiceImpl#insert failed, language has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("LanguageServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LanguageService.update")
    public boolean update(Language language) {
        boolean resultFlag = false;
        try {
            if (null != language && null != language.getId()) {
                resultFlag = languageManager.update(language);
            } else {
                LOG.warn("LanguageServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LanguageServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LanguageService.queryLanguageList")
    public List<Language> queryLanguageList(LanguageQuery queryBean) {
        List<Language> languageList = null;
        try {
            languageList = languageManager.queryLanguageList(queryBean);
        } catch (Exception e) {
            LOG.error("LanguageServiceImpl#queryLanguageList has error.", e);
        }
        return languageList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LanguageService.queryLanguageListWithPage")
    public List<Language> queryLanguageListWithPage(LanguageQuery queryBean, PageUtil pageUtil) {
        List<Language> languageList = null;
        try {
            languageList = languageManager.queryLanguageListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("LanguageServiceImpl#queryLanguageListWithPage has error.", e);
        }
        return languageList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LanguageService.delete")
    public boolean delete(Language language) {
        boolean resultFlag = false;
        try {
            if (null != language && null != language.getId()) {
                resultFlag = languageManager.delete(language);
            } else {
                LOG.warn("LanguageServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LanguageServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LanguageService.batchDelete")
    public boolean delete(Language[] languages) {
        boolean resultFlag = false;
        try {
            if (null != languages && languages.length > 0) {
                resultFlag = languageManager.delete(languages);
            } else {
                LOG.warn("LanguageServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LanguageServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "LanguageService.getLanguageById")
    public Language getLanguageById(Long id) {
        Language language = null;
        try {
            if (null != id) {
                language = languageManager.getLanguageById(id);
            } else {
                LOG.warn("LanguageServiceImpl#getLanguageById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("LanguageServiceImpl#getLanguageById has error.", e);
        }
        return language;
    }
}

