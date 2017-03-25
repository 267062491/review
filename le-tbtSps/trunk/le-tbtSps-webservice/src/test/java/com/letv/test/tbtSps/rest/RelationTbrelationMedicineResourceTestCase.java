package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.RelationTbrelationMedicineRequest;
import com.letv.test.tbtSps.rest.response.RelationTbrelationMedicineResponse;
import com.letv.test.tbtSps.rest.response.dto.RelationTbrelationMedicine;
import com.letv.test.tbtSps.rest.Urls;

/**
 * RelationTbrelationMedicineResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class RelationTbrelationMedicineResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetRelationTbrelationMedicine() {
        String url= Urls.API_DOMAIN + "services/relationTbrelationMedicine/getRelationTbrelationMedicine";

        RelationTbrelationMedicineRequest request = new RelationTbrelationMedicineRequest();
        request.setId(1l);
        RelationTbrelationMedicineResponse response = super.getRestTemplate().postForObject(url, request, RelationTbrelationMedicineResponse.class);
        Assert.notNull(response);
        RelationTbrelationMedicine relationTbrelationMedicine = super.getResult(response);
        Assert.notNull(relationTbrelationMedicine);
    }
}
