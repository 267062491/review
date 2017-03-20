package com.letv.tbtSps.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.schedule.task.rest.TaskCommonResource;
import com.letv.tbtSps.utils.constant.TaskDefine;

/**
 * 任务监控接口<br/>
 * 1.支持积压任务数量查询
 * 
 * @author lijianzhong
 * @version 2015-9-7 下午4:22:27
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
public class TaskMonitorResource extends TaskCommonResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    /**
     * 积压任务数量查询
     * 
     * @param taskType
     *            任务类型
     * @param taskStatus
     *            任务状态
     * @return
     */
    @GET
    @Path("/taskMonitor/queryTaskCount")
    public Wrapper<?> queryTaskCount(@QueryParam("taskType") Integer taskType,
            @QueryParam("taskStatus") Integer taskStatus) {
        try {
            TaskDefine task = TaskDefine.valueOf(taskType);
            return super.queryTaskCount(task.getTableName(), task.getTaskType(), taskStatus);
        } catch (IllegalArgumentException e) {
            this.logger.error("查询数据失败，taskType非法：" + taskType, e);
            return WrapMapper.illegalArgument();
        } catch (Exception e) {
            this.logger.error("查询数据异常", e);
            return WrapMapper.error();
        }
    }

}
