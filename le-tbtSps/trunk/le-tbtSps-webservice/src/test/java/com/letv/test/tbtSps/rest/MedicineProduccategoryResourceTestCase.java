package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.MedicineProduccategoryRequest;
import com.letv.test.tbtSps.rest.response.MedicineProduccategoryResponse;
import com.letv.test.tbtSps.rest.response.dto.MedicineProduccategory;
import com.letv.test.tbtSps.rest.Urls;

/**
 * MedicineProduccategoryResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class MedicineProduccategoryResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetMedicineProduccategory() {
        String url= Urls.API_DOMAIN + "services/medicineProduccategory/getMedicineProduccategory";

        MedicineProduccategoryRequest request = new MedicineProduccategoryRequest();
        request.setId(1l);
        MedicineProduccategoryResponse response = super.getRestTemplate().postForObject(url, request, MedicineProduccategoryResponse.class);
        Assert.notNull(response);
        MedicineProduccategory medicineProduccategory = super.getResult(response);
        Assert.notNull(medicineProduccategory);
    }
}
