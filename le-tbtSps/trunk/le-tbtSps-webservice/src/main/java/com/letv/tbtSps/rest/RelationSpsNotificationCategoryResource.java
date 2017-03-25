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
import com.letv.tbtSps.domain.RelationSpsNotificationCategory;
import com.letv.tbtSps.sdk.api.request.RelationSpsNotificationCategoryRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationSpsNotificationCategoryResponseDto;
import com.letv.tbtSps.service.RelationSpsNotificationCategoryService;

/**
 * sps信息表通报分类关联表REST服务：提供有关sps信息表通报分类关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationSpsNotificationCategoryResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationSpsNotificationCategoryService relationSpsNotificationCategoryService; 

    /**
     * 查询sps信息表通报分类关联表信息
     * 
     * @param request
     *            sps信息表通报分类关联表请求参数
     * @return sps信息表通报分类关联表返回对象
     * 
     */
    @POST
    @Path("/relationSpsNotificationCategory/getRelationSpsNotificationCategory")
    public Wrapper<?> getRelationSpsNotificationCategory(RelationSpsNotificationCategoryRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationSpsNotificationCategory 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationSpsNotificationCategory relationSpsNotificationCategory = relationSpsNotificationCategoryService.getRelationSpsNotificationCategoryById(request.getId());
            RelationSpsNotificationCategoryResponseDto responseDto = convert(relationSpsNotificationCategory);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表通报分类关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationSpsNotificationCategoryResponseDto convert(RelationSpsNotificationCategory relationSpsNotificationCategory) {
        if (null == relationSpsNotificationCategory) {
            return null;
        }

        RelationSpsNotificationCategoryResponseDto relationSpsNotificationCategoryResponseDto = new RelationSpsNotificationCategoryResponseDto();
        BeanUtils.copyProperties(relationSpsNotificationCategory, relationSpsNotificationCategoryResponseDto);
        return relationSpsNotificationCategoryResponseDto;
    }

    // 数据转换
    private List<RelationSpsNotificationCategoryResponseDto> convertList(List<RelationSpsNotificationCategory> relationSpsNotificationCategorys) {
        if (CollectionUtils.isEmpty(relationSpsNotificationCategorys)) {
            return null;
        }

        List<RelationSpsNotificationCategoryResponseDto> list = new ArrayList<RelationSpsNotificationCategoryResponseDto>(relationSpsNotificationCategorys.size());
        for (RelationSpsNotificationCategory relationSpsNotificationCategory : relationSpsNotificationCategorys) {
            list.add(convert(relationSpsNotificationCategory));
        }
        return list;
    } 

}
