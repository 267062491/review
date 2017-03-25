package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationSpsNotificationCategoryRequest;
import com.letv.test.tbtSps.rest.response.RelationSpsNotificationCategoryResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationSpsNotificationCategory;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationSpsNotificationCategoryResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationSpsNotificationCategoryResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationSpsNotificationCategory() {
        String url= Urls.API_DOMAIN + "services/relationSpsNotificationCategory/getRelationSpsNotificationCategory";

        RelationSpsNotificationCategoryRequest request = new RelationSpsNotificationCategoryRequest();
        request.setId(1l);
        RelationSpsNotificationCategoryResponse response = super.getRestTemplate().postForObject(url, request, RelationSpsNotificationCategoryResponse.class);
        Assert.notNull(response);
        RelationSpsNotificationCategory relationSpsNotificationCategory = super.getResult(response);
        Assert.notNull(relationSpsNotificationCategory);
    }
}
