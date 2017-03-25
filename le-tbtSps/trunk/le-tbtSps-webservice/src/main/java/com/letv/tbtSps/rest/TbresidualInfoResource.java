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
import com.letv.tbtSps.domain.TbresidualInfo;
import com.letv.tbtSps.sdk.api.request.TbresidualInfoRequest;
import com.letv.tbtSps.sdk.api.response.dto.TbresidualInfoResponseDto;
import com.letv.tbtSps.service.TbresidualInfoService;

/**
 * tbt残留量信息REST服务：提供有关tbt残留量信息的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class TbresidualInfoResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private TbresidualInfoService tbresidualInfoService; 

    /**
     * 查询tbt残留量信息信息
     * 
     * @param request
     *            tbt残留量信息请求参数
     * @return tbt残留量信息返回对象
     * 
     */
    @POST
    @Path("/tbresidualInfo/getTbresidualInfo")
    public Wrapper<?> getTbresidualInfo(TbresidualInfoRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getTbresidualInfo 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            TbresidualInfo tbresidualInfo = tbresidualInfoService.getTbresidualInfoById(request.getId());
            TbresidualInfoResponseDto responseDto = convert(tbresidualInfo);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt残留量信息数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private TbresidualInfoResponseDto convert(TbresidualInfo tbresidualInfo) {
        if (null == tbresidualInfo) {
            return null;
        }

        TbresidualInfoResponseDto tbresidualInfoResponseDto = new TbresidualInfoResponseDto();
        BeanUtils.copyProperties(tbresidualInfo, tbresidualInfoResponseDto);
        return tbresidualInfoResponseDto;
    }

    // 数据转换
    private List<TbresidualInfoResponseDto> convertList(List<TbresidualInfo> tbresidualInfos) {
        if (CollectionUtils.isEmpty(tbresidualInfos)) {
            return null;
        }

        List<TbresidualInfoResponseDto> list = new ArrayList<TbresidualInfoResponseDto>(tbresidualInfos.size());
        for (TbresidualInfo tbresidualInfo : tbresidualInfos) {
            list.add(convert(tbresidualInfo));
        }
        return list;
    } 

}
