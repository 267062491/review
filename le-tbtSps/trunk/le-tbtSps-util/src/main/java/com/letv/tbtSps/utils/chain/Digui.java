package com.letv.tbtSps.utils.chain;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-6-24
 * Time: 下午10:17
 * To change this template use File | Settings | File Templates.
 */
public class Digui {

    public static int oneToTen(int num){
        if(num == 5){
            return num ;
        }
        int n = num+oneToTen(num+1);
        return n ;
    }


    public static int jieChen(int num){
        if(num==5){
            return num ;
        }
        return num*jieChen(num+1);
    }

    public static void main(String[] args) {
        System.out.print(Digui.jieChen(1));
    }
}
