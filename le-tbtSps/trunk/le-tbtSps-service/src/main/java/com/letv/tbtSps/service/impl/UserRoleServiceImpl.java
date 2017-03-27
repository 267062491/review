package com.letv.tbtSps.service.impl;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.manager.UserRoleManager;
import com.letv.tbtSps.service.UserRoleService;
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
 * UserRoleService接口的实现类
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleManager userRoleManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.batchInsert")
    public boolean batchInsert(List<UserRole> userRoleList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                resultFlag = userRoleManager.insert(userRoleList);
            } else {
                LOG.warn("UserRoleServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.insert")
    public boolean insert(UserRole userRole) {
        boolean resultFlag = false;
        try {
            if (null != userRole) {
                if (userRoleManager.exist(userRole)) {
                    throw new ExistedException();
                }
                resultFlag = userRoleManager.insert(userRole);
            } else {
                LOG.warn("UserRoleServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("UserRoleServiceImpl#insert failed, userRole has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.update")
    public boolean update(UserRole userRole) {
        boolean resultFlag = false;
        try {
            if (null != userRole && null != userRole.getId()) {
                resultFlag = userRoleManager.update(userRole);
            } else {
                LOG.warn("UserRoleServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.queryUserRoleList")
    public List<UserRole> queryUserRoleList(UserRoleQuery queryBean) {
        List<UserRole> userRoleList = null;
        try {
            userRoleList = userRoleManager.queryUserRoleList(queryBean);
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#queryUserRoleList has error.", e);
        }
        return userRoleList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.queryUserRoleListWithPage")
    public PagedResultDto<UserRole> queryUserRoleListWithPage(PagedQueryDto<UserRoleQuery> pagedQuery) {
        UserRoleQuery queryBean = pagedQuery.getQueryDto();
        PageUtil pageUtil = pagedQuery.getPageUtil();
        List<UserRole> userRoleList = null;
        try {
            userRoleList = userRoleManager.queryUserRoleListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#queryUserRoleListWithPage has error.", e);
        }
        return new PagedResultDto(userRoleList,pageUtil);
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.delete")
    public boolean delete(UserRole userRole) {
        boolean resultFlag = false;
        try {
            if (null != userRole && null != userRole.getId()) {
                resultFlag = userRoleManager.delete(userRole);
            } else {
                LOG.warn("UserRoleServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.batchDelete")
    public boolean batchDelete(UserRole[] userRoles) {
        boolean resultFlag = false;
        try {
            if (null != userRoles && userRoles.length > 0) {
                resultFlag = userRoleManager.delete(userRoles);
            } else {
                LOG.warn("UserRoleServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "UserRoleService.getUserRoleById")
    public UserRole getUserRoleById(Long id) {
        UserRole userRole = null;
        try {
            if (null != id) {
                userRole = userRoleManager.getUserRoleById(id);
            } else {
                LOG.warn("UserRoleServiceImpl#getUserRoleById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("UserRoleServiceImpl#getUserRoleById has error.", e);
        }
        return userRole;
    }

    /**
     * 修改用户拥有的角色
     * @param userRole
     * @return
     */
    public LetvResponse<Boolean> batchSave(UserRole userRole) {
        LOG.info("inputPar:UserRoleServiceImpl#getUserRoleById.userRole="+ JsonHelper.toJson(userRole));
        LetvResponse<Boolean> letvResponse = new LetvResponse<Boolean>() ;
        if(null==letvResponse || StringUtils.isEmpty(userRole.getUserCode())
                || StringUtils.isEmpty(userRole.getCreateUser())
                || null==userRole.getRoleCodes()){
            letvResponse.setCode(PortalSystemTipCodeEnum.PAR_ERROR.getValue());
            letvResponse.setMessage(PortalSystemTipCodeEnum.PAR_ERROR.getNote());
            LOG.error("error:UserRoleServiceImpl#getUserRoleById.letvResponse="+ JsonHelper.toJson(letvResponse));
            return letvResponse;
        }
        boolean resultFlag = false;
        try {
            resultFlag = userRoleManager.batchUpdate(userRole.getUserCode(), userRole.getRoleCodes(), userRole.getCreateUser());
            if(resultFlag){
                letvResponse.setCode(PortalSystemTipCodeEnum.SCUESS.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.SCUESS.getNote());
                letvResponse.setResult(resultFlag);
            } else{
                letvResponse.setCode(PortalSystemTipCodeEnum.DATA_NOTEXIST.getValue());
                letvResponse.setMessage(PortalSystemTipCodeEnum.DATA_NOTEXIST.getNote());
                letvResponse.setResult(resultFlag);
            }
        } catch (Exception e) {
            LOG.error("error:UserRoleServiceImpl#getUserRoleById.e=",e);
            letvResponse.setCode(PortalSystemTipCodeEnum.ERROR.getValue());
            letvResponse.setMessage(e.getMessage());
        }
        LOG.info("outputPar:UserRoleServiceImpl#getUserRoleById.letvResponse="+ JsonHelper.toJson(letvResponse));
        return letvResponse ;
    }
}

