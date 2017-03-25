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
import com.letv.tbtSps.domain.SpsResidualInfo;
import com.letv.tbtSps.sdk.api.request.SpsResidualInfoRequest;
import com.letv.tbtSps.sdk.api.response.dto.SpsResidualInfoResponseDto;
import com.letv.tbtSps.service.SpsResidualInfoService;

/**
 * sps残留量信息REST服务：提供有关sps残留量信息的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SpsResidualInfoResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SpsResidualInfoService spsResidualInfoService; 

    /**
     * 查询sps残留量信息信息
     * 
     * @param request
     *            sps残留量信息请求参数
     * @return sps残留量信息返回对象
     * 
     */
    @POST
    @Path("/spsResidualInfo/getSpsResidualInfo")
    public Wrapper<?> getSpsResidualInfo(SpsResidualInfoRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getSpsResidualInfo 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            SpsResidualInfo spsResidualInfo = spsResidualInfoService.getSpsResidualInfoById(request.getId());
            SpsResidualInfoResponseDto responseDto = convert(spsResidualInfo);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps残留量信息数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private SpsResidualInfoResponseDto convert(SpsResidualInfo spsResidualInfo) {
        if (null == spsResidualInfo) {
            return null;
        }

        SpsResidualInfoResponseDto spsResidualInfoResponseDto = new SpsResidualInfoResponseDto();
        BeanUtils.copyProperties(spsResidualInfo, spsResidualInfoResponseDto);
        return spsResidualInfoResponseDto;
    }

    // 数据转换
    private List<SpsResidualInfoResponseDto> convertList(List<SpsResidualInfo> spsResidualInfos) {
        if (CollectionUtils.isEmpty(spsResidualInfos)) {
            return null;
        }

        List<SpsResidualInfoResponseDto> list = new ArrayList<SpsResidualInfoResponseDto>(spsResidualInfos.size());
        for (SpsResidualInfo spsResidualInfo : spsResidualInfos) {
            list.add(convert(spsResidualInfo));
        }
        return list;
    } 

}
