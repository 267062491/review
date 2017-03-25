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
import com.letv.tbtSps.domain.Country;
import com.letv.tbtSps.sdk.api.request.CountryRequest;
import com.letv.tbtSps.sdk.api.response.dto.CountryResponseDto;
import com.letv.tbtSps.service.CountryService;

/**
 * 通报成员REST服务：提供有关通报成员的接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Component
@Path("services")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class CountryResource {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private CountryService countryService; 

    /**
     * 查询通报成员信息
     * 
     * @param request
     *            通报成员请求参数
     * @return 通报成员返回对象
     * 
     */
    @POST
    @Path("/country/getCountry")
    public Wrapper<?> getCountry(CountryRequest request) {
        if (null == request || null == request.getId()) {
            this.logger.error("getCountry 传入参数有误");
            return WrapMapper.illegalArgument();
        }

        try {
            Country country = countryService.getCountryById(request.getId());
            CountryResponseDto responseDto = convert(country);
            return WrapMapper.ok().result(responseDto);
        } catch (Exception e) {
            this.logger.error("查询通报成员数据异常", e);
            return WrapMapper.error();
        }
    } 

    // 数据转换
    private CountryResponseDto convert(Country country) {
        if (null == country) {
            return null;
        }

        CountryResponseDto countryResponseDto = new CountryResponseDto();
        BeanUtils.copyProperties(country, countryResponseDto);
        return countryResponseDto;
    }

    // 数据转换
    private List<CountryResponseDto> convertList(List<Country> countrys) {
        if (CollectionUtils.isEmpty(countrys)) {
            return null;
        }

        List<CountryResponseDto> list = new ArrayList<CountryResponseDto>(countrys.size());
        for (Country country : countrys) {
            list.add(convert(country));
        }
        return list;
    } 

}
