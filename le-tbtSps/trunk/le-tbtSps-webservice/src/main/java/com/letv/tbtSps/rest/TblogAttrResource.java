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
import com.letv.tbtSps.domain.TblogAttr;
import com.letv.tbtSps.sdk.api.request.TblogAttrRequest;
import com.letv.tbtSps.sdk.api.response.dto.TblogAttrResponseDto;
import com.letv.tbtSps.service.TblogAttrService;

/**
 * tbt评审信息表对应的附件REST服务：提供有关tbt评审信息表对应的附件的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class TblogAttrResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private TblogAttrService tblogAttrService; 

    /**
     * 查询tbt评审信息表对应的附件信息
     * 
     * @param request
     *            tbt评审信息表对应的附件请求参数
     * @return tbt评审信息表对应的附件返回对象
     * 
     */
    @POST
    @Path("/tblogAttr/getTblogAttr")
    public Wrapper<?> getTblogAttr(TblogAttrRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getTblogAttr 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            TblogAttr tblogAttr = tblogAttrService.getTblogAttrById(request.getId());
            TblogAttrResponseDto responseDto = convert(tblogAttr);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt评审信息表对应的附件数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private TblogAttrResponseDto convert(TblogAttr tblogAttr) {
        if (null == tblogAttr) {
            return null;
        }

        TblogAttrResponseDto tblogAttrResponseDto = new TblogAttrResponseDto();
        BeanUtils.copyProperties(tblogAttr, tblogAttrResponseDto);
        return tblogAttrResponseDto;
    }

    // 数据转换
    private List<TblogAttrResponseDto> convertList(List<TblogAttr> tblogAttrs) {
        if (CollectionUtils.isEmpty(tblogAttrs)) {
            return null;
        }

        List<TblogAttrResponseDto> list = new ArrayList<TblogAttrResponseDto>(tblogAttrs.size());
        for (TblogAttr tblogAttr : tblogAttrs) {
            list.add(convert(tblogAttr));
        }
        return list;
    } 

}
