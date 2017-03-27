package com.letv.tbtSps.utils;


import net.sf.json.JSONArray;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-11-3
 * Time: 上午11:39
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtilHelp {

    /**
     * list转JSONArray
     * @param object
     * @return
     */
    public static JSONArray listToJsonArray(Object object){
        return JSONArray.fromObject(object) ;
    }
}
