package com.letv.tbtSps.controller;
   

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.tbtSps.service.ResourceService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * ResourceController ：资源表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    /** 视图前缀 */
    private static final String viewPrefix ="resource";
    
    private static final Log LOG = LogFactory.getLog(ResourceController.class);

    /**
     * 首页
     * 
     * @param model
     * @param page
     * @param query
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
            List<Resource> dataList = resourceService.queryResourceListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("resource queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 资源表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
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
            resource.setCreateUser(getLoginUserCnName());
            if (resourceService.insert(resource)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
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
}
