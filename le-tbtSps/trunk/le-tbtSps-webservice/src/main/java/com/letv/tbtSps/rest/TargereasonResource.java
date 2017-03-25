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
import com.letv.tbtSps.domain.Targereason;
import com.letv.tbtSps.sdk.api.request.TargereasonRequest;
import com.letv.tbtSps.sdk.api.response.dto.TargereasonResponseDto;
import com.letv.tbtSps.service.TargereasonService;

/**
 * 目标理由REST服务：提供有关目标理由的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class TargereasonResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private TargereasonService targereasonService; 

    /**
     * 查询目标理由信息
     * 
     * @param request
     *            目标理由请求参数
     * @return 目标理由返回对象
     * 
     */
    @POST
    @Path("/targereason/getTargereason")
    public Wrapper<?> getTargereason(TargereasonRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getTargereason 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            Targereason targereason = targereasonService.getTargereasonById(request.getId());
            TargereasonResponseDto responseDto = convert(targereason);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询目标理由数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private TargereasonResponseDto convert(Targereason targereason) {
        if (null == targereason) {
            return null;
        }

        TargereasonResponseDto targereasonResponseDto = new TargereasonResponseDto();
        BeanUtils.copyProperties(targereason, targereasonResponseDto);
        return targereasonResponseDto;
    }

    // 数据转换
    private List<TargereasonResponseDto> convertList(List<Targereason> targereasons) {
        if (CollectionUtils.isEmpty(targereasons)) {
            return null;
        }

        List<TargereasonResponseDto> list = new ArrayList<TargereasonResponseDto>(targereasons.size());
        for (Targereason targereason : targereasons) {
            list.add(convert(targereason));
        }
        return list;
    } 

}
