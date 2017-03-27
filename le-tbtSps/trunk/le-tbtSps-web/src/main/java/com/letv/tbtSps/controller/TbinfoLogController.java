package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.TbinfoLog;
import com.letv.tbtSps.domain.query.TbinfoLogQuery;
import com.letv.tbtSps.service.TbinfoLogService;
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
 * TbinfoLogController ：tbt信息操作日志表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("tbinfoLog")
public class TbinfoLogController extends ReviewBaseController {

    @Autowired
    private TbinfoLogService tbinfoLogService;

    /** 视图前缀 */
    private static final String viewPrefix ="tbinfoLog";
    
    private static final Log LOG = LogFactory.getLog(TbinfoLogController.class);

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
    public String queryByPage(Model model, PageUtil page, TbinfoLogQuery query) {
        try {
            List<TbinfoLog> dataList = tbinfoLogService.queryTbinfoLogListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("tbinfoLog queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt信息操作日志表----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt信息操作日志表----添加
     * 
     * @param tbinfoLog
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(TbinfoLog tbinfoLog) {
        try {
            tbinfoLog.setCreateUser(getLoginUserCnName());
            if (tbinfoLogService.insert(tbinfoLog)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("tbinfoLog add fail, exist tbinfoLog.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("tbinfoLog add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt信息操作日志表----更新跳转
     * 
     * @param model
     * @param tbinfoLog
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, TbinfoLog tbinfoLog) {
        try {
            TbinfoLog tbinfoLogResult = tbinfoLogService.getTbinfoLogById(tbinfoLog.getId());
            model.addAttribute("tbinfoLog", tbinfoLogResult);
        } catch (Exception e) {
            LOG.error("tbinfoLog updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt信息操作日志表----更新
     * 
     * @param model
     * @param tbinfoLog
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, TbinfoLog tbinfoLog) {
        try {
            tbinfoLog.setUpdateUser(getLoginUserCnName());
            if (tbinfoLogService.update(tbinfoLog)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("tbinfoLog update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息操作日志表----删除
     * 
     * @param tbinfoLog
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(TbinfoLog tbinfoLog) {
        try {
            tbinfoLog.setUpdateUser(getLoginUserCnName());
            if (tbinfoLogService.delete(tbinfoLog)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("tbinfoLog delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息操作日志表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(TbinfoLogQuery query) {
        try {
            List<TbinfoLog> list = tbinfoLogService.queryTbinfoLogList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("tbinfoLog query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt信息操作日志表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(TbinfoLogQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            TbinfoLog tbinfoLog = tbinfoLogService.getTbinfoLogById(query.getId());
            if (tbinfoLog != null) {
                return new Wrapper<TbinfoLog>().result(tbinfoLog);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt信息操作日志表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail tbinfoLog has error.", e);
            return error();
        }
    }
}
