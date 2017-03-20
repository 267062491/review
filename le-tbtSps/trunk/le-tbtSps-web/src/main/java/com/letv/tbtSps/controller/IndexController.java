package com.letv.tbtSps.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.letv.common.controller.base.BaseController;

/**
 * 主界面控制器：首页、登录等
 * 
 * @author lijianzhong
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController {
    private static final String REDIRECT = "redirect:";
    private static final String VIEW_INDEX = "index";

    private final Log logger = LogFactory.getLog(this.getClass());

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String welcome(Model model) {
        return index(model);
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("go to index page");
        return VIEW_INDEX;
    }

}
