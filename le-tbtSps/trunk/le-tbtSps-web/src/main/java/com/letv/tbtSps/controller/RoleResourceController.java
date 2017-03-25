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

import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.service.RoleResourceService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * RoleResourceController ：角色-资源控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("roleResource")
public class RoleResourceController extends BaseController {

    @Autowired
    private RoleResourceService roleResourceService;

    /** 视图前缀 */
    private static final String viewPrefix ="roleResource";
    
    private static final Log LOG = LogFactory.getLog(RoleResourceController.class);

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
    public String queryByPage(Model model, PageUtil page, RoleResourceQuery query) {
        try {
            List<RoleResource> dataList = roleResourceService.queryRoleResourceListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("roleResource queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 角色-资源----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 角色-资源----添加
     * 
     * @param roleResource
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RoleResource roleResource) {
        try {
            roleResource.setCreateUser(getLoginUserCnName());
            if (roleResourceService.insert(roleResource)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("roleResource add fail, exist roleResource.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("roleResource add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 角色-资源----更新跳转
     * 
     * @param model
     * @param roleResource
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RoleResource roleResource) {
        try {
            RoleResource roleResourceResult = roleResourceService.getRoleResourceById(roleResource.getId());
            model.addAttribute("roleResource", roleResourceResult);
        } catch (Exception e) {
            LOG.error("roleResource updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 角色-资源----更新
     * 
     * @param model
     * @param roleResource
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RoleResource roleResource) {
        try {
            roleResource.setUpdateUser(getLoginUserCnName());
            if (roleResourceService.update(roleResource)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("roleResource update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 角色-资源----删除
     * 
     * @param roleResource
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RoleResource roleResource) {
        try {
            roleResource.setUpdateUser(getLoginUserCnName());
            if (roleResourceService.delete(roleResource)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("roleResource delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 角色-资源----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RoleResourceQuery query) {
        try {
            List<RoleResource> list = roleResourceService.queryRoleResourceList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("roleResource query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询角色-资源详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RoleResourceQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RoleResource roleResource = roleResourceService.getRoleResourceById(query.getId());
            if (roleResource != null) {
                return new Wrapper<RoleResource>().result(roleResource);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询角色-资源详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail roleResource has error.", e);
            return error();
        }
    }
}
