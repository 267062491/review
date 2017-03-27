package com.letv.tbtSps.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-8-1
 * Time: 上午10:06
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientUtil {
    public static final Log logger = LogFactory.getLog(HttpClientUtil.class);


    /**
     * post请求
     * @param url 请求的地址
     * @param mediaType 请求的数据协议
     * @param content 传送的报文
     * @return
     * @throws Exception
     */
    public static String postForObject1(String url,String mediaType,String content) throws Exception{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);//设置超时时间
        if("".equals(mediaType)){
            mediaType = "application/json" ;
        }
        post.setHeader("Content-Type", mediaType);
        String retStr="";
        //这种单独传字符串进去
        StringEntity reqEntity = new StringEntity(content,"utf-8");
        post.setEntity(reqEntity);
        HttpResponse response = client.execute(post);
        int code = response.getStatusLine().getStatusCode();
        if(code==200){
            HttpEntity entity = response.getEntity();
            retStr= EntityUtils.toString(entity);
        }else
            throw new Exception(String.valueOf(code));
        return retStr;
    }
}
