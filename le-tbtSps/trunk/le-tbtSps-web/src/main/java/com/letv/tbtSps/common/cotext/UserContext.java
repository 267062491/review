package com.letv.tbtSps.common.cotext;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * UserContext:在上下文保存登录的用户信息类 <br/>
 * 
 * @see LoginUser
 * @author yuguodong
 *
 */
public class UserContext {

    private LoginUser user;

    private final static ThreadLocal<UserContext> holder = new ThreadLocal<UserContext>() {
        protected UserContext initialValue() {
            return new UserContext();
        }
    };

    private UserContext() {
    }

    /** 生产并保存 userContext，实际存在 ThreadLocal中 */
    public static void set(LoginUser user) {
        UserContext context = new UserContext();
        context.setUser(user);
        holder.set(context);
    }

    /**
     * 取出UserContext
     */
    public static UserContext get() {
        return holder.get();
    }

    /**
     * 删除上下文、其实一般不用删除
     */
    public static void remove() {
        holder.remove();
    }

    /**
     * 从 UserContext 取得登录用户
     *
     * @return
     */
    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    public Long getUserId() {
        checkUser();
        return user.getUserId();
    }

    public String getCnName() {
        checkUser();
        return user.getCnName();
    }

    public String getUserName() {
        checkUser();
        return user.getUserName();
    }
    public String getUserCode() {
        checkUser();
        return user.getUserCode();
    }

    private void checkUser() {
        Assert.notNull(user, "[Assertion failed] - this user is required; it must not be null");
    }

    /**
     * 判断是否登录。标准：isNotBlank(getUserName())
     * 
     * @return true 已经登录 false 没有登录
     */
    public boolean isLogin() {
        try {
            return StringUtils.isNotBlank(getUserName());
        } catch (Exception e) {
            return false;
        }
    }

}
