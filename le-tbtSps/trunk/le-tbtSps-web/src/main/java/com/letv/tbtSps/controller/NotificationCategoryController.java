package com.letv.tbtSps.controller;
   

import java.util.List;

import com.letv.tbtSps.common.controller.ReviewBaseController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letv.tbtSps.domain.NotificationCategory;
import com.letv.tbtSps.domain.query.NotificationCategoryQuery;
import com.letv.tbtSps.service.NotificationCategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * NotificationCategoryController ：通报内部分类控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("notificationCategory")
public class NotificationCategoryController extends ReviewBaseController {

    @Autowired
    private NotificationCategoryService notificationCategoryService;

    /** 视图前缀 */
    private static final String viewPrefix ="notificationCategory";
    
    private static final Log LOG = LogFactory.getLog(NotificationCategoryController.class);

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
    public String queryByPage(Model model, PageUtil page, NotificationCategoryQuery query) {
        try {
            List<NotificationCategory> dataList = notificationCategoryService.queryNotificationCategoryListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("notificationCategory queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 通报内部分类----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 通报内部分类----添加
     * 
     * @param notificationCategory
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(NotificationCategory notificationCategory) {
        try {
            notificationCategory.setCreateUser(getLoginUserCnName());
            if (notificationCategoryService.insert(notificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("notificationCategory add fail, exist notificationCategory.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("notificationCategory add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 通报内部分类----更新跳转
     * 
     * @param model
     * @param notificationCategory
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, NotificationCategory notificationCategory) {
        try {
            NotificationCategory notificationCategoryResult = notificationCategoryService.getNotificationCategoryById(notificationCategory.getId());
            model.addAttribute("notificationCategory", notificationCategoryResult);
        } catch (Exception e) {
            LOG.error("notificationCategory updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 通报内部分类----更新
     * 
     * @param model
     * @param notificationCategory
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, NotificationCategory notificationCategory) {
        try {
            notificationCategory.setUpdateUser(getLoginUserCnName());
            if (notificationCategoryService.update(notificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("notificationCategory update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 通报内部分类----删除
     * 
     * @param notificationCategory
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(NotificationCategory notificationCategory) {
        try {
            notificationCategory.setUpdateUser(getLoginUserCnName());
            if (notificationCategoryService.delete(notificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("notificationCategory delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 通报内部分类----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(NotificationCategoryQuery query) {
        try {
            List<NotificationCategory> list = notificationCategoryService.queryNotificationCategoryList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("notificationCategory query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询通报内部分类详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(NotificationCategoryQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            NotificationCategory notificationCategory = notificationCategoryService.getNotificationCategoryById(query.getId());
            if (notificationCategory != null) {
                return new Wrapper<NotificationCategory>().result(notificationCategory);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询通报内部分类详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail notificationCategory has error.", e);
            return error();
        }
    }
}
