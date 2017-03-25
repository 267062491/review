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

import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.query.SpsInfoLogQuery;
import com.letv.tbtSps.service.SpsInfoLogService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * SpsInfoLogController ：sps信息操作日志表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("spsInfoLog")
public class SpsInfoLogController extends BaseController {

    @Autowired
    private SpsInfoLogService spsInfoLogService;

    /** 视图前缀 */
    private static final String viewPrefix ="spsInfoLog";
    
    private static final Log LOG = LogFactory.getLog(SpsInfoLogController.class);

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
    public String queryByPage(Model model, PageUtil page, SpsInfoLogQuery query) {
        try {
            List<SpsInfoLog> dataList = spsInfoLogService.querySpsInfoLogListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("spsInfoLog queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * sps信息操作日志表----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * sps信息操作日志表----添加
     * 
     * @param spsInfoLog
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(SpsInfoLog spsInfoLog) {
        try {
            spsInfoLog.setCreateUser(getLoginUserCnName());
            if (spsInfoLogService.insert(spsInfoLog)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("spsInfoLog add fail, exist spsInfoLog.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("spsInfoLog add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps信息操作日志表----更新跳转
     * 
     * @param model
     * @param spsInfoLog
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, SpsInfoLog spsInfoLog) {
        try {
            SpsInfoLog spsInfoLogResult = spsInfoLogService.getSpsInfoLogById(spsInfoLog.getId());
            model.addAttribute("spsInfoLog", spsInfoLogResult);
        } catch (Exception e) {
            LOG.error("spsInfoLog updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps信息操作日志表----更新
     * 
     * @param model
     * @param spsInfoLog
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, SpsInfoLog spsInfoLog) {
        try {
            spsInfoLog.setUpdateUser(getLoginUserCnName());
            if (spsInfoLogService.update(spsInfoLog)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfoLog update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息操作日志表----删除
     * 
     * @param spsInfoLog
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(SpsInfoLog spsInfoLog) {
        try {
            spsInfoLog.setUpdateUser(getLoginUserCnName());
            if (spsInfoLogService.delete(spsInfoLog)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfoLog delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息操作日志表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(SpsInfoLogQuery query) {
        try {
            List<SpsInfoLog> list = spsInfoLogService.querySpsInfoLogList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("spsInfoLog query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps信息操作日志表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(SpsInfoLogQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            SpsInfoLog spsInfoLog = spsInfoLogService.getSpsInfoLogById(query.getId());
            if (spsInfoLog != null) {
                return new Wrapper<SpsInfoLog>().result(spsInfoLog);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps信息操作日志表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail spsInfoLog has error.", e);
            return error();
        }
    }
}