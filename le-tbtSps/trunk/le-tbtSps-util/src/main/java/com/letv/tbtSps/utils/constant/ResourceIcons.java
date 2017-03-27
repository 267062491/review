package com.letv.tbtSps.utils.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 资源图标
 * yuguodong
 */
public class ResourceIcons {

    private static List<String> iconList;

    private static String[] icons = { "icon-desktop", "icon-laptop", "icon-cog", "icon-pencil", "icon-print",
            "icon-search", "icon-list", "icon-plus", "icon-star", "icon-leaf", "icon-globe", "icon-glass",
            "icon-table", "icon-envelope" };

    static {
        iconList = Arrays.asList(icons);
    }

    /**
     * 资源图标 ace
     * 
     * @return
     */
    public static List<String> getIcons() {
        return iconList;
    }

}
