package com.letv.tbtSps.manager.impl;

import com.letv.common.manager.BaseManager;
import com.letv.tbtSps.dao.ResourceDao;
import com.letv.tbtSps.dao.RoleResourceDao;
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.manager.RoleResourceManager;
import com.letv.wmscommon.dto.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * RoleResourceManager接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Component
public class RoleResourceManagerImpl extends BaseManager implements RoleResourceManager {
	
    @Autowired
    private RoleResourceDao roleResourceDao;

    @Autowired
    private ResourceDao resourceDao ;

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

    /**
     * 根据用编码获取用户拥有的资源
     * @param queryBean
     * @return
     */
    @Override
    public List<RoleResource> queryRoleResourceListByUserCode(RoleResourceQuery queryBean){
        return roleResourceDao.queryRoleResourceListByUserCode(queryBean);
    }

    /**
     * 修改角色拥有的资源
     * @param roleResource
     * @return
     */
    @Override
    public boolean updateRoleResource(RoleResource roleResource) {
        /**
         * 1、根据角色编码， 删除角色拥有的所有的资源
         * 2、根据插入pc角色和编码的对应关系
         * 3、插入rf角色和编码的对应关系
         */
        RoleResource roleResource_d = new RoleResource () ;
        roleResource_d.setUpdateUser(roleResource.getUpdateUser());
        roleResource_d.setRoleCode(roleResource.getRoleCode());
        boolean d_ret = roleResourceDao.deleteByRoleCode(roleResource_d);
        String[] pcResourceCode = roleResource.getPcResourceCode().split(",");
        String[] rfResourceCode = roleResource.getRfResourceCode().split(",");
        List<String> list_resourceCode = new ArrayList<String>();
        for(String resourceCode : pcResourceCode){
            list_resourceCode.add(resourceCode);
        }
        for(String resourceCode : rfResourceCode){
            list_resourceCode.add(resourceCode);
        }
        ResourceQuery resourceQuery = new ResourceQuery();
        resourceQuery.setList_resourceCode(list_resourceCode);
        List<Resource> list_resource = resourceDao.queryResourceListByCodes(resourceQuery);
        for(Resource resource : list_resource){
            RoleResource roleResource_add = new RoleResource () ;
            roleResource_add.setRoleCode(roleResource.getRoleCode());
            roleResource_add.setResourceCode(resource.getResourceCode());
            roleResource_add.setUpdateUser(roleResource.getCreateUser());
            roleResource_add.setCreateUser(roleResource.getCreateUser());
            boolean in_ret = roleResourceDao.insert(roleResource_add);
            if(!in_ret){
                throw new RuntimeException("插入角色资源异常");
            }
        }
        return true;
    }
}
