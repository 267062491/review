package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationMedicineRequest;
import com.letv.test.tbtSps.rest.response.RelationMedicineResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationMedicine;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationMedicineResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationMedicineResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationMedicine() {
        String url= Urls.API_DOMAIN + "services/relationMedicine/getRelationMedicine";

        RelationMedicineRequest request = new RelationMedicineRequest();
        request.setId(1l);
        RelationMedicineResponse response = super.getRestTemplate().postForObject(url, request, RelationMedicineResponse.class);
        Assert.notNull(response);
        RelationMedicine relationMedicine = super.getResult(response);
        Assert.notNull(relationMedicine);
    }
}
