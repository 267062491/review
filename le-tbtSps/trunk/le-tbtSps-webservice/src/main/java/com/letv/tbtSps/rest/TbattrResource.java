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
import com.letv.tbtSps.domain.Tbattr;
import com.letv.tbtSps.sdk.api.request.TbattrRequest;
import com.letv.tbtSps.sdk.api.response.dto.TbattrResponseDto;
import com.letv.tbtSps.service.TbattrService;

/**
 * tbt信息表对应的附件REST服务：提供有关tbt信息表对应的附件的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class TbattrResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private TbattrService tbattrService; 

    /**
     * 查询tbt信息表对应的附件信息
     * 
     * @param request
     *            tbt信息表对应的附件请求参数
     * @return tbt信息表对应的附件返回对象
     * 
     */
    @POST
    @Path("/tbattr/getTbattr")
    public Wrapper<?> getTbattr(TbattrRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getTbattr 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            Tbattr tbattr = tbattrService.getTbattrById(request.getId());
            TbattrResponseDto responseDto = convert(tbattr);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt信息表对应的附件数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private TbattrResponseDto convert(Tbattr tbattr) {
        if (null == tbattr) {
            return null;
        }

        TbattrResponseDto tbattrResponseDto = new TbattrResponseDto();
        BeanUtils.copyProperties(tbattr, tbattrResponseDto);
        return tbattrResponseDto;
    }

    // 数据转换
    private List<TbattrResponseDto> convertList(List<Tbattr> tbattrs) {
        if (CollectionUtils.isEmpty(tbattrs)) {
            return null;
        }

        List<TbattrResponseDto> list = new ArrayList<TbattrResponseDto>(tbattrs.size());
        for (Tbattr tbattr : tbattrs) {
            list.add(convert(tbattr));
        }
        return list;
    } 

}
