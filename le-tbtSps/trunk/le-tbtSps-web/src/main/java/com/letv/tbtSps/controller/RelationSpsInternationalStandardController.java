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

import com.letv.tbtSps.domain.RelationSpsInternationalStandard;
import com.letv.tbtSps.domain.query.RelationSpsInternationalStandardQuery;
import com.letv.tbtSps.service.RelationSpsInternationalStandardService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * RelationSpsInternationalStandardController ：sps信息表-国际标准关联表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationSpsInternationalStandard")
public class RelationSpsInternationalStandardController extends BaseController {

    @Autowired
    private RelationSpsInternationalStandardService relationSpsInternationalStandardService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationSpsInternationalStandard";
    
    private static final Log LOG = LogFactory.getLog(RelationSpsInternationalStandardController.class);

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
    public String queryByPage(Model model, PageUtil page, RelationSpsInternationalStandardQuery query) {
        try {
            List<RelationSpsInternationalStandard> dataList = relationSpsInternationalStandardService.queryRelationSpsInternationalStandardListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationSpsInternationalStandard queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * sps信息表-国际标准关联表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * sps信息表-国际标准关联表----添加
     * 
     * @param relationSpsInternationalStandard
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        try {
            relationSpsInternationalStandard.setCreateUser(getLoginUserCnName());
            if (relationSpsInternationalStandardService.insert(relationSpsInternationalStandard)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationSpsInternationalStandard add fail, exist relationSpsInternationalStandard.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationSpsInternationalStandard add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps信息表-国际标准关联表----更新跳转
     * 
     * @param model
     * @param relationSpsInternationalStandard
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationSpsInternationalStandard relationSpsInternationalStandard) {
        try {
            RelationSpsInternationalStandard relationSpsInternationalStandardResult = relationSpsInternationalStandardService.getRelationSpsInternationalStandardById(relationSpsInternationalStandard.getId());
            model.addAttribute("relationSpsInternationalStandard", relationSpsInternationalStandardResult);
        } catch (Exception e) {
            LOG.error("relationSpsInternationalStandard updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps信息表-国际标准关联表----更新
     * 
     * @param model
     * @param relationSpsInternationalStandard
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationSpsInternationalStandard relationSpsInternationalStandard) {
        try {
            relationSpsInternationalStandard.setUpdateUser(getLoginUserCnName());
            if (relationSpsInternationalStandardService.update(relationSpsInternationalStandard)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsInternationalStandard update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表-国际标准关联表----删除
     * 
     * @param relationSpsInternationalStandard
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        try {
            relationSpsInternationalStandard.setUpdateUser(getLoginUserCnName());
            if (relationSpsInternationalStandardService.delete(relationSpsInternationalStandard)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsInternationalStandard delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表-国际标准关联表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationSpsInternationalStandardQuery query) {
        try {
            List<RelationSpsInternationalStandard> list = relationSpsInternationalStandardService.queryRelationSpsInternationalStandardList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationSpsInternationalStandard query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps信息表-国际标准关联表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationSpsInternationalStandardQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationSpsInternationalStandard relationSpsInternationalStandard = relationSpsInternationalStandardService.getRelationSpsInternationalStandardById(query.getId());
            if (relationSpsInternationalStandard != null) {
                return new Wrapper<RelationSpsInternationalStandard>().result(relationSpsInternationalStandard);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps信息表-国际标准关联表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationSpsInternationalStandard has error.", e);
            return error();
        }
    }
}
