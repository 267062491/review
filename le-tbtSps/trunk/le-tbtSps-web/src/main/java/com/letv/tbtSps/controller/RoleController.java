package com.letv.tbtSps.controller;


import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.Role;
import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleQuery;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.domain.query.TreeDomain;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.service.RoleResourceService;
import com.letv.tbtSps.service.RoleService;
import com.letv.tbtSps.utils.constant.PortalSystemTipCodeEnum;
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

import java.util.List;

/**
 * RoleController ：角色表控制器
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:38
*/
@Controller
@RequestMapping("role")
public class RoleController extends ReviewBaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleResourceService roleResourceService;

    /** 视图前缀 */
    private static final String viewPrefix ="role";
    
    private static final Log LOG = LogFactory.getLog(RoleController.class);


    /**
     * 首页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        return viewPrefix + "/index";
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
    public String queryByPage(Model model, PageUtil page, RoleQuery query) {
        try {
        query.setRoleType(getLoginUserType());
        PagedQueryDto<RoleQuery> pagedQuery = new PagedQueryDto<RoleQuery>();
        pagedQuery.setPageUtil(page);
        pagedQuery.setQueryDto(query);
        PagedResultDto<Role> pagedResult = roleService.queryRoleListWithPage(pagedQuery);
        List<Role> dataList = pagedResult.getResult();
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", pagedResult.getPageUtil());// 分页
        } catch (Exception e) {
            LOG.error("role queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 角色表----添加跳转
     *
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward(Model model) {
        if(getLoginUserType()==Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
            model.addAttribute("roleType",Boolean.TRUE);
        }
        return viewPrefix + "/add";
    }


    /**
     * 角色表----更新跳转
     * 
     * @param model
     * @param role
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, Role role) {
        try {
            if(getLoginUserType()==Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
                model.addAttribute("roleType",Boolean.TRUE);
            }
            Role roleResult = roleService.getRoleById(role.getId());
            model.addAttribute("role", roleResult);
        } catch (Exception e) {
            LOG.error("role updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 角色表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RoleQuery query) {
        try {
            List<Role> list = roleService.queryRoleList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("role query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询角色表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RoleQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            Role role = roleService.getRoleById(query.getId());
            if (role != null) {
                return new Wrapper<Role>().result(role);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询角色表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail role has error.", e);
            return error();
        }
    }
    /***********************************以上是生成代码*****************************************************/
    /**
     * 根据用编码查询用户拥有的角色
     * @param userRoleQuery
     * @return
     */
    @RequestMapping(value = "queryRoleListByUserCode")
    @ResponseBody
    public Wrapper<?> queryRoleListByUserCode(UserRoleQuery userRoleQuery){
        if (null == userRoleQuery || StringUtils.isEmpty(userRoleQuery.getUserCode())) {
            return illegalArgument();
        }

        try {
            LetvResponse<List<Role>> letvResponse = roleService.queryRoleListByUserCode(userRoleQuery);
            if (letvResponse.getCode() == PortalSystemTipCodeEnum.SCUESS.getValue()) {
                return new Wrapper<List<Role>>().result(letvResponse.getResult());
            } else {
                return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
            }
        } catch (Exception e) {
            LOG.warn("detail role has error.", e);
            return error();
        }
    }

    /**
     * 根据用编码， 查询用户可选择的角色
     * @param userRoleQuery
     * @return
     */
    @RequestMapping(value = "queryChooseRoleListByUserCode")
    @ResponseBody
    public Wrapper<?> queryChooseRoleListByUserCode(UserRoleQuery userRoleQuery){
        logger.info("inputPar:RoleController#queryChooseRoleListByUserCode.userRoleQuery="+ JsonHelper.toJson(userRoleQuery));
        if (null == userRoleQuery || StringUtils.isEmpty(userRoleQuery.getUserCode())) {
            logger.error("error:RoleController#queryChooseRoleListByUserCode.userRoleQuery=" + JsonHelper.toJson(userRoleQuery));
            return illegalArgument();
        }

        try {
            userRoleQuery.setRoleType(getLoginUserType());
            LetvResponse<List<Role>> letvResponse = roleService.queryChooseRoleListByUserCode(userRoleQuery);
            if (letvResponse.getCode() == PortalSystemTipCodeEnum.SCUESS.getValue()) {
                return new Wrapper<List<Role>>().result(letvResponse.getResult());
            } else {
                return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
            }
        } catch (Exception e) {
            LOG.warn("detail role has error.", e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }

    /**
     * 查询角色拥有的资源
     * @param roleResourceQuery
     * @return
     */
    @RequestMapping(value = "queryRoleResourceByRoleCode")
    @ResponseBody
    public Wrapper<?> queryRoleResourceByRoleCode(RoleResourceQuery roleResourceQuery){
        logger.info("inputPar:RoleController#queryRoleResourceByRoleCode.roleResourceQuery="+ JsonHelper.toJson(roleResourceQuery));
        if (null == roleResourceQuery || StringUtils.isEmpty(roleResourceQuery.getRoleCode())) {
            logger.error("error:RoleController#queryRoleResourceByRoleCode.roleResourceQuery="+ JsonHelper.toJson(roleResourceQuery));
            return illegalArgument();
        }

        try {
            if(getLoginUserType()!=Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
                roleResourceQuery.setResourceType(getLoginUserType());
            }
            LetvResponse<List<TreeDomain>> letvResponse = roleResourceService.queryRoleResourceByRoleCode(roleResourceQuery);
            if (letvResponse.getCode() == PortalSystemTipCodeEnum.SCUESS.getValue()) {
                return new Wrapper<List<TreeDomain>>().result(letvResponse.getResult());
            } else {
                return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
            }
        } catch (Exception e) {
            LOG.warn("detail role has error.", e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }


    /**
     * 角色-资源 更新
     *
     * @param model
     * @param roleResource
     * @return
     */
    @RequestMapping(value = "updateRoleResource")
    @ResponseBody
    public Wrapper<?> updateRoleResource(Model model, RoleResource roleResource) {
        logger.info("inputPar:RoleController#updateRoleResource.roleResource="+ JsonHelper.toJson(roleResource));
        if (null == roleResource || StringUtils.isEmpty(roleResource.getRoleCode())
                || (StringUtils.isEmpty(roleResource.getPcResourceCode())
                && StringUtils.isEmpty(roleResource.getRfResourceCode()))) {
            logger.error("error:RoleController#updateRoleResource.roleResource="+ JsonHelper.toJson(roleResource));
            return illegalArgument();
        }
        try {
            roleResource.setCreateUser(getLoginUserName());
            roleResource.setUpdateUser(getLoginUserName());
            LetvResponse<Boolean> letvResponse = roleResourceService.updateRoleResource(roleResource);
            return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
        } catch (Exception e) {
            LOG.error("error:RoleController#updateRoleResource.e=",e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }

    /**
     * 根据角色编码查询角色表详情
     *
     * @param roleQuery
     * @return
     */
    @RequestMapping(value = "detailByRoleCode")
    @ResponseBody
    public Wrapper<?> detailByRoleCode(RoleQuery roleQuery) {
        LOG.info("inputPar:RoleController#detailByRoleCode.roleQuery="+JsonHelper.toJson(roleQuery));
        if (null == roleQuery || StringUtils.isEmpty(roleQuery.getRoleCode())) {
            LOG.error("error:RoleController#detailByRoleCode.roleQuery="+JsonHelper.toJson(roleQuery));
            return illegalArgument();
        }
        try {
            roleQuery.setRoleType(getLoginUserType());
            LetvResponse<Role>  letvResponse = roleService.getRoleByCode(roleQuery);
            if (letvResponse.getCode()==PortalSystemTipCodeEnum.SCUESS.getValue()) {
                LOG.info("outputPar:RoleController#detailByRoleCode.letvResponse="+JsonHelper.toJson(letvResponse));
                return new Wrapper<Role>().result(letvResponse.getResult());
            } else {
                LOG.error("error:RoleController#detailByRoleCode.letvResponse="+JsonHelper.toJson(letvResponse));
                return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
            }
        } catch (Exception e) {
            LOG.error("error:RoleController#detailByRoleCode.e=",e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }

    /**
     * 角色表----添加
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(Role role) {
        LOG.info("inputPar:RoleController#add.role="+JsonHelper.toJson(role));
        if (null == role || StringUtils.isEmpty(role.getRoleCode())
                || StringUtils.isEmpty(role.getRoleName())) {
            LOG.error("error:RoleController#add.role="+JsonHelper.toJson(role));
            return illegalArgument();
        }
        try {
            if(null == role.getRoleType() ){
                role.setRoleType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            }else if(role.getRoleType().intValue()!=Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
                role.setRoleType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            }

            role.setCreateUser(getLoginUserName());
            role.setUpdateUser(getLoginUserName());
            LetvResponse<Boolean> letvResponse = roleService.insertRole(role);
            if(letvResponse.getCode()==PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue()){
                letvResponse.setMessage("编码或者名称重复");
            }
            LOG.info("outputPar:RoleController#add.letvResponse="+JsonHelper.toJson(letvResponse));
            return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
        }  catch (Exception e) {
            LOG.error("error:RoleController#add.e=",e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }

    /**
     * 角色表----更新
     *
     * @param model
     * @param role
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, Role role) {
        LOG.info("inputPar:RoleController#update.role="+JsonHelper.toJson(role));
        if (null == role || StringUtils.isEmpty(role.getRoleCode())
                || StringUtils.isEmpty(role.getRoleName())) {
            LOG.error("error:RoleController#update.role="+JsonHelper.toJson(role));
            return illegalArgument();
        }
        try {
            if(null == role.getRoleType() ){
                role.setRoleType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            }else if(role.getRoleType().intValue()!=Integer.parseInt(UserTypeEnum.SUPPER_USER.getValue())){
                role.setRoleType(Integer.parseInt(UserTypeEnum.SELF_DEFIND.getValue()));
            }

            role.setUpdateUser(getLoginUserCnName());
            LetvResponse<Boolean> letvResponse = roleService.updateRole(role);
            if(letvResponse.getCode()==PortalSystemTipCodeEnum.WORKBENCH_MORETHANONE.getValue()){
                letvResponse.setMessage("编码或者名称重复");
            }
            LOG.info("outputPar:RoleController#update.letvResponse="+JsonHelper.toJson(letvResponse));
            return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
        } catch (Exception e) {
            LOG.error("error:RoleController#update.e=",e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }

    /**
     * 角色表----删除
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(Role role) {
        LOG.info("inputPar:RoleController#delete.role="+JsonHelper.toJson(role));
        if (null == role || StringUtils.isEmpty(role.getRoleCode())) {
            LOG.error("error:RoleController#delete.role="+JsonHelper.toJson(role));
            return illegalArgument();
        }
        try {
            role.setUpdateUser(getLoginUserCnName());
            LetvResponse<Boolean> letvResponse = roleService.deleteRole(role);
            LOG.info("outputPar:RoleController#delete.letvResponse="+JsonHelper.toJson(letvResponse));
            return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
        } catch (Exception e) {
            LOG.error("error:RoleController#delete.e=",e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }

    @RequestMapping(value = "enableOrDisable")
    @ResponseBody
    public Wrapper<?> enableOrDisable(Role role) {
        LOG.info("inputPar:RoleController#enableOrDisable.role=" + JsonHelper.toJson(role));
        if (null == role || StringUtils.isEmpty(role.getRoleCode())
                || null == role.getEnable()) {
            LOG.error("error:RoleController#enableOrDisable.role=" + JsonHelper.toJson(role));
            return illegalArgument();
        }
        try {
            role.setUpdateUser(getLoginUserCnName());
            LetvResponse<Boolean> letvResponse = roleService.enableOrDisable(role);
            LOG.info("outputPar:RoleController#enableOrDisable.letvResponse=" + JsonHelper.toJson(letvResponse));
            return WrapMapper.wrap(letvResponse.getCode(), letvResponse.getMessage());
        } catch (Exception e) {
            LOG.error("error:RoleController#enableOrDisable.e=" , e);
            return WrapMapper.wrap(PortalSystemTipCodeEnum.ERROR.getValue(), e.getMessage());
        }
    }
}
