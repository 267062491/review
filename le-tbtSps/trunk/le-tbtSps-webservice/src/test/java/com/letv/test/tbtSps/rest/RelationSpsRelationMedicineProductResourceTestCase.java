package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationSpsRelationMedicineProductRequest;
import com.letv.test.tbtSps.rest.response.RelationSpsRelationMedicineProductResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationSpsRelationMedicineProduct;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationSpsRelationMedicineProductResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationSpsRelationMedicineProductResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationSpsRelationMedicineProduct() {
        String url= Urls.API_DOMAIN + "services/relationSpsRelationMedicineProduct/getRelationSpsRelationMedicineProduct";

        RelationSpsRelationMedicineProductRequest request = new RelationSpsRelationMedicineProductRequest();
        request.setId(1l);
        RelationSpsRelationMedicineProductResponse response = super.getRestTemplate().postForObject(url, request, RelationSpsRelationMedicineProductResponse.class);
        Assert.notNull(response);
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = super.getResult(response);
        Assert.notNull(relationSpsRelationMedicineProduct);
    }
}
