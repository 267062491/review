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
import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.sdk.api.request.SpsInfoLogRequest;
import com.letv.tbtSps.sdk.api.response.dto.SpsInfoLogResponseDto;
import com.letv.tbtSps.service.SpsInfoLogService;

/**
 * sps信息操作日志表REST服务：提供有关sps信息操作日志表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SpsInfoLogResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SpsInfoLogService spsInfoLogService; 

    /**
     * 查询sps信息操作日志表信息
     * 
     * @param request
     *            sps信息操作日志表请求参数
     * @return sps信息操作日志表返回对象
     * 
     */
    @POST
    @Path("/spsInfoLog/getSpsInfoLog")
    public Wrapper<?> getSpsInfoLog(SpsInfoLogRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getSpsInfoLog 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            SpsInfoLog spsInfoLog = spsInfoLogService.getSpsInfoLogById(request.getId());
            SpsInfoLogResponseDto responseDto = convert(spsInfoLog);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息操作日志表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private SpsInfoLogResponseDto convert(SpsInfoLog spsInfoLog) {
        if (null == spsInfoLog) {
            return null;
        }

        SpsInfoLogResponseDto spsInfoLogResponseDto = new SpsInfoLogResponseDto();
        BeanUtils.copyProperties(spsInfoLog, spsInfoLogResponseDto);
        return spsInfoLogResponseDto;
    }

    // 数据转换
    private List<SpsInfoLogResponseDto> convertList(List<SpsInfoLog> spsInfoLogs) {
        if (CollectionUtils.isEmpty(spsInfoLogs)) {
            return null;
        }

        List<SpsInfoLogResponseDto> list = new ArrayList<SpsInfoLogResponseDto>(spsInfoLogs.size());
        for (SpsInfoLog spsInfoLog : spsInfoLogs) {
            list.add(convert(spsInfoLog));
        }
        return list;
    } 

}
