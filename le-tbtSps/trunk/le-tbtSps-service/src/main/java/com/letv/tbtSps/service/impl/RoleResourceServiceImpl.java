package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.manager.RoleResourceManager;
import com.letv.tbtSps.service.RoleResourceService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RoleResourceService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RoleResourceServiceImpl implements RoleResourceService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RoleResourceServiceImpl.class);

    @Autowired
    private RoleResourceManager roleResourceManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.batchInsert")
    public boolean insert(List<RoleResource> roleResourceList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(roleResourceList)) {
                resultFlag = roleResourceManager.insert(roleResourceList);
            } else {
                LOG.warn("RoleResourceServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.insert")
    public boolean insert(RoleResource roleResource) {
        boolean resultFlag = false;
        try {
            if (null != roleResource) {
                if (roleResourceManager.exist(roleResource)) {
                    throw new ExistedException();
                }
                resultFlag = roleResourceManager.insert(roleResource);
            } else {
                LOG.warn("RoleResourceServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RoleResourceServiceImpl#insert failed, roleResource has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.update")
    public boolean update(RoleResource roleResource) {
        boolean resultFlag = false;
        try {
            if (null != roleResource && null != roleResource.getId()) {
                resultFlag = roleResourceManager.update(roleResource);
            } else {
                LOG.warn("RoleResourceServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.queryRoleResourceList")
    public List<RoleResource> queryRoleResourceList(RoleResourceQuery queryBean) {
        List<RoleResource> roleResourceList = null;
        try {
            roleResourceList = roleResourceManager.queryRoleResourceList(queryBean);
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#queryRoleResourceList has error.", e);
        }
        return roleResourceList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.queryRoleResourceListWithPage")
    public List<RoleResource> queryRoleResourceListWithPage(RoleResourceQuery queryBean, PageUtil pageUtil) {
        List<RoleResource> roleResourceList = null;
        try {
            roleResourceList = roleResourceManager.queryRoleResourceListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#queryRoleResourceListWithPage has error.", e);
        }
        return roleResourceList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.delete")
    public boolean delete(RoleResource roleResource) {
        boolean resultFlag = false;
        try {
            if (null != roleResource && null != roleResource.getId()) {
                resultFlag = roleResourceManager.delete(roleResource);
            } else {
                LOG.warn("RoleResourceServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.batchDelete")
    public boolean delete(RoleResource[] roleResources) {
        boolean resultFlag = false;
        try {
            if (null != roleResources && roleResources.length > 0) {
                resultFlag = roleResourceManager.delete(roleResources);
            } else {
                LOG.warn("RoleResourceServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.getRoleResourceById")
    public RoleResource getRoleResourceById(Long id) {
        RoleResource roleResource = null;
        try {
            if (null != id) {
                roleResource = roleResourceManager.getRoleResourceById(id);
            } else {
                LOG.warn("RoleResourceServiceImpl#getRoleResourceById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#getRoleResourceById has error.", e);
        }
        return roleResource;
    }
}

