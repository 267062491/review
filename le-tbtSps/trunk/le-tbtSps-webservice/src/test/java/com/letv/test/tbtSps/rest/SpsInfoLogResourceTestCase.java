package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.SpsInfoLogRequest;
import com.letv.test.tbtSps.rest.response.SpsInfoLogResponse;
import com.letv.test.tbtSps.rest.response.dto.SpsInfoLog;
import com.letv.test.tbtSps.rest.Urls;

/**
 * SpsInfoLogResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class SpsInfoLogResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetSpsInfoLog() {
        String url= Urls.API_DOMAIN + "services/spsInfoLog/getSpsInfoLog";

        SpsInfoLogRequest request = new SpsInfoLogRequest();
        request.setId(1l);
        SpsInfoLogResponse response = super.getRestTemplate().postForObject(url, request, SpsInfoLogResponse.class);
        Assert.notNull(response);
        SpsInfoLog spsInfoLog = super.getResult(response);
        Assert.notNull(spsInfoLog);
    }
}
