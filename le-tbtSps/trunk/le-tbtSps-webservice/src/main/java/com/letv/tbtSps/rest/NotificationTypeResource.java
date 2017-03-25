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
import com.letv.tbtSps.domain.NotificationType;
import com.letv.tbtSps.sdk.api.request.NotificationTypeRequest;
import com.letv.tbtSps.sdk.api.response.dto.NotificationTypeResponseDto;
import com.letv.tbtSps.service.NotificationTypeService;

/**
 * 通报类型REST服务：提供有关通报类型的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class NotificationTypeResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private NotificationTypeService notificationTypeService; 

    /**
     * 查询通报类型信息
     * 
     * @param request
     *            通报类型请求参数
     * @return 通报类型返回对象
     * 
     */
    @POST
    @Path("/notificationType/getNotificationType")
    public Wrapper<?> getNotificationType(NotificationTypeRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getNotificationType 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            NotificationType notificationType = notificationTypeService.getNotificationTypeById(request.getId());
            NotificationTypeResponseDto responseDto = convert(notificationType);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询通报类型数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private NotificationTypeResponseDto convert(NotificationType notificationType) {
        if (null == notificationType) {
            return null;
        }

        NotificationTypeResponseDto notificationTypeResponseDto = new NotificationTypeResponseDto();
        BeanUtils.copyProperties(notificationType, notificationTypeResponseDto);
        return notificationTypeResponseDto;
    }

    // 数据转换
    private List<NotificationTypeResponseDto> convertList(List<NotificationType> notificationTypes) {
        if (CollectionUtils.isEmpty(notificationTypes)) {
            return null;
        }

        List<NotificationTypeResponseDto> list = new ArrayList<NotificationTypeResponseDto>(notificationTypes.size());
        for (NotificationType notificationType : notificationTypes) {
            list.add(convert(notificationType));
        }
        return list;
    } 

}
