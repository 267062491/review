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

import com.letv.tbtSps.domain.Language;
import com.letv.tbtSps.domain.query.LanguageQuery;
import com.letv.tbtSps.service.LanguageService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * LanguageController ：原文语种控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("language")
public class LanguageController extends BaseController {

    @Autowired
    private LanguageService languageService;

    /** 视图前缀 */
    private static final String viewPrefix ="language";
    
    private static final Log LOG = LogFactory.getLog(LanguageController.class);

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
    public String queryByPage(Model model, PageUtil page, LanguageQuery query) {
        try {
            List<Language> dataList = languageService.queryLanguageListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("language queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 原文语种----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 原文语种----添加
     * 
     * @param language
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(Language language) {
        try {
            language.setCreateUser(getLoginUserCnName());
            if (languageService.insert(language)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("language add fail, exist language.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("language add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 原文语种----更新跳转
     * 
     * @param model
     * @param language
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, Language language) {
        try {
            Language languageResult = languageService.getLanguageById(language.getId());
            model.addAttribute("language", languageResult);
        } catch (Exception e) {
            LOG.error("language updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 原文语种----更新
     * 
     * @param model
     * @param language
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, Language language) {
        try {
            language.setUpdateUser(getLoginUserCnName());
            if (languageService.update(language)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("language update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 原文语种----删除
     * 
     * @param language
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(Language language) {
        try {
            language.setUpdateUser(getLoginUserCnName());
            if (languageService.delete(language)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("language delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 原文语种----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(LanguageQuery query) {
        try {
            List<Language> list = languageService.queryLanguageList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("language query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询原文语种详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(LanguageQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            Language language = languageService.getLanguageById(query.getId());
            if (language != null) {
                return new Wrapper<Language>().result(language);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询原文语种详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail language has error.", e);
            return error();
        }
    }
}
