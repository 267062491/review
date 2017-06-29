package com.letv.tbtSps.utils.design;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-6-25
 * Time: 上午11:34
 * To change this template use File | Settings | File Templates.
 */
public class SonModel2 extends Model {
    @Override
    protected void start() {
        System.out.println("model2.start");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void stop() {
        System.out.println("model2.stop");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void alert() {
        System.out.println("model2.alert");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAlertFalg() {
        return false;    //To change body of overridden methods use File | Settings | File Templates.
    }
}
