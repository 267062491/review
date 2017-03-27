package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.Targereason;
import com.letv.tbtSps.domain.query.TargereasonQuery;
import com.letv.tbtSps.service.TargereasonService;
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
 * TargereasonController ：目标理由控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("targereason")
public class TargereasonController extends ReviewBaseController {

    @Autowired
    private TargereasonService targereasonService;

    /** 视图前缀 */
    private static final String viewPrefix ="targereason";
    
    private static final Log LOG = LogFactory.getLog(TargereasonController.class);

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
    public String queryByPage(Model model, PageUtil page, TargereasonQuery query) {
        try {
            List<Targereason> dataList = targereasonService.queryTargereasonListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("targereason queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 目标理由----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 目标理由----添加
     * 
     * @param targereason
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(Targereason targereason) {
        try {
            targereason.setCreateUser(getLoginUserCnName());
            if (targereasonService.insert(targereason)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("targereason add fail, exist targereason.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("targereason add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 目标理由----更新跳转
     * 
     * @param model
     * @param targereason
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, Targereason targereason) {
        try {
            Targereason targereasonResult = targereasonService.getTargereasonById(targereason.getId());
            model.addAttribute("targereason", targereasonResult);
        } catch (Exception e) {
            LOG.error("targereason updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 目标理由----更新
     * 
     * @param model
     * @param targereason
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, Targereason targereason) {
        try {
            targereason.setUpdateUser(getLoginUserCnName());
            if (targereasonService.update(targereason)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("targereason update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 目标理由----删除
     * 
     * @param targereason
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(Targereason targereason) {
        try {
            targereason.setUpdateUser(getLoginUserCnName());
            if (targereasonService.delete(targereason)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("targereason delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 目标理由----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(TargereasonQuery query) {
        try {
            List<Targereason> list = targereasonService.queryTargereasonList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("targereason query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询目标理由详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(TargereasonQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            Targereason targereason = targereasonService.getTargereasonById(query.getId());
            if (targereason != null) {
                return new Wrapper<Targereason>().result(targereason);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询目标理由详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail targereason has error.", e);
            return error();
        }
    }
}
