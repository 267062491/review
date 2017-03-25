package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.Country;
import com.letv.tbtSps.domain.query.CountryQuery;
import com.letv.tbtSps.manager.CountryManager;
import com.letv.tbtSps.service.CountryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * CountryService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class CountryServiceImpl implements CountryService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(CountryServiceImpl.class);

    @Autowired
    private CountryManager countryManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "CountryService.batchInsert")
    public boolean insert(List<Country> countryList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(countryList)) {
                resultFlag = countryManager.insert(countryList);
            } else {
                LOG.warn("CountryServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("CountryServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "CountryService.insert")
    public boolean insert(Country country) {
        boolean resultFlag = false;
        try {
            if (null != country) {
                if (countryManager.exist(country)) {
                    throw new ExistedException();
                }
                resultFlag = countryManager.insert(country);
            } else {
                LOG.warn("CountryServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("CountryServiceImpl#insert failed, country has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("CountryServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "CountryService.update")
    public boolean update(Country country) {
        boolean resultFlag = false;
        try {
            if (null != country && null != country.getId()) {
                resultFlag = countryManager.update(country);
            } else {
                LOG.warn("CountryServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("CountryServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "CountryService.queryCountryList")
    public List<Country> queryCountryList(CountryQuery queryBean) {
        List<Country> countryList = null;
        try {
            countryList = countryManager.queryCountryList(queryBean);
        } catch (Exception e) {
            LOG.error("CountryServiceImpl#queryCountryList has error.", e);
        }
        return countryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "CountryService.queryCountryListWithPage")
    public List<Country> queryCountryListWithPage(CountryQuery queryBean, PageUtil pageUtil) {
        List<Country> countryList = null;
        try {
            countryList = countryManager.queryCountryListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("CountryServiceImpl#queryCountryListWithPage has error.", e);
        }
        return countryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "CountryService.delete")
    public boolean delete(Country country) {
        boolean resultFlag = false;
        try {
            if (null != country && null != country.getId()) {
                resultFlag = countryManager.delete(country);
            } else {
                LOG.warn("CountryServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("CountryServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "CountryService.batchDelete")
    public boolean delete(Country[] countrys) {
        boolean resultFlag = false;
        try {
            if (null != countrys && countrys.length > 0) {
                resultFlag = countryManager.delete(countrys);
            } else {
                LOG.warn("CountryServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("CountryServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "CountryService.getCountryById")
    public Country getCountryById(Long id) {
        Country country = null;
        try {
            if (null != id) {
                country = countryManager.getCountryById(id);
            } else {
                LOG.warn("CountryServiceImpl#getCountryById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("CountryServiceImpl#getCountryById has error.", e);
        }
        return country;
    }
}

