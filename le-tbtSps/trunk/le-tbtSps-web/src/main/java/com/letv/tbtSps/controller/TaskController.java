package com.letv.tbtSps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.letv.common.utils.page.PageUtil;
import com.letv.schedule.consts.TaskStatusManager;
import com.letv.schedule.task.controller.TaskCommonController;
import com.letv.schedule.task.domain.query.TaskCommonQuery;
import com.letv.tbtSps.utils.constant.TaskDefine;

/**
 * 所有任务表的通用控制器
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
@Controller
@RequestMapping("task")
public class TaskController extends TaskCommonController {

    /**
     * 任务监控管理首页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("statusMap", TaskStatusManager.getStatusMap());// 任务状态
        model.addAttribute("tasks", TaskDefine.values());
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
    public String queryByPage(Model model, PageUtil page, TaskCommonQuery query) {
        if (null != query && null != query.getTaskType()) {
            TaskDefine taskDefine = TaskDefine.valueOf(query.getTaskType());
            super.queryTasks(model, page, query, taskDefine.getTableName(), taskDefine.getTaskType());
        }
        model.addAttribute("tasks", TaskDefine.values());
        return viewPrefix + "/index";
    }
}
