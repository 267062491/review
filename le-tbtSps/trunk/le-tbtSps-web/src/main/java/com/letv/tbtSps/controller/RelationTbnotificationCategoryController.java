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

import com.letv.tbtSps.domain.RelationTbnotificationCategory;
import com.letv.tbtSps.domain.query.RelationTbnotificationCategoryQuery;
import com.letv.tbtSps.service.RelationTbnotificationCategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * RelationTbnotificationCategoryController ：tbt信息表通报分类关联表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationTbnotificationCategory")
public class RelationTbnotificationCategoryController extends BaseController {

    @Autowired
    private RelationTbnotificationCategoryService relationTbnotificationCategoryService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationTbnotificationCategory";
    
    private static final Log LOG = LogFactory.getLog(RelationTbnotificationCategoryController.class);

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
    public String queryByPage(Model model, PageUtil page, RelationTbnotificationCategoryQuery query) {
        try {
            List<RelationTbnotificationCategory> dataList = relationTbnotificationCategoryService.queryRelationTbnotificationCategoryListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationTbnotificationCategory queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt信息表通报分类关联表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt信息表通报分类关联表----添加
     * 
     * @param relationTbnotificationCategory
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationTbnotificationCategory relationTbnotificationCategory) {
        try {
            relationTbnotificationCategory.setCreateUser(getLoginUserCnName());
            if (relationTbnotificationCategoryService.insert(relationTbnotificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationTbnotificationCategory add fail, exist relationTbnotificationCategory.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationTbnotificationCategory add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt信息表通报分类关联表----更新跳转
     * 
     * @param model
     * @param relationTbnotificationCategory
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationTbnotificationCategory relationTbnotificationCategory) {
        try {
            RelationTbnotificationCategory relationTbnotificationCategoryResult = relationTbnotificationCategoryService.getRelationTbnotificationCategoryById(relationTbnotificationCategory.getId());
            model.addAttribute("relationTbnotificationCategory", relationTbnotificationCategoryResult);
        } catch (Exception e) {
            LOG.error("relationTbnotificationCategory updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt信息表通报分类关联表----更新
     * 
     * @param model
     * @param relationTbnotificationCategory
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationTbnotificationCategory relationTbnotificationCategory) {
        try {
            relationTbnotificationCategory.setUpdateUser(getLoginUserCnName());
            if (relationTbnotificationCategoryService.update(relationTbnotificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbnotificationCategory update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表通报分类关联表----删除
     * 
     * @param relationTbnotificationCategory
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationTbnotificationCategory relationTbnotificationCategory) {
        try {
            relationTbnotificationCategory.setUpdateUser(getLoginUserCnName());
            if (relationTbnotificationCategoryService.delete(relationTbnotificationCategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbnotificationCategory delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表通报分类关联表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationTbnotificationCategoryQuery query) {
        try {
            List<RelationTbnotificationCategory> list = relationTbnotificationCategoryService.queryRelationTbnotificationCategoryList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbnotificationCategory query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt信息表通报分类关联表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationTbnotificationCategoryQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationTbnotificationCategory relationTbnotificationCategory = relationTbnotificationCategoryService.getRelationTbnotificationCategoryById(query.getId());
            if (relationTbnotificationCategory != null) {
                return new Wrapper<RelationTbnotificationCategory>().result(relationTbnotificationCategory);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt信息表通报分类关联表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationTbnotificationCategory has error.", e);
            return error();
        }
    }
}
