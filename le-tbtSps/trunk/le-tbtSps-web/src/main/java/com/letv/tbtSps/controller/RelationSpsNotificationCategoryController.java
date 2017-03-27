package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.RelationSpsNotificationCategory;
import com.letv.tbtSps.domain.query.RelationSpsNotificationCategoryQuery;
import com.letv.tbtSps.service.RelationSpsNotificationCategoryService;
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
 * RelationSpsNotificationCategoryController ：sps信息表通报分类关联表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationSpsNotificationCategory")
public class RelationSpsNotificationCategoryController extends ReviewBaseController {

    @Autowired
    private RelationSpsNotificationCategoryService relationSpsNotificationCategoryService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationSpsNotificationCategory";
    
    private static final Log LOG = LogFactory.getLog(RelationSpsNotificationCategoryController.class);

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
    public String queryByPage(Model model, PageUtil page, RelationSpsNotificationCategoryQuery query) {
        try {
            List<RelationSpsNotificationCategory> dataList = relationSpsNotificationCategoryService.queryRelationSpsNotificationCategoryListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationSpsNotificationCategory queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * sps信息表通报分类关联表----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * sps信息表通报分类关联表----添加
     * 
     * @param relationSpsNotificationCategory
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        try {
            relationSpsNotificationCategory.setCreateUser(getLoginUserCnName());
            if (relationSpsNotificationCategoryService.insert(relationSpsNotificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationSpsNotificationCategory add fail, exist relationSpsNotificationCategory.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationSpsNotificationCategory add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps信息表通报分类关联表----更新跳转
     * 
     * @param model
     * @param relationSpsNotificationCategory
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationSpsNotificationCategory relationSpsNotificationCategory) {
        try {
            RelationSpsNotificationCategory relationSpsNotificationCategoryResult = relationSpsNotificationCategoryService.getRelationSpsNotificationCategoryById(relationSpsNotificationCategory.getId());
            model.addAttribute("relationSpsNotificationCategory", relationSpsNotificationCategoryResult);
        } catch (Exception e) {
            LOG.error("relationSpsNotificationCategory updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps信息表通报分类关联表----更新
     * 
     * @param model
     * @param relationSpsNotificationCategory
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationSpsNotificationCategory relationSpsNotificationCategory) {
        try {
            relationSpsNotificationCategory.setUpdateUser(getLoginUserCnName());
            if (relationSpsNotificationCategoryService.update(relationSpsNotificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsNotificationCategory update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表通报分类关联表----删除
     * 
     * @param relationSpsNotificationCategory
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        try {
            relationSpsNotificationCategory.setUpdateUser(getLoginUserCnName());
            if (relationSpsNotificationCategoryService.delete(relationSpsNotificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsNotificationCategory delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表通报分类关联表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationSpsNotificationCategoryQuery query) {
        try {
            List<RelationSpsNotificationCategory> list = relationSpsNotificationCategoryService.queryRelationSpsNotificationCategoryList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsNotificationCategory query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps信息表通报分类关联表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationSpsNotificationCategoryQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationSpsNotificationCategory relationSpsNotificationCategory = relationSpsNotificationCategoryService.getRelationSpsNotificationCategoryById(query.getId());
            if (relationSpsNotificationCategory != null) {
                return new Wrapper<RelationSpsNotificationCategory>().result(relationSpsNotificationCategory);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps信息表通报分类关联表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationSpsNotificationCategory has error.", e);
            return error();
        }
    }
}
