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
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.sdk.api.request.UserRequest;
import com.letv.tbtSps.sdk.api.response.dto.UserResponseDto;
import com.letv.tbtSps.service.UserService;

/**
 * 用户表REST服务：提供有关用户表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class UserResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserService userService; 

    /**
     * 查询用户表信息
     * 
     * @param request
     *            用户表请求参数
     * @return 用户表返回对象
     * 
     */
    @POST
    @Path("/user/getUser")
    public Wrapper<?> getUser(UserRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getUser 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
//            User user = userService.getUserById(request.getId());
//            UserResponseDto responseDto = convert(user);
//            return WrapMapper.ok().result(responseDto);
            return null ;
        } catch (Exception e) {
            this.logger.error("查询用户表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private UserResponseDto convert(User user) {
        if (null == user) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }

    // 数据转换
    private List<UserResponseDto> convertList(List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>(users.size());
        for (User user : users) {
            list.add(convert(user));
        }
        return list;
    } 

}
