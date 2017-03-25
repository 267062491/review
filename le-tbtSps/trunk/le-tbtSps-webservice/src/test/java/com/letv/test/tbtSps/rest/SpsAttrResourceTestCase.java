package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.SpsAttrRequest;
import com.letv.test.tbtSps.rest.response.SpsAttrResponse;
import com.letv.test.tbtSps.rest.response.dto.SpsAttr;
import com.letv.test.tbtSps.rest.Urls;

/**
 * SpsAttrResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class SpsAttrResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetSpsAttr() {
        String url= Urls.API_DOMAIN + "services/spsAttr/getSpsAttr";

        SpsAttrRequest request = new SpsAttrRequest();
        request.setId(1l);
        SpsAttrResponse response = super.getRestTemplate().postForObject(url, request, SpsAttrResponse.class);
        Assert.notNull(response);
        SpsAttr spsAttr = super.getResult(response);
        Assert.notNull(spsAttr);
    }
}
