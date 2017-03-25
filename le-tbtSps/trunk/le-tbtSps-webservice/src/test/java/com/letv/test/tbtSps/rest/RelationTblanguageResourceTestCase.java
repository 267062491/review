package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationTblanguageRequest;
import com.letv.test.tbtSps.rest.response.RelationTblanguageResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationTblanguage;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationTblanguageResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationTblanguageResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationTblanguage() {
        String url= Urls.API_DOMAIN + "services/relationTblanguage/getRelationTblanguage";

        RelationTblanguageRequest request = new RelationTblanguageRequest();
        request.setId(1l);
        RelationTblanguageResponse response = super.getRestTemplate().postForObject(url, request, RelationTblanguageResponse.class);
        Assert.notNull(response);
        RelationTblanguage relationTblanguage = super.getResult(response);
        Assert.notNull(relationTblanguage);
    }
}
