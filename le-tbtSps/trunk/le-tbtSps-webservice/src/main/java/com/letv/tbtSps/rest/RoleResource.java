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
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.sdk.api.request.RoleRequest;
import com.letv.tbtSps.sdk.api.response.dto.RoleResponseDto;
import com.letv.tbtSps.service.RoleService;

/**
 * 角色表REST服务：提供有关角色表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class RoleResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RoleService roleService; 

    /**
     * 查询角色表信息
     * 
     * @param request
     *            角色表请求参数
     * @return 角色表返回对象
     * 
     */
    @POST
    @Path("/role/getRole")
    public Wrapper<?> getRole(RoleRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getRole 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            Role role = roleService.getRoleById(request.getId());
            RoleResponseDto responseDto = convert(role);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询角色表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private RoleResponseDto convert(Role role) {
        if (null == role) {
            return null;
        }

        RoleResponseDto roleResponseDto = new RoleResponseDto();
        BeanUtils.copyProperties(role, roleResponseDto);
        return roleResponseDto;
    }

    // 数据转换
    private List<RoleResponseDto> convertList(List<Role> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }

        List<RoleResponseDto> list = new ArrayList<RoleResponseDto>(roles.size());
        for (Role role : roles) {
            list.add(convert(role));
        }
        return list;
    } 

}
