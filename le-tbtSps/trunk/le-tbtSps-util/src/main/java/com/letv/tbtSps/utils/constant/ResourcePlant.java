package com.letv.tbtSps.utils.constant;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-27
 * Time: 下午5:49
 * To change this template use File | Settings | File Templates.
 */
public class ResourcePlant {

    public static final String PC = "PC" ;
    public static final String RF = "RF" ;

    private static List<String> plantList;

    private static String[] icons = { "PC", "RF" };

    static {
        plantList = Arrays.asList(icons);
    }

    /**
     * 资源图标 ace
     *
     * @return
     */
    public static List<String> getPlants() {
        return plantList;
    }
}
