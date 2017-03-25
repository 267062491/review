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
import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.sdk.api.request.SpsLogAttrRequest;
import com.letv.tbtSps.sdk.api.response.dto.SpsLogAttrResponseDto;
import com.letv.tbtSps.service.SpsLogAttrService;

/**
 * sps评审信息表对应的附件REST服务：提供有关sps评审信息表对应的附件的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SpsLogAttrResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SpsLogAttrService spsLogAttrService; 

    /**
     * 查询sps评审信息表对应的附件信息
     * 
     * @param request
     *            sps评审信息表对应的附件请求参数
     * @return sps评审信息表对应的附件返回对象
     * 
     */
    @POST
    @Path("/spsLogAttr/getSpsLogAttr")
    public Wrapper<?> getSpsLogAttr(SpsLogAttrRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getSpsLogAttr 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            SpsLogAttr spsLogAttr = spsLogAttrService.getSpsLogAttrById(request.getId());
            SpsLogAttrResponseDto responseDto = convert(spsLogAttr);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps评审信息表对应的附件数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private SpsLogAttrResponseDto convert(SpsLogAttr spsLogAttr) {
        if (null == spsLogAttr) {
            return null;
        }

        SpsLogAttrResponseDto spsLogAttrResponseDto = new SpsLogAttrResponseDto();
        BeanUtils.copyProperties(spsLogAttr, spsLogAttrResponseDto);
        return spsLogAttrResponseDto;
    }

    // 数据转换
    private List<SpsLogAttrResponseDto> convertList(List<SpsLogAttr> spsLogAttrs) {
        if (CollectionUtils.isEmpty(spsLogAttrs)) {
            return null;
        }

        List<SpsLogAttrResponseDto> list = new ArrayList<SpsLogAttrResponseDto>(spsLogAttrs.size());
        for (SpsLogAttr spsLogAttr : spsLogAttrs) {
            list.add(convert(spsLogAttr));
        }
        return list;
    } 

}
