package com.letv.tbtSps.utils.design;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-6-25
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
public class SonModel1 extends Model {

    @Override
    protected void start() {
       System.out.println("model1.start");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void stop() {
        System.out.println("model1.stop");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void alert() {
        System.out.println("model1.alert");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAlertFalg(boolean alertFalg) {
        super.setAlertFalg(alertFalg);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
