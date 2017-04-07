package com.letv.tbtSps.utils;


import com.letv.common.utils.serialize.JsonHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-11-3
 * Time: 上午11:39
 * To change this template use File | Settings | File Templates.
 */
public class JsonHelperImpl extends JsonHelper {

    /**
     * list转JSONArray
     * @param obj
     * @return
     */
    public static JSONArray listToJsonArray(Object obj){
        return JSONArray.fromObject(obj) ;
    }

    /**
     * 把json数组格式字符串转成list<对象>
     * @param clazz
     * @param jsonArrayFormatStr
     * @return
     */
    public static List<Object> jsonFormatArrayToListBean(Class clazz , String jsonArrayFormatStr){
        List<Object> list = null ;
        JSONArray json = JSONArray.fromObject(jsonArrayFormatStr);
        if(json.size()>0){
            list = new ArrayList<Object>();
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);
                Object obj = JsonHelper.toBean(JsonHelperImpl.toJson(job),clazz);
                list.add(obj);
            }
        }
        return list ;
    }
}
