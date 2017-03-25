package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationTbnotificationTypeRequest;
import com.letv.test.tbtSps.rest.response.RelationTbnotificationTypeResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationTbnotificationType;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationTbnotificationTypeResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationTbnotificationTypeResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationTbnotificationType() {
        String url= Urls.API_DOMAIN + "services/relationTbnotificationType/getRelationTbnotificationType";

        RelationTbnotificationTypeRequest request = new RelationTbnotificationTypeRequest();
        request.setId(1l);
        RelationTbnotificationTypeResponse response = super.getRestTemplate().postForObject(url, request, RelationTbnotificationTypeResponse.class);
        Assert.notNull(response);
        RelationTbnotificationType relationTbnotificationType = super.getResult(response);
        Assert.notNull(relationTbnotificationType);
    }
}
