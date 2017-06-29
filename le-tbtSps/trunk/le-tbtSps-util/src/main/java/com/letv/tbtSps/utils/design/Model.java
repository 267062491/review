package com.letv.tbtSps.utils.design;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-6-25
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */
public abstract class Model {
    private boolean alertFalg ;
    protected abstract void start();
    protected abstract void stop();
    protected abstract void alert();
    protected final void run(){
        this.start();
        if(this.isAlertFalg()){
            this.alert();
        }
        this.stop();
    }


    public boolean isAlertFalg() {
        return alertFalg;
    }

    public void setAlertFalg(boolean alertFalg) {
        this.alertFalg = alertFalg;
    }
}
