package com.letv.tbtSps.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.domain.TbinfoLog;
import com.letv.tbtSps.sdk.api.request.TbinfoLogRequest;
import com.letv.tbtSps.sdk.api.response.dto.TbinfoLogResponseDto;
import com.letv.tbtSps.service.TbinfoLogService;

/**
 * tbt信息操作日志表REST服务：提供有关tbt信息操作日志表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class TbinfoLogResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private TbinfoLogService tbinfoLogService; 

    /**
     * 查询tbt信息操作日志表信息
     * 
     * @param request
     *            tbt信息操作日志表请求参数
     * @return tbt信息操作日志表返回对象
     * 
     */
    @POST
    @Path("/tbinfoLog/getTbinfoLog")
    public Wrapper<?> getTbinfoLog(TbinfoLogRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getTbinfoLog 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            TbinfoLog tbinfoLog = tbinfoLogService.getTbinfoLogById(request.getId());
            TbinfoLogResponseDto responseDto = convert(tbinfoLog);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt信息操作日志表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private TbinfoLogResponseDto convert(TbinfoLog tbinfoLog) {
        if (null == tbinfoLog) {
            return null;
        }

        TbinfoLogResponseDto tbinfoLogResponseDto = new TbinfoLogResponseDto();
        BeanUtils.copyProperties(tbinfoLog, tbinfoLogResponseDto);
        return tbinfoLogResponseDto;
    }

    // 数据转换
    private List<TbinfoLogResponseDto> convertList(List<TbinfoLog> tbinfoLogs) {
        if (CollectionUtils.isEmpty(tbinfoLogs)) {
            return null;
        }

        List<TbinfoLogResponseDto> list = new ArrayList<TbinfoLogResponseDto>(tbinfoLogs.size());
        for (TbinfoLog tbinfoLog : tbinfoLogs) {
            list.add(convert(tbinfoLog));
        }
        return list;
    } 

}
