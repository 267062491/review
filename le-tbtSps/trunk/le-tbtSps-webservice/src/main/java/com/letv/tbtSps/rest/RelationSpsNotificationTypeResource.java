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
import com.letv.tbtSps.domain.RelationSpsNotificationType;
import com.letv.tbtSps.sdk.api.request.RelationSpsNotificationTypeRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationSpsNotificationTypeResponseDto;
import com.letv.tbtSps.service.RelationSpsNotificationTypeService;

/**
 * sps信息表-通报类型关联表REST服务：提供有关sps信息表-通报类型关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationSpsNotificationTypeResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationSpsNotificationTypeService relationSpsNotificationTypeService; 

    /**
     * 查询sps信息表-通报类型关联表信息
     * 
     * @param request
     *            sps信息表-通报类型关联表请求参数
     * @return sps信息表-通报类型关联表返回对象
     * 
     */
    @POST
    @Path("/relationSpsNotificationType/getRelationSpsNotificationType")
    public Wrapper<?> getRelationSpsNotificationType(RelationSpsNotificationTypeRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationSpsNotificationType 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationSpsNotificationType relationSpsNotificationType = relationSpsNotificationTypeService.getRelationSpsNotificationTypeById(request.getId());
            RelationSpsNotificationTypeResponseDto responseDto = convert(relationSpsNotificationType);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表-通报类型关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationSpsNotificationTypeResponseDto convert(RelationSpsNotificationType relationSpsNotificationType) {
        if (null == relationSpsNotificationType) {
            return null;
        }

        RelationSpsNotificationTypeResponseDto relationSpsNotificationTypeResponseDto = new RelationSpsNotificationTypeResponseDto();
        BeanUtils.copyProperties(relationSpsNotificationType, relationSpsNotificationTypeResponseDto);
        return relationSpsNotificationTypeResponseDto;
    }

    // 数据转换
    private List<RelationSpsNotificationTypeResponseDto> convertList(List<RelationSpsNotificationType> relationSpsNotificationTypes) {
        if (CollectionUtils.isEmpty(relationSpsNotificationTypes)) {
            return null;
        }

        List<RelationSpsNotificationTypeResponseDto> list = new ArrayList<RelationSpsNotificationTypeResponseDto>(relationSpsNotificationTypes.size());
        for (RelationSpsNotificationType relationSpsNotificationType : relationSpsNotificationTypes) {
            list.add(convert(relationSpsNotificationType));
        }
        return list;
    } 

}
