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

import com.letv.tbtSps.domain.InternationalStandard;
import com.letv.tbtSps.domain.query.InternationalStandardQuery;
import com.letv.tbtSps.service.InternationalStandardService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * InternationalStandardController ：国际标准控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("internationalStandard")
public class InternationalStandardController extends BaseController {

    @Autowired
    private InternationalStandardService internationalStandardService;

    /** 视图前缀 */
    private static final String viewPrefix ="internationalStandard";
    
    private static final Log LOG = LogFactory.getLog(InternationalStandardController.class);

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
    public String queryByPage(Model model, PageUtil page, InternationalStandardQuery query) {
        try {
            List<InternationalStandard> dataList = internationalStandardService.queryInternationalStandardListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("internationalStandard queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 国际标准----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 国际标准----添加
     * 
     * @param internationalStandard
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(InternationalStandard internationalStandard) {
        try {
            internationalStandard.setCreateUser(getLoginUserCnName());
            if (internationalStandardService.insert(internationalStandard)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("internationalStandard add fail, exist internationalStandard.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("internationalStandard add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 国际标准----更新跳转
     * 
     * @param model
     * @param internationalStandard
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, InternationalStandard internationalStandard) {
        try {
            InternationalStandard internationalStandardResult = internationalStandardService.getInternationalStandardById(internationalStandard.getId());
            model.addAttribute("internationalStandard", internationalStandardResult);
        } catch (Exception e) {
            LOG.error("internationalStandard updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 国际标准----更新
     * 
     * @param model
     * @param internationalStandard
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, InternationalStandard internationalStandard) {
        try {
            internationalStandard.setUpdateUser(getLoginUserCnName());
            if (internationalStandardService.update(internationalStandard)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("internationalStandard update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 国际标准----删除
     * 
     * @param internationalStandard
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(InternationalStandard internationalStandard) {
        try {
            internationalStandard.setUpdateUser(getLoginUserCnName());
            if (internationalStandardService.delete(internationalStandard)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("internationalStandard delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 国际标准----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(InternationalStandardQuery query) {
        try {
            List<InternationalStandard> list = internationalStandardService.queryInternationalStandardList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("internationalStandard query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询国际标准详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(InternationalStandardQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            InternationalStandard internationalStandard = internationalStandardService.getInternationalStandardById(query.getId());
            if (internationalStandard != null) {
                return new Wrapper<InternationalStandard>().result(internationalStandard);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询国际标准详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail internationalStandard has error.", e);
            return error();
        }
    }
}
