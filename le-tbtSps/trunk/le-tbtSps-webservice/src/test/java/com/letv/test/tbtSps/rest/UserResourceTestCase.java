package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.UserRequest;
import com.letv.test.tbtSps.rest.response.UserResponse;
import com.letv.test.tbtSps.rest.response.dto.User;
import com.letv.test.tbtSps.rest.Urls;

/**
 * UserResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class UserResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetUser() {
        String url= Urls.API_DOMAIN + "services/user/getUser";

        UserRequest request = new UserRequest();
        request.setId(1l);
        UserResponse response = super.getRestTemplate().postForObject(url, request, UserResponse.class);
        Assert.notNull(response);
        User user = super.getResult(response);
        Assert.notNull(user);
    }
}
