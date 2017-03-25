package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.Country;
import com.letv.tbtSps.domain.query.CountryQuery;
import com.letv.tbtSps.dao.CountryDao;
import com.letv.tbtSps.manager.CountryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CountryManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class CountryManagerImpl extends BaseManager implements CountryManager {
	
    @Autowired
    private CountryDao countryDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<Country> countryList) {
        boolean resultFlag = false;
        if (null != countryList && countryList.size() > 0) {
            for (Country country : countryList) {
                resultFlag = countryDao.insert(country);
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
    public boolean insert(Country country) {
        return countryDao.insert(country);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final Country country) {
        return countryDao.update(country);
    }

    /**
     * {@inheritDoc}
     */
    public List<Country> queryCountryList(CountryQuery queryBean) {
        return countryDao.queryCountryList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Country> queryCountryListWithPage(CountryQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new CountryQuery();
        }

        // 查询总数
        int totalItem = queryCountryCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return countryDao.queryCountryListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryCountryCount(CountryQuery queryBean) {
        return countryDao.queryCountryCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Country country) {
        return countryDao.delete(country);
    }

    /**
     * {@inheritDoc}
     */
    public Country getCountryById(Long id) {
        return countryDao.getCountryById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final Country[] countrys) {
        boolean resultFlag = false;
        if (null != countrys && countrys.length > 0) {
            for (int i = 0; i < countrys.length; i++) {
                resultFlag = delete(countrys[i]);
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
    public boolean exist(Country country) {
        return countryDao.exist(country);
    }
}
