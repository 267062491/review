package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.ResourceRequest;
import com.letv.test.tbtSps.rest.response.ResourceResponse;
import com.letv.test.tbtSps.rest.response.dto.Resource;
import com.letv.test.tbtSps.rest.Urls;

/**
 * ResourceResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class ResourceResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetResource() {
        String url= Urls.API_DOMAIN + "services/resource/getResource";

        ResourceRequest request = new ResourceRequest();
        request.setId(1l);
        ResourceResponse response = super.getRestTemplate().postForObject(url, request, ResourceResponse.class);
        Assert.notNull(response);
        Resource resource = super.getResult(response);
        Assert.notNull(resource);
    }
}
