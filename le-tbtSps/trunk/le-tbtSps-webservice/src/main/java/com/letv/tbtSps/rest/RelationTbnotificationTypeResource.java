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
import com.letv.tbtSps.domain.RelationTbnotificationType;
import com.letv.tbtSps.sdk.api.request.RelationTbnotificationTypeRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationTbnotificationTypeResponseDto;
import com.letv.tbtSps.service.RelationTbnotificationTypeService;

/**
 * tbt信息表-通报类型关联表REST服务：提供有关tbt信息表-通报类型关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationTbnotificationTypeResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationTbnotificationTypeService relationTbnotificationTypeService; 

    /**
     * 查询tbt信息表-通报类型关联表信息
     * 
     * @param request
     *            tbt信息表-通报类型关联表请求参数
     * @return tbt信息表-通报类型关联表返回对象
     * 
     */
    @POST
    @Path("/relationTbnotificationType/getRelationTbnotificationType")
    public Wrapper<?> getRelationTbnotificationType(RelationTbnotificationTypeRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationTbnotificationType 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationTbnotificationType relationTbnotificationType = relationTbnotificationTypeService.getRelationTbnotificationTypeById(request.getId());
            RelationTbnotificationTypeResponseDto responseDto = convert(relationTbnotificationType);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt信息表-通报类型关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationTbnotificationTypeResponseDto convert(RelationTbnotificationType relationTbnotificationType) {
        if (null == relationTbnotificationType) {
            return null;
        }

        RelationTbnotificationTypeResponseDto relationTbnotificationTypeResponseDto = new RelationTbnotificationTypeResponseDto();
        BeanUtils.copyProperties(relationTbnotificationType, relationTbnotificationTypeResponseDto);
        return relationTbnotificationTypeResponseDto;
    }

    // 数据转换
    private List<RelationTbnotificationTypeResponseDto> convertList(List<RelationTbnotificationType> relationTbnotificationTypes) {
        if (CollectionUtils.isEmpty(relationTbnotificationTypes)) {
            return null;
        }

        List<RelationTbnotificationTypeResponseDto> list = new ArrayList<RelationTbnotificationTypeResponseDto>(relationTbnotificationTypes.size());
        for (RelationTbnotificationType relationTbnotificationType : relationTbnotificationTypes) {
            list.add(convert(relationTbnotificationType));
        }
        return list;
    } 

}
