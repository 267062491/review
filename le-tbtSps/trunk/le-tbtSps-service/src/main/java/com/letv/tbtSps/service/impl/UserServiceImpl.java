package com.letv.tbtSps.service.impl;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.config.PropertiesHelper;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.security.MD5Util;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.tbtSps.manager.RoleManager;
import com.letv.tbtSps.manager.UserManager;
import com.letv.tbtSps.service.UserService;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
import com.letv.tbtSps.utils.constant.SystemConstant;
import com.letv.wmscommon.dto.PageUtil;
import com.letv.wmscommon.dto.PagedQueryDto;
import com.letv.wmscommon.dto.PagedResultDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserService接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Service
public class UserServiceImpl implements UserService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserManager userManager;
    @Autowired
    private RoleManager roleManager ;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.batchInsert")
    public boolean batchInsert(List<User> userList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(userList)) {
                resultFlag = userManager.insert(userList);
            } else {
                LOG.warn("UserServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.insert")
    public LetvResponse<Boolean> insert(User user) {
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>() ;
        letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
        boolean resultFlag = false;
        try {
            if (null != user) {
                if (userManager.exist(user)) {
                    letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                    letvResponse.setMessage("账号重复");
                } else{
                    resultFlag = userManager.insert(user);
                }
            } else {
                LOG.warn("UserServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("UserServiceImpl#insert failed, user has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("UserServiceImpl#insert has error.", e);
        }
        return letvResponse;
    }



    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.queryUserList")
    public List<User> queryUserList(UserQuery queryBean) {
        List<User> userList = null;
        try {
            userList = userManager.queryUserList(queryBean);
        } catch (Exception e) {
            LOG.error("UserServiceImpl#queryUserList has error.", e);
        }
        return userList;
    }



    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.delete")
    public boolean delete(User user) {
        boolean resultFlag = false;
        try {
            if (null != user && null != user.getId()) {
                resultFlag = userManager.delete(user);
            } else {
                LOG.warn("UserServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.batchDelete")
    public boolean batchDelete(User[] users) {
        boolean resultFlag = false;
        try {
            if (null != users && users.length > 0) {
                resultFlag = userManager.delete(users);
            } else {
                LOG.warn("UserServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }



    /****************************以上是生成代码***********************************************/

    /**
     * 根据用户名和密码查询用户
     * @param queryBean
     * @return
     */
    @Override
    public LetvResponse<User> searchUserByNameAndPassword(UserQuery queryBean) {
        LOG.info("inputPar:UserServiceImpl#searchUserByNameAndPassword.queryBean="+ JsonHelper.toJson(queryBean));
        LetvResponse<User> letvResponse = new LetvResponse<User>();
        if(null==queryBean || StringUtils.isEmpty(queryBean.getUserName())
                || StringUtils.isEmpty(queryBean.getPassword())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#searchUserByNameAndPassword.queryBean="+ JsonHelper.toJson(queryBean));
            return letvResponse ;
        }
        try{
            if(null == queryBean.getUserState()){
                queryBean.setUserState(SystemConstant.YES);
            }
            queryBean.setPassword(MD5Util.md5Hex(SystemConstant.passwordPre + queryBean.getPassword()));
            List<User> list_user = userManager.queryUserList(queryBean) ;
            if(CollectionUtils.isEmpty(list_user)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else if(list_user.size()>1){
                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
            } else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_user.get(0));
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#searchUserByNameAndPassword.e=" ,e);
        }
        LOG.info("outputPar:UserServiceImpl#searchUserByNameAndPassword.letvResponse="+ JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 根据用户编码获取用户信息
     * @param queryBean
     * @return
     */
    @Override
    public LetvResponse<User> searchUserByUserCode(UserQuery queryBean) {
        LOG.info("inputPar:UserServiceImpl#searchUserByUserCode.queryBean="+ JsonHelper.toJson(queryBean));
        LetvResponse<User> letvResponse = new LetvResponse<User>() ;
        if(null == queryBean || StringUtils.isEmpty(queryBean.getUserCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#searchUserByUserCode.queryBean=" + JsonHelper.toJson(queryBean));
            return letvResponse ;
        }
        try{
            if(null == queryBean.getUserState()){
                queryBean.setUserState(SystemConstant.YES);
            }
            List<User> list_user = userManager.queryUserList(queryBean);
            if(CollectionUtils.isEmpty(list_user)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else if(list_user.size()>1){
                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_user.get(0));
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#searchUserByUserCode.e=", e);
        }
        LOG.info("outputPar:UserServiceImpl#searchUserByUserCode.letvResponse="+ JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 根据用户名获取用户信息
     * @param queryBean
     * @return
     */
    @Override
    public LetvResponse<User> searchUserByUserName(UserQuery queryBean) {
        LOG.info("inputPar:UserServiceImpl#searchUserByUserName.queryBean="+ JsonHelper.toJson(queryBean));
        LetvResponse<User> letvResponse = new LetvResponse<User>() ;
        if(null == queryBean || StringUtils.isEmpty(queryBean.getUserName())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#searchUserByUserName.queryBean=" + JsonHelper.toJson(queryBean));
            return letvResponse ;
        }
        try{
            if(null == queryBean.getUserState()){
                queryBean.setUserState(SystemConstant.YES);
            }
            List<User> list_user = userManager.queryUserList(queryBean);
            if(CollectionUtils.isEmpty(list_user)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else if(list_user.size()>1){
                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_user.get(0));
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#searchUserByUserName.e=", e);
        }
        LOG.info("outputPar:UserServiceImpl#searchUserByUserName.letvResponse="+ JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 根据用户登录名或者中文名称查询用户信息
     * @param queryBean
     * @return
     */
    @Override
    public LetvResponse<List<User>> searchUserByCodeOrName(UserQuery queryBean) {
        LOG.info("inputPar:UserServiceImpl#searchUserByCodeOrName.queryBean="+ JsonHelper.toJson(queryBean));
        LetvResponse<List<User>> letvResponse = new LetvResponse<List<User>>() ;
        if(null == queryBean || StringUtils.isEmpty(queryBean.getUserName())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#searchUserByCodeOrName.queryBean="+ JsonHelper.toJson(queryBean));
            return letvResponse ;
        }
        try{
            if(null == queryBean.getUserState()){
                queryBean.setUserState(SystemConstant.YES);
            }
            List<User> list_user = userManager.searchUserByCodeOrName(queryBean);
            if(CollectionUtils.isEmpty(list_user)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            } else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_user);
            }
        } catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#searchUserByCodeOrName.e=", e);
        }
        LOG.info("outputPar:UserServiceImpl#searchUserByCodeOrName.letvResponse="+ JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    @Override
    public LetvResponse<String> getCookieName(){
        LetvResponse<String> letvResponse = new LetvResponse<String>() ;
        try{
            String cookieName = PropertiesHelper.newInstance().getValue("portal.cookie.names") ;
            LOG.info("UserResource#getCookieName.cookieName="+cookieName );
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
            letvResponse.setResult(cookieName);
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserResource#getCookieName.e=", e);
        }
        LOG.info("outputPar:UserResource#getCookieName.letvResponse="+JsonHelper.toJson(letvResponse) );
        return letvResponse ;
    }
    /**
     * 启用、禁用 用户
     * @param user
     * @return
     */
    public LetvResponse<Boolean> enableOrDisable(User user) {
        LOG.info("inputPar:UserResource#enableOrDisable.user="+JsonHelper.toJson(user) );
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(null == user || StringUtils.isEmpty(user.getUserCode())
                || null == user.getUserState()){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserResource#enableOrDisable.user=" + JsonHelper.toJson(user)) ;
            return letvResponse ;
        }
        try {
            boolean resultFlag = userManager.enableOrDisable(user);
            if(resultFlag){
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(resultFlag);
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
                letvResponse.setResult(resultFlag);
            }
        } catch (Exception e) {
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserResource#enableOrDisable.e=",e ) ;
        }
        LOG.info("outputPar:UserResource#enableOrDisable.letvResponse="+JsonHelper.toJson(letvResponse) );
        return letvResponse;
    }


    @Profiled(tag = "UserService.queryUserListWithPage")
    public LetvResponse<PagedResultDto<User>> queryUserListWithPage(PagedQueryDto<UserQuery> pagedQuery) {
        LOG.info("inputPar:UserServiceImpl#queryUserListWithPage.pagedQuery="+JsonHelper.toJson(pagedQuery));
        LetvResponse<PagedResultDto<User>> letvResponse = new LetvResponse<PagedResultDto<User>>();
        try {
            UserQuery queryBean = pagedQuery.getQueryDto();
            PageUtil pageUtil = pagedQuery.getPageUtil();
            List<User> list_user = userManager.queryUserListByRoleCodeWithPage(queryBean, pageUtil);
            /**
             * 根据userCode查询用户拥有的角色 、仓库 并封装页面显示
             */
            List<String> list_userCode = new ArrayList<String>();
            for(User user : list_user){
                list_userCode.add(user.getUserCode()) ;
            }
            if(CollectionUtils.isNotEmpty(list_userCode)){
                RoleQuery roleQuery = new RoleQuery () ;
                roleQuery.setList_userCode(list_userCode);
                List<Role> list_role = roleManager.queryRoleListByUserCodes(roleQuery) ;
                for(User user : list_user){
                    for(Role role : list_role){
                        if(user.getUserCode().equals(role.getUserCode())){
                            if(StringUtils.isNotEmpty(queryBean.getRoleCode())){
                                user.setRoleName(role.getRoleName());
                                break ;
                            }else{
                                if(StringUtils.isEmpty(user.getRoleName())){
                                    user.setRoleName(role.getRoleName());
                                }else if(StringUtils.isNotEmpty(user.getRoleName()) &&!user.getRoleName().contains("……")){
                                    user.setRoleName(user.getRoleName()+" ……");
                                }
                            }
                        }
                    }
                    if(StringUtils.isEmpty(user.getRoleName())){
                        user.setRoleName("--");
                    }
                }
            }
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
            PagedResultDto pagedResultDto = new PagedResultDto(list_user,pageUtil);
            letvResponse.setResult(pagedResultDto);
        } catch (Exception e) {
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#queryUserListWithPage.e=",e);
        }
        LOG.info("outputPar:UserServiceImpl#queryUserListWithPage.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }
    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.getUserById")
    public LetvResponse<User> getUserById(UserQuery userQuery) {
        LOG.info("inputPar:UserServiceImpl#queryUserListWithPage.userQuery="+JsonHelper.toJson(userQuery));
        LetvResponse<User> letvResponse = new LetvResponse<User>() ;
        if(null==userQuery || null == userQuery.getId()){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#queryUserListWithPage.userQuery="+JsonHelper.toJson(userQuery));
            return letvResponse ;
        }
        try {
            User user = userManager.getUserById(userQuery.getId());
            List<String> list_userCode = new ArrayList<String>();
            list_userCode.add(user.getUserCode()) ;
            RoleQuery roleQuery = new RoleQuery () ;
            roleQuery.setList_userCode(list_userCode);
            List<Role> list_role = roleManager.queryRoleListByUserCodes(roleQuery) ;
            for(Role role : list_role){
                if(StringUtils.isEmpty(user.getRoleName())){
                    user.setRoleName(role.getRoleName());
                }else{
                    user.setRoleName(user.getRoleName()+","+role.getRoleName());
                }
            }
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
            letvResponse.setResult(user);
        } catch (Exception e) {
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#queryUserListWithPage.e=",e);
        }
        LOG.info("outputPar:UserServiceImpl#queryUserListWithPage.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse;
    }
    /**
     * 根据用户编码删除用户信息
     * @param user
     * @return
     */
    @Override
    public LetvResponse<Boolean> deleteByUserCode(User user){
        LOG.info("inputPar:UserServiceImpl#deleteByUserCode.user="+JsonHelper.toJson(user));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(null == user || StringUtils.isEmpty(user.getUserCode())
                || StringUtils.isEmpty(user.getUpdateUser())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#deleteByUserCode.user="+JsonHelper.toJson(user));
            return letvResponse ;
        }
        try{
            Boolean ret = userManager.deleteByUserCode(user);
            if(ret){
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(ret);
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
            }

        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#deleteByUserCode.e=",e);
        }
        LOG.info("outputPar:UserServiceImpl#deleteByUserCode.letvResponse="+JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserService.update")
    public LetvResponse<Boolean> update(User user) {
        LOG.info("inputPar:UserServiceImpl#update.user="+JsonHelper.toJson(user));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(null == user || StringUtils.isEmpty(user.getUserCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#update.user="+JsonHelper.toJson(user));
            return letvResponse ;
        }
        try {
            boolean resultFlag = userManager.updateByUserCode(user);
            if(resultFlag){
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(resultFlag);
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
            }
        } catch (Exception e) {
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#update.e=",e);
        }
        LOG.info("outputPar:UserServiceImpl#update.user=" + JsonHelper.toJson(user));
        return letvResponse;
    }

    /**
     * 批量上传用户信息
     * @param list
     * @return
     */
    @Override
    public LetvResponse<Boolean> uploadUsers(List<User> list) {
        LOG.info("inputPar:UserServiceImpl#uploadUsers.list=" + JsonHelper.toJson(list));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(CollectionUtils.isEmpty(list)){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#uploadUsers.list=" + JsonHelper.toJson(list));
            return letvResponse ;
        }
        StringBuffer message = new StringBuffer();
        try{
            /**
             * 校验excel数据
             */
            this.checkExcelData(list, message);
            /**
             * 保存用户信息
             */
            if(StringUtils.isEmpty(String.valueOf(message))){
                boolean ret = userManager.saveUsers(list);
                if(ret){
                    letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                    letvResponse.setResult(ret);
                }else{
                    letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
                    letvResponse.setResult(ret);
                }
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.UPLOAD_EXCEL_FAIL.getValue());
                letvResponse.setMessage(String.valueOf(message));
                letvResponse.setResult(false);
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#uploadUsers.e=" , e);
        }
        LOG.info("outputPar:UserServiceImpl#uploadUsers.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse;
    }


    /**
     * 1、校验excel中的用户账号不能重复
     * 2、校验用户账号是否存在于数据库中
     * 3、校验角色编码是否存在于数据库中
     * 4、校验仓库编码是否存在于数据库中
     * 5、校验各个项的长度
     * @param list
     * @param message
     */
    private void checkExcelData(List<User> list, StringBuffer message) {
        LOG.info("inputPar:UserServiceImpl#uploadUsers.checkExcelData=" + JsonHelper.toJson(list));
        Map<String,String> map_userName = new HashMap <String,String>();
        List<String> list_userName = new ArrayList<String>();
        List<String> list_roleCode = new ArrayList<String>();
        List<String> list_warehouseNo = new ArrayList<String>();
        for(User user : list){
            /**
             * 校验用户名是否在excel中重复
             */
            if(StringUtils.isNotEmpty(map_userName.get(user.getUserName()))){
                message.append(user.getUserName()).append("在excel中重复；");
            }else{
                map_userName.put(user.getUserName(),user.getUserName());
            }
            /**
             * 把用户名放到list中，一会查询数据库，看是否有存在的
             */
            list_userName.add(user.getUserName());
            /**
             * 把用户角色编码放到list中，一会查询数据库， 看是否有存在的
             */
            LOG.info("juese="+user.getRoleName());
            if(StringUtils.isNotEmpty(user.getRoleName())){
                LOG.info("juese1="+user.getRoleName());
                String[] roleNames = user.getRoleName().split(",");
                LOG.info("juese2="+JsonHelper.toJson(roleNames));
                for(String roleName : roleNames){
                    list_roleCode.add(roleName);
                }
                LOG.info("juese3="+JsonHelper.toJson(list_roleCode));
            }
            /**
             * 把仓库编码放到list中，一会查询数据库， 看是否有存在的
             */
            if(StringUtils.isNotEmpty(user.getWarehouseName())){
                String[] warehouseNames = user.getWarehouseName().split(",");
                for(String warehouseName : warehouseNames){
                    list_warehouseNo.add(warehouseName);
                }
            }
            /**
             * 校验各个项的长度和空
             */
            if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getShowName())){
                message.append("用户账号/用户名不能为空;") ;
            }else if(user.getUserName().length()>50){
                message.append(user.getUserName()).append("：用户账号不能多于50个字符;") ;
                message.append(user.getShowName()).append("：用户名不能多于50个字符;") ;
            }
            if(StringUtils.isNotEmpty(user.getDuty()) && user.getDuty().length()>50){
                message.append(user.getDuty()).append("：职位不能多于50个字符;") ;
            }
            if(StringUtils.isNotEmpty(user.getEmail()) && user.getEmail().length()>50){
                message.append(user.getEmail()).append("：邮箱不能多于50个字符;") ;
            }
            if(StringUtils.isNotEmpty(user.getPhoneno()) && user.getPhoneno().length()>50){
                message.append(user.getPhoneno()).append("：联系方式不能多于50个字符;") ;
            }
            if(StringUtils.isNotEmpty(user.getUserAddress()) && user.getUserAddress().length()>200){
                message.append(user.getUserAddress()).append("：地址不能多于200个字符;") ;
            }
            if(StringUtils.isNotEmpty(user.getNote()) && user.getNote().length()>400){
                message.append(user.getNote()).append("：备注不能多于400个字符;") ;
            }
        }

        /**
         * 批量查询用户、角色、仓库
         */
        if(CollectionUtils.isNotEmpty(list_roleCode)){
            List<Role> list_role = roleManager.queryRoleListByRoleCodes(list_roleCode);
            if(CollectionUtils.isNotEmpty(list_role)){
                for(Role role : list_role){
                    boolean flag = false ;
                    for(String roleCode : list_roleCode){
                        if(role.getRoleCode().equals(roleCode)){
                            flag = true ;
                            break ;
                        }
                    }
                    if(!flag){
                        message.append(role.getRoleCode()).append("：角色在数据库中不存在；");
                    }
                }
            } else{
                for(String roleCode : list_roleCode){
                    message.append(roleCode).append("：角色在数据库中不存在；");
                }
            }
        }

        List<User> list_user = userManager.searchUserByUserNames(list_userName);
        if(CollectionUtils.isNotEmpty(list_user)){
            for(User user : list_user){
                message.append(user.getUserName()).append(":用户在数据库中已经存在；");
            }
        }
        LOG.info("outputPar:UserServiceImpl#uploadUsers.message=" + JsonHelper.toJson(message));
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @Override
    public LetvResponse<Boolean> updatePassword(User user) {
        LOG.info("inputPar:UserServiceImpl#updatePassword.user=" + JsonHelper.toJson(user));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(null== user || StringUtils.isEmpty(user.getPassword())
                || StringUtils.isEmpty(user.getOraPassword())
                || StringUtils.isEmpty(user.getConfirmPassword())
                || StringUtils.isEmpty(user.getUserName())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#updatePassword.user=" + JsonHelper.toJson(user));
            return letvResponse ;
        }
        if(user.getPassword().equals(user.getOraPassword())){
            letvResponse.setCode(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_ORASAME.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_ORASAME.getNote());
            LOG.error("error:UserServiceImpl#updatePassword.user=" + JsonHelper.toJson(user));
            return letvResponse ;
        }
        if(!user.getPassword().equals(user.getConfirmPassword())){
            letvResponse.setCode(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_SAMEDOUBLE.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.USER_UPDATE_PASSWORD_SAMEDOUBLE.getNote());
            LOG.error("error:UserServiceImpl#updatePassword.user=" + JsonHelper.toJson(user));
            return letvResponse ;
        }
        try{
            UserQuery queryBean = new UserQuery () ;
            queryBean.setUserName(user.getUserName());
            queryBean.setPassword(user.getOraPassword());
            List<User> list_user = userManager.queryUserList(queryBean) ;
            if(CollectionUtils.isEmpty(list_user)){
                letvResponse.setCode(PortalSystemTipCodeEnum.USER_UPDATE_ORA_PASSWORD_ERROR.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.USER_UPDATE_ORA_PASSWORD_ERROR.getNote());
            } else if(list_user.size()>1) {
                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
            }else{
                User u = list_user.get(0);
                User user_update = new User() ;
                user_update.setUserCode(u.getUserCode());
                user_update.setPassword(user.getPassword());
                boolean ret = userManager.updateByUserCode(user_update) ;
                if(ret){
                    letvResponse.setCode(PortalSystemTipCodeEnum.USER_SCUESS.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.USER_SCUESS.getNote());
                    letvResponse.setResult(ret);
                }else{
                    letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
                }
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#updatePassword.e=" , e);
        }
        LOG.info("outputPar:UserServiceImpl#updatePassword.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse;
    }
    /**
     * 根据用户编码查询用户信息，批量接口
     * @param userQuery
     * @return
     */
    @Override
    public LetvResponse<List<User>> searchUserByCodes(UserQuery userQuery) {
        LOG.info("inputPar:UserServiceImpl#searchUserByCodes.userQuery=" + JsonHelper.toJson(userQuery));
        LetvResponse<List<User>> letvResponse = new LetvResponse<List<User>>() ;
        if(null == userQuery || CollectionUtils.isEmpty(userQuery.getUserCodes())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserServiceImpl#searchUserByCodes.userQuery=" + JsonHelper.toJson(userQuery));
            return letvResponse ;
        }
        letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
        letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
        try{
            List<User> list = userManager.searchUserByCodes(userQuery);
            letvResponse.setResult(list);
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:UserServiceImpl#searchUserByCodes.e=" , e);
        }
        LOG.info("outputPar:UserServiceImpl#searchUserByCodes.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    public List<User> queryUserByRoleCodeNoPage(UserQuery queryBean) {
        return userManager.queryUserByRoleCodeNoPage(queryBean);
    }
}

