package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.dao.UserDao;
import com.letv.tbtSps.manager.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UserManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class UserManagerImpl extends BaseManager implements UserManager {
	
    @Autowired
    private UserDao userDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<User> userList) {
        boolean resultFlag = false;
        if (null != userList && userList.size() > 0) {
            for (User user : userList) {
                resultFlag = userDao.insert(user);
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
    public boolean insert(User user) {
        return userDao.insert(user);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final User user) {
        return userDao.update(user);
    }

    /**
     * {@inheritDoc}
     */
    public List<User> queryUserList(UserQuery queryBean) {
        return userDao.queryUserList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<User> queryUserListWithPage(UserQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new UserQuery();
        }

        // 查询总数
        int totalItem = queryUserCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return userDao.queryUserListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryUserCount(UserQuery queryBean) {
        return userDao.queryUserCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    /**
     * {@inheritDoc}
     */
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final User[] users) {
        boolean resultFlag = false;
        if (null != users && users.length > 0) {
            for (int i = 0; i < users.length; i++) {
                resultFlag = delete(users[i]);
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
    public boolean exist(User user) {
        return userDao.exist(user);
    }
}
