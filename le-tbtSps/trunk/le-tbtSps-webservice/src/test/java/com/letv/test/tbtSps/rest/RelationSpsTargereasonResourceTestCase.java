package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationSpsTargereasonRequest;
import com.letv.test.tbtSps.rest.response.RelationSpsTargereasonResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationSpsTargereason;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationSpsTargereasonResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationSpsTargereasonResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationSpsTargereason() {
        String url= Urls.API_DOMAIN + "services/relationSpsTargereason/getRelationSpsTargereason";

        RelationSpsTargereasonRequest request = new RelationSpsTargereasonRequest();
        request.setId(1l);
        RelationSpsTargereasonResponse response = super.getRestTemplate().postForObject(url, request, RelationSpsTargereasonResponse.class);
        Assert.notNull(response);
        RelationSpsTargereason relationSpsTargereason = super.getResult(response);
        Assert.notNull(relationSpsTargereason);
    }
}
