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
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.sdk.api.request.ResourceRequest;
import com.letv.tbtSps.sdk.api.response.dto.ResourceResponseDto;
import com.letv.tbtSps.service.ResourceService;

/**
 * 资源表REST服务：提供有关资源表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ResourceResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ResourceService resourceService; 

    /**
     * 查询资源表信息
     * 
     * @param request
     *            资源表请求参数
     * @return 资源表返回对象
     * 
     */
    @POST
    @Path("/resource/getResource")
    public Wrapper<?> getResource(ResourceRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getResource 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            Resource resource = resourceService.getResourceById(request.getId());
            ResourceResponseDto responseDto = convert(resource);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询资源表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private ResourceResponseDto convert(Resource resource) {
        if (null == resource) {
            return null;
        }

        ResourceResponseDto resourceResponseDto = new ResourceResponseDto();
        BeanUtils.copyProperties(resource, resourceResponseDto);
        return resourceResponseDto;
    }

    // 数据转换
    private List<ResourceResponseDto> convertList(List<Resource> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            return null;
        }

        List<ResourceResponseDto> list = new ArrayList<ResourceResponseDto>(resources.size());
        for (Resource resource : resources) {
            list.add(convert(resource));
        }
        return list;
    } 

}
