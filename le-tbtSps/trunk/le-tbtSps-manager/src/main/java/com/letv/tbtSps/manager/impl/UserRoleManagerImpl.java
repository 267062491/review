package com.letv.tbtSps.manager.impl;

import com.letv.common.manager.BaseManager;
import com.letv.tbtSps.dao.UserRoleDao;
import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.manager.UserRoleManager;
import com.letv.tbtSps.utils.constant.SystemConstant;
import com.letv.wmscommon.dto.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * UserRoleManager接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Component
public class UserRoleManagerImpl extends BaseManager implements UserRoleManager {
	
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<UserRole> userRoleList) {
        boolean resultFlag = false;
        if (null != userRoleList && userRoleList.size() > 0) {
            for (UserRole userRole : userRoleList) {
                resultFlag = userRoleDao.insert(userRole);
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
    public boolean insert(UserRole userRole) {
        return userRoleDao.insert(userRole);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final UserRole userRole) {
        return userRoleDao.update(userRole);
    }

    /**
     * {@inheritDoc}
     */
    public List<UserRole> queryUserRoleList(UserRoleQuery queryBean) {
        return userRoleDao.queryUserRoleList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<UserRole> queryUserRoleListWithPage(UserRoleQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new UserRoleQuery();
        }

        // 查询总数
        int totalItem = queryUserRoleCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return userRoleDao.queryUserRoleListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryUserRoleCount(UserRoleQuery queryBean) {
        return userRoleDao.queryUserRoleCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(UserRole userRole) {
        return userRoleDao.delete(userRole);
    }

    /**
     * {@inheritDoc}
     */
    public UserRole getUserRoleById(Long id) {
        return userRoleDao.getUserRoleById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final UserRole[] userRoles) {
        boolean resultFlag = false;
        if (null != userRoles && userRoles.length > 0) {
            for (int i = 0; i < userRoles.length; i++) {
                resultFlag = delete(userRoles[i]);
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
    public boolean exist(UserRole userRole) {
        return userRoleDao.exist(userRole);
    }


    public boolean batchUpdate(String userCode, String[] roleCodes, String createUser) {
        UserRoleQuery userRoleQuery = new UserRoleQuery();
        userRoleQuery.setUserCode(userCode);
        String[] userIds = new String[]{userCode};
        UserRoleQuery uquery = new UserRoleQuery () ;
        uquery.setUserCode(userCode);
        this.userRoleDao.deleteUserRoleByUserCode(uquery);
        List<UserRole> beanList = new ArrayList<UserRole>(roleCodes.length);
        for (String roleCode : roleCodes) {
            UserRole userRole = new UserRole();
            userRole.setUserCode(userCode);
            userRole.setRoleCode(roleCode);
            userRole.setCreateUser(createUser);
            userRole.setUpdateUser(createUser);
            userRole.setNote("");
            userRole.setYn(SystemConstant.YES);
            beanList.add(userRole);
        }
        return this.insert(beanList);
    }
}
