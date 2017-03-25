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

import com.letv.tbtSps.domain.SpsResidualInfo;
import com.letv.tbtSps.domain.query.SpsResidualInfoQuery;
import com.letv.tbtSps.service.SpsResidualInfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * SpsResidualInfoController ：sps残留量信息控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("spsResidualInfo")
public class SpsResidualInfoController extends BaseController {

    @Autowired
    private SpsResidualInfoService spsResidualInfoService;

    /** 视图前缀 */
    private static final String viewPrefix ="spsResidualInfo";
    
    private static final Log LOG = LogFactory.getLog(SpsResidualInfoController.class);

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
    public String queryByPage(Model model, PageUtil page, SpsResidualInfoQuery query) {
        try {
            List<SpsResidualInfo> dataList = spsResidualInfoService.querySpsResidualInfoListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("spsResidualInfo queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * sps残留量信息----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * sps残留量信息----添加
     * 
     * @param spsResidualInfo
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(SpsResidualInfo spsResidualInfo) {
        try {
            spsResidualInfo.setCreateUser(getLoginUserCnName());
            if (spsResidualInfoService.insert(spsResidualInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("spsResidualInfo add fail, exist spsResidualInfo.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("spsResidualInfo add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps残留量信息----更新跳转
     * 
     * @param model
     * @param spsResidualInfo
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, SpsResidualInfo spsResidualInfo) {
        try {
            SpsResidualInfo spsResidualInfoResult = spsResidualInfoService.getSpsResidualInfoById(spsResidualInfo.getId());
            model.addAttribute("spsResidualInfo", spsResidualInfoResult);
        } catch (Exception e) {
            LOG.error("spsResidualInfo updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps残留量信息----更新
     * 
     * @param model
     * @param spsResidualInfo
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, SpsResidualInfo spsResidualInfo) {
        try {
            spsResidualInfo.setUpdateUser(getLoginUserCnName());
            if (spsResidualInfoService.update(spsResidualInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("spsResidualInfo update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps残留量信息----删除
     * 
     * @param spsResidualInfo
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(SpsResidualInfo spsResidualInfo) {
        try {
            spsResidualInfo.setUpdateUser(getLoginUserCnName());
            if (spsResidualInfoService.delete(spsResidualInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("spsResidualInfo delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps残留量信息----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(SpsResidualInfoQuery query) {
        try {
            List<SpsResidualInfo> list = spsResidualInfoService.querySpsResidualInfoList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("spsResidualInfo query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps残留量信息详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(SpsResidualInfoQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            SpsResidualInfo spsResidualInfo = spsResidualInfoService.getSpsResidualInfoById(query.getId());
            if (spsResidualInfo != null) {
                return new Wrapper<SpsResidualInfo>().result(spsResidualInfo);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps残留量信息详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail spsResidualInfo has error.", e);
            return error();
        }
    }
}
