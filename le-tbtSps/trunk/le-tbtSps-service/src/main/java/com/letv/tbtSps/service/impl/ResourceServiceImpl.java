package com.letv.tbtSps.service.impl;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.config.PropertiesHelper;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.domain.query.TreeDomain;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.manager.ResourceManager;
import com.letv.tbtSps.manager.RoleResourceManager;
import com.letv.tbtSps.service.ResourceService;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
import com.letv.tbtSps.utils.constant.ResourcePlant;
import com.letv.tbtSps.utils.constant.SystemConstant;
import com.letv.tbtSps.utils.enums.UserTypeEnum;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ResourceService接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(ResourceServiceImpl.class);

    @Autowired
    private ResourceManager resourceManager;
    @Autowired
    private RoleResourceManager roleResourceManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "ResourceService.batchInsert")
    public boolean batchInsert(List<Resource> resourceList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(resourceList)) {
                resultFlag = resourceManager.insert(resourceList);
            } else {
                LOG.warn("ResourceServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("ResourceServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "ResourceService.insert")
    public LetvResponse<Resource> insert(Resource resource) {
        LetvResponse<Resource> letvResponse = new LetvResponse<Resource>() ;
        if(null==resource || StringUtils.isEmpty(resource.getResourceName())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            return letvResponse ;
        }
        try {
//            if (!resourceManager.exist(resource)) {
                boolean resultFlag = resourceManager.insert(resource);
                if(resultFlag){
                    letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                    letvResponse.setResult(resource);
                } else {
                    letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
                }
//            }else{
//                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
//                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
//            }
        } catch (Exception e) {
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
        }
        return letvResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "ResourceService.update")
    public boolean update(Resource resource) {
        boolean resultFlag = false;
        try {
            if (null != resource && null != resource.getResourceCode()) {
                resultFlag = resourceManager.update(resource);
            } else {
                LOG.warn("ResourceServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("ResourceServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "ResourceService.queryResourceList")
    public List<Resource> queryResourceList(ResourceQuery queryBean) {
        List<Resource> resourceList = null;
        try {
            resourceList = resourceManager.queryResourceList(queryBean);
        } catch (Exception e) {
            LOG.error("ResourceServiceImpl#queryResourceList has error.", e);
        }
        return resourceList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "ResourceService.queryResourceListWithPage")
    public PagedResultDto<Resource> queryResourceListWithPage(PagedQueryDto<ResourceQuery> pagedQuery) {
        ResourceQuery queryBean = pagedQuery.getQueryDto();
        PageUtil pageUtil = pagedQuery.getPageUtil();
        List<Resource> resourceList = null;
        try {
            resourceList = resourceManager.queryResourceListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("ResourceServiceImpl#queryResourceListWithPage has error.", e);
        }
        return new PagedResultDto(resourceList,pageUtil);
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "ResourceService.delete")
    public boolean delete(Resource resource) {
        boolean resultFlag = false;
        try {
            if (null != resource && null != resource.getId()) {
                resultFlag = resourceManager.delete(resource);
            } else {
                LOG.warn("ResourceServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("ResourceServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "ResourceService.batchDelete")
    public boolean batchDelete(Resource[] resources) {
        boolean resultFlag = false;
        try {
            if (null != resources && resources.length > 0) {
                resultFlag = resourceManager.delete(resources);
            } else {
                LOG.warn("ResourceServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("ResourceServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }


    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "ResourceService.getResourceById")
    public Resource getResourceById(Long id) {
        Resource resource = null;
        try {
            if (null != id) {
                resource = resourceManager.getResourceById(id);
            } else {
                LOG.warn("ResourceServiceImpl#getResourceById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("ResourceServiceImpl#getResourceById has error.", e);
        }
        return resource;
    }

    public LetvResponse<Resource> getResourceByCode(String resourceCode) {
        LOG.info("inputPar:ResourceServiceImpl#getResourceByCode.resourceCode"+ JsonHelper.toJson(resourceCode));
        LetvResponse<Resource> letvResponse = new LetvResponse<Resource>() ;
        if(StringUtils.isEmpty(resourceCode)){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#getResourceByCode.letvResponse"+JsonHelper.toJson(letvResponse));
            return letvResponse ;
        }
        try{
            Resource resource = resourceManager.getResourceByCode(resourceCode);
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
            letvResponse.setResult(resource);
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#getResourceByCode.e", e);
        }
        LOG.info("outputPar:ResourceServiceImpl#getResourceByCode.letvResponse" + JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /*************************************以上是生成代码****************************************************/

    /**
     * 根据用户名称，查询用户拥有的资源列表
     * @param userQuery
     * @return
     */
    @Override
    public LetvResponse<List<Resource>> searchResourceByUserName(UserQuery userQuery) {
        LOG.info("inputPar:ResourceServiceImpl#searchResourceByUserName.userQuery=" + JsonHelper.toJson(userQuery));
        LetvResponse<List<Resource>> letvResponse = new LetvResponse<List<Resource>>() ;
        if(null == userQuery || StringUtils.isEmpty(userQuery.getUserName())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#searchResourceByUserName.userQuery=" + JsonHelper.toJson(userQuery));
            return letvResponse ;
        }
        return letvResponse;
    }

    /**
     * 根据资源编码集合查询资源
     * 1、如果不带任何条件，那么查询的是资源表中的所有的数据
     * 2、如果带list,那么查询的是某个对应资源编码的具体资源
     * @param queryBean
     * @return
     */
    @Override
    public LetvResponse<List<TreeDomain>> queryResourceListByCodes(ResourceQuery queryBean){
        LOG.info("inputPar:ResourceServiceImpl#queryResourceListByCodes.queryBean="+JsonHelper.toJson(queryBean));
        LetvResponse<List<TreeDomain>> letvResponse = new LetvResponse<List<TreeDomain>>();
        try{
            List<Resource> list_resource = resourceManager.queryResourceListByCodes(queryBean) ;
            List<TreeDomain> list_treeDomain = getTreeDomains(list_resource);
            letvResponse.setResult(list_treeDomain);
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#queryResourceListByCodes.e=" , e);
        }
        LOG.info("outputPar:ResourceServiceImpl#queryResourceListByCodes.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }

    /**
     * 查询某个节点以及节点的子节点
     * @param queryBean
     * @return
     */
    public LetvResponse<List<TreeDomain>> queryResourceListByLikeCode(ResourceQuery queryBean) {
        LOG.info("inputPar:ResourceServiceImpl#queryResourceListByLikeCode.queryBean="+JsonHelper.toJson(queryBean));
        LetvResponse<List<TreeDomain>> letvResponse = new LetvResponse<List<TreeDomain>>() ;
        if(null == queryBean || StringUtils.isEmpty(queryBean.getResourceCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#queryResourceListByLikeCode.queryBean="+JsonHelper.toJson(queryBean));
        }
        try{
            if("0".equals(queryBean.getResourceCode())){
                queryBean.setResourceCode("");
            }
            List<Resource> list_resource = resourceManager.queryResourceListByLikeCode(queryBean) ;
            List<TreeDomain> list_treeDomain = getTreeDomains(list_resource);
            letvResponse.setResult(list_treeDomain);
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#queryResourceListByLikeCode.e="+e);
        }
        LOG.info("outputPar:ResourceServiceImpl#queryResourceListByLikeCode.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }

    private List<TreeDomain> getTreeDomains(List<Resource> list_resource) {
        List<TreeDomain> list_treeDomain = new ArrayList<TreeDomain>() ;
        for(Resource resource : list_resource){
            TreeDomain treeDomain = new TreeDomain() ;
            treeDomain.setId(resource.getResourceCode());
            treeDomain.setName(resource.getResourceName());
            treeDomain.setpId(resource.getParentCode());
            if (SystemConstant.NO == resource.getParentFlag()) {
                String url = "";
                if (ResourcePlant.PC.equals(resource.getPlatfrom())) {
                    if (SystemConstant.NGINX_FLAG.equals(PropertiesHelper.newInstance().getValue("portal.web.nginx.flag"))) {
                        if (resource.getNote().startsWith("portal")) {
                            url = PropertiesHelper.newInstance().getValue("portal.web.domain") + "/" + resource.getUrl();
                        } else if (resource.getNote().startsWith("master")) {
                            url = PropertiesHelper.newInstance().getValue("master.web.domain") + "/" + resource.getUrl();
                        } else if (resource.getNote().startsWith("inbound")) {
                            url = PropertiesHelper.newInstance().getValue("inbound.web.domain") + "/" + resource.getUrl();
                        } else if (resource.getNote().startsWith("outbound")) {
                            url = PropertiesHelper.newInstance().getValue("outbound.web.domain") + "/" + resource.getUrl();
                        } else if (resource.getNote().startsWith("inside")) {
                            url = PropertiesHelper.newInstance().getValue("inside.web.domain") + "/" + resource.getUrl();
                        } else if (resource.getNote().startsWith("stock")) {
                            url = PropertiesHelper.newInstance().getValue("stock.web.domain") + "/" + resource.getUrl();
                        } else if (resource.getNote().startsWith("dtc")) {
                            url = PropertiesHelper.newInstance().getValue("dtc.web.domain") + "/" + resource.getUrl();
                        }
                    } else {
                        url = "/" + resource.getUrl();
                    }
                } else {
                    url = resource.getUrl();
                }
                treeDomain.setUrl(url);
            }
            treeDomain.setIconSkin(resource.getIcon());
            treeDomain.setTarget("frame-content");

            treeDomain.setButtonFlag(resource.getButtonflag() + "");
            treeDomain.setNote(resource.getNote());
            treeDomain.setOrders(resource.getOrders());
            treeDomain.setPlatfrom(resource.getPlatfrom());
            treeDomain.setParentFlag(resource.getParentFlag());
            if(null!=resource.getOpenFlag() && SystemConstant.YES==resource.getOpenFlag()){
                treeDomain.setOpen(true);
            }else{
                treeDomain.setOpen(false);
            }
            list_treeDomain.add(treeDomain) ;
        }
        return list_treeDomain;
    }

    /**
     * 根据用户编码和资源，验证用户是否拥有该资源的权限
     * @param resourceQuery
     * @return
     */
    public LetvResponse<Boolean> isPermitted( ResourceQuery resourceQuery) {
        LOG.info("inputPar:ResourceServiceImpl#isPermitted.resourceQuery="+JsonHelper.toJson(resourceQuery));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>() ;
        if (StringUtils.isEmpty(resourceQuery.getUserCode()) || null == resourceQuery
                || (StringUtils.isBlank(resourceQuery.getResourceCode()) && StringUtils.isBlank(resourceQuery.getUrl()))) {
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#isPermitted.resourceQuery="+JsonHelper.toJson(resourceQuery));
            return letvResponse;
        }
        boolean result = false ;
        try{
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
            /**
             * 查询资源是否被系统管理
             */
            List<Resource> list_resource = resourceManager.queryResourceList(resourceQuery);
            if(CollectionUtils.isEmpty(list_resource)){
//                Resource resource = new Resource () ;
//                resource.setUrl(resourceQuery.getUrl());
//                resource.setPlatfrom(resourceQuery.getPlatfrom());
//                resourceManager.insertRequestURL(resource);
                result = false ;
                letvResponse.setCode(PortalSystemTipCodeEnum.RESOURCE_NOT_EXISTS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.RESOURCE_NOT_EXISTS.getNote()+":"+resourceQuery.getUrl());
            } else{
                /**
                 * 查询用户拥有的全部资源
                  */
                RoleResourceQuery roleResourceQuery = new RoleResourceQuery () ;
                roleResourceQuery.setUserCode(resourceQuery.getUserCode());
                List<RoleResource> list_roleResource = roleResourceManager.queryRoleResourceListByUserCode(roleResourceQuery) ;

                if(CollectionUtils.isEmpty(list_roleResource)){
                    result = false ;
                }else{
                    for(Resource r : list_resource){
                        for (RoleResource rr : list_roleResource) {
                            if (StringUtils.isNotEmpty(r.getResourceCode()) && r.getResourceCode().equals(rr.getResourceCode())) {
                                result = true ;
                                break;
                            }
                        }
                    }
                    if(!result){
                        letvResponse.setCode(PortalSystemTipCodeEnum.RESOURCE_USER_NOT_HAVE.getValue());
                        letvResponse.setMessage(PortalSystemTipCodeEnum.RESOURCE_USER_NOT_HAVE.getNote()+":"+resourceQuery.getUrl());
                    }
                }
            }
//            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
            letvResponse.setResult(result);
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            letvResponse.setResult(result);
            LOG.error("error:ResourceServiceImpl#isPermitted.e="+e);
        }
        LOG.info("outputPar:ResourceServiceImpl#isPermitted.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 删除资源节点及其下所有子节点
     * @param resource
     * @return
     */
    public LetvResponse<Boolean> deleteTreeNode(Resource resource){
        LOG.info("inputPar:ResourceServiceImpl#isPermitted.resource="+JsonHelper.toJson(resource));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>() ;
        if(null==resource || StringUtils.isEmpty(resource.getResourceCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#isPermitted.resource="+JsonHelper.toJson(resource));
            return letvResponse ;
        }
        try{
            Boolean ret = resourceManager.deleteTreeNode(resource) ;
            if(ret){
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(ret);
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:ResourceServiceImpl#isPermitted.e=", e);
        }
        LOG.info("outputPar:ResourceServiceImpl#isPermitted.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }

    /**
     *根据资源层级查询资源信息
     * @return
     */
    @Override
    public LetvResponse<List<Resource>> queryResourceByLevel(ResourceQuery resourceQuery) {
        LOG.info("inputPar:ResourceServiceImpl#queryResourceByLevel.resourceQuery="+JsonHelper.toJson(resourceQuery));
        LetvResponse<List<Resource>>  letvResponse = new LetvResponse<List<Resource>> ();
        if(null == resourceQuery || null == resourceQuery.getLevel()){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#queryResourceByLevel.resourceQuery=" + JsonHelper.toJson(resourceQuery));
            return letvResponse ;
        }
        try{
            List<Resource> list_resource = resourceManager.queryResourceList(resourceQuery) ;
            if(CollectionUtils.isEmpty(list_resource)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_resource);
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:ResourceServiceImpl#queryResourceByLevel.e=", e);
        }
        LOG.info("outputPar:ResourceServiceImpl#queryResourceByLevel.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 插入资源以及资源对应的角色
     * @param resource
     * @return
     */
    @Override
    public LetvResponse<Boolean> addResourceAndBelongRole(Resource resource) {
        LOG.info("inputPar:ResourceServiceImpl#addResourceAndBelongRole.resource="+JsonHelper.toJson(resource));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>() ;
        if(null == resource || StringUtils.isEmpty(resource.getResourceName())
                || StringUtils.isEmpty(resource.getUrl())
                || StringUtils.isEmpty(resource.getCreateUser())
                || StringUtils.isEmpty(resource.getParentCode())
                || StringUtils.isEmpty(resource.getPlatfrom())
                || StringUtils.isEmpty(resource.getRoleCode())
                || null == resource.getButtonflag()
                || null == resource.getParentFlag() // 传入0
                || null == resource.getLevel()
                || !Objects.equals(resource.getParentCode().split("_").length,resource.getLevel()+1)){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:ResourceServiceImpl#addResourceAndBelongRole.resource="+JsonHelper.toJson(resource));
            return letvResponse ;
        }
        try{
            resource.setResourceType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            if(null == resource.getOpenFlag()){
                resource.setOpenFlag(1);
            }
            if(null == resource.getOrders()){
                resource.setOrders(10);
            }
            if(null == resource.getNote()){
                resource.setNote(resource.getResourceName());
            }
            boolean ret = resourceManager.insertResourceAndBelongRole(resource);
            if(ret){
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
            }
            letvResponse.setResult(ret);
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:ResourceServiceImpl#addResourceAndBelongRole.e=",e);
        }
        LOG.info("outputPar:ResourceServiceImpl#addResourceAndBelongRole.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse;
    }


    public LetvResponse<Boolean> insertRequestURL(Resource resource) {
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>() ;
        if(null==resource ){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            return letvResponse ;
        }
        try {
            boolean resultFlag = resourceManager.insertRequestURL(resource);
        } catch (Exception e) {
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
        }
        return letvResponse;
    }
}

