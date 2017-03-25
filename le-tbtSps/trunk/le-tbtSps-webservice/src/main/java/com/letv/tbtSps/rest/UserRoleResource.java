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
import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.sdk.api.request.UserRoleRequest;
import com.letv.tbtSps.sdk.api.response.dto.UserRoleResponseDto;
import com.letv.tbtSps.service.UserRoleService;

/**
 * 用户-角色REST服务：提供有关用户-角色的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class UserRoleResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserRoleService userRoleService; 

    /**
     * 查询用户-角色信息
     * 
     * @param request
     *            用户-角色请求参数
     * @return 用户-角色返回对象
     * 
     */
    @POST
    @Path("/userRole/getUserRole")
    public Wrapper<?> getUserRole(UserRoleRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getUserRole 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            UserRole userRole = userRoleService.getUserRoleById(request.getId());
            UserRoleResponseDto responseDto = convert(userRole);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询用户-角色数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private UserRoleResponseDto convert(UserRole userRole) {
        if (null == userRole) {
            return null;
        }

        UserRoleResponseDto userRoleResponseDto = new UserRoleResponseDto();
        BeanUtils.copyProperties(userRole, userRoleResponseDto);
        return userRoleResponseDto;
    }

    // 数据转换
    private List<UserRoleResponseDto> convertList(List<UserRole> userRoles) {
        if (CollectionUtils.isEmpty(userRoles)) {
            return null;
        }

        List<UserRoleResponseDto> list = new ArrayList<UserRoleResponseDto>(userRoles.size());
        for (UserRole userRole : userRoles) {
            list.add(convert(userRole));
        }
        return list;
    } 

}
