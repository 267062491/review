package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationSpsRelationMedicineRequest;
import com.letv.test.tbtSps.rest.response.RelationSpsRelationMedicineResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationSpsRelationMedicine;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationSpsRelationMedicineResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationSpsRelationMedicineResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationSpsRelationMedicine() {
        String url= Urls.API_DOMAIN + "services/relationSpsRelationMedicine/getRelationSpsRelationMedicine";

        RelationSpsRelationMedicineRequest request = new RelationSpsRelationMedicineRequest();
        request.setId(1l);
        RelationSpsRelationMedicineResponse response = super.getRestTemplate().postForObject(url, request, RelationSpsRelationMedicineResponse.class);
        Assert.notNull(response);
        RelationSpsRelationMedicine relationSpsRelationMedicine = super.getResult(response);
        Assert.notNull(relationSpsRelationMedicine);
    }
}
