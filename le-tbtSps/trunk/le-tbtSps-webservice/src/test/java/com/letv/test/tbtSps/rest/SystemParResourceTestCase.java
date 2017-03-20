package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.SystemParRequest;
import com.letv.test.tbtSps.rest.response.SystemParResponse;
import com.letv.test.tbtSps.rest.response.dto.SystemPar;
import com.letv.test.tbtSps.rest.Urls;

/**
 * SystemParResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
public class SystemParResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetSystemPar() {
        String url= Urls.API_DOMAIN + "services/systemPar/getSystemPar";

        SystemParRequest request = new SystemParRequest();
        request.setId(1l);
        SystemParResponse response = super.getRestTemplate().postForObject(url, request, SystemParResponse.class);
        Assert.notNull(response);
        SystemPar systemPar = super.getResult(response);
        Assert.notNull(systemPar);
    }
}
