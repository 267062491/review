package com.letv.tbtSps.controller;
   

import java.util.List;

import com.letv.tbtSps.common.controller.ReviewBaseController;
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

import com.letv.tbtSps.domain.Country;
import com.letv.tbtSps.domain.query.CountryQuery;
import com.letv.tbtSps.service.CountryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * CountryController ：通报成员控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("country")
public class CountryController extends ReviewBaseController {

    @Autowired
    private CountryService countryService;

    /** 视图前缀 */
    private static final String viewPrefix ="country";
    
    private static final Log LOG = LogFactory.getLog(CountryController.class);

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
    public String queryByPage(Model model, PageUtil page, CountryQuery query) {
        try {
            List<Country> dataList = countryService.queryCountryListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("country queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 通报成员----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 通报成员----添加
     * 
     * @param country
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(Country country) {
        try {
            country.setCreateUser(getLoginUserCnName());
            if (countryService.insert(country)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("country add fail, exist country.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("country add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 通报成员----更新跳转
     * 
     * @param model
     * @param country
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, Country country) {
        try {
            Country countryResult = countryService.getCountryById(country.getId());
            model.addAttribute("country", countryResult);
        } catch (Exception e) {
            LOG.error("country updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 通报成员----更新
     * 
     * @param model
     * @param country
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, Country country) {
        try {
            country.setUpdateUser(getLoginUserCnName());
            if (countryService.update(country)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("country update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 通报成员----删除
     * 
     * @param country
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(Country country) {
        try {
            country.setUpdateUser(getLoginUserCnName());
            if (countryService.delete(country)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("country delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 通报成员----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(CountryQuery query) {
        try {
            List<Country> list = countryService.queryCountryList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("country query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询通报成员详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(CountryQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            Country country = countryService.getCountryById(query.getId());
            if (country != null) {
                return new Wrapper<Country>().result(country);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询通报成员详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail country has error.", e);
            return error();
        }
    }
}
