package com.letv.tbtSps.manager.impl;

import com.letv.common.manager.BaseManager;
import com.letv.tbtSps.dao.ResourceDao;
import com.letv.tbtSps.dao.RoleResourceDao;
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.manager.ResourceManager;
import com.letv.wmscommon.dto.PageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ResourceManager接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Component
public class ResourceManagerImpl extends BaseManager implements ResourceManager {
	
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleResourceDao roleResourceDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<Resource> resourceList) {
        boolean resultFlag = false;
        if (null != resourceList && resourceList.size() > 0) {
            for (Resource resource : resourceList) {
                resultFlag = resourceDao.insert(resource);
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
    public boolean insert(Resource resource) {
        String parentCode = resource.getParentCode();
        /**
         * 页面传过来的是父节点的level,+1才是添加的节点的level
         */
        resource.setLevel(resource.getLevel().intValue()+1);
        ResourceQuery resourceQuery = new ResourceQuery () ;
        resourceQuery.setParentCode(parentCode);
        List<Resource> list_resource = resourceDao.queryResourceListByParentCode(resourceQuery);
        if(CollectionUtils.isNotEmpty(list_resource)){
            String resource_code = list_resource.get(list_resource.size()-1).getResourceCode();
            int temp = Integer.parseInt(resource_code.replaceFirst(parentCode+"_",""))+1;
            resource.setResourceCode(parentCode+"_"+temp);
        }else{
            resource.setResourceCode(parentCode+"_1");
        }

        return resourceDao.insert(resource);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final Resource resource) {
        return resourceDao.update(resource);
    }

    /**
     * {@inheritDoc}
     */
    public List<Resource> queryResourceList(ResourceQuery queryBean) {
        return resourceDao.queryResourceList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Resource> queryResourceListWithPage(ResourceQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new ResourceQuery();
        }

        // 查询总数
        int totalItem = queryResourceCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return resourceDao.queryResourceListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryResourceCount(ResourceQuery queryBean) {
        return resourceDao.queryResourceCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Resource resource) {
        return resourceDao.delete(resource);
    }

    /**
     * {@inheritDoc}
     */
    public Resource getResourceById(Long id) {
        return resourceDao.getResourceById(id);
    }
    public Resource getResourceByCode(String resourceCode) {
        return resourceDao.getResourceByCode(resourceCode);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final Resource[] resources) {
        boolean resultFlag = false;
        if (null != resources && resources.length > 0) {
            for (int i = 0; i < resources.length; i++) {
                resultFlag = delete(resources[i]);
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
    public boolean exist(Resource resource) {
        return resourceDao.exist(resource);
    }

    /**
     * 根据资源编码集合查询资源
     * @param queryBean
     * @return
     */
    @Override
    public List<Resource> queryResourceListByCodes(ResourceQuery queryBean){
        return resourceDao.queryResourceListByCodes(queryBean) ;
    }

    /**
     * 查询某个节点以及节点的子节点
     * @param queryBean
     * @return
     */
    public List<Resource> queryResourceListByLikeCode(ResourceQuery queryBean){
        return resourceDao.queryResourceListByLikeCode(queryBean) ;
    }

    /**
     * 删除资源节点及其下所有子节点
     * @param resource
     * @return
     */
    @Override
    public boolean deleteTreeNode(Resource resource){
        return resourceDao.deleteTreeNode(resource);
    }

    /**
     * 插入资源以及资源对应的角色 , 这里没有做资源添加防重，没必要，不常操作的东西
     * @param resource
     * @return
     */
    @Override
    public boolean insertResourceAndBelongRole(Resource resource) {
        // 插入资源
        boolean resource_insert = this.insert(resource);
        /**
         * 按照规则解析资源编码， 如果然后查询用户是否拥有解析后的资源权限
         * 如果没有，则增加，有则不增加
         */
        List<String> list_resourceCode = new ArrayList<String>();
        String[] resource_codes = resource.getResourceCode().split("_");
        String code = "" ;
        for(int i = 0 ; i<resource_codes.length ; i++){
            if(i==0){
                code =  resource_codes[i] ;
            }else{
                code = code + "_" + resource_codes[i] ;
            }
            list_resourceCode.add(code);
        }
        RoleResourceQuery roleResourceQuery = new RoleResourceQuery() ;
        roleResourceQuery.setRoleCode(resource.getRoleCode());
        roleResourceQuery.setCodes(list_resourceCode);
        List<RoleResource> list_resource = roleResourceDao.queryRoleResourceByRoleAndResourceCode(roleResourceQuery);
        List<RoleResource> list_roleResource = new ArrayList<RoleResource> () ;
        if(CollectionUtils.isEmpty(list_resource) ){ // 角色对应的资源都不存在，则全部插入
            for(String rCode : list_resourceCode){
                RoleResource roleResource = new RoleResource() ;
                roleResource.setResourceCode(rCode);
                roleResource.setRoleCode(resource.getRoleCode());
                roleResource.setCreateUser(resource.getCreateUser());
                roleResource.setUpdateUser(resource.getCreateUser());
                list_roleResource.add(roleResource);
            }

        }else if(list_resource.size()!=list_resourceCode.size()){ // 查询和返回的数量不一致，说明有部分没有，则循环比较，看哪个没有插入哪个
            for(RoleResource roleResource : list_resource){
                for(String rcode : list_resourceCode){
                    if(roleResource.getResourceCode().equals(rcode)){
                        list_resourceCode.remove(rcode);
                        break ;
                    }
                }
            }
            for(String rCode : list_resourceCode){
                RoleResource roleResource = new RoleResource() ;
                roleResource.setResourceCode(rCode);
                roleResource.setRoleCode(resource.getRoleCode());
                roleResource.setCreateUser(resource.getCreateUser());
                roleResource.setUpdateUser(resource.getCreateUser());
                list_roleResource.add(roleResource);
            }
        }
        boolean ret = true ;
        if(CollectionUtils.isNotEmpty(list_roleResource)){
            ret = roleResourceDao.insertBatch(list_roleResource);
        }
        if(!resource_insert || !ret){
            throw new RuntimeException("插入失败") ;
        }
        return true;
    }



    public boolean insertRequestURL(Resource resource) {
        ResourceQuery queryBean = new ResourceQuery () ;
        queryBean.setUrl(resource.getUrl());
        List<Resource> list = resourceDao.queryResourceList(queryBean) ;
        if(CollectionUtils.isEmpty(list)){
            resource.setLevel(0);
            resource.setNote("需要修改");
            resource.setOpenFlag(1);
            resource.setParentFlag(0);
            resource.setResourceName("需要修改");
            resource.setOrders(10);
            resource.setUpdateUser("init");
            resource.setParentCode("0_13");
            resource.setButtonflag(0);
            resource.setCreateUser("init");
            return this.insert(resource);
        }else{
            return Boolean.TRUE ;
        }
    }
}
