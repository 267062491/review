package com.letv.tbtSps.utils;

import com.letv.common.utils.DateHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description
 * Created by ygd on 2017/11/21.
 */
public class DateHelperImpl extends DateHelper {

    /**
     * var1-var2 得到两个日期相差的天数
     * @param var1
     * @param var2
     * @return 两个日期相差的天数
     */
    public static int subDate(Date var1 , Date var2){
        long subtractionTime = var1.getTime()-var2.getTime();
        Long day = (subtractionTime/1000)/3600/24 ;
        return day.intValue() ;
    }

    public static void main(String[] args) {
        DateHelper.parseDate("2017-11-21");
        DateHelper.parseDate("2017-11-25");
        DateHelperImpl.subDate(DateHelper.parseDate("2017-11-18"),DateHelper.parseDate("2017-11-21"));

        DateHelperImpl.addDate(new Date(),3);
        System.out.print("11");
    }
}
