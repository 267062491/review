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

import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.service.UserService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * UserController ：用户表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /** 视图前缀 */
    private static final String viewPrefix ="user";
    
    private static final Log LOG = LogFactory.getLog(UserController.class);

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
    public String queryByPage(Model model, PageUtil page, UserQuery query) {
        try {
            List<User> dataList = userService.queryUserListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("user queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 用户表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 用户表----添加
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(User user) {
        try {
            user.setCreateUser(getLoginUserCnName());
            if (userService.insert(user)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("user add fail, exist user.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("user add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 用户表----更新跳转
     * 
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, User user) {
        try {
            User userResult = userService.getUserById(user.getId());
            model.addAttribute("user", userResult);
        } catch (Exception e) {
            LOG.error("user updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 用户表----更新
     * 
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, User user) {
        try {
            user.setUpdateUser(getLoginUserCnName());
            if (userService.update(user)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("user update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 用户表----删除
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(User user) {
        try {
            user.setUpdateUser(getLoginUserCnName());
            if (userService.delete(user)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("user delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 用户表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(UserQuery query) {
        try {
            List<User> list = userService.queryUserList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("user query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询用户表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(UserQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            User user = userService.getUserById(query.getId());
            if (user != null) {
                return new Wrapper<User>().result(user);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询用户表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail user has error.", e);
            return error();
        }
    }
}
