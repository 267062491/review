package com.letv.tbtSps.manager.impl;

import com.letv.common.manager.BaseManager;
import com.letv.tbtSps.dao.UserDao;
import com.letv.tbtSps.dao.UserRoleDao;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.manager.UserManager;
import com.letv.wmscommon.dto.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * UserManager接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Component
public class UserManagerImpl extends BaseManager implements UserManager {
	
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao ;

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

    /**
     * 根据用户登录名或者中文名称查询用户信息
     * @param queryBean
     * @return
     */
    @Override
    public List<User> searchUserByCodeOrName(UserQuery queryBean){
        return userDao.searchUserByCodeOrName(queryBean);
    }

    /**
     * 根据角色编码联合查询用户，翻页
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<User> queryUserListByRoleCodeWithPage(UserQuery queryBean,PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new UserQuery();
        }

        // 查询总数
        int totalItem = userDao.queryUserCountByRoleCode(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();

        if (totalItem > 0) {
            queryBean.setStartIndex(pageUtil.getStart());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return userDao.queryUserByRoleCode(queryBean);
        }
        return new ArrayList<User>();
    }

    public List<User> queryUserByRoleCodeNoPage(UserQuery queryBean) {
        return userDao.queryUserByRoleCodeNoPage(queryBean);  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 启用、禁用用户
     * @param user
     * @return
     */
    @Override
    public boolean enableOrDisable(User user) {
        return userDao.enableOrDisable(user);
    }
    /**
     * 根据用户编码删除用户
     * @param user
     * @return
     */
    @Override
    public boolean deleteByUserCode(User user) {
        userDao.deleteByUserCode(user);
        UserRoleQuery userRole = new UserRoleQuery () ;
        userRole.setUserCode(user.getUserCode());
        userRole.setUpdateUser(user.getUpdateUser());
        userRoleDao.deleteUserRoleByUserCode(userRole);
        return true ;
    }

    /**
     * 根据用户编码修改用户
     * @param user
     * @return
     */
    public boolean updateByUserCode(final User user) {
        return userDao.updateByUserCode(user);
    }

    /**
     * 根据用户名集合查询用户信息
     * @param list
     * @return
     */
    @Override
    public List<User> searchUserByUserNames(List<String> list){
        return userDao.searchUserByUserNames(list);
    }

    /**
     * 批量保存用户信息
     * @param list_user
     * @return
     */
    @Override
    public boolean saveUsers(List<User> list_user) {
        for(User user : list_user){
            userDao.insert(user);
            if(StringUtils.isNotEmpty(user.getRoleName())){
                String[] roleCodes = user.getRoleName().split(",");
                for(String roleCode : roleCodes){
                    UserRole userRole = new UserRole () ;
                    userRole.setUserCode(user.getUserCode());
                    userRole.setRoleCode(roleCode);
                    userRole.setUpdateUser(user.getUpdateUser());
                    userRole.setCreateUser(user.getCreateUser());
                    userRoleDao.insert(userRole);
                }
            }
        }
        return true;
    }
    /**
     * 根据用户编码查询用户信息 ， 批量接口
     * @param queryBean
     * @return
     */
    @Override
    public List<User> searchUserByCodes(UserQuery queryBean) {
       return userDao.searchUserByCodes(queryBean) ;
    }
}
