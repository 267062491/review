package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.manager.UserRoleManager;
import com.letv.tbtSps.service.UserRoleService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * UserRoleService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleManager userRoleManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.batchInsert")
    public boolean insert(List<UserRole> userRoleList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                resultFlag = userRoleManager.insert(userRoleList);
            } else {
                LOG.warn("UserRoleServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.insert")
    public boolean insert(UserRole userRole) {
        boolean resultFlag = false;
        try {
            if (null != userRole) {
                if (userRoleManager.exist(userRole)) {
                    throw new ExistedException();
                }
                resultFlag = userRoleManager.insert(userRole);
            } else {
                LOG.warn("UserRoleServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("UserRoleServiceImpl#insert failed, userRole has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.update")
    public boolean update(UserRole userRole) {
        boolean resultFlag = false;
        try {
            if (null != userRole && null != userRole.getId()) {
                resultFlag = userRoleManager.update(userRole);
            } else {
                LOG.warn("UserRoleServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.queryUserRoleList")
    public List<UserRole> queryUserRoleList(UserRoleQuery queryBean) {
        List<UserRole> userRoleList = null;
        try {
            userRoleList = userRoleManager.queryUserRoleList(queryBean);
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#queryUserRoleList has error.", e);
        }
        return userRoleList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.queryUserRoleListWithPage")
    public List<UserRole> queryUserRoleListWithPage(UserRoleQuery queryBean, PageUtil pageUtil) {
        List<UserRole> userRoleList = null;
        try {
            userRoleList = userRoleManager.queryUserRoleListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#queryUserRoleListWithPage has error.", e);
        }
        return userRoleList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.delete")
    public boolean delete(UserRole userRole) {
        boolean resultFlag = false;
        try {
            if (null != userRole && null != userRole.getId()) {
                resultFlag = userRoleManager.delete(userRole);
            } else {
                LOG.warn("UserRoleServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.batchDelete")
    public boolean delete(UserRole[] userRoles) {
        boolean resultFlag = false;
        try {
            if (null != userRoles && userRoles.length > 0) {
                resultFlag = userRoleManager.delete(userRoles);
            } else {
                LOG.warn("UserRoleServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.getUserRoleById")
    public UserRole getUserRoleById(Long id) {
        UserRole userRole = null;
        try {
            if (null != id) {
                userRole = userRoleManager.getUserRoleById(id);
            } else {
                LOG.warn("UserRoleServiceImpl#getUserRoleById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#getUserRoleById has error.", e);
        }
        return userRole;
    }
}

