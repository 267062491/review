package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationTbrelationMedicineProductRequest;
import com.letv.test.tbtSps.rest.response.RelationTbrelationMedicineProductResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationTbrelationMedicineProduct;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationTbrelationMedicineProductResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationTbrelationMedicineProductResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationTbrelationMedicineProduct() {
        String url= Urls.API_DOMAIN + "services/relationTbrelationMedicineProduct/getRelationTbrelationMedicineProduct";

        RelationTbrelationMedicineProductRequest request = new RelationTbrelationMedicineProductRequest();
        request.setId(1l);
        RelationTbrelationMedicineProductResponse response = super.getRestTemplate().postForObject(url, request, RelationTbrelationMedicineProductResponse.class);
        Assert.notNull(response);
        RelationTbrelationMedicineProduct relationTbrelationMedicineProduct = super.getResult(response);
        Assert.notNull(relationTbrelationMedicineProduct);
    }
}
