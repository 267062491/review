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

import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.tbtSps.service.SpsInfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * SpsInfoController ：sps信息表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("spsInfo")
public class SpsInfoController extends BaseController {

    @Autowired
    private SpsInfoService spsInfoService;

    /** 视图前缀 */
    private static final String viewPrefix ="spsInfo";
    
    private static final Log LOG = LogFactory.getLog(SpsInfoController.class);

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
    public String queryByPage(Model model, PageUtil page, SpsInfoQuery query) {
        try {
            List<SpsInfo> dataList = spsInfoService.querySpsInfoListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("spsInfo queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * sps信息表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * sps信息表----添加
     * 
     * @param spsInfo
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(SpsInfo spsInfo) {
        try {
            spsInfo.setCreateUser(getLoginUserCnName());
            if (spsInfoService.insert(spsInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("spsInfo add fail, exist spsInfo.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("spsInfo add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps信息表----更新跳转
     * 
     * @param model
     * @param spsInfo
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, SpsInfo spsInfo) {
        try {
            SpsInfo spsInfoResult = spsInfoService.getSpsInfoById(spsInfo.getId());
            model.addAttribute("spsInfo", spsInfoResult);
        } catch (Exception e) {
            LOG.error("spsInfo updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps信息表----更新
     * 
     * @param model
     * @param spsInfo
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, SpsInfo spsInfo) {
        try {
            spsInfo.setUpdateUser(getLoginUserCnName());
            if (spsInfoService.update(spsInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfo update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表----删除
     * 
     * @param spsInfo
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(SpsInfo spsInfo) {
        try {
            spsInfo.setUpdateUser(getLoginUserCnName());
            if (spsInfoService.delete(spsInfo)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfo delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(SpsInfoQuery query) {
        try {
            List<SpsInfo> list = spsInfoService.querySpsInfoList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfo query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps信息表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(SpsInfoQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            SpsInfo spsInfo = spsInfoService.getSpsInfoById(query.getId());
            if (spsInfo != null) {
                return new Wrapper<SpsInfo>().result(spsInfo);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps信息表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail spsInfo has error.", e);
            return error();
        }
    }
}
