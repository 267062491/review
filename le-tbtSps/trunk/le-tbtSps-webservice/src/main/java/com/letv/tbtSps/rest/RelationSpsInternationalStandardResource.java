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
import com.letv.tbtSps.domain.RelationSpsInternationalStandard;
import com.letv.tbtSps.sdk.api.request.RelationSpsInternationalStandardRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationSpsInternationalStandardResponseDto;
import com.letv.tbtSps.service.RelationSpsInternationalStandardService;

/**
 * sps信息表-国际标准关联表REST服务：提供有关sps信息表-国际标准关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationSpsInternationalStandardResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationSpsInternationalStandardService relationSpsInternationalStandardService; 

    /**
     * 查询sps信息表-国际标准关联表信息
     * 
     * @param request
     *            sps信息表-国际标准关联表请求参数
     * @return sps信息表-国际标准关联表返回对象
     * 
     */
    @POST
    @Path("/relationSpsInternationalStandard/getRelationSpsInternationalStandard")
    public Wrapper<?> getRelationSpsInternationalStandard(RelationSpsInternationalStandardRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationSpsInternationalStandard 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationSpsInternationalStandard relationSpsInternationalStandard = relationSpsInternationalStandardService.getRelationSpsInternationalStandardById(request.getId());
            RelationSpsInternationalStandardResponseDto responseDto = convert(relationSpsInternationalStandard);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表-国际标准关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationSpsInternationalStandardResponseDto convert(RelationSpsInternationalStandard relationSpsInternationalStandard) {
        if (null == relationSpsInternationalStandard) {
            return null;
        }

        RelationSpsInternationalStandardResponseDto relationSpsInternationalStandardResponseDto = new RelationSpsInternationalStandardResponseDto();
        BeanUtils.copyProperties(relationSpsInternationalStandard, relationSpsInternationalStandardResponseDto);
        return relationSpsInternationalStandardResponseDto;
    }

    // 数据转换
    private List<RelationSpsInternationalStandardResponseDto> convertList(List<RelationSpsInternationalStandard> relationSpsInternationalStandards) {
        if (CollectionUtils.isEmpty(relationSpsInternationalStandards)) {
            return null;
        }

        List<RelationSpsInternationalStandardResponseDto> list = new ArrayList<RelationSpsInternationalStandardResponseDto>(relationSpsInternationalStandards.size());
        for (RelationSpsInternationalStandard relationSpsInternationalStandard : relationSpsInternationalStandards) {
            list.add(convert(relationSpsInternationalStandard));
        }
        return list;
    } 

}
