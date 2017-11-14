package com.letv.tbtSps.common.util;

import com.letv.common.utils.security.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-9-19
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class LsCookieUtils {

    private final static Log logger = LogFactory.getLog(LsCookieUtils.class);



    /**
     * 获取 cookie
     *
     * @param request
     *            HttpServletRequest
     * @param cookieName
     *            cookie名称
     * @return Cookie对象
     */
    private static Cookie getCookieInfo(HttpServletRequest request, String cookieName) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    /**
     * 读取Cookie的值:直接返回cookie的值，不编码
     *
     * @param request
     *            HttpServletRequest
     * @param name
     *            cookie名称
     * @return Cookie的值
     */
    public static String getCookieValueSimple(HttpServletRequest request, String name) {
        String value = null;
        Cookie cookie = LsCookieUtils.getCookieInfo(request, name);
        if (cookie != null) {
            value = cookie.getValue();
            logger.debug("Found cookie value [" + value + "]");
        } else {
            logger.debug("No cookie value be found for cookieName '{" + name + "}'");
        }
        return value;
    }


    /**
     * 写入Cookie:对cookie的值，进行Base64编码后存储 然后md5存储
     *
     * @param response
     *            HttpServletResponse
     * @param name
     *            cookie名称
     *            cookie值
     * @param domain
     *            cookie域
     * @param path
     *            cookie Path
     */
    public static void setCookie(HttpServletRequest request,HttpServletResponse response, String name
            , String cookieValue, String domain, String path,String userCode) {
        /**
         * 把cookie value写入到session里面， 然后把sessionID 放到cookie里面 就可以了
         */
        HttpSession session = request.getSession();
        session.setAttribute(userCode,cookieValue);
        String sessionId = session.getId();
        int expiry = 3 * 60 * 60*10000;// 有效期为3小时
        Cookie cookie = new Cookie(name,sessionId+"_"+userCode );
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(expiry); // 设置cookie有效期为3小时
        logger.info("LsCookieUtils.setCookie="+JsonUtil.toJson(cookie));
        response.addCookie(cookie);
    }

    /**
     * 设置cookie失效
     *
     * @param response
     *            HttpServletResponse
     * @param name
     *            cookie名称
     * @param domain
     *            cookie域
     * @param path
     *            cookie Path
     */
    public static void invalidateCookie(HttpServletResponse response, String name, String domain, String path) {
        Cookie cookie = new Cookie(name, "");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(0); // 使cookie失效
        response.addCookie(cookie);
    }

    /**
     * 生成cookieValue
     * @param value
     * @param platform
     * @param environment
     * @return
     */
    public static String createCookieValue(String value, String platform , String environment){
        String cookieValue = null;
        try {
//            cookieValue = environment+"."+platform+"."+MD5Util.md5Hex(UUID.randomUUID().toString());
            cookieValue = value;
        } catch (Exception e) {
            logger.warn("setCookie has error, " + e.getMessage());
            return cookieValue;
        }
        return cookieValue ;
    }



}
