package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.NotificationTypeRequest;
import com.letv.test.tbtSps.rest.response.NotificationTypeResponse;
import com.letv.test.tbtSps.rest.response.dto.NotificationType;
import com.letv.test.tbtSps.rest.Urls;

/**
 * NotificationTypeResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class NotificationTypeResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetNotificationType() {
        String url= Urls.API_DOMAIN + "services/notificationType/getNotificationType";

        NotificationTypeRequest request = new NotificationTypeRequest();
        request.setId(1l);
        NotificationTypeResponse response = super.getRestTemplate().postForObject(url, request, NotificationTypeResponse.class);
        Assert.notNull(response);
        NotificationType notificationType = super.getResult(response);
        Assert.notNull(notificationType);
    }
}
