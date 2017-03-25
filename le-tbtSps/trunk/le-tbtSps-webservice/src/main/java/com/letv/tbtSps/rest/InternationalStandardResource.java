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
import com.letv.tbtSps.domain.InternationalStandard;
import com.letv.tbtSps.sdk.api.request.InternationalStandardRequest;
import com.letv.tbtSps.sdk.api.response.dto.InternationalStandardResponseDto;
import com.letv.tbtSps.service.InternationalStandardService;

/**
 * 国际标准REST服务：提供有关国际标准的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class InternationalStandardResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private InternationalStandardService internationalStandardService; 

    /**
     * 查询国际标准信息
     * 
     * @param request
     *            国际标准请求参数
     * @return 国际标准返回对象
     * 
     */
    @POST
    @Path("/internationalStandard/getInternationalStandard")
    public Wrapper<?> getInternationalStandard(InternationalStandardRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getInternationalStandard 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            InternationalStandard internationalStandard = internationalStandardService.getInternationalStandardById(request.getId());
            InternationalStandardResponseDto responseDto = convert(internationalStandard);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询国际标准数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private InternationalStandardResponseDto convert(InternationalStandard internationalStandard) {
        if (null == internationalStandard) {
            return null;
        }

        InternationalStandardResponseDto internationalStandardResponseDto = new InternationalStandardResponseDto();
        BeanUtils.copyProperties(internationalStandard, internationalStandardResponseDto);
        return internationalStandardResponseDto;
    }

    // 数据转换
    private List<InternationalStandardResponseDto> convertList(List<InternationalStandard> internationalStandards) {
        if (CollectionUtils.isEmpty(internationalStandards)) {
            return null;
        }

        List<InternationalStandardResponseDto> list = new ArrayList<InternationalStandardResponseDto>(internationalStandards.size());
        for (InternationalStandard internationalStandard : internationalStandards) {
            list.add(convert(internationalStandard));
        }
        return list;
    } 

}
