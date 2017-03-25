package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationMedicineProductRequest;
import com.letv.test.tbtSps.rest.response.RelationMedicineProductResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationMedicineProduct;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationMedicineProductResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationMedicineProductResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationMedicineProduct() {
        String url= Urls.API_DOMAIN + "services/relationMedicineProduct/getRelationMedicineProduct";

        RelationMedicineProductRequest request = new RelationMedicineProductRequest();
        request.setId(1l);
        RelationMedicineProductResponse response = super.getRestTemplate().postForObject(url, request, RelationMedicineProductResponse.class);
        Assert.notNull(response);
        RelationMedicineProduct relationMedicineProduct = super.getResult(response);
        Assert.notNull(relationMedicineProduct);
    }
}
