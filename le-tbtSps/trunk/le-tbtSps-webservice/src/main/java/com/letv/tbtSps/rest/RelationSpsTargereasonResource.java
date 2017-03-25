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
import com.letv.tbtSps.domain.RelationSpsTargereason;
import com.letv.tbtSps.sdk.api.request.RelationSpsTargereasonRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationSpsTargereasonResponseDto;
import com.letv.tbtSps.service.RelationSpsTargereasonService;

/**
 * sps信息表-目标理由关联表REST服务：提供有关sps信息表-目标理由关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationSpsTargereasonResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationSpsTargereasonService relationSpsTargereasonService; 

    /**
     * 查询sps信息表-目标理由关联表信息
     * 
     * @param request
     *            sps信息表-目标理由关联表请求参数
     * @return sps信息表-目标理由关联表返回对象
     * 
     */
    @POST
    @Path("/relationSpsTargereason/getRelationSpsTargereason")
    public Wrapper<?> getRelationSpsTargereason(RelationSpsTargereasonRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationSpsTargereason 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationSpsTargereason relationSpsTargereason = relationSpsTargereasonService.getRelationSpsTargereasonById(request.getId());
            RelationSpsTargereasonResponseDto responseDto = convert(relationSpsTargereason);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表-目标理由关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationSpsTargereasonResponseDto convert(RelationSpsTargereason relationSpsTargereason) {
        if (null == relationSpsTargereason) {
            return null;
        }

        RelationSpsTargereasonResponseDto relationSpsTargereasonResponseDto = new RelationSpsTargereasonResponseDto();
        BeanUtils.copyProperties(relationSpsTargereason, relationSpsTargereasonResponseDto);
        return relationSpsTargereasonResponseDto;
    }

    // 数据转换
    private List<RelationSpsTargereasonResponseDto> convertList(List<RelationSpsTargereason> relationSpsTargereasons) {
        if (CollectionUtils.isEmpty(relationSpsTargereasons)) {
            return null;
        }

        List<RelationSpsTargereasonResponseDto> list = new ArrayList<RelationSpsTargereasonResponseDto>(relationSpsTargereasons.size());
        for (RelationSpsTargereason relationSpsTargereason : relationSpsTargereasons) {
            list.add(convert(relationSpsTargereason));
        }
        return list;
    } 

}
