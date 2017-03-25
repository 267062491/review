package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.TbattrRequest;
import com.letv.test.tbtSps.rest.response.TbattrResponse;
import com.letv.test.tbtSps.rest.response.dto.Tbattr;
import com.letv.test.tbtSps.rest.Urls;

/**
 * TbattrResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class TbattrResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetTbattr() {
        String url= Urls.API_DOMAIN + "services/tbattr/getTbattr";

        TbattrRequest request = new TbattrRequest();
        request.setId(1l);
        TbattrResponse response = super.getRestTemplate().postForObject(url, request, TbattrResponse.class);
        Assert.notNull(response);
        Tbattr tbattr = super.getResult(response);
        Assert.notNull(tbattr);
    }
}
