package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.RelationSpsNotificationType;
import com.letv.tbtSps.domain.query.RelationSpsNotificationTypeQuery;
import com.letv.tbtSps.service.RelationSpsNotificationTypeService;
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
 * RelationSpsNotificationTypeController ：sps信息表-通报类型关联表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationSpsNotificationType")
public class RelationSpsNotificationTypeController extends ReviewBaseController {

    @Autowired
    private RelationSpsNotificationTypeService relationSpsNotificationTypeService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationSpsNotificationType";
    
    private static final Log LOG = LogFactory.getLog(RelationSpsNotificationTypeController.class);

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
    public String queryByPage(Model model, PageUtil page, RelationSpsNotificationTypeQuery query) {
        try {
            List<RelationSpsNotificationType> dataList = relationSpsNotificationTypeService.queryRelationSpsNotificationTypeListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationSpsNotificationType queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * sps信息表-通报类型关联表----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * sps信息表-通报类型关联表----添加
     * 
     * @param relationSpsNotificationType
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationSpsNotificationType relationSpsNotificationType) {
        try {
            relationSpsNotificationType.setCreateUser(getLoginUserCnName());
            if (relationSpsNotificationTypeService.insert(relationSpsNotificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationSpsNotificationType add fail, exist relationSpsNotificationType.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationSpsNotificationType add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps信息表-通报类型关联表----更新跳转
     * 
     * @param model
     * @param relationSpsNotificationType
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationSpsNotificationType relationSpsNotificationType) {
        try {
            RelationSpsNotificationType relationSpsNotificationTypeResult = relationSpsNotificationTypeService.getRelationSpsNotificationTypeById(relationSpsNotificationType.getId());
            model.addAttribute("relationSpsNotificationType", relationSpsNotificationTypeResult);
        } catch (Exception e) {
            LOG.error("relationSpsNotificationType updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps信息表-通报类型关联表----更新
     * 
     * @param model
     * @param relationSpsNotificationType
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationSpsNotificationType relationSpsNotificationType) {
        try {
            relationSpsNotificationType.setUpdateUser(getLoginUserCnName());
            if (relationSpsNotificationTypeService.update(relationSpsNotificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsNotificationType update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表-通报类型关联表----删除
     * 
     * @param relationSpsNotificationType
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationSpsNotificationType relationSpsNotificationType) {
        try {
            relationSpsNotificationType.setUpdateUser(getLoginUserCnName());
            if (relationSpsNotificationTypeService.delete(relationSpsNotificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsNotificationType delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表-通报类型关联表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationSpsNotificationTypeQuery query) {
        try {
            List<RelationSpsNotificationType> list = relationSpsNotificationTypeService.queryRelationSpsNotificationTypeList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsNotificationType query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps信息表-通报类型关联表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationSpsNotificationTypeQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationSpsNotificationType relationSpsNotificationType = relationSpsNotificationTypeService.getRelationSpsNotificationTypeById(query.getId());
            if (relationSpsNotificationType != null) {
                return new Wrapper<RelationSpsNotificationType>().result(relationSpsNotificationType);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps信息表-通报类型关联表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationSpsNotificationType has error.", e);
            return error();
        }
    }
}
