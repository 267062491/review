package com.letv.tbtSps.manager.impl;

import com.letv.common.manager.BaseManager;
import com.letv.tbtSps.dao.RoleDao;
import com.letv.tbtSps.dao.RoleResourceDao;
import com.letv.tbtSps.dao.UserRoleDao;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.manager.RoleManager;
import com.letv.tbtSps.manager.UserRoleManager;
import com.letv.wmscommon.dto.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RoleManager接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Component
public class RoleManagerImpl extends BaseManager implements RoleManager {
	
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UserRoleManager userRoleManager;
    @Autowired
    private RoleResourceDao roleResourceDao;

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
            queryBean.setStartIndex(pageUtil.getStart());
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
        roleDao.deleteByCode(role);
        RoleResource roleResource = new RoleResource ();
        roleResource.setRoleCode(role.getRoleCode());
        roleResource.setUpdateUser(role.getUpdateUser());
        roleResourceDao.deleteByRoleCode(roleResource) ;
        return true ;
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


    /**
     * 根据用户编码查询用户拥有的角色
     * @param userRoleQuery
     * @return
     */
    public List<Role> queryRoleListByUserCode(UserRoleQuery userRoleQuery){
        return roleDao.queryRoleListByUserCode(userRoleQuery);
    }

    /**
     * 根据用编码， 查询用户可选择的角色
     * @param userRoleQuery
     * @return
     */
    public List<Role> queryChooseRoleListByUserCode(UserRoleQuery userRoleQuery){
        return roleDao.queryChooseRoleListByUserCode(userRoleQuery);
    }

    /**
     * 按照角色角色编码集合查询角色信息
     * @param roleQuery
     * @return
     */
    @Override
    public List<Role> queryRoleListByUserCodes(RoleQuery roleQuery) {
        return roleDao.queryRoleListByUserCodes(roleQuery);
    }

    /**
     * 根据角色编码查询角色信息
     * @param list
     * @return
     */
    @Override
    public List<Role> queryRoleListByRoleCodes(List<String> list){
        return roleDao.queryRoleListByRoleCodes(list);
    }

    /**
     * 启用、禁用角色
     * @param role
     * @return
     */
    @Override
    public boolean enableOrDisable(Role role) {
        return roleDao.enableOrDisable(role);
    }
}
