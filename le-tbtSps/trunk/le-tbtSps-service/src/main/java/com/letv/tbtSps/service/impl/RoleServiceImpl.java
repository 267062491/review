package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.manager.RoleManager;
import com.letv.tbtSps.service.RoleService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RoleService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RoleServiceImpl implements RoleService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RoleServiceImpl.class);

    @Autowired
    private RoleManager roleManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.batchInsert")
    public boolean insert(List<Role> roleList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(roleList)) {
                resultFlag = roleManager.insert(roleList);
            } else {
                LOG.warn("RoleServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.insert")
    public boolean insert(Role role) {
        boolean resultFlag = false;
        try {
            if (null != role) {
                if (roleManager.exist(role)) {
                    throw new ExistedException();
                }
                resultFlag = roleManager.insert(role);
            } else {
                LOG.warn("RoleServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RoleServiceImpl#insert failed, role has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.update")
    public boolean update(Role role) {
        boolean resultFlag = false;
        try {
            if (null != role && null != role.getId()) {
                resultFlag = roleManager.update(role);
            } else {
                LOG.warn("RoleServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.queryRoleList")
    public List<Role> queryRoleList(RoleQuery queryBean) {
        List<Role> roleList = null;
        try {
            roleList = roleManager.queryRoleList(queryBean);
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#queryRoleList has error.", e);
        }
        return roleList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.queryRoleListWithPage")
    public List<Role> queryRoleListWithPage(RoleQuery queryBean, PageUtil pageUtil) {
        List<Role> roleList = null;
        try {
            roleList = roleManager.queryRoleListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#queryRoleListWithPage has error.", e);
        }
        return roleList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.delete")
    public boolean delete(Role role) {
        boolean resultFlag = false;
        try {
            if (null != role && null != role.getId()) {
                resultFlag = roleManager.delete(role);
            } else {
                LOG.warn("RoleServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.batchDelete")
    public boolean delete(Role[] roles) {
        boolean resultFlag = false;
        try {
            if (null != roles && roles.length > 0) {
                resultFlag = roleManager.delete(roles);
            } else {
                LOG.warn("RoleServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.getRoleById")
    public Role getRoleById(Long id) {
        Role role = null;
        try {
            if (null != id) {
                role = roleManager.getRoleById(id);
            } else {
                LOG.warn("RoleServiceImpl#getRoleById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#getRoleById has error.", e);
        }
        return role;
    }
}

