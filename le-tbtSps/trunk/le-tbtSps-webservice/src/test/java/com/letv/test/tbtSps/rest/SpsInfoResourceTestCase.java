package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.SpsInfoRequest;
import com.letv.test.tbtSps.rest.response.SpsInfoResponse;
import com.letv.test.tbtSps.rest.response.dto.SpsInfo;
import com.letv.test.tbtSps.rest.Urls;

/**
 * SpsInfoResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class SpsInfoResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetSpsInfo() {
        String url= Urls.API_DOMAIN + "services/spsInfo/getSpsInfo";

        SpsInfoRequest request = new SpsInfoRequest();
        request.setId(1l);
        SpsInfoResponse response = super.getRestTemplate().postForObject(url, request, SpsInfoResponse.class);
        Assert.notNull(response);
        SpsInfo spsInfo = super.getResult(response);
        Assert.notNull(spsInfo);
    }
}
