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

import com.letv.tbtSps.domain.RelationTblanguage;
import com.letv.tbtSps.domain.query.RelationTblanguageQuery;
import com.letv.tbtSps.service.RelationTblanguageService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * RelationTblanguageController ：tbt信息表-原文语种关联表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationTblanguage")
public class RelationTblanguageController extends BaseController {

    @Autowired
    private RelationTblanguageService relationTblanguageService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationTblanguage";
    
    private static final Log LOG = LogFactory.getLog(RelationTblanguageController.class);

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
    public String queryByPage(Model model, PageUtil page, RelationTblanguageQuery query) {
        try {
            List<RelationTblanguage> dataList = relationTblanguageService.queryRelationTblanguageListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationTblanguage queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt信息表-原文语种关联表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt信息表-原文语种关联表----添加
     * 
     * @param relationTblanguage
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationTblanguage relationTblanguage) {
        try {
            relationTblanguage.setCreateUser(getLoginUserCnName());
            if (relationTblanguageService.insert(relationTblanguage)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationTblanguage add fail, exist relationTblanguage.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationTblanguage add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt信息表-原文语种关联表----更新跳转
     * 
     * @param model
     * @param relationTblanguage
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationTblanguage relationTblanguage) {
        try {
            RelationTblanguage relationTblanguageResult = relationTblanguageService.getRelationTblanguageById(relationTblanguage.getId());
            model.addAttribute("relationTblanguage", relationTblanguageResult);
        } catch (Exception e) {
            LOG.error("relationTblanguage updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt信息表-原文语种关联表----更新
     * 
     * @param model
     * @param relationTblanguage
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationTblanguage relationTblanguage) {
        try {
            relationTblanguage.setUpdateUser(getLoginUserCnName());
            if (relationTblanguageService.update(relationTblanguage)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTblanguage update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表-原文语种关联表----删除
     * 
     * @param relationTblanguage
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationTblanguage relationTblanguage) {
        try {
            relationTblanguage.setUpdateUser(getLoginUserCnName());
            if (relationTblanguageService.delete(relationTblanguage)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTblanguage delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表-原文语种关联表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationTblanguageQuery query) {
        try {
            List<RelationTblanguage> list = relationTblanguageService.queryRelationTblanguageList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTblanguage query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt信息表-原文语种关联表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationTblanguageQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationTblanguage relationTblanguage = relationTblanguageService.getRelationTblanguageById(query.getId());
            if (relationTblanguage != null) {
                return new Wrapper<RelationTblanguage>().result(relationTblanguage);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt信息表-原文语种关联表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationTblanguage has error.", e);
            return error();
        }
    }
}
