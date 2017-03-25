package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.SpsResidualInfoRequest;
import com.letv.test.tbtSps.rest.response.SpsResidualInfoResponse;
import com.letv.test.tbtSps.rest.response.dto.SpsResidualInfo;
import com.letv.test.tbtSps.rest.Urls;

/**
 * SpsResidualInfoResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class SpsResidualInfoResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetSpsResidualInfo() {
        String url= Urls.API_DOMAIN + "services/spsResidualInfo/getSpsResidualInfo";

        SpsResidualInfoRequest request = new SpsResidualInfoRequest();
        request.setId(1l);
        SpsResidualInfoResponse response = super.getRestTemplate().postForObject(url, request, SpsResidualInfoResponse.class);
        Assert.notNull(response);
        SpsResidualInfo spsResidualInfo = super.getResult(response);
        Assert.notNull(spsResidualInfo);
    }
}
