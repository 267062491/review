package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationTbnotificationCategoryRequest;
import com.letv.test.tbtSps.rest.response.RelationTbnotificationCategoryResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationTbnotificationCategory;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationTbnotificationCategoryResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationTbnotificationCategoryResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationTbnotificationCategory() {
        String url= Urls.API_DOMAIN + "services/relationTbnotificationCategory/getRelationTbnotificationCategory";

        RelationTbnotificationCategoryRequest request = new RelationTbnotificationCategoryRequest();
        request.setId(1l);
        RelationTbnotificationCategoryResponse response = super.getRestTemplate().postForObject(url, request, RelationTbnotificationCategoryResponse.class);
        Assert.notNull(response);
        RelationTbnotificationCategory relationTbnotificationCategory = super.getResult(response);
        Assert.notNull(relationTbnotificationCategory);
    }
}
