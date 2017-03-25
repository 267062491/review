package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.TargereasonRequest;
import com.letv.test.tbtSps.rest.response.TargereasonResponse;
import com.letv.test.tbtSps.rest.response.dto.Targereason;
import com.letv.test.tbtSps.rest.Urls;

/**
 * TargereasonResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class TargereasonResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetTargereason() {
        String url= Urls.API_DOMAIN + "services/targereason/getTargereason";

        TargereasonRequest request = new TargereasonRequest();
        request.setId(1l);
        TargereasonResponse response = super.getRestTemplate().postForObject(url, request, TargereasonResponse.class);
        Assert.notNull(response);
        Targereason targereason = super.getResult(response);
        Assert.notNull(targereason);
    }
}
