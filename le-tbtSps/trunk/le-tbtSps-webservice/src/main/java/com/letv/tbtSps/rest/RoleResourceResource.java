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
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.sdk.api.request.RoleResourceRequest;
import com.letv.tbtSps.sdk.api.response.dto.RoleResourceResponseDto;
import com.letv.tbtSps.service.RoleResourceService;

/**
 * 角色-资源REST服务：提供有关角色-资源的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RoleResourceResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RoleResourceService roleResourceService; 

    /**
     * 查询角色-资源信息
     * 
     * @param request
     *            角色-资源请求参数
     * @return 角色-资源返回对象
     * 
     */
    @POST
    @Path("/roleResource/getRoleResource")
    public Wrapper<?> getRoleResource(RoleResourceRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRoleResource 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            RoleResource roleResource = roleResourceService.getRoleResourceById(request.getId());
            RoleResourceResponseDto responseDto = convert(roleResource);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询角色-资源数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RoleResourceResponseDto convert(RoleResource roleResource) {
        if (null == roleResource) {
            return null;
        }

        RoleResourceResponseDto roleResourceResponseDto = new RoleResourceResponseDto();
        BeanUtils.copyProperties(roleResource, roleResourceResponseDto);
        return roleResourceResponseDto;
    }

    // 数据转换
    private List<RoleResourceResponseDto> convertList(List<RoleResource> roleResources) {
        if (CollectionUtils.isEmpty(roleResources)) {
            return null;
        }

        List<RoleResourceResponseDto> list = new ArrayList<RoleResourceResponseDto>(roleResources.size());
        for (RoleResource roleResource : roleResources) {
            list.add(convert(roleResource));
        }
        return list;
    } 

}
