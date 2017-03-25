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

import com.letv.tbtSps.domain.NotificationType;
import com.letv.tbtSps.domain.query.NotificationTypeQuery;
import com.letv.tbtSps.service.NotificationTypeService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * NotificationTypeController ：通报类型控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("notificationType")
public class NotificationTypeController extends BaseController {

    @Autowired
    private NotificationTypeService notificationTypeService;

    /** 视图前缀 */
    private static final String viewPrefix ="notificationType";
    
    private static final Log LOG = LogFactory.getLog(NotificationTypeController.class);

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
    public String queryByPage(Model model, PageUtil page, NotificationTypeQuery query) {
        try {
            List<NotificationType> dataList = notificationTypeService.queryNotificationTypeListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("notificationType queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 通报类型----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 通报类型----添加
     * 
     * @param notificationType
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(NotificationType notificationType) {
        try {
            notificationType.setCreateUser(getLoginUserCnName());
            if (notificationTypeService.insert(notificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("notificationType add fail, exist notificationType.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("notificationType add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 通报类型----更新跳转
     * 
     * @param model
     * @param notificationType
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, NotificationType notificationType) {
        try {
            NotificationType notificationTypeResult = notificationTypeService.getNotificationTypeById(notificationType.getId());
            model.addAttribute("notificationType", notificationTypeResult);
        } catch (Exception e) {
            LOG.error("notificationType updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 通报类型----更新
     * 
     * @param model
     * @param notificationType
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, NotificationType notificationType) {
        try {
            notificationType.setUpdateUser(getLoginUserCnName());
            if (notificationTypeService.update(notificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("notificationType update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 通报类型----删除
     * 
     * @param notificationType
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(NotificationType notificationType) {
        try {
            notificationType.setUpdateUser(getLoginUserCnName());
            if (notificationTypeService.delete(notificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("notificationType delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 通报类型----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(NotificationTypeQuery query) {
        try {
            List<NotificationType> list = notificationTypeService.queryNotificationTypeList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("notificationType query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询通报类型详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(NotificationTypeQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            NotificationType notificationType = notificationTypeService.getNotificationTypeById(query.getId());
            if (notificationType != null) {
                return new Wrapper<NotificationType>().result(notificationType);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询通报类型详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail notificationType has error.", e);
            return error();
        }
    }
}
