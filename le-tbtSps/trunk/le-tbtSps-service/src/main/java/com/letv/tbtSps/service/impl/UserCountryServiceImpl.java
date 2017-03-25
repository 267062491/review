package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.UserCountry;
import com.letv.tbtSps.domain.query.UserCountryQuery;
import com.letv.tbtSps.manager.UserCountryManager;
import com.letv.tbtSps.service.UserCountryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * UserCountryService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class UserCountryServiceImpl implements UserCountryService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(UserCountryServiceImpl.class);

    @Autowired
    private UserCountryManager userCountryManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserCountryService.batchInsert")
    public boolean insert(List<UserCountry> userCountryList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(userCountryList)) {
                resultFlag = userCountryManager.insert(userCountryList);
            } else {
                LOG.warn("UserCountryServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserCountryServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserCountryService.insert")
    public boolean insert(UserCountry userCountry) {
        boolean resultFlag = false;
        try {
            if (null != userCountry) {
                if (userCountryManager.exist(userCountry)) {
                    throw new ExistedException();
                }
                resultFlag = userCountryManager.insert(userCountry);
            } else {
                LOG.warn("UserCountryServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("UserCountryServiceImpl#insert failed, userCountry has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("UserCountryServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserCountryService.update")
    public boolean update(UserCountry userCountry) {
        boolean resultFlag = false;
        try {
            if (null != userCountry && null != userCountry.getId()) {
                resultFlag = userCountryManager.update(userCountry);
            } else {
                LOG.warn("UserCountryServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserCountryServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserCountryService.queryUserCountryList")
    public List<UserCountry> queryUserCountryList(UserCountryQuery queryBean) {
        List<UserCountry> userCountryList = null;
        try {
            userCountryList = userCountryManager.queryUserCountryList(queryBean);
        } catch (Exception e) {
            LOG.error("UserCountryServiceImpl#queryUserCountryList has error.", e);
        }
        return userCountryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserCountryService.queryUserCountryListWithPage")
    public List<UserCountry> queryUserCountryListWithPage(UserCountryQuery queryBean, PageUtil pageUtil) {
        List<UserCountry> userCountryList = null;
        try {
            userCountryList = userCountryManager.queryUserCountryListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("UserCountryServiceImpl#queryUserCountryListWithPage has error.", e);
        }
        return userCountryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserCountryService.delete")
    public boolean delete(UserCountry userCountry) {
        boolean resultFlag = false;
        try {
            if (null != userCountry && null != userCountry.getId()) {
                resultFlag = userCountryManager.delete(userCountry);
            } else {
                LOG.warn("UserCountryServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserCountryServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserCountryService.batchDelete")
    public boolean delete(UserCountry[] userCountrys) {
        boolean resultFlag = false;
        try {
            if (null != userCountrys && userCountrys.length > 0) {
                resultFlag = userCountryManager.delete(userCountrys);
            } else {
                LOG.warn("UserCountryServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserCountryServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserCountryService.getUserCountryById")
    public UserCountry getUserCountryById(Long id) {
        UserCountry userCountry = null;
        try {
            if (null != id) {
                userCountry = userCountryManager.getUserCountryById(id);
            } else {
                LOG.warn("UserCountryServiceImpl#getUserCountryById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserCountryServiceImpl#getUserCountryById has error.", e);
        }
        return userCountry;
    }
}

