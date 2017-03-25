package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RoleResourceRequest;
import com.letv.test.tbtSps.rest.response.RoleResourceResponse;
import com.letv.test.tbtSps.rest.response.dto.RoleResource;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RoleResourceResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RoleResourceResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRoleResource() {
        String url= Urls.API_DOMAIN + "services/roleResource/getRoleResource";

        RoleResourceRequest request = new RoleResourceRequest();
        request.setId(1l);
        RoleResourceResponse response = super.getRestTemplate().postForObject(url, request, RoleResourceResponse.class);
        Assert.notNull(response);
        RoleResource roleResource = super.getResult(response);
        Assert.notNull(roleResource);
    }
}
