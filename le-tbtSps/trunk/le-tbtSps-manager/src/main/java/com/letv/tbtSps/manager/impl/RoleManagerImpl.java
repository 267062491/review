package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.dao.RoleDao;
import com.letv.tbtSps.manager.RoleManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RoleManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RoleManagerImpl extends BaseManager implements RoleManager {
	
    @Autowired
    private RoleDao roleDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<Role> roleList) {
        boolean resultFlag = false;
        if (null != roleList && roleList.size() > 0) {
            for (Role role : roleList) {
                resultFlag = roleDao.insert(role);
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
    public boolean insert(Role role) {
        return roleDao.insert(role);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final Role role) {
        return roleDao.update(role);
    }

    /**
     * {@inheritDoc}
     */
    public List<Role> queryRoleList(RoleQuery queryBean) {
        return roleDao.queryRoleList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Role> queryRoleListWithPage(RoleQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RoleQuery();
        }

        // 查询总数
        int totalItem = queryRoleCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return roleDao.queryRoleListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRoleCount(RoleQuery queryBean) {
        return roleDao.queryRoleCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Role role) {
        return roleDao.delete(role);
    }

    /**
     * {@inheritDoc}
     */
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final Role[] roles) {
        boolean resultFlag = false;
        if (null != roles && roles.length > 0) {
            for (int i = 0; i < roles.length; i++) {
                resultFlag = delete(roles[i]);
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
    public boolean exist(Role role) {
        return roleDao.exist(role);
    }
}
