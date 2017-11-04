package com.letv.tbtSps.common.controller;


import com.letv.common.utils.DateHelper;
import com.letv.tbtSps.common.cotext.LoginUser;
import com.letv.tbtSps.common.cotext.UserContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基本控制器：提供通用的方法
 * 
 * @author yuguodong
 */
public abstract class ReviewBaseController extends WrapController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    /**
     * init binder, set datetime format
     * 
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat(DateHelper.DATE_TIME_FORMAT);
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 获取登录用户信息<br/>
     * 改方法从cookie获取，只有ID,账号，中文姓名等简单字段，<br/>
     * 如需详细信息，请使用getUser()方法。
     * 
     * @return 用户信息
     */
    public LoginUser getLoginUser() {

        LoginUser loginUser = UserContext.get().getUser();
        if(null==loginUser){
            loginUser = new LoginUser();
            loginUser.setUserId(1L);
            loginUser.setUserName("liucaifeng");
            loginUser.setCnName("刘彩凤");
            loginUser.setWarehouseNo("1");
            loginUser.setWarehouseName("刘彩凤的仓库");
            loginUser.setLanguage("zh");
            loginUser.setUserCode("liucaifeng");
            loginUser.setWarehouseSimpleCode("LCF");
            loginUser.setUserType(1);
        }
        Assert.notNull(loginUser, "loginUser must be not null");
        return loginUser;
    }

    /**
     * 获取登录用户ID
     *
     * @return
     */
    public Long getLoginUserId() {
        return getLoginUser().getUserId();
    }

    /**
     * 获取登录用户名
     * 
     * @return
     */
    public String getLoginUserName() {
        return getLoginUser().getUserName();

    }

    /**
     * 获取登录用户中文名
     * 
     * @return
     */
    public String getLoginUserCnName() {
        return getLoginUser().getCnName();
    }

    /**
     * 获取登录用户当前仓库编码
     * @return
     */
    public String getLoginUserWarehouseNo() {
        return getLoginUser().getWarehouseNo();
    }

    /**
     * 获取登录用户当前登录仓库名称
     * @return
     */
    public String getLoginUserWarehouseName() {
        return getLoginUser().getWarehouseName();
    }
    /**
     * 获取登录用户当前语言
     * @return
     */
    public String getLoginUserLanguage(){
        return  getLoginUser().getLanguage();
    }

    /**
     * 获取登录用户当前编码
     * @return
     */
    public String getLoginUserCode(){
        return  getLoginUser().getUserCode();
    }

    /**
     * 获取登录用户的库房简码
     * @return
     */
    public String getLoginUserWarehouseSimpleCode(){
        return getLoginUser().getWarehouseSimpleCode();
    }

    /**
     * 获取用户类型
     * @return
     */
    public int getLoginUserType(){
        try{
            return getLoginUser().getUserType().intValue();
        } catch (Exception e){
            return 1 ;
        }
    }
}
