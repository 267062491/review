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

import com.letv.tbtSps.domain.Tbinfo;
import com.letv.tbtSps.domain.query.TbinfoQuery;
import com.letv.tbtSps.service.TbinfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * TbinfoController ：tbt信息表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("tbinfo")
public class TbinfoController extends BaseController {

    @Autowired
    private TbinfoService tbinfoService;

    /** 视图前缀 */
    private static final String viewPrefix ="tbinfo";
    
    private static final Log LOG = LogFactory.getLog(TbinfoController.class);

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
    public String queryByPage(Model model, PageUtil page, TbinfoQuery query) {
        try {
            List<Tbinfo> dataList = tbinfoService.queryTbinfoListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("tbinfo queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt信息表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt信息表----添加
     * 
     * @param tbinfo
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(Tbinfo tbinfo) {
        try {
            tbinfo.setCreateUser(getLoginUserCnName());
            if (tbinfoService.insert(tbinfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("tbinfo add fail, exist tbinfo.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("tbinfo add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt信息表----更新跳转
     * 
     * @param model
     * @param tbinfo
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, Tbinfo tbinfo) {
        try {
            Tbinfo tbinfoResult = tbinfoService.getTbinfoById(tbinfo.getId());
            model.addAttribute("tbinfo", tbinfoResult);
        } catch (Exception e) {
            LOG.error("tbinfo updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt信息表----更新
     * 
     * @param model
     * @param tbinfo
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, Tbinfo tbinfo) {
        try {
            tbinfo.setUpdateUser(getLoginUserCnName());
            if (tbinfoService.update(tbinfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("tbinfo update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表----删除
     * 
     * @param tbinfo
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(Tbinfo tbinfo) {
        try {
            tbinfo.setUpdateUser(getLoginUserCnName());
            if (tbinfoService.delete(tbinfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("tbinfo delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(TbinfoQuery query) {
        try {
            List<Tbinfo> list = tbinfoService.queryTbinfoList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("tbinfo query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt信息表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(TbinfoQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            Tbinfo tbinfo = tbinfoService.getTbinfoById(query.getId());
            if (tbinfo != null) {
                return new Wrapper<Tbinfo>().result(tbinfo);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt信息表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail tbinfo has error.", e);
            return error();
        }
    }
}
