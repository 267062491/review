package com.letv.tbtSps.service.impl;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.manager.RoleManager;
import com.letv.tbtSps.service.RoleService;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
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

import java.util.List;

/**
 * RoleService接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Service
public class RoleServiceImpl implements RoleService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RoleServiceImpl.class);

    @Autowired
    private RoleManager roleManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.batchInsert")
    public boolean batchInsert(List<Role> roleList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(roleList)) {
                resultFlag = roleManager.insert(roleList);
            } else {
                LOG.warn("RoleServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.insert")
    public boolean insert(Role role) {
        boolean resultFlag = false;
        try {
            if (null != role) {
                if (roleManager.exist(role)) {
                    throw new ExistedException();
                }
                resultFlag = roleManager.insert(role);
            } else {
                LOG.warn("RoleServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RoleServiceImpl#insert failed, role has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.update")
    public boolean update(Role role) {
        boolean resultFlag = false;
        try {
            if (null != role && null != role.getId()) {
                resultFlag = roleManager.update(role);
            } else {
                LOG.warn("RoleServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.queryRoleList")
    public List<Role> queryRoleList(RoleQuery queryBean) {
        List<Role> roleList = null;
        try {
            roleList = roleManager.queryRoleList(queryBean);
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#queryRoleList has error.", e);
        }
        return roleList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.queryRoleListWithPage")
    public PagedResultDto<Role> queryRoleListWithPage(PagedQueryDto<RoleQuery> pagedQuery) {
        RoleQuery queryBean = pagedQuery.getQueryDto();
        PageUtil pageUtil = pagedQuery.getPageUtil();
        List<Role> roleList = null;
        try {
            roleList = roleManager.queryRoleListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#queryRoleListWithPage has error.", e);
        }
        return new PagedResultDto(roleList,pageUtil);
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.delete")
    public boolean delete(Role role) {
        boolean resultFlag = false;
        try {
            if (null != role && null != role.getId()) {
                resultFlag = roleManager.delete(role);
            } else {
                LOG.warn("RoleServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.batchDelete")
    public boolean batchDelete(Role[] roles) {
        boolean resultFlag = false;
        try {
            if (null != roles && roles.length > 0) {
                resultFlag = roleManager.delete(roles);
            } else {
                LOG.warn("RoleServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RoleService.getRoleById")
    public Role getRoleById(Long id) {
        Role role = null;
        try {
            if (null != id) {
                role = roleManager.getRoleById(id);
            } else {
                LOG.warn("RoleServiceImpl#getRoleById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RoleServiceImpl#getRoleById has error.", e);
        }
        return role;
    }

    /**
     * 查询角色列表
     * @param roleQuery
     * @return
     */
    public LetvResponse<List<Role>> queryRoleListLe(RoleQuery roleQuery){
        LOG.info("inputPar:RoleServiceImpl#queryRoleListLe.roleQuery=" + JsonHelper.toJson(roleQuery));
        LetvResponse<List<Role>> letvResponse = new LetvResponse<List<Role>>();
        try{
            List<Role> list_role = roleManager.queryRoleList(roleQuery);
            if(CollectionUtils.isEmpty(list_role)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_role);
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleServiceImpl#queryRoleListLe.e=",e );
        }
        LOG.info("outputPar:RoleServiceImpl#queryRoleListLe.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }

    /**
     * 根据用户编码查询用户拥有的角色
     * @param userRoleQuery
     * @return
     */
    public LetvResponse<List<Role>> queryRoleListByUserCode(UserRoleQuery userRoleQuery){
        LOG.info("inputPar:RoleServiceImpl#queryChooseRoleListByUserCode.userRoleQuery=" + JsonHelper.toJson(userRoleQuery));
        LetvResponse<List<Role>> letvResponse = new LetvResponse<List<Role>>() ;
        if(null == userRoleQuery || StringUtils.isEmpty(userRoleQuery.getUserCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleServiceImpl#queryChooseRoleListByUserCode.userRoleQuery=" + JsonHelper.toJson(userRoleQuery));
            return letvResponse ;
        }
        try{
            List<Role> list_role = roleManager.queryRoleListByUserCode(userRoleQuery);
            if(CollectionUtils.isEmpty(list_role)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_role);
            }

        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleServiceImpl#queryChooseRoleListByUserCode.e=" , e);
        }
        LOG.info("outputPar:RoleServiceImpl#queryChooseRoleListByUserCode.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }

    /**
     * 根据用编码， 查询用户可选择的角色
     * @param userRoleQuery
     * @return
     */
    public  LetvResponse<List<Role>> queryChooseRoleListByUserCode(UserRoleQuery userRoleQuery){
        LOG.info("inputPar:RoleServiceImpl#queryChooseRoleListByUserCode.userRoleQuery=" + JsonHelper.toJson(userRoleQuery));
        LetvResponse<List<Role>> letvResponse = new LetvResponse<List<Role>>() ;
        if(null == userRoleQuery || StringUtils.isEmpty(userRoleQuery.getUserCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleServiceImpl#queryChooseRoleListByUserCode.userRoleQuery=" + JsonHelper.toJson(userRoleQuery));
            return letvResponse ;
        }
        try{
            List<Role> list_role = roleManager.queryChooseRoleListByUserCode(userRoleQuery);;
            if(CollectionUtils.isEmpty(list_role)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_role);
            }

        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleServiceImpl#queryChooseRoleListByUserCode.e="+e);
        }
        LOG.info("outputPar:RoleServiceImpl#queryChooseRoleListByUserCode.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }

    /**
     * 根据角色编码查询角色信息
     * @param roleQuery
     * @return
     */
    @Override
    public LetvResponse<Role> getRoleByCode(RoleQuery roleQuery) {
        LOG.info("inputPar:RoleServiceImpl#getRoleByCode.roleQuery=" + JsonHelper.toJson(roleQuery));
        LetvResponse<Role> letvResponse = new LetvResponse<Role>() ;
        if(null==roleQuery || StringUtils.isEmpty(roleQuery.getRoleCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleServiceImpl#getRoleByCode.roleQuery=" + JsonHelper.toJson(roleQuery));
            return letvResponse ;
        }
        try{
            List<Role> list_role = roleManager.queryRoleList(roleQuery);
            if(CollectionUtils.isEmpty(list_role)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            }else if(list_role.size()>1){
                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
            }else{
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(list_role.get(0));
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleServiceImpl#getRoleByCode.e=" + e);
        }
        LOG.info("outputPar:RoleServiceImpl#getRoleByCode.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 插入角色
     * @param role
     * @return
     */
    public LetvResponse<Boolean> insertRole(Role role) {
        LOG.info("inputPar:RoleServiceImpl#insertRole.role=" + JsonHelper.toJson(role));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(null == letvResponse || StringUtils.isEmpty(role.getRoleCode())
                || StringUtils.isEmpty(role.getRoleName())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleServiceImpl#insertRole.role=" + JsonHelper.toJson(role));
            return letvResponse ;
        }
        try{
            boolean exists = roleManager.exist(role);
            if(exists){
                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
            }else{
                boolean resultFlag = roleManager.insert(role);
                if(resultFlag){
                    letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                    letvResponse.setResult(resultFlag);
                }else{
                    letvResponse.setCode(PortalSystemTipCodeEnum.UPDATE_FAIL.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.UPDATE_FAIL.getNote());
                }
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleServiceImpl#insertRole.e=" , e);
        }
        LOG.info("outputPar:RoleServiceImpl#insertRole.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse;
    }
    /**
     * 更新角色
     * @param role
     * @return
     */
    public LetvResponse<Boolean> updateRole(Role role) {
        LOG.info("inputPar:RoleServiceImpl#updateRole.role=" + JsonHelper.toJson(role));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(null == letvResponse || StringUtils.isEmpty(role.getRoleCode())
                || StringUtils.isEmpty(role.getRoleName())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleServiceImpl#updateRole.role=" + JsonHelper.toJson(role));
            return letvResponse ;
        }
        try{
            RoleQuery queryBean = new RoleQuery() ;
            queryBean.setRoleCode(role.getRoleCode());
            queryBean.setRoleType(role.getRoleType());
            List<Role> list_role = roleManager.queryRoleList(queryBean);
            if(CollectionUtils.isEmpty(list_role)){
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
            } else if(list_role.size()>1){
                letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
            } else {
                boolean resultFlag = false;
                Role r = list_role.get(0);
                if(r.getRoleName().equals(role.getRoleName())){
                    resultFlag = roleManager.update(role);
                    letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                    letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                    letvResponse.setResult(resultFlag);
                }else{
                    queryBean = new RoleQuery() ;
                    queryBean.setRoleName(role.getRoleName());
                    List<Role> list_role1 = roleManager.queryRoleList(queryBean);
                    if(CollectionUtils.isNotEmpty(list_role1)){
                        letvResponse.setCode(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue());
                        letvResponse.setMessage(PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getNote());
                    }else{
                        resultFlag = roleManager.update(role);
                        letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                        letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                        letvResponse.setResult(resultFlag);
                    }
                }
            }
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleServiceImpl#updateRole.e=" , e);
        }
        LOG.info("outputPar:RoleServiceImpl#updateRole.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 根据角色编码删除角色
     * @param role
     * @return
     */
    public LetvResponse<Boolean> deleteRole(Role role) {
        LOG.info("inputPar:RoleServiceImpl#deleteRole.role=" + JsonHelper.toJson(role));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(null == letvResponse || StringUtils.isEmpty(role.getRoleCode())){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleServiceImpl#deleteRole.role=" + JsonHelper.toJson(role));
            return letvResponse ;
        }
        try{
            boolean resultFlag = roleManager.delete(role);
            letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
            letvResponse.setResult(resultFlag);
        }catch (Exception e){
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
            LOG.error("error:RoleServiceImpl#deleteRole.e=" + e);
        }
        LOG.info("outputPar:RoleServiceImpl#deleteRole.letvResponse=" + JsonHelper.toJson(letvResponse));
        return letvResponse;
    }

    /**
     * 启用、禁用 角色
     * @param role
     * @return
     */
    public LetvResponse<Boolean> enableOrDisable(Role role) {
        LOG.info("inputPar:RoleResource#enableOrDisable.role="+JsonHelper.toJson(role) );
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>();
        if(null == role || StringUtils.isEmpty(role.getRoleCode())
                || null == role.getEnable()){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:RoleResource#enableOrDisable.role=" + JsonHelper.toJson(role)) ;
            return letvResponse ;
        }
        try {
            boolean resultFlag = roleManager.enableOrDisable(role);
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
            LOG.error("error:RoleResource#enableOrDisable.e=",e ) ;
        }
        LOG.info("outputPar:RoleResource#enableOrDisable.letvResponse="+JsonHelper.toJson(letvResponse) );
        return letvResponse;
    }

    public void dd(){
        new UserServiceImpl(){
            public LetvResponse<Boolean> insert(User user){
                roleManager.queryRoleListByRoleCodes(null);
                return  null ;
            };
        };
    }
}

