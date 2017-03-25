package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RoleRequest;
import com.letv.test.tbtSps.rest.response.RoleResponse;
import com.letv.test.tbtSps.rest.response.dto.Role;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RoleResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RoleResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRole() {
        String url= Urls.API_DOMAIN + "services/role/getRole";

        RoleRequest request = new RoleRequest();
        request.setId(1l);
        RoleResponse response = super.getRestTemplate().postForObject(url, request, RoleResponse.class);
        Assert.notNull(response);
        Role role = super.getResult(response);
        Assert.notNull(role);
    }
}
