package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.TbinfoRequest;
import com.letv.test.tbtSps.rest.response.TbinfoResponse;
import com.letv.test.tbtSps.rest.response.dto.Tbinfo;
import com.letv.test.tbtSps.rest.Urls;

/**
 * TbinfoResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class TbinfoResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetTbinfo() {
        String url= Urls.API_DOMAIN + "services/tbinfo/getTbinfo";

        TbinfoRequest request = new TbinfoRequest();
        request.setId(1l);
        TbinfoResponse response = super.getRestTemplate().postForObject(url, request, TbinfoResponse.class);
        Assert.notNull(response);
        Tbinfo tbinfo = super.getResult(response);
        Assert.notNull(tbinfo);
    }
}
