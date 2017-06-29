package com.letv.tbtSps.utils.design;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-6-25
 * Time: 上午11:34
 * To change this template use File | Settings | File Templates.
 */
public class ModelClient {

    public static void main(String[] args) {
        Model model1 = new SonModel1();
        Model model2 = new SonModel2();
        model1.setAlertFalg(true);
        model1.run();
        model2.run();
    }
}
