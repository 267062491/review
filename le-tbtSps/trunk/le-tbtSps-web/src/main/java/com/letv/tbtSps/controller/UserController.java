package com.letv.tbtSps.controller;


import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.security.MD5Util;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.service.RoleService;
import com.letv.tbtSps.service.UserService;
import com.letv.tbtSps.utils.PortalWeb;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
import com.letv.tbtSps.utils.constant.SystemConstant;
import com.letv.tbtSps.utils.enums.UserTypeEnum;
import com.letv.wmscommon.dto.PageUtil;
import com.letv.wmscommon.dto.PagedQueryDto;
import com.letv.wmscommon.dto.PagedResultDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * UserController ：用户表控制器
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:38
*/
@Controller
@RequestMapping("user")
public class UserController extends ReviewBaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService ;
//    @Autowired
//    private PortalWeb portalWeb ;

    /** 视图前缀 */
    private static final String viewPrefix ="user";
    private static final String REDIRECT = "redirect:";
    private static final String VIEW_INDEX = "index";
    
    private static final Log LOG = LogFactory.getLog(UserController.class);

    public int getLoginUserType(){
        return 1 ;
    }

    public String getLoginUserCode(){
        return null ;
    }
    /**
     * 首页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        /**
         * 查询角色列表
         */
        RoleQuery roleQuery = new RoleQuery ();
        roleQuery.setRoleType(getLoginUserType());
        LetvResponse<List<Role>> letvResponse = roleService.queryRoleListLe(roleQuery);
        if(letvResponse.getCode()== PortalSystemTipCodeEnum.SCUESS.getValue()){
            model.addAttribute("list_role",letvResponse.getResult());
        }
        return viewPrefix + "/index";
    }
    

    /**
     * 用户表----添加跳转
     *
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward(Model model) {
        if(getLoginUserType()==Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
            model.addAttribute("userType",Boolean.TRUE);
        }
        return viewPrefix + "/add";
    }

    /**
     * 用户表----添加
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(User user) {
        try {
            user.setCreateUser(getLoginUserName());
            if(null == user.getUserType() ){
                user.setUserType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            }else if(user.getUserType().intValue()!=Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
                user.setUserType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            }
            user.setUserCode(user.getUserName());
            user.setPassword(MD5Util.md5Hex(SystemConstant.passwordPre+user.getPassword()));
            LetvResponse<Boolean> letvResponse =  userService.insert(user) ;
            if (letvResponse.getCode()==LetvResponse.SUCCESS_CODE) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else if(letvResponse.getCode() == PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue()){
                return WrapMapper.wrap(Wrapper.ERROR_CODE,letvResponse.getMessage());
            }else{
                return WrapMapper.wrap(Wrapper.ERROR_CODE,"添加失败");
            }
        } catch (ExistedException e) {
            LOG.warn("user add fail, exist user.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("user add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 用户表----更新跳转
     * 
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, User user) {
        LOG.info("inputPar:UserController#updateForward.user=" + JsonHelper.toJson(user));
        try {
            UserQuery userQuery = new UserQuery();
            userQuery.setId(user.getId());
            LetvResponse<User> letvResponse = userService.getUserById(userQuery);
            if(letvResponse.getCode()==PortalSystemTipCodeEnum.SCUESS.getValue()){
                model.addAttribute("user", letvResponse.getResult());
                if(getLoginUserType()==Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
                    model.addAttribute("userType",Boolean.TRUE);
                }
            }
        } catch (Exception e) {
            LOG.error("error:UserController#updateForward.e=" ,e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 用户表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(UserQuery query) {
        try {
            List<User> list = userService.queryUserList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("user query has error.", e);
            return WrapMapper.error();
        }
    }
    


    /**
     * 分页 查询数据
     *
     * @param model
     * @param page
     * @param query
     * @return
     */
    @RequestMapping(value = "queryByPage")
    public String queryByPage(Model model, PageUtil page, UserQuery query) {
        try {
            query.setUserType(getLoginUserType());
            PagedQueryDto<UserQuery> pagedQuery = new PagedQueryDto<UserQuery>();
            pagedQuery.setPageUtil(page);
            pagedQuery.setQueryDto(query);
            LetvResponse<PagedResultDto<User>> letvResponse_u = userService.queryUserListWithPage(pagedQuery);
            PagedResultDto<User> pagedResult = letvResponse_u.getResult();
            List<User> dataList = pagedResult.getResult();
            /**
             * 查询角色列表
             */
            RoleQuery roleQuery = new RoleQuery ();
            roleQuery.setRoleType(getLoginUserType());
            LetvResponse<List<Role>> letvResponse = roleService.queryRoleListLe(roleQuery);
            if(letvResponse.getCode()== PortalSystemTipCodeEnum.SCUESS.getValue()){
                model.addAttribute("list_role",letvResponse.getResult());
            }
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", pagedResult.getPageUtil());// 分页
        } catch (Exception e) {
            LOG.error("user queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 用户表----启用、禁用
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "enableOrDisable")
    @ResponseBody
    public Wrapper<?> enableOrDisable(User user) {
        LOG.info("inputPar:UserController#enableOrDisable.user=" + JsonHelper.toJson(user));
        if (null == user || StringUtils.isEmpty(user.getUserCode())
                || null == user.getUserState()) {
            LOG.error("error:UserController#enableOrDisable.user=" + JsonHelper.toJson(user));
            return illegalArgument();
        }
        try {
            user.setUpdateUser(getLoginUserCnName());
            LetvResponse<Boolean> letvResponse = userService.enableOrDisable(user);
            LOG.info("outputPar:UserController#enableOrDisable.letvResponse=" + JsonHelper.toJson(letvResponse));
            return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
        } catch (Exception e) {
            LOG.error("error:UserController#enableOrDisable.e=" , e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }

    /**
     * 查询用户表详情
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(UserQuery query) {
        LOG.info("inputPar:UserController#detail.query=" + JsonHelper.toJson(query));
        if (null == query || null == query.getId()) {
            LOG.error("error:UserController#detail.query=" + JsonHelper.toJson(query));
            return illegalArgument();
        }
        try {
            LetvResponse<User> letvResponse = userService.getUserById(query);
            if(letvResponse.getCode()==PortalSystemTipCodeEnum.SCUESS.getValue()){
                return new Wrapper<User>().result(letvResponse.getResult());
            }else {
                return WrapMapper.wrap(letvResponse.getCode(),letvResponse.getMessage());
            }
        } catch (Exception e) {
            LOG.error("error:UserController#detail.e=" , e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }


    /**
     * 用户表----删除
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(User user) {
        LOG.info("inputPar:UserController#delete.user=" + JsonHelper.toJson(user));
        if (null == user || StringUtils.isEmpty(user.getUserCode())) {
            LOG.error("error:UserController#delete.user=" + JsonHelper.toJson(user));
            return illegalArgument();
        }
        try {
            user.setUpdateUser(getLoginUserCnName());
            LetvResponse<Boolean> letvResponse = userService.deleteByUserCode(user);
            LOG.info("outputPar:UserController#delete.letvResponse=" + JsonHelper.toJson(letvResponse));
            return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
        } catch (Exception e) {
            LOG.error("error:UserController#delete.e=", e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }

    /**
     * 用户表----更新
     *
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, User user) {
        LOG.info("inputPar:UserController#update.user=" +JsonHelper.toJson(user));
        if (null == user || StringUtils.isEmpty(user.getUserCode())) {
            LOG.error("error:UserController#update.user=" + JsonHelper.toJson(user));
            return illegalArgument();
        }
        try {
            if(null == user.getUserType() ){
                user.setUserType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            }else if(user.getUserType().intValue()!=Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
                user.setUserType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            }

            user.setUpdateUser(getLoginUserCnName());
            LetvResponse<Boolean> letvResponse = userService.update(user);
            LOG.info("outputPar:UserController#update.user=" + JsonHelper.toJson(user));
            return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
        } catch (Exception e) {
            LOG.error("error:UserController#update.e=" , e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }


    /**
     * 用户表----更新跳转
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "toUpdatePassword")
    public String toUpdatePassword(Model model) {
        LOG.info("inputPar:UserController#toUpdatePassword");
        try {
            model.addAttribute("userName", getLoginUserName());
        } catch (Exception e) {
            LOG.error("error:UserController#toUpdatePassword.e=" , e);
        }
        return viewPrefix + "/updatePassword";
    }

    /**
     * 修改密码
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value="updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<?> updatePassword(Model model ,User user , HttpServletResponse response,  HttpServletRequest request){
        LOG.info("inputPar:UserController#updatePassword.user=" +JsonHelper.toJson(user));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>() ;
        if(null== user || StringUtils.isEmpty(user.getPassword())
                || StringUtils.isEmpty(user.getOraPassword())
                || StringUtils.isEmpty(user.getConfirmPassword())
                || StringUtils.isEmpty(user.getUserName())){
            letvResponse.setCode(PortalSystemTipCodeEnum.USER_PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.USER_PAR_ERROR.getNote());
            LOG.error("error:UserController#updatePassword.user=" + JsonHelper.toJson(user));
            return letvResponse ;
        }
        if(user.getPassword().equals(user.getOraPassword())){
            letvResponse.setCode(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_ORASAME.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_ORASAME.getNote());
            LOG.error("error:UserController#updatePassword.user=" + JsonHelper.toJson(user));
            return letvResponse ;
        }
        if(!user.getPassword().equals(user.getConfirmPassword())){
            letvResponse.setCode(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_SAMEDOUBLE.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_SAMEDOUBLE.getNote());
            LOG.error("error:UserController#updatePassword.user=" + JsonHelper.toJson(user));
            return letvResponse ;
        }
        if(user.getPassword().length()<10){
            letvResponse.setCode(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_SIZE.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_SIZE.getNote());
            LOG.error("error:UserController#updatePassword.user=" + JsonHelper.toJson(user));
            return letvResponse ;
        }
        try{
            user.setPassword(MD5Util.md5Hex(SystemConstant.passwordPre+user.getPassword()));
            user.setOraPassword(MD5Util.md5Hex(SystemConstant.passwordPre+user.getOraPassword()));
            user.setConfirmPassword(MD5Util.md5Hex(SystemConstant.passwordPre+user.getConfirmPassword()));
            letvResponse = userService.updatePassword(user) ;
            if(letvResponse.getCode()==PortalSystemTipCodeEnum.SCUESS.getValue()){
//                portalWeb.updateInvalidateCookiesAndRedis(request,response,getLoginUserCode());
                RequestContext requestContext = new RequestContext(request);
                model.addAttribute("language", requestContext.getLocale().toString());
            }
            LOG.info("outputPar:UserController#updatePassword.letvResponse=" + JsonHelper.toJson(letvResponse));
            return letvResponse ;
        }catch(Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserController#updatePassword.letvResponse=" + JsonHelper.toJson(letvResponse));
        }
        return letvResponse ;
    }



}
