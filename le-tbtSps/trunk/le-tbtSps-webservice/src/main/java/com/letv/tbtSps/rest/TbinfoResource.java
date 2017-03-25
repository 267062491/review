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
import com.letv.tbtSps.domain.Tbinfo;
import com.letv.tbtSps.sdk.api.request.TbinfoRequest;
import com.letv.tbtSps.sdk.api.response.dto.TbinfoResponseDto;
import com.letv.tbtSps.service.TbinfoService;

/**
 * tbt信息表REST服务：提供有关tbt信息表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class TbinfoResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private TbinfoService tbinfoService; 

    /**
     * 查询tbt信息表信息
     * 
     * @param request
     *            tbt信息表请求参数
     * @return tbt信息表返回对象
     * 
     */
    @POST
    @Path("/tbinfo/getTbinfo")
    public Wrapper<?> getTbinfo(TbinfoRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getTbinfo 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            Tbinfo tbinfo = tbinfoService.getTbinfoById(request.getId());
            TbinfoResponseDto responseDto = convert(tbinfo);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt信息表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private TbinfoResponseDto convert(Tbinfo tbinfo) {
        if (null == tbinfo) {
            return null;
        }

        TbinfoResponseDto tbinfoResponseDto = new TbinfoResponseDto();
        BeanUtils.copyProperties(tbinfo, tbinfoResponseDto);
        return tbinfoResponseDto;
    }

    // 数据转换
    private List<TbinfoResponseDto> convertList(List<Tbinfo> tbinfos) {
        if (CollectionUtils.isEmpty(tbinfos)) {
            return null;
        }

        List<TbinfoResponseDto> list = new ArrayList<TbinfoResponseDto>(tbinfos.size());
        for (Tbinfo tbinfo : tbinfos) {
            list.add(convert(tbinfo));
        }
        return list;
    } 

}
