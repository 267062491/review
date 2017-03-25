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

import com.letv.tbtSps.domain.TbresidualInfo;
import com.letv.tbtSps.domain.query.TbresidualInfoQuery;
import com.letv.tbtSps.service.TbresidualInfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * TbresidualInfoController ：tbt残留量信息控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("tbresidualInfo")
public class TbresidualInfoController extends BaseController {

    @Autowired
    private TbresidualInfoService tbresidualInfoService;

    /** 视图前缀 */
    private static final String viewPrefix ="tbresidualInfo";
    
    private static final Log LOG = LogFactory.getLog(TbresidualInfoController.class);

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
    public String queryByPage(Model model, PageUtil page, TbresidualInfoQuery query) {
        try {
            List<TbresidualInfo> dataList = tbresidualInfoService.queryTbresidualInfoListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("tbresidualInfo queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt残留量信息----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt残留量信息----添加
     * 
     * @param tbresidualInfo
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(TbresidualInfo tbresidualInfo) {
        try {
            tbresidualInfo.setCreateUser(getLoginUserCnName());
            if (tbresidualInfoService.insert(tbresidualInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("tbresidualInfo add fail, exist tbresidualInfo.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("tbresidualInfo add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt残留量信息----更新跳转
     * 
     * @param model
     * @param tbresidualInfo
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, TbresidualInfo tbresidualInfo) {
        try {
            TbresidualInfo tbresidualInfoResult = tbresidualInfoService.getTbresidualInfoById(tbresidualInfo.getId());
            model.addAttribute("tbresidualInfo", tbresidualInfoResult);
        } catch (Exception e) {
            LOG.error("tbresidualInfo updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt残留量信息----更新
     * 
     * @param model
     * @param tbresidualInfo
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, TbresidualInfo tbresidualInfo) {
        try {
            tbresidualInfo.setUpdateUser(getLoginUserCnName());
            if (tbresidualInfoService.update(tbresidualInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("tbresidualInfo update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt残留量信息----删除
     * 
     * @param tbresidualInfo
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(TbresidualInfo tbresidualInfo) {
        try {
            tbresidualInfo.setUpdateUser(getLoginUserCnName());
            if (tbresidualInfoService.delete(tbresidualInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("tbresidualInfo delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt残留量信息----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(TbresidualInfoQuery query) {
        try {
            List<TbresidualInfo> list = tbresidualInfoService.queryTbresidualInfoList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("tbresidualInfo query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt残留量信息详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(TbresidualInfoQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            TbresidualInfo tbresidualInfo = tbresidualInfoService.getTbresidualInfoById(query.getId());
            if (tbresidualInfo != null) {
                return new Wrapper<TbresidualInfo>().result(tbresidualInfo);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt残留量信息详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail tbresidualInfo has error.", e);
            return error();
        }
    }
}
