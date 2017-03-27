package com.letv.tbtSps.service.impl;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.domain.query.TreeDomain;
import com.letv.tbtSps.manager.ResourceManager;
import com.letv.tbtSps.manager.RoleResourceManager;
import com.letv.tbtSps.service.ResourceService;
import com.letv.tbtSps.service.RoleResourceService;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
import com.letv.wmscommon.dto.PageUtil;
import com.letv.wmscommon.dto.PagedQueryDto;
import com.letv.wmscommon.dto.PagedResultDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleResourceService接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Service
public class RoleResourceServiceImpl implements RoleResourceService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RoleResourceServiceImpl.class);

    @Autowired
    private RoleResourceManager roleResourceManager;
    @Autowired
    private ResourceManager resourceManager;
    @Autowired
    private ResourceService resourceService;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleResourceService.batchInsert")
    public boolean batchInsert(List<RoleResource> roleResourceList) {
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
    public PagedResultDto<RoleResource> queryRoleResourceListWithPage(PagedQueryDto<RoleResourceQuery> pagedQuery) {
        RoleResourceQuery queryBean = pagedQuery.getQueryDto();
        PageUtil pageUtil = pagedQuery.getPageUtil();
        List<RoleResource> roleResourceList = null;
        try {
            roleResourceList = roleResourceManager.queryRoleResourceListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RoleResourceServiceImpl#queryRoleResourceListWithPage has error.", e);
        }
        return new PagedResultDto(roleResourceList,pageUtil);
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
    public boolean batchDelete(RoleResource[] roleResources) {
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

    /**
     * 根据用编码获取用户拥有的资源
     * @param queryBean
     * @return
     */
    @Override
    public LetvResponse<List<RoleResource>> queryRoleResourceListByUserCode(RoleResourceQuery queryBean){
        LOG.info("inputPar:RoleResourceServiceImpl#queryRoleResourceListByUserCode.queryBean"+ JsonHelper.toJson(queryBean));
        LetvResponse<List<RoleResource>> letvResponse = new LetvResponse<List<RoleResource>>() ;
        if(null==queryBean || StringUtils.isEmpty(queryBean.getUserCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleResourceServiceImpl#queryRoleResourceListByUserCode.queryBean"+ JsonHelper.toJson(queryBean));
            return  letvResponse ;
        }
        try{
            List<RoleResource> list_roleResource = roleResourceManager.queryRoleResourceListByUserCode(queryBean);
            if(CollectionUtils.isEmpty(list_roleResource)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_roleResource);
            }

        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleResourceServiceImpl#queryRoleResourceListByUserCode.e"+e);
        }
        LOG.info("outputPar:RoleResourceServiceImpl#queryRoleResourceListByUserCode.letvResponse"+ JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }

    /**
     * 根据角色编码， 获取角色拥有的资源
     * @param queryBean
     * @return
     */
    @Override
    public LetvResponse<List<TreeDomain>>  queryRoleResourceByRoleCode(RoleResourceQuery queryBean) {
        LOG.info("inputPar:RoleResourceServiceImpl#queryRoleResourceByRoleCode.queryBean"+ JsonHelper.toJson(queryBean));
        LetvResponse<List<TreeDomain>> letvResponse = new LetvResponse<List<TreeDomain>>() ;
       if(null == queryBean || StringUtils.isEmpty(queryBean.getRoleCode())
               || StringUtils.isEmpty(queryBean.getPlatForm())){
           letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
           letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
           LOG.error("error:RoleResourceServiceImpl#queryRoleResourceByRoleCode.queryBean"+ JsonHelper.toJson(queryBean));
           return letvResponse ;
       }
       try{
           /**
            * 1、查询数据库中的全部资源
            */
           ResourceQuery resourceQuery = new ResourceQuery () ;
           resourceQuery.setPlatfrom(queryBean.getPlatForm());
           if(null!=queryBean.getResourceType()){
               resourceQuery.setResourceType(queryBean.getResourceType());
           }
           LetvResponse<List<TreeDomain>> list_resource_response = resourceService.queryResourceListByCodes(resourceQuery);
           if(list_resource_response.getCode()==PortalSystemTipCodeEnum.SCUESS.getValue()){
               List<TreeDomain> list_resource = list_resource_response.getResult();
               /**
                * 2、查询角色拥有的资源
                */
               RoleResourceQuery roleResourceQuery = new RoleResourceQuery() ;
               roleResourceQuery.setRoleCode(queryBean.getRoleCode());
               List<RoleResource> list_roleResource = roleResourceManager.queryRoleResourceList(roleResourceQuery);
               for(TreeDomain treeDomain : list_resource){
                   for(RoleResource roleResource : list_roleResource){
                       if(treeDomain.getId().equals(roleResource.getResourceCode())){
                           treeDomain.setChecked(true);
                           break ;
                       }
                   }
               }
               letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
               letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
               letvResponse.setResult(list_resource);
           }else{
               letvResponse.setCode(list_resource_response.getCode());
               letvResponse.setMessage(list_resource_response.getMessage());
           }
       }catch (Exception e){
           letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
           letvResponse.setMessage(e.getMessage());
           LOG.error("error:RoleResourceServiceImpl#queryRoleResourceByRoleCode.e"+ e);
       }
        LOG.info("outputPar:RoleResourceServiceImpl#queryRoleResourceByRoleCode.letvResponse"+ JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 修改某个角色拥有的所有资源
     * @param roleResource
     * @return
     */
    @Override
    public LetvResponse<Boolean> updateRoleResource(RoleResource roleResource) {
        LOG.info("inputPar:RoleResourceServiceImpl#updateRoleResource.roleResource"+ JsonHelper.toJson(roleResource));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>() ;
        if(null==roleResource || StringUtils.isEmpty(roleResource.getRoleCode())
                || (StringUtils.isEmpty(roleResource.getPcResourceCode())
                && StringUtils.isEmpty(roleResource.getRfResourceCode()))){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleResourceServiceImpl#updateRoleResource.roleResource"+ JsonHelper.toJson(roleResource));
            return letvResponse ;
        }
        try{
            boolean ret = roleResourceManager.updateRoleResource(roleResource) ;
            if(ret){
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(ret);
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
                letvResponse.setResult(ret);
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleResourceServiceImpl#updateRoleResource.e"+ e);
        }
        LOG.info("outputPar:RoleResourceServiceImpl#updateRoleResource.letvResponse"+ JsonHelper.toJson(letvResponse));
        return letvResponse;
    }
}

