package com.letv.tbtSps.controller;


import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.tbtSps.domain.query.TreeDomain;
import com.letv.tbtSps.service.ResourceService;
import com.letv.tbtSps.utils.JsonUtilHelp;
import com.letv.tbtSps.utils.constant.CommonConstants;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
import com.letv.tbtSps.utils.constant.ResourceIcons;
import com.letv.tbtSps.utils.constant.ResourcePlant;
import com.letv.wmscommon.dto.PageUtil;
import com.letv.wmscommon.dto.PagedQueryDto;
import com.letv.wmscommon.dto.PagedResultDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ResourceController ：资源表控制器
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:38
*/
@Controller
@RequestMapping("resource")
public class ResourceController extends ReviewBaseController {

    @Autowired
    private ResourceService resourceService;

    /** 视图前缀 */
    private static final String viewPrefix ="resource";
    
    private static final Log LOG = LogFactory.getLog(ResourceController.class);

    /**
     * 首页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        return viewPrefix + "/index";
    }
    
    /**
     * 分页 查询数据
     * 
     * @param model
     * @param page
     * @param query
     * @return
     */
    @RequestMapping(value = "queryByPage")
    public String queryByPage(Model model, PageUtil page, ResourceQuery query) {
        try {
            PagedQueryDto<ResourceQuery> pagedQuery = new PagedQueryDto<ResourceQuery>();
            pagedQuery.setPageUtil(page);
            pagedQuery.setQueryDto(query);
            PagedResultDto<Resource> pagedResult = resourceService.queryResourceListWithPage(pagedQuery);
            List<Resource> dataList = pagedResult.getResult();
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", pagedResult.getPageUtil());// 分页
        } catch (Exception e) {
            LOG.error("resource queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 资源表----添加跳转
     *
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward(Model model) {
        model.addAttribute(CommonConstants.RESOURCE_ICONS_KEY, ResourceIcons.getIcons());
        model.addAttribute(CommonConstants.RESOURCE_PLANT_KEY, ResourcePlant.getPlants());
        return viewPrefix + "/add";
    }
    /**
     * 资源表----修改跳转
     *
     * @return
     */
    @RequestMapping(value = "getResourceByCode")
    public String getResourceByCode(Model model, Resource resource) {
        model.addAttribute(CommonConstants.RESOURCE_ICONS_KEY, ResourceIcons.getIcons());
        model.addAttribute(CommonConstants.RESOURCE_PLANT_KEY, ResourcePlant.getPlants());
        LetvResponse<Resource> letvResponse = resourceService.getResourceByCode(resource.getResourceCode());
        if(letvResponse.getCode() == LetvResponse.SUCCESS_CODE){
            model.addAttribute("resource",letvResponse.getResult()) ;
        }
        return viewPrefix + "/update";
    }

    /**
     * 资源表----添加
     * 
     * @param resource
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(Resource resource) {
        try {
            resource.setCreateUser(getLoginUserName());
            resource.setUpdateUser(getLoginUserName());
            LetvResponse<Resource> letvResponse = resourceService.insert(resource);
            if (letvResponse.getCode()== PortalSystemTipCodeEnum.SCUESS.getValue()) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！",letvResponse.getResult().getResourceCode());
            } else {
                return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
            }
        } catch (ExistedException e) {
            LOG.warn("resource add fail, exist resource.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("resource add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 资源表----更新跳转
     * 
     * @param model
     * @param resource
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, Resource resource) {
        try {
            Resource resourceResult = resourceService.getResourceById(resource.getId());
            model.addAttribute("resource", resourceResult);
        } catch (Exception e) {
            LOG.error("resource updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 资源表----更新
     * 
     * @param model
     * @param resource
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, Resource resource) {
        try {
            resource.setUpdateUser(getLoginUserCnName());
            if (resourceService.update(resource)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("resource update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 资源表----删除
     * 
     * @param resource
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(Resource resource) {
        try {
            resource.setUpdateUser(getLoginUserCnName());
            if (resourceService.delete(resource)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("resource delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 资源表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(ResourceQuery query) {
        try {
            List<Resource> list = resourceService.queryResourceList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("resource query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询资源表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(ResourceQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            Resource resource = resourceService.getResourceById(query.getId());
            if (resource != null) {
                return new Wrapper<Resource>().result(resource);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询资源表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail resource has error.", e);
            return error();
        }
    }

    /**
     * 初始化加载树
     * @param query
     * @return
     */
    @RequestMapping(value = "initTree")
    @ResponseBody
    public Wrapper<?> initTree(ResourceQuery query) {
        try {
            /**
             * 查询资源列表树
             */
            LetvResponse<List<TreeDomain>> letvResponse = resourceService.queryResourceListByCodes( query) ;
            if (letvResponse.getCode()== PortalSystemTipCodeEnum.SCUESS.getValue()) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, JsonUtilHelp.listToJsonArray(letvResponse.getResult()));
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("resource query has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 查询某个节点以及节点的子节点
     * @param query
     * @return
     */
    @RequestMapping(value = "queryResourceListByLikeCode")
    @ResponseBody
    public Wrapper<?> queryResourceListByLikeCode(ResourceQuery query) {
        try {
            /**
             * 查询资源列表树
             */
            LetvResponse<List<TreeDomain>> letvResponse = resourceService.queryResourceListByLikeCode( query) ;
            if (letvResponse.getCode()== PortalSystemTipCodeEnum.SCUESS.getValue()) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, JsonUtilHelp.listToJsonArray(letvResponse.getResult()));
            } else {
                return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
            }
        } catch (Exception e) {
            LOG.error("resource query has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 删除节点以及子节点
     * @param resource
     * @return
     */
    @RequestMapping(value = "deleteTreeNode")
    @ResponseBody
    public Wrapper<?> deleteTreeNode(Resource resource) {
        LOG.info("inputPar:ResourceController#deleteTreeNode.resource="+JsonHelper.toJson(resource));
        try {
            LetvResponse<Boolean> letvResponse = resourceService.deleteTreeNode( resource) ;
            if (letvResponse.getCode()== PortalSystemTipCodeEnum.SCUESS.getValue()) {
                LOG.info("outputPar:ResourceController#deleteTreeNode.letvResponse="+JsonHelper.toJson(letvResponse));
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
            } else {
                LOG.error("error:ResourceController#deleteTreeNode.letvResponse="+JsonHelper.toJson(letvResponse));
                return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
            }
        } catch (Exception e) {
            LOG.error("error:ResourceController#deleteTreeNode.e=",e);
            return WrapMapper.error();
        }
    }



}
