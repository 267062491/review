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
import com.letv.tbtSps.domain.NotificationCategory;
import com.letv.tbtSps.sdk.api.request.NotificationCategoryRequest;
import com.letv.tbtSps.sdk.api.response.dto.NotificationCategoryResponseDto;
import com.letv.tbtSps.service.NotificationCategoryService;

/**
 * 通报内部分类REST服务：提供有关通报内部分类的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class NotificationCategoryResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private NotificationCategoryService notificationCategoryService; 

    /**
     * 查询通报内部分类信息
     * 
     * @param request
     *            通报内部分类请求参数
     * @return 通报内部分类返回对象
     * 
     */
    @POST
    @Path("/notificationCategory/getNotificationCategory")
    public Wrapper<?> getNotificationCategory(NotificationCategoryRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getNotificationCategory 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            NotificationCategory notificationCategory = notificationCategoryService.getNotificationCategoryById(request.getId());
            NotificationCategoryResponseDto responseDto = convert(notificationCategory);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询通报内部分类数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private NotificationCategoryResponseDto convert(NotificationCategory notificationCategory) {
        if (null == notificationCategory) {
            return null;
        }

        NotificationCategoryResponseDto notificationCategoryResponseDto = new NotificationCategoryResponseDto();
        BeanUtils.copyProperties(notificationCategory, notificationCategoryResponseDto);
        return notificationCategoryResponseDto;
    }

    // 数据转换
    private List<NotificationCategoryResponseDto> convertList(List<NotificationCategory> notificationCategorys) {
        if (CollectionUtils.isEmpty(notificationCategorys)) {
            return null;
        }

        List<NotificationCategoryResponseDto> list = new ArrayList<NotificationCategoryResponseDto>(notificationCategorys.size());
        for (NotificationCategory notificationCategory : notificationCategorys) {
            list.add(convert(notificationCategory));
        }
        return list;
    } 

}
