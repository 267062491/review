package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.CountryRequest;
import com.letv.test.tbtSps.rest.response.CountryResponse;
import com.letv.test.tbtSps.rest.response.dto.Country;
import com.letv.test.tbtSps.rest.Urls;

/**
 * CountryResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class CountryResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetCountry() {
        String url= Urls.API_DOMAIN + "services/country/getCountry";

        CountryRequest request = new CountryRequest();
        request.setId(1l);
        CountryResponse response = super.getRestTemplate().postForObject(url, request, CountryResponse.class);
        Assert.notNull(response);
        Country country = super.getResult(response);
        Assert.notNull(country);
    }
}
