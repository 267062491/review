package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.InternationalStandardRequest;
import com.letv.test.tbtSps.rest.response.InternationalStandardResponse;
import com.letv.test.tbtSps.rest.response.dto.InternationalStandard;
import com.letv.test.tbtSps.rest.Urls;

/**
 * InternationalStandardResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class InternationalStandardResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetInternationalStandard() {
        String url= Urls.API_DOMAIN + "services/internationalStandard/getInternationalStandard";

        InternationalStandardRequest request = new InternationalStandardRequest();
        request.setId(1l);
        InternationalStandardResponse response = super.getRestTemplate().postForObject(url, request, InternationalStandardResponse.class);
        Assert.notNull(response);
        InternationalStandard internationalStandard = super.getResult(response);
        Assert.notNull(internationalStandard);
    }
}
