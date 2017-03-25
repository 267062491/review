package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.UserRoleRequest;
import com.letv.test.tbtSps.rest.response.UserRoleResponse;
import com.letv.test.tbtSps.rest.response.dto.UserRole;
import com.letv.test.tbtSps.rest.Urls;

/**
 * UserRoleResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class UserRoleResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetUserRole() {
        String url= Urls.API_DOMAIN + "services/userRole/getUserRole";

        UserRoleRequest request = new UserRoleRequest();
        request.setId(1l);
        UserRoleResponse response = super.getRestTemplate().postForObject(url, request, UserRoleResponse.class);
        Assert.notNull(response);
        UserRole userRole = super.getResult(response);
        Assert.notNull(userRole);
    }
}
