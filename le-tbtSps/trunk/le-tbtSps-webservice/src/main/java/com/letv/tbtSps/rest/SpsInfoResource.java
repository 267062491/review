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
import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.sdk.api.request.SpsInfoRequest;
import com.letv.tbtSps.sdk.api.response.dto.SpsInfoResponseDto;
import com.letv.tbtSps.service.SpsInfoService;

/**
 * sps信息表REST服务：提供有关sps信息表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SpsInfoResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SpsInfoService spsInfoService; 

    /**
     * 查询sps信息表信息
     * 
     * @param request
     *            sps信息表请求参数
     * @return sps信息表返回对象
     * 
     */
    @POST
    @Path("/spsInfo/getSpsInfo")
    public Wrapper<?> getSpsInfo(SpsInfoRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getSpsInfo 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            SpsInfo spsInfo = spsInfoService.getSpsInfoById(request.getId());
            SpsInfoResponseDto responseDto = convert(spsInfo);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private SpsInfoResponseDto convert(SpsInfo spsInfo) {
        if (null == spsInfo) {
            return null;
        }

        SpsInfoResponseDto spsInfoResponseDto = new SpsInfoResponseDto();
        BeanUtils.copyProperties(spsInfo, spsInfoResponseDto);
        return spsInfoResponseDto;
    }

    // 数据转换
    private List<SpsInfoResponseDto> convertList(List<SpsInfo> spsInfos) {
        if (CollectionUtils.isEmpty(spsInfos)) {
            return null;
        }

        List<SpsInfoResponseDto> list = new ArrayList<SpsInfoResponseDto>(spsInfos.size());
        for (SpsInfo spsInfo : spsInfos) {
            list.add(convert(spsInfo));
        }
        return list;
    } 

}
