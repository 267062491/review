package com.letv.tbtSps.common.interceptor;

import com.letv.tbtSps.common.cotext.UserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.letv.tbtSps.domain.query.ResourceQuery;
//import com.letv.tbtSps.service.ResourceService;
//import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;

/**
 * 用户访问授权的拦截器，基于权限系统的资源码或者请求的URL。<br/>
 * 主要作用：<br/>
 * 拦截用户请求，获取并解析登录的用户cookie；<br/>
 * 判断用户是否有权限访问，如果没权限，则跳转到没权限的界面。
 * 
 *
 * @author yuguodong
 * 
 */
public class UrlPermissionInterceptor extends AbstractHandlerInterceptorAdapter {
//    @Autowired
//    private ResourceService resourceService;
    /** 特殊参数 UID */
    protected String SPECIAL_PARAM_UID = "UID";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (getContext().isExclude(uri)) {
            LOG.debug("in request [" + uri + "], this url is excluded");
            return true;
        } else {
            LOG.debug("in request [" + uri + "], this url is not excluded");
        }

        UserContext context = UserContext.get();
        if (context == null || !context.isLogin()) {// 没登录
            LOG.error("用户没登录");
            return false;
        }
        boolean result = true;
        String resource = null;
        String userCode = context.getUserCode();

        try {
//            resource = request.getRequestURI().replaceFirst("/","");
//            // 权限验证逻辑：基于URL
//            ResourceQuery resourceQuery = new ResourceQuery () ;
//            resourceQuery.setUserCode(userCode);
//            resourceQuery.setUrl(resource);
//            LetvResponse<Boolean> letvResponse = resourceService.isPermitted(resourceQuery);
//            if(LetvResponse.SUCCESS_CODE==letvResponse.getCode()){
//                result = true ;
//            }else if (letvResponse.getCode()== PortalSystemTipCodeEnum.RESOURCE_NOT_EXISTS.getValue()) { // 如果验证失败，跳转到无权限访问资源的错误提示界面
//                LOG.error("系统未定义资源" + resource+"用户" + context.getUserName() + "]不能访问");
//                request.setAttribute("messageCode",PortalSystemTipCodeEnum.RESOURCE_NOT_EXISTS.getValue());
//                response.sendRedirect(getContext().getNoPermissionUrl(request, context.getUserName(), resource));
//                return false;
//            }else if (letvResponse.getCode()== PortalSystemTipCodeEnum.RESOURCE_USER_NOT_HAVE.getValue()) { // 如果验证失败，跳转到无权限访问资源的错误提示界面
//                LOG.error("用户[" + context.getUserName() + "]没权限访问资源" + resource);
//                request.setAttribute("messageCode",PortalSystemTipCodeEnum.RESOURCE_USER_NOT_HAVE.getValue());
//                response.sendRedirect(getContext().getNoPermissionUrl(request, context.getUserName(), resource));
//                return false;
//            } else {
//                LOG.error("用户[" + context.getUserName() + "]因为其他原因不能访问资源" + resource);
//                result = false ;
//            }

        } catch (Exception e) {
            LOG.error("UrlPermissionInterceptor has error,", e);
        }
        return result ;
    }

}
