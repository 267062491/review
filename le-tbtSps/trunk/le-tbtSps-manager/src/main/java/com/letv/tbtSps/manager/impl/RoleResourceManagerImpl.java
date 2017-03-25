package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.dao.RoleResourceDao;
import com.letv.tbtSps.manager.RoleResourceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RoleResourceManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RoleResourceManagerImpl extends BaseManager implements RoleResourceManager {
	
    @Autowired
    private RoleResourceDao roleResourceDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RoleResource> roleResourceList) {
        boolean resultFlag = false;
        if (null != roleResourceList && roleResourceList.size() > 0) {
            for (RoleResource roleResource : roleResourceList) {
                resultFlag = roleResourceDao.insert(roleResource);
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
    public boolean insert(RoleResource roleResource) {
        return roleResourceDao.insert(roleResource);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RoleResource roleResource) {
        return roleResourceDao.update(roleResource);
    }

    /**
     * {@inheritDoc}
     */
    public List<RoleResource> queryRoleResourceList(RoleResourceQuery queryBean) {
        return roleResourceDao.queryRoleResourceList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RoleResource> queryRoleResourceListWithPage(RoleResourceQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RoleResourceQuery();
        }

        // 查询总数
        int totalItem = queryRoleResourceCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return roleResourceDao.queryRoleResourceListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRoleResourceCount(RoleResourceQuery queryBean) {
        return roleResourceDao.queryRoleResourceCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RoleResource roleResource) {
        return roleResourceDao.delete(roleResource);
    }

    /**
     * {@inheritDoc}
     */
    public RoleResource getRoleResourceById(Long id) {
        return roleResourceDao.getRoleResourceById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RoleResource[] roleResources) {
        boolean resultFlag = false;
        if (null != roleResources && roleResources.length > 0) {
            for (int i = 0; i < roleResources.length; i++) {
                resultFlag = delete(roleResources[i]);
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
    public boolean exist(RoleResource roleResource) {
        return roleResourceDao.exist(roleResource);
    }
}
