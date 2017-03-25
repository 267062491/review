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
import com.letv.tbtSps.domain.RelationSpsLanguage;
import com.letv.tbtSps.sdk.api.request.RelationSpsLanguageRequest;
import com.letv.tbtSps.sdk.api.response.dto.RelationSpsLanguageResponseDto;
import com.letv.tbtSps.service.RelationSpsLanguageService;

/**
 * sps信息表-原文语种关联表REST服务：提供有关sps信息表-原文语种关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RelationSpsLanguageResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RelationSpsLanguageService relationSpsLanguageService; 

    /**
     * 查询sps信息表-原文语种关联表信息
     * 
     * @param request
     *            sps信息表-原文语种关联表请求参数
     * @return sps信息表-原文语种关联表返回对象
     * 
     */
    @POST
    @Path("/relationSpsLanguage/getRelationSpsLanguage")
    public Wrapper<?> getRelationSpsLanguage(RelationSpsLanguageRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRelationSpsLanguage 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RelationSpsLanguage relationSpsLanguage = relationSpsLanguageService.getRelationSpsLanguageById(request.getId());
            RelationSpsLanguageResponseDto responseDto = convert(relationSpsLanguage);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询sps信息表-原文语种关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RelationSpsLanguageResponseDto convert(RelationSpsLanguage relationSpsLanguage) {
        if (null == relationSpsLanguage) {
            return null;
        }

        RelationSpsLanguageResponseDto relationSpsLanguageResponseDto = new RelationSpsLanguageResponseDto();
        BeanUtils.copyProperties(relationSpsLanguage, relationSpsLanguageResponseDto);
        return relationSpsLanguageResponseDto;
    }

    // 数据转换
    private List<RelationSpsLanguageResponseDto> convertList(List<RelationSpsLanguage> relationSpsLanguages) {
        if (CollectionUtils.isEmpty(relationSpsLanguages)) {
            return null;
        }

        List<RelationSpsLanguageResponseDto> list = new ArrayList<RelationSpsLanguageResponseDto>(relationSpsLanguages.size());
        for (RelationSpsLanguage relationSpsLanguage : relationSpsLanguages) {
            list.add(convert(relationSpsLanguage));
        }
        return list;
    } 

}
