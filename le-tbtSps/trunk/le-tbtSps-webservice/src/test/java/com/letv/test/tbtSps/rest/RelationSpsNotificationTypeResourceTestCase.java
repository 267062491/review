package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationSpsNotificationTypeRequest;
import com.letv.test.tbtSps.rest.response.RelationSpsNotificationTypeResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationSpsNotificationType;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationSpsNotificationTypeResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationSpsNotificationTypeResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationSpsNotificationType() {
        String url= Urls.API_DOMAIN + "services/relationSpsNotificationType/getRelationSpsNotificationType";

        RelationSpsNotificationTypeRequest request = new RelationSpsNotificationTypeRequest();
        request.setId(1l);
        RelationSpsNotificationTypeResponse response = super.getRestTemplate().postForObject(url, request, RelationSpsNotificationTypeResponse.class);
        Assert.notNull(response);
        RelationSpsNotificationType relationSpsNotificationType = super.getResult(response);
        Assert.notNull(relationSpsNotificationType);
    }
}
