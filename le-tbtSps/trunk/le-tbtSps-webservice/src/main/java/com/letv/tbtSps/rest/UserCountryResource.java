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
import com.letv.tbtSps.domain.UserCountry;
import com.letv.tbtSps.sdk.api.request.UserCountryRequest;
import com.letv.tbtSps.sdk.api.response.dto.UserCountryResponseDto;
import com.letv.tbtSps.service.UserCountryService;

/**
 * 用户通报成员关联表REST服务：提供有关用户通报成员关联表的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class UserCountryResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserCountryService userCountryService; 

    /**
     * 查询用户通报成员关联表信息
     * 
     * @param request
     *            用户通报成员关联表请求参数
     * @return 用户通报成员关联表返回对象
     * 
     */
    @POST
    @Path("/userCountry/getUserCountry")
    public Wrapper<?> getUserCountry(UserCountryRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getUserCountry 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            UserCountry userCountry = userCountryService.getUserCountryById(request.getId());
            UserCountryResponseDto responseDto = convert(userCountry);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询用户通报成员关联表数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private UserCountryResponseDto convert(UserCountry userCountry) {
        if (null == userCountry) {
            return null;
        }

        UserCountryResponseDto userCountryResponseDto = new UserCountryResponseDto();
        BeanUtils.copyProperties(userCountry, userCountryResponseDto);
        return userCountryResponseDto;
    }

    // 数据转换
    private List<UserCountryResponseDto> convertList(List<UserCountry> userCountrys) {
        if (CollectionUtils.isEmpty(userCountrys)) {
            return null;
        }

        List<UserCountryResponseDto> list = new ArrayList<UserCountryResponseDto>(userCountrys.size());
        for (UserCountry userCountry : userCountrys) {
            list.add(convert(userCountry));
        }
        return list;
    } 

}
