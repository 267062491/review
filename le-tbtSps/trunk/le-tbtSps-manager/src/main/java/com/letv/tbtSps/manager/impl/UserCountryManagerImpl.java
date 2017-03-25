package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.UserCountry;
import com.letv.tbtSps.domain.query.UserCountryQuery;
import com.letv.tbtSps.dao.UserCountryDao;
import com.letv.tbtSps.manager.UserCountryManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserCountryManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class UserCountryManagerImpl extends BaseManager implements UserCountryManager {
	
    @Autowired
    private UserCountryDao userCountryDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<UserCountry> userCountryList) {
        boolean resultFlag = false;
        if (null != userCountryList && userCountryList.size() > 0) {
            for (UserCountry userCountry : userCountryList) {
                resultFlag = userCountryDao.insert(userCountry);
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
    public boolean insert(UserCountry userCountry) {
        return userCountryDao.insert(userCountry);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final UserCountry userCountry) {
        return userCountryDao.update(userCountry);
    }

    /**
     * {@inheritDoc}
     */
    public List<UserCountry> queryUserCountryList(UserCountryQuery queryBean) {
        return userCountryDao.queryUserCountryList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<UserCountry> queryUserCountryListWithPage(UserCountryQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new UserCountryQuery();
        }

        // 查询总数
        int totalItem = queryUserCountryCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return userCountryDao.queryUserCountryListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryUserCountryCount(UserCountryQuery queryBean) {
        return userCountryDao.queryUserCountryCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(UserCountry userCountry) {
        return userCountryDao.delete(userCountry);
    }

    /**
     * {@inheritDoc}
     */
    public UserCountry getUserCountryById(Long id) {
        return userCountryDao.getUserCountryById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final UserCountry[] userCountrys) {
        boolean resultFlag = false;
        if (null != userCountrys && userCountrys.length > 0) {
            for (int i = 0; i < userCountrys.length; i++) {
                resultFlag = delete(userCountrys[i]);
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
    public boolean exist(UserCountry userCountry) {
        return userCountryDao.exist(userCountry);
    }
}
