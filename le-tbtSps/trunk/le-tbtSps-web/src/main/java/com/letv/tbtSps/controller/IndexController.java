package com.letv.tbtSps.controller;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.security.MD5Util;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.common.cotext.LoginUser;
import com.letv.tbtSps.common.cotext.UserContext;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.domain.query.TreeDomain;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.service.IndexService;
import com.letv.tbtSps.service.ResourceService;
import com.letv.tbtSps.service.RoleResourceService;
import com.letv.tbtSps.service.UserRoleService;
import com.letv.tbtSps.utils.JsonHelperImpl;
import com.letv.tbtSps.utils.ParameterLoad;
import com.letv.tbtSps.utils.PortalWeb;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
import com.letv.tbtSps.utils.constant.ResourcePlant;
import com.letv.tbtSps.utils.constant.SystemConstant;
import com.letv.tbtSps.utils.enums.RoleEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 主界面控制器：首页、登录等
 * 
 * @author ygd
 */
@Controller
@RequestMapping("")
public class IndexController extends ReviewBaseController {

    private static final String REDIRECT = "redirect:";
    private static final String VIEW_INDEX = "index";

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private IndexService indexService ;
    @Autowired
    private PortalWeb portalWeb ;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ParameterLoad parameterLoad;
    private final Log logger = LogFactory.getLog(this.getClass());

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String welcome(Model model, HttpServletRequest request) {
        return index(model , request);
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        request.setAttribute("indexFlag",true);
        logger.debug("go to index page");
        return VIEW_INDEX;
    }
    @RequestMapping(value = "indexPage", method = RequestMethod.GET)
    public String indexPage(Model model, HttpServletRequest request) {
        logger.debug("go to index page");
        return "indexPage";
    }

