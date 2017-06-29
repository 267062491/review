package com.letv.tbtSps.utils.runOrder;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-6-14
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public class Father {

    static {
        System.out.println("我是father-static");
    }

    {
        System.out.println("我是father-method");
    }

    public Father(){
        System.out.println("我是father-construct");
    }

    public void method(){
        System.out.println("我是father");
    }
}
