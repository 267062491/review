package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.TbinfoLogRequest;
import com.letv.test.tbtSps.rest.response.TbinfoLogResponse;
import com.letv.test.tbtSps.rest.response.dto.TbinfoLog;
import com.letv.test.tbtSps.rest.Urls;

/**
 * TbinfoLogResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class TbinfoLogResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetTbinfoLog() {
        String url= Urls.API_DOMAIN + "services/tbinfoLog/getTbinfoLog";

        TbinfoLogRequest request = new TbinfoLogRequest();
        request.setId(1l);
        TbinfoLogResponse response = super.getRestTemplate().postForObject(url, request, TbinfoLogResponse.class);
        Assert.notNull(response);
        TbinfoLog tbinfoLog = super.getResult(response);
        Assert.notNull(tbinfoLog);
    }
}