    /**
     * 进入登录页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {// , @RequestParam("ReturnUrl") String forwardUrl
        setLocale(model, request);
        return "login";
    }



    /**
     * 进行登录操作
     * @param userQuery
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "loginSys", method = RequestMethod.POST)
    public String loginSys(UserQuery userQuery, Model model, HttpServletRequest request, HttpServletResponse response ,String checkCode) {
        logger.info("inputPar:IndexController#loginSys.userQuery="+ JsonHelper.toJson(userQuery));
        setLocale(model, request);
        try {

//            if (!CheckCodeController.validateCheckCode(checkCode, request)) {// 验证码
//                model.addAttribute("errorCode","11");
//                model.addAttribute("errorMessage","验证码错误");
//                return "login";
//            }
//            userQuery.setUserState(SystemConstant.YES);
//            userQuery.setPassword(MD5Util.md5Hex(SystemConstant.passwordPre + userQuery.getPassword()));
            userQuery.setPassword(userQuery.getPassword());
            LetvResponse<User> letvResponse_userDto = indexService.login(userQuery) ;
            if(letvResponse_userDto.getCode()== PortalSystemTipCodeEnum.SCUESS.getValue()){
                /**
                 * 登录成功
                 * 1、写cookie
                 * 2、跳转到登录首页
                 */
                User user = letvResponse_userDto.getResult();
                setCookie(request,response, user);
                logger.info("outputPar:IndexController#loginSys.userDto="+JsonHelper.toJson(user));
                this.setUserMenu(model,user.getUserName());
                return VIEW_INDEX;
//                return REDIRECT+"index";
            }else{
                /**
                 * 登录失败
                 * 1、跳转到登录页面，提示失败原因
                 */
                model.addAttribute("errorCode",letvResponse_userDto.getCode());
                model.addAttribute("errorMessage",letvResponse_userDto.getMessage());
                return "login";
            }
        } catch (Exception e) {
            logger.error("error:IndexController#loginSys.e=",e);
            return "login";
        }
    }


    /**
     * 用户退出:失效所有的cookie
     *
     * @param response
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("quit")
    public String quit(HttpServletResponse response, Model model, HttpServletRequest request) {
//        portalWeb.invalidateCookies(request,response);
        setLocale(model, request);
        portalWeb.invalidateCookie(response);
        return REDIRECT + VIEW_INDEX;
    }

    public void setLocale(Model model, HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        model.addAttribute("language", requestContext.getLocale().toString());
    }

    private void setCookie(HttpServletRequest request,HttpServletResponse response, User user) {

        if (null == user) {
            return;
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setUserCode(user.getUserCode());
        loginUser.setUserName(user.getUserName());
        loginUser.setCnName(user.getShowName());
        loginUser.setLanguage("zh");
        loginUser.setUserType(user.getUserType());
        String cookieValue = loginUser.toString();// 用户信息的json值
        portalWeb.setCookies(request,response, cookieValue ,user.getUserCode() );
        UserContext.set(loginUser);
    }

    /**
     * 获取当前登录用户：从上下文获取，（cookie解析而得）
     *
     * @return
     */
    private LoginUser getCurrentUser() {
        LoginUser loginUser = getLoginUser();
        return loginUser;
    }



    /**
     * 获取当前登录用户拥有的资源
     */
    @RequestMapping(value = "getLoginUserResource")
    @ResponseBody
    public Wrapper<?> getLoginUserResource(HttpServletRequest request , String id  ){
        try{

            RoleResourceQuery roleResourceQuery = new RoleResourceQuery () ;
            roleResourceQuery.setUserCode(getLoginUserCode());
            roleResourceQuery.setResourceCode(id);
            roleResourceQuery.setButtonFlag(SystemConstant.YES);
            roleResourceQuery.setPlatForm(ResourcePlant.PC);
            LetvResponse<List<TreeDomain>> listLetvResponse = indexService.getLoginUserResource(roleResourceQuery) ;
            if(listLetvResponse.getCode()==PortalSystemTipCodeEnum.SCUESS.getValue()){
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, JsonHelperImpl.listToJsonArray(listLetvResponse.getResult()));
            }else{
                return WrapMapper.wrap(listLetvResponse.getCode(), listLetvResponse.getMessage(), JsonHelperImpl.listToJsonArray(listLetvResponse.getResult()));
            }
        }catch (Exception e){
            return WrapMapper.error();
        }
    }

    /**
     * 进入到系统首页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "home")
    public String home(Model model, HttpServletRequest request) {
        return "homePage";
    }


    /**
     * 设置用户菜单
     model.addAttribute("user",true);// 用户
     model.addAttribute("role",true);// 角色
     model.addAttribute("notificationType",true);//通报类型管理
     model.addAttribute("country",true);//通报成员管理
     model.addAttribute("language",true);//原文语种
     model.addAttribute("targereason",true);//目标理由
     model.addAttribute("internationalStandard",true);//国际标准
     model.addAttribute("relationMedicine",true);//相关农药
     model.addAttribute("spsInfo",true);//sps通报
     model.addAttribute("tbt",true);//tbt通报
     model.addAttribute("indexReview",true);//通报评审
     model.addAttribute("relationMedicineProduct",true);//相关农产品
     model.addAttribute("indexExpertsReview",true);//专家评议
     model.addAttribute("relationMedicine",true);//全文检索
     model.addAttribute("relationMedicine",true);//统计
     * @param model
     * @param userName
     */
    private void setUserMenu(Model model,String userName){
        // 根据用户名称查询对应的角色
        UserRoleQuery userRoleQuery = new UserRoleQuery();
        userRoleQuery.setUserCode(userName);
        List<UserRole>  userRoleList = userRoleService.queryUserRoleList(userRoleQuery);
        model.addAttribute("indexSolr",true);//全文检索
        model.addAttribute("loginFlag", org.apache.commons.lang.StringUtils.isNotEmpty(this.getLoginUserName()));//是否登录
        model.addAttribute("userName",this.getLoginUserCnName());//是否登录

        for(UserRole userRole : userRoleList){
            if(userRole.getRoleCode().equals(RoleEnum.CCPR.getStatusCode())){
                model.addAttribute("relationMedicine",true);//相关农药
                model.addAttribute("spsInfo",true);//sps通报
                model.addAttribute("tbt",true);//tbt通报
                model.addAttribute("indexReview",true);//通报评审
                model.addAttribute("relationMedicineProduct",true);//相关农产品
                model.addAttribute("indexExpertsReview",true);//专家评议
                model.addAttribute("relationMedicine",true);//全文检索
                model.addAttribute("relationMedicine",true);//统计
            }else if(userRole.getRoleCode().equals(RoleEnum.outExports.getStatusCode())){ // 外部专家
                model.addAttribute("indexReview",true);//通报评审
                model.addAttribute("indexExpertsReview",true);//专家评议
                model.addAttribute("relationMedicine",true);//全文检索
            }else if(userRole.getRoleCode().equals(RoleEnum.notice_manager.getStatusCode())){// 通报管理员
                model.addAttribute("relationMedicine",true);//相关农药
                model.addAttribute("spsInfo",true);//sps通报
                model.addAttribute("tbt",true);//tbt通报
                model.addAttribute("relationMedicineProduct",true);//相关农产品
                model.addAttribute("relationMedicine",true);//全文检索
            }else if(userRole.getRoleCode().equals(RoleEnum.limit_manager.getStatusCode())){ // 限量数据管理员
                model.addAttribute("relationMedicine",true);//相关农药
                model.addAttribute("relationMedicineProduct",true);//相关农产品
                model.addAttribute("relationMedicine",true);//全文检索
            }else if(userRole.getRoleCode().equals(RoleEnum.sys_manager.getStatusCode())){ // 系统管理员
                model.addAttribute("user",true);// 用户
                model.addAttribute("role",true);// 角色
                model.addAttribute("notificationType",true);//通报类型管理
                model.addAttribute("country",true);//通报成员管理
                model.addAttribute("language",true);//原文语种
                model.addAttribute("targereason",true);//目标理由
                model.addAttribute("internationalStandard",true);//国际标准
                model.addAttribute("relationMedicine",true);//相关农药
                model.addAttribute("spsInfo",true);//sps通报
                model.addAttribute("tbt",true);//tbt通报
                model.addAttribute("relationMedicineProduct",true);//相关农产品
                model.addAttribute("relationMedicine",true);//全文检索
                model.addAttribute("relationMedicine",true);//统计
            }
        }
    }

}
