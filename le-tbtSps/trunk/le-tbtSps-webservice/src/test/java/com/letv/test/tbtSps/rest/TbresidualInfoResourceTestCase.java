package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.TbresidualInfoRequest;
import com.letv.test.tbtSps.rest.response.TbresidualInfoResponse;
import com.letv.test.tbtSps.rest.response.dto.TbresidualInfo;
import com.letv.test.tbtSps.rest.Urls;

/**
 * TbresidualInfoResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class TbresidualInfoResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetTbresidualInfo() {
        String url= Urls.API_DOMAIN + "services/tbresidualInfo/getTbresidualInfo";

        TbresidualInfoRequest request = new TbresidualInfoRequest();
        request.setId(1l);
        TbresidualInfoResponse response = super.getRestTemplate().postForObject(url, request, TbresidualInfoResponse.class);
        Assert.notNull(response);
        TbresidualInfo tbresidualInfo = super.getResult(response);
        Assert.notNull(tbresidualInfo);
    }
}
