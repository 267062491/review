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
import com.letv.tbtSps.domain.RelationTbnotificationCategory;
import com.letv.tbtSps.sdk.api.request.RelationTbnotificationCategoryRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationTbnotificationCategoryResponseDto;
import com.letv.tbtSps.service.RelationTbnotificationCategoryService;

/**
 * tbt信息表通报分类关联表REST服务：提供有关tbt信息表通报分类关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationTbnotificationCategoryResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationTbnotificationCategoryService relationTbnotificationCategoryService; 

    /**
     * 查询tbt信息表通报分类关联表信息
     * 
     * @param request
     *            tbt信息表通报分类关联表请求参数
     * @return tbt信息表通报分类关联表返回对象
     * 
     */
    @POST
    @Path("/relationTbnotificationCategory/getRelationTbnotificationCategory")
    public Wrapper<?> getRelationTbnotificationCategory(RelationTbnotificationCategoryRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationTbnotificationCategory 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationTbnotificationCategory relationTbnotificationCategory = relationTbnotificationCategoryService.getRelationTbnotificationCategoryById(request.getId());
            RelationTbnotificationCategoryResponseDto responseDto = convert(relationTbnotificationCategory);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt信息表通报分类关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationTbnotificationCategoryResponseDto convert(RelationTbnotificationCategory relationTbnotificationCategory) {
        if (null == relationTbnotificationCategory) {
            return null;
        }

        RelationTbnotificationCategoryResponseDto relationTbnotificationCategoryResponseDto = new RelationTbnotificationCategoryResponseDto();
        BeanUtils.copyProperties(relationTbnotificationCategory, relationTbnotificationCategoryResponseDto);
        return relationTbnotificationCategoryResponseDto;
    }

    // 数据转换
    private List<RelationTbnotificationCategoryResponseDto> convertList(List<RelationTbnotificationCategory> relationTbnotificationCategorys) {
        if (CollectionUtils.isEmpty(relationTbnotificationCategorys)) {
            return null;
        }

        List<RelationTbnotificationCategoryResponseDto> list = new ArrayList<RelationTbnotificationCategoryResponseDto>(relationTbnotificationCategorys.size());
        for (RelationTbnotificationCategory relationTbnotificationCategory : relationTbnotificationCategorys) {
            list.add(convert(relationTbnotificationCategory));
        }
        return list;
    } 

}
