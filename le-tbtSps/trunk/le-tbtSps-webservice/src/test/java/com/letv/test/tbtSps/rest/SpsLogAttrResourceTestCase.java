package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.SpsLogAttrRequest;
import com.letv.test.tbtSps.rest.response.SpsLogAttrResponse;
import com.letv.test.tbtSps.rest.response.dto.SpsLogAttr;
import com.letv.test.tbtSps.rest.Urls;

/**
 * SpsLogAttrResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class SpsLogAttrResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetSpsLogAttr() {
        String url= Urls.API_DOMAIN + "services/spsLogAttr/getSpsLogAttr";

        SpsLogAttrRequest request = new SpsLogAttrRequest();
        request.setId(1l);
        SpsLogAttrResponse response = super.getRestTemplate().postForObject(url, request, SpsLogAttrResponse.class);
        Assert.notNull(response);
        SpsLogAttr spsLogAttr = super.getResult(response);
        Assert.notNull(spsLogAttr);
    }
}
