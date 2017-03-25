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

import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.service.UserRoleService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * UserRoleController ：用户-角色控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("userRole")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;

    /** 视图前缀 */
    private static final String viewPrefix ="userRole";
    
    private static final Log LOG = LogFactory.getLog(UserRoleController.class);

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
    public String queryByPage(Model model, PageUtil page, UserRoleQuery query) {
        try {
            List<UserRole> dataList = userRoleService.queryUserRoleListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("userRole queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 用户-角色----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 用户-角色----添加
     * 
     * @param userRole
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(UserRole userRole) {
        try {
            userRole.setCreateUser(getLoginUserCnName());
            if (userRoleService.insert(userRole)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("userRole add fail, exist userRole.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("userRole add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 用户-角色----更新跳转
     * 
     * @param model
     * @param userRole
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, UserRole userRole) {
        try {
            UserRole userRoleResult = userRoleService.getUserRoleById(userRole.getId());
            model.addAttribute("userRole", userRoleResult);
        } catch (Exception e) {
            LOG.error("userRole updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 用户-角色----更新
     * 
     * @param model
     * @param userRole
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, UserRole userRole) {
        try {
            userRole.setUpdateUser(getLoginUserCnName());
            if (userRoleService.update(userRole)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("userRole update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 用户-角色----删除
     * 
     * @param userRole
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(UserRole userRole) {
        try {
            userRole.setUpdateUser(getLoginUserCnName());
            if (userRoleService.delete(userRole)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("userRole delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 用户-角色----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(UserRoleQuery query) {
        try {
            List<UserRole> list = userRoleService.queryUserRoleList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("userRole query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询用户-角色详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(UserRoleQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            UserRole userRole = userRoleService.getUserRoleById(query.getId());
            if (userRole != null) {
                return new Wrapper<UserRole>().result(userRole);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询用户-角色详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail userRole has error.", e);
            return error();
        }
    }
}
