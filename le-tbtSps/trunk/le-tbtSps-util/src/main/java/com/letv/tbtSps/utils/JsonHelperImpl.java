package com.letv.tbtSps.utils;


import com.letv.common.utils.serialize.JsonHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    private static final String enter_code = "\n";
    private static final String enter = "(enter---n)";
    private static final String enter_r_code = "\r";
    private static final String enter_r = "(enter---r)";

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
        List<Object> list = new ArrayList<Object>() ;
        if(StringUtils.isEmpty(jsonArrayFormatStr)){
            return list ;
        }
        String temp = jsonArrayFormatStr.replaceAll(enter_code,enter).replaceAll(enter_r_code,enter_r);
        JSONArray json = JSONArray.fromObject(temp);
        if(json.size()>0){
            list = new ArrayList<Object>();
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);
                Object obj = JsonHelper.toBean(JsonHelperImpl.toJson(job),clazz);
                Field[] fields = clazz.getDeclaredFields();
                for(Field field: fields){
                    try {
                        String name = field.getName();
                        String setMethodStr = "set"+name.toUpperCase().substring(0, 1)+name.substring(1);
                        String getMethodStr = "get"+name.toUpperCase().substring(0, 1)+name.substring(1);
                        Method setMethod = null;
                        Method getMethod = null;

                        setMethod = clazz.getMethod(setMethodStr,new Class[]{field.getType()});
                        getMethod = clazz.getMethod(getMethodStr);
                        if(field.getType().getSimpleName().equals("String")){
                            String objValue = (String) getMethod.invoke(obj);
                            if(null!=objValue){
                                String getMethodValue = objValue.replaceAll(enter,enter_code).replaceAll(enter_r,enter_r_code);
                                setMethod.invoke(obj,getMethodValue);
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
                list.add(obj);
            }
        }
        return list ;
    }

    public static void main(String[] args){
        String getMethodValue = "yuguodong".replaceAll(enter,enter_code);
        System.out.print(getMethodValue);
    }
}
