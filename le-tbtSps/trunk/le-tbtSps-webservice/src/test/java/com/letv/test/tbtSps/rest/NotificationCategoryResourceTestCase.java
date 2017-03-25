package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.NotificationCategoryRequest;
import com.letv.test.tbtSps.rest.response.NotificationCategoryResponse;
import com.letv.test.tbtSps.rest.response.dto.NotificationCategory;
import com.letv.test.tbtSps.rest.Urls;

/**
 * NotificationCategoryResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class NotificationCategoryResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetNotificationCategory() {
        String url= Urls.API_DOMAIN + "services/notificationCategory/getNotificationCategory";

        NotificationCategoryRequest request = new NotificationCategoryRequest();
        request.setId(1l);
        NotificationCategoryResponse response = super.getRestTemplate().postForObject(url, request, NotificationCategoryResponse.class);
        Assert.notNull(response);
        NotificationCategory notificationCategory = super.getResult(response);
        Assert.notNull(notificationCategory);
    }
}
