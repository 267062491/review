package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.TblogAttrRequest;
import com.letv.test.tbtSps.rest.response.TblogAttrResponse;
import com.letv.test.tbtSps.rest.response.dto.TblogAttr;
import com.letv.test.tbtSps.rest.Urls;

/**
 * TblogAttrResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class TblogAttrResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetTblogAttr() {
        String url= Urls.API_DOMAIN + "services/tblogAttr/getTblogAttr";

        TblogAttrRequest request = new TblogAttrRequest();
        request.setId(1l);
        TblogAttrResponse response = super.getRestTemplate().postForObject(url, request, TblogAttrResponse.class);
        Assert.notNull(response);
        TblogAttr tblogAttr = super.getResult(response);
        Assert.notNull(tblogAttr);
    }
}
