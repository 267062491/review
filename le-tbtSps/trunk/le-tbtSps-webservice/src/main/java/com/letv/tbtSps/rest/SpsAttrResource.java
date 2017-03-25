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
import com.letv.tbtSps.domain.SpsAttr;
import com.letv.tbtSps.sdk.api.request.SpsAttrRequest;
import com.letv.tbtSps.sdk.api.response.dto.SpsAttrResponseDto;
import com.letv.tbtSps.service.SpsAttrService;

/**
 * sps信息表对应的附件REST服务：提供有关sps信息表对应的附件的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SpsAttrResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SpsAttrService spsAttrService; 

    /**
     * 查询sps信息表对应的附件信息
     * 
     * @param request
     *            sps信息表对应的附件请求参数
     * @return sps信息表对应的附件返回对象
     * 
     */
    @POST
    @Path("/spsAttr/getSpsAttr")
    public Wrapper<?> getSpsAttr(SpsAttrRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getSpsAttr 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            SpsAttr spsAttr = spsAttrService.getSpsAttrById(request.getId());
            SpsAttrResponseDto responseDto = convert(spsAttr);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表对应的附件数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private SpsAttrResponseDto convert(SpsAttr spsAttr) {
        if (null == spsAttr) {
            return null;
        }

        SpsAttrResponseDto spsAttrResponseDto = new SpsAttrResponseDto();
        BeanUtils.copyProperties(spsAttr, spsAttrResponseDto);
        return spsAttrResponseDto;
    }

    // 数据转换
    private List<SpsAttrResponseDto> convertList(List<SpsAttr> spsAttrs) {
        if (CollectionUtils.isEmpty(spsAttrs)) {
            return null;
        }

        List<SpsAttrResponseDto> list = new ArrayList<SpsAttrResponseDto>(spsAttrs.size());
        for (SpsAttr spsAttr : spsAttrs) {
            list.add(convert(spsAttr));
        }
        return list;
    } 

}
