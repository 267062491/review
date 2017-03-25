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
import com.letv.tbtSps.domain.Language;
import com.letv.tbtSps.sdk.api.request.LanguageRequest;
import com.letv.tbtSps.sdk.api.response.dto.LanguageResponseDto;
import com.letv.tbtSps.service.LanguageService;

/**
 * 原文语种REST服务：提供有关原文语种的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class LanguageResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private LanguageService languageService; 

    /**
     * 查询原文语种信息
     * 
     * @param request
     *            原文语种请求参数
     * @return 原文语种返回对象
     * 
     */
    @POST
    @Path("/language/getLanguage")
    public Wrapper<?> getLanguage(LanguageRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getLanguage 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            Language language = languageService.getLanguageById(request.getId());
            LanguageResponseDto responseDto = convert(language);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询原文语种数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private LanguageResponseDto convert(Language language) {
        if (null == language) {
            return null;
        }

        LanguageResponseDto languageResponseDto = new LanguageResponseDto();
        BeanUtils.copyProperties(language, languageResponseDto);
        return languageResponseDto;
    }

    // 数据转换
    private List<LanguageResponseDto> convertList(List<Language> languages) {
        if (CollectionUtils.isEmpty(languages)) {
            return null;
        }

        List<LanguageResponseDto> list = new ArrayList<LanguageResponseDto>(languages.size());
        for (Language language : languages) {
            list.add(convert(language));
        }
        return list;
    } 

}
