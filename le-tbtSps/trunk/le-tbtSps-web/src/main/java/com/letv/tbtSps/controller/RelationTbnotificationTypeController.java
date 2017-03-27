package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.RelationTbnotificationType;
import com.letv.tbtSps.domain.query.RelationTbnotificationTypeQuery;
import com.letv.tbtSps.service.RelationTbnotificationTypeService;
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
 * RelationTbnotificationTypeController ：tbt信息表-通报类型关联表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationTbnotificationType")
public class RelationTbnotificationTypeController extends ReviewBaseController {

    @Autowired
    private RelationTbnotificationTypeService relationTbnotificationTypeService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationTbnotificationType";
    
    private static final Log LOG = LogFactory.getLog(RelationTbnotificationTypeController.class);

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
    public String queryByPage(Model model, PageUtil page, RelationTbnotificationTypeQuery query) {
        try {
            List<RelationTbnotificationType> dataList = relationTbnotificationTypeService.queryRelationTbnotificationTypeListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationTbnotificationType queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt信息表-通报类型关联表----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt信息表-通报类型关联表----添加
     * 
     * @param relationTbnotificationType
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationTbnotificationType relationTbnotificationType) {
        try {
            relationTbnotificationType.setCreateUser(getLoginUserCnName());
            if (relationTbnotificationTypeService.insert(relationTbnotificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationTbnotificationType add fail, exist relationTbnotificationType.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationTbnotificationType add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt信息表-通报类型关联表----更新跳转
     * 
     * @param model
     * @param relationTbnotificationType
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationTbnotificationType relationTbnotificationType) {
        try {
            RelationTbnotificationType relationTbnotificationTypeResult = relationTbnotificationTypeService.getRelationTbnotificationTypeById(relationTbnotificationType.getId());
            model.addAttribute("relationTbnotificationType", relationTbnotificationTypeResult);
        } catch (Exception e) {
            LOG.error("relationTbnotificationType updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt信息表-通报类型关联表----更新
     * 
     * @param model
     * @param relationTbnotificationType
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationTbnotificationType relationTbnotificationType) {
        try {
            relationTbnotificationType.setUpdateUser(getLoginUserCnName());
            if (relationTbnotificationTypeService.update(relationTbnotificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbnotificationType update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表-通报类型关联表----删除
     * 
     * @param relationTbnotificationType
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationTbnotificationType relationTbnotificationType) {
        try {
            relationTbnotificationType.setUpdateUser(getLoginUserCnName());
            if (relationTbnotificationTypeService.delete(relationTbnotificationType)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbnotificationType delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表-通报类型关联表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationTbnotificationTypeQuery query) {
        try {
            List<RelationTbnotificationType> list = relationTbnotificationTypeService.queryRelationTbnotificationTypeList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbnotificationType query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt信息表-通报类型关联表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationTbnotificationTypeQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationTbnotificationType relationTbnotificationType = relationTbnotificationTypeService.getRelationTbnotificationTypeById(query.getId());
            if (relationTbnotificationType != null) {
                return new Wrapper<RelationTbnotificationType>().result(relationTbnotificationType);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt信息表-通报类型关联表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationTbnotificationType has error.", e);
            return error();
        }
    }
}
