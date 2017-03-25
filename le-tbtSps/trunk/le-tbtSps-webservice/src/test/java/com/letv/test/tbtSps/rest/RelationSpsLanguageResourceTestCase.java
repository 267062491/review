package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationSpsLanguageRequest;
import com.letv.test.tbtSps.rest.response.RelationSpsLanguageResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationSpsLanguage;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationSpsLanguageResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationSpsLanguageResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationSpsLanguage() {
        String url= Urls.API_DOMAIN + "services/relationSpsLanguage/getRelationSpsLanguage";

        RelationSpsLanguageRequest request = new RelationSpsLanguageRequest();
        request.setId(1l);
        RelationSpsLanguageResponse response = super.getRestTemplate().postForObject(url, request, RelationSpsLanguageResponse.class);
        Assert.notNull(response);
        RelationSpsLanguage relationSpsLanguage = super.getResult(response);
        Assert.notNull(relationSpsLanguage);
    }
}
