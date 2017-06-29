package com.letv.tbtSps.utils.runOrder;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-6-14
 * Time: 下午7:50
 * To change this template use File | Settings | File Templates.
 */
public class Son extends Father {
    static {
        System.out.println("我是son-static");
    }

    {
        System.out.println("是是son-method");
    }

    public Son(){
        System.out.println("我是son-construct");
    }

    public void method(){
        System.out.println("我是son");
    }
}
