package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.manager.UserManager;
import com.letv.tbtSps.service.UserService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * UserService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class UserServiceImpl implements UserService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserManager userManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.batchInsert")
    public boolean insert(List<User> userList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(userList)) {
                resultFlag = userManager.insert(userList);
            } else {
                LOG.warn("UserServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.insert")
    public boolean insert(User user) {
        boolean resultFlag = false;
        try {
            if (null != user) {
                if (userManager.exist(user)) {
                    throw new ExistedException();
                }
                resultFlag = userManager.insert(user);
            } else {
                LOG.warn("UserServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("UserServiceImpl#insert failed, user has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("UserServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.update")
    public boolean update(User user) {
        boolean resultFlag = false;
        try {
            if (null != user && null != user.getId()) {
                resultFlag = userManager.update(user);
            } else {
                LOG.warn("UserServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.queryUserList")
    public List<User> queryUserList(UserQuery queryBean) {
        List<User> userList = null;
        try {
            userList = userManager.queryUserList(queryBean);
        } catch (Exception e) {
            LOG.error("UserServiceImpl#queryUserList has error.", e);
        }
        return userList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.queryUserListWithPage")
    public List<User> queryUserListWithPage(UserQuery queryBean, PageUtil pageUtil) {
        List<User> userList = null;
        try {
            userList = userManager.queryUserListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("UserServiceImpl#queryUserListWithPage has error.", e);
        }
        return userList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.delete")
    public boolean delete(User user) {
        boolean resultFlag = false;
        try {
            if (null != user && null != user.getId()) {
                resultFlag = userManager.delete(user);
            } else {
                LOG.warn("UserServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.batchDelete")
    public boolean delete(User[] users) {
        boolean resultFlag = false;
        try {
            if (null != users && users.length > 0) {
                resultFlag = userManager.delete(users);
            } else {
                LOG.warn("UserServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.getUserById")
    public User getUserById(Long id) {
        User user = null;
        try {
            if (null != id) {
                user = userManager.getUserById(id);
            } else {
                LOG.warn("UserServiceImpl#getUserById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserServiceImpl#getUserById has error.", e);
        }
        return user;
    }
}

