package com.letv.test.tbtSps.rest;

import org.junit.Test;
import org.springframework.util.Assert;

import com.letv.common.client.AbstractClient;
import com.letv.test.tbtSps.rest.request.LanguageRequest;
import com.letv.test.tbtSps.rest.response.LanguageResponse;
import com.letv.test.tbtSps.rest.response.dto.Language;
import com.letv.test.tbtSps.rest.Urls;

/**
 * LanguageResource单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:05
 * 
 */
public class LanguageResourceTestCase extends AbstractClient {
    
    @Test
    public void testGetLanguage() {
        String url= Urls.API_DOMAIN + "services/language/getLanguage";

        LanguageRequest request = new LanguageRequest();
        request.setId(1l);
        LanguageResponse response = super.getRestTemplate().postForObject(url, request, LanguageResponse.class);
        Assert.notNull(response);
        Language language = super.getResult(response);
        Assert.notNull(language);
    }
}
