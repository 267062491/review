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
import com.letv.tbtSps.domain.RelationTblanguage;
import com.letv.tbtSps.sdk.api.request.RelationTblanguageRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationTblanguageResponseDto;
import com.letv.tbtSps.service.RelationTblanguageService;

/**
 * tbt信息表-原文语种关联表REST服务：提供有关tbt信息表-原文语种关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationTblanguageResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationTblanguageService relationTblanguageService; 

    /**
     * 查询tbt信息表-原文语种关联表信息
     * 
     * @param request
     *            tbt信息表-原文语种关联表请求参数
     * @return tbt信息表-原文语种关联表返回对象
     * 
     */
    @POST
    @Path("/relationTblanguage/getRelationTblanguage")
    public Wrapper<?> getRelationTblanguage(RelationTblanguageRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationTblanguage 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationTblanguage relationTblanguage = relationTblanguageService.getRelationTblanguageById(request.getId());
            RelationTblanguageResponseDto responseDto = convert(relationTblanguage);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询tbt信息表-原文语种关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationTblanguageResponseDto convert(RelationTblanguage relationTblanguage) {
        if (null == relationTblanguage) {
            return null;
        }

        RelationTblanguageResponseDto relationTblanguageResponseDto = new RelationTblanguageResponseDto();
        BeanUtils.copyProperties(relationTblanguage, relationTblanguageResponseDto);
        return relationTblanguageResponseDto;
    }

    // 数据转换
    private List<RelationTblanguageResponseDto> convertList(List<RelationTblanguage> relationTblanguages) {
        if (CollectionUtils.isEmpty(relationTblanguages)) {
            return null;
        }

        List<RelationTblanguageResponseDto> list = new ArrayList<RelationTblanguageResponseDto>(relationTblanguages.size());
        for (RelationTblanguage relationTblanguage : relationTblanguages) {
            list.add(convert(relationTblanguage));
        }
        return list;
    } 

}
