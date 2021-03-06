package com.letv.tbtSps.utils;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.letv.common.utils.serialize.JsonHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
*
* @author wanghui6
*  远程调用接口方法
*/
public class HttpClientUtil {

	private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * post请求
	 * @param url 请求的地址
	 * @param mediaType 请求的数据协议
	 * @param content 传送的报文
	 * @return
	 * @throws Exception
	 */
	public static String postForObject(String url,String mediaType,String content) throws Exception{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);//设置超时时间
		post.setHeader("Content-Type", mediaType);
		String retStr="";
	    //这种单独传字符串进去
		StringEntity reqEntity = new StringEntity(content,"utf-8");
		post.setEntity(reqEntity);
		Long start = System.currentTimeMillis();
		HttpResponse response = client.execute(post);
		logger.info("url:"+url+",调用时间:"+(System.currentTimeMillis()-start));
		int code = response.getStatusLine().getStatusCode();
		if(code==200){
			 HttpEntity entity = response.getEntity();
			 retStr=EntityUtils.toString(entity);
		}else{
            throw new Exception("http post call error, url: "+url+", httpCode: "+String.valueOf(code));
        }
        return retStr;
	}
	/**
	 * post请求
	 * @param url 请求的地址
	 * @param mediaType 请求的数据协议
	 * @param map 参数结合
	 * @return
	 * @throws Exception
	 */
	public static String postForObject(String url,String mediaType,Map<String,String> map) throws Exception{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", mediaType);
		String retStr="";
		List<BasicNameValuePair> formParams = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		HttpEntity httpentity = new UrlEncodedFormEntity(formParams, "UTF-8");
	    //这种单独传字符串进去
		post.setEntity(httpentity);
		Long start = System.currentTimeMillis();
		HttpResponse response = client.execute(post);
		logger.info("url:"+url+",调用时间:"+(System.currentTimeMillis()-start));
		int code = response.getStatusLine().getStatusCode();
		if(code==200){
             HttpEntity entity = response.getEntity();
             retStr=EntityUtils.toString(entity);
		}else{
            throw new Exception("http post call error, url: "+url+", httpCode: "+String.valueOf(code));
        }
		return retStr;
	}
	/**
	 *
	 * @param url 请求的URL
	 * @param map 请求的参数结合
	 * @return
	 * @throws Exception
	 */
	public static String getForObject(String url,Map<String,String> map) throws Exception{
		StringBuffer sb = new StringBuffer(url);
		if(map!=null){
			sb.append("?");
			 int a=0;
			 for (Map.Entry<String, String> entry : map.entrySet()) {
				   if(a!=0){
					  sb.append("&");
				   }
				   sb.append(entry.getKey());
				   sb.append("=");
				   sb.append(entry.getValue());
				   a++;
			 }
		}
        logger.info("HttpClientUtil#getForObject.url: "+sb.toString());
		URL url1 = new URL(sb.toString());
		URI uri = new URI(url1.getProtocol(), url1.getHost(), url1.getPath(), url1.getQuery(), null);
		//得到浏览器
		HttpClient client = new DefaultHttpClient();
		//指定请求方式
		HttpGet  get = new HttpGet(uri);
		String retStr="";
		//执行请求
		Long start = System.currentTimeMillis();
		HttpResponse response = client.execute(get);
		logger.info("url:"+url+",调用时间:"+(System.currentTimeMillis()-start));
		int code = response.getStatusLine().getStatusCode();

		//判断请求是否成功
		if(code==200){
		    //获取返回的实体
		    HttpEntity entity = response.getEntity();
		    //获取返回的内容
		    retStr=EntityUtils.toString(entity,"UTF-8");
		}else{
            throw new Exception("http post call error, url: "+url+", httpCode: "+String.valueOf(code));
        }
		return retStr;
	}
}
