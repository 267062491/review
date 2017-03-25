package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.UserCountryRequest;
import com.letv.test.tbtSps.rest.response.UserCountryResponse;
import com.letv.test.tbtSps.rest.response.dto.UserCountry;
import com.letv.test.tbtSps.rest.Urls;

/**
 * UserCountryResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class UserCountryResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetUserCountry() {
        String url= Urls.API_DOMAIN + "services/userCountry/getUserCountry";

        UserCountryRequest request = new UserCountryRequest();
        request.setId(1l);
        UserCountryResponse response = super.getRestTemplate().postForObject(url, request, UserCountryResponse.class);
        Assert.notNull(response);
        UserCountry userCountry = super.getResult(response);
        Assert.notNull(userCountry);
    }
}
