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
import com.letv.tbtSps.domain.SystemPar;
import com.letv.tbtSps.sdk.api.request.SystemParRequest;
import com.letv.tbtSps.sdk.api.response.dto.SystemParResponseDto;
import com.letv.tbtSps.service.SystemParService;

/**
 * 系统参数表REST服务：提供有关系统参数表的接口
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SystemParResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SystemParService systemParService; 

    /**
     * 查询系统参数表信息
     * 
     * @param request
     *            系统参数表请求参数
     * @return 系统参数表返回对象
     * 
     */
    @POST
    @Path("/systemPar/getSystemPar")
    public Wrapper<?> getSystemPar(SystemParRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getSystemPar 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            SystemPar systemPar = systemParService.getSystemParById(request.getId());
            SystemParResponseDto responseDto = convert(systemPar);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询系统参数表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private SystemParResponseDto convert(SystemPar systemPar) {
        if (null == systemPar) {
            return null;
        }

        SystemParResponseDto systemParResponseDto = new SystemParResponseDto();
        BeanUtils.copyProperties(systemPar, systemParResponseDto);
        return systemParResponseDto;
    }

    // 数据转换
    private List<SystemParResponseDto> convertList(List<SystemPar> systemPars) {
        if (CollectionUtils.isEmpty(systemPars)) {
            return null;
        }

        List<SystemParResponseDto> list = new ArrayList<SystemParResponseDto>(systemPars.size());
        for (SystemPar systemPar : systemPars) {
            list.add(convert(systemPar));
        }
        return list;
    } 

}
