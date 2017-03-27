package com.letv.tbtSps.service.impl;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.*;
import com.letv.tbtSps.manager.RoleManager;
import com.letv.tbtSps.manager.UserManager;
import com.letv.tbtSps.manager.UserRoleManager;
import com.letv.tbtSps.service.IndexService;
import com.letv.tbtSps.service.ResourceService;
import com.letv.tbtSps.service.RoleResourceService;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
import com.letv.tbtSps.utils.constant.SystemConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-28
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class IndexServiceImpl implements IndexService {


    /** LOG */
    private static final Log LOG = LogFactory.getLog(IndexServiceImpl.class);

    @Autowired
    private UserManager userManager ;


    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private UserRoleManager userRoleManager;

    @Override
    public LetvResponse<User> login(UserQuery userQuery){
        LOG.info("inputPar:IndexServiceImpl#login.userQuery="+ JsonHelper.toJson(userQuery));
        LetvResponse<User> letvResponse = new LetvResponse<User>();
        if(null == userQuery || StringUtils.isEmpty(userQuery.getUserName())
                || StringUtils.isEmpty(userQuery.getPassword())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:IndexServiceImpl#login.userQuery=" + JsonHelper.toJson(userQuery));
            return letvResponse ;
        }

        try{
            List<User> list_user = userManager.queryUserList(userQuery);
            if(CollectionUtils.isEmpty(list_user)){
                letvResponse.setCode(PortalSystemTipCodeEnum.USER_NAMEORPASSWORD_ERROR.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.USER_NAMEORPASSWORD_ERROR.getNote());
            } else if(list_user.size()>1) {
                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
            }else{
                User user = list_user.get(0);
                if(null == user.getUserState() || SystemConstant.YES!=user.getUserState().intValue()){
                    letvResponse.setCode(PortalSystemTipCodeEnum.USER_DISTIBUTE.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.USER_DISTIBUTE.getNote());
                }else{
                    /**
                     * 查询用户是否拥有角色
                     */
                    UserRoleQuery userRoleQuery = new UserRoleQuery () ;
                    userRoleQuery.setUserCode(user.getUserCode());
                    List<UserRole> userRole_list = userRoleManager.queryUserRoleList(userRoleQuery) ;
                    if(CollectionUtils.isEmpty(userRole_list)){
                        letvResponse.setCode(PortalSystemTipCodeEnum.ROLE_USER_NOT_EXISTS.getValue());
                        letvResponse.setMessage(PortalSystemTipCodeEnum.ROLE_USER_NOT_EXISTS.getNote());
                    } else{
                        letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                        letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                        letvResponse.setResult(user);
                    }
                }
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:IndexServiceImpl#login.e=" + e);
        }
        LOG.info("outputPar:IndexServiceImpl#login.letvResponse="+ JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }


    /**
     * 根据用户编码查询用户拥有的资源
     * 1、根据用户编码查询出用户拥有的所有的角色对应的资源
     * 2、根据资源编码查询具体的资源信息（按照PC/App ， 是否菜单 ，查询第几级菜单）
     * @param roleResourceQuery
     * @return
     */
    public LetvResponse<List<TreeDomain>> getLoginUserResource(RoleResourceQuery roleResourceQuery){
        LetvResponse<List<TreeDomain>> letvResponse = new LetvResponse<List<TreeDomain>>() ;
        if(null == roleResourceQuery || StringUtils.isEmpty(roleResourceQuery.getUserCode())
                || StringUtils.isEmpty(roleResourceQuery.getPlatForm())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            return letvResponse ;
        }
        try{
            /**
             * 查询用户拥有的角色下的对应的资源code
             */
            LetvResponse<List<RoleResource>>  letvResponse_list_roleResource = roleResourceService.queryRoleResourceListByUserCode(roleResourceQuery) ;
            if(letvResponse_list_roleResource.getCode()==PortalSystemTipCodeEnum.SCUESS.getValue()){
                List<String> list_resourceCode = new ArrayList<String>() ;
                for(RoleResource roleResource : letvResponse_list_roleResource.getResult()){
                    list_resourceCode.add(roleResource.getResourceCode());
                }
                /**
                 * 查询资源列表
                 */
                ResourceQuery resourceQuery = new ResourceQuery () ;
                resourceQuery.setList_resourceCode(list_resourceCode);
                if(null!=roleResourceQuery.getLevel()){
                    resourceQuery.setLevel(roleResourceQuery.getLevel());
                }
                if(null!=roleResourceQuery.getButtonFlag()){
                    resourceQuery.setButtonflag(roleResourceQuery.getButtonFlag());
                }
                resourceQuery.setPlatfrom(roleResourceQuery.getPlatForm());
                LetvResponse<List<TreeDomain>> letvResponse_list_resource = resourceService.queryResourceListByCodes(resourceQuery) ;
                if(CollectionUtils.isEmpty(list_resourceCode)){
                    letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
                }else{
                    letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                    letvResponse.setResult(letvResponse_list_resource.getResult());
                }
            } else {
                letvResponse.setCode(letvResponse_list_roleResource.getCode());
                letvResponse.setMessage(letvResponse_list_roleResource.getMessage());
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.ERROR.getNote());
        }
        return letvResponse ;
    }
    /**
     * 根据cookieValue获取redis中存储的用户登录信息
     * @param cookieValue
     * @return
     */
    @Override
    public LetvResponse<String> getLoginUserInfoFromRedis(String cookieValue) {
        LOG.info("inputPar:IndexServiceImpl#getLoginUserInfoFromRedis.cookieValue="+cookieValue);
        LetvResponse<String> letvResponse = new LetvResponse<String>();
        if( StringUtils.isEmpty(cookieValue)){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:IndexServiceImpl#getLoginUserInfoFromRedis.cookieValue=" + cookieValue);
            return letvResponse ;
        }
        try{
//            String value = (String) jedisUtil.get(cookieValue);
            String value = "{\"userId\":1,\"userName\":\"ygd\",\"cnName\":\"1\",\"language\":\"zh\",\"userCode\":\"ygd\",\"userType\":1}";
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
            letvResponse.setResult(value);
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:IndexServiceImpl#getLoginUserInfoFromRedis.letvResponse="+JsonHelper.toJson(letvResponse));
        }
        LOG.info("outputPar:IndexServiceImpl#getLoginUserInfoFromRedis.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

}
