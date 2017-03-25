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

import com.letv.tbtSps.domain.UserCountry;
import com.letv.tbtSps.domain.query.UserCountryQuery;
import com.letv.tbtSps.service.UserCountryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * UserCountryController ：用户通报成员关联表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("userCountry")
public class UserCountryController extends BaseController {

    @Autowired
    private UserCountryService userCountryService;

    /** 视图前缀 */
    private static final String viewPrefix ="userCountry";
    
    private static final Log LOG = LogFactory.getLog(UserCountryController.class);

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
    public String queryByPage(Model model, PageUtil page, UserCountryQuery query) {
        try {
            List<UserCountry> dataList = userCountryService.queryUserCountryListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("userCountry queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 用户通报成员关联表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 用户通报成员关联表----添加
     * 
     * @param userCountry
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(UserCountry userCountry) {
        try {
            userCountry.setCreateUser(getLoginUserCnName());
            if (userCountryService.insert(userCountry)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("userCountry add fail, exist userCountry.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("userCountry add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 用户通报成员关联表----更新跳转
     * 
     * @param model
     * @param userCountry
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, UserCountry userCountry) {
        try {
            UserCountry userCountryResult = userCountryService.getUserCountryById(userCountry.getId());
            model.addAttribute("userCountry", userCountryResult);
        } catch (Exception e) {
            LOG.error("userCountry updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 用户通报成员关联表----更新
     * 
     * @param model
     * @param userCountry
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, UserCountry userCountry) {
        try {
            userCountry.setUpdateUser(getLoginUserCnName());
            if (userCountryService.update(userCountry)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("userCountry update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 用户通报成员关联表----删除
     * 
     * @param userCountry
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(UserCountry userCountry) {
        try {
            userCountry.setUpdateUser(getLoginUserCnName());
            if (userCountryService.delete(userCountry)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("userCountry delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 用户通报成员关联表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(UserCountryQuery query) {
        try {
            List<UserCountry> list = userCountryService.queryUserCountryList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("userCountry query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询用户通报成员关联表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(UserCountryQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            UserCountry userCountry = userCountryService.getUserCountryById(query.getId());
            if (userCountry != null) {
                return new Wrapper<UserCountry>().result(userCountry);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询用户通报成员关联表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail userCountry has error.", e);
            return error();
        }
    }
}
