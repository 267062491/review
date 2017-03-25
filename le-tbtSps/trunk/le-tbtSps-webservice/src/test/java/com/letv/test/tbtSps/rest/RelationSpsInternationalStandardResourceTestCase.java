package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationSpsInternationalStandardRequest;
import com.letv.test.tbtSps.rest.response.RelationSpsInternationalStandardResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationSpsInternationalStandard;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationSpsInternationalStandardResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationSpsInternationalStandardResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationSpsInternationalStandard() {
        String url= Urls.API_DOMAIN + "services/relationSpsInternationalStandard/getRelationSpsInternationalStandard";

        RelationSpsInternationalStandardRequest request = new RelationSpsInternationalStandardRequest();
        request.setId(1l);
        RelationSpsInternationalStandardResponse response = super.getRestTemplate().postForObject(url, request, RelationSpsInternationalStandardResponse.class);
        Assert.notNull(response);
        RelationSpsInternationalStandard relationSpsInternationalStandard = super.getResult(response);
        Assert.notNull(relationSpsInternationalStandard);
    }
}
