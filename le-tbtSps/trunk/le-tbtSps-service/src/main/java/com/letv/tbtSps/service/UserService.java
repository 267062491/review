package com.letv.tbtSps.service;

import com.letv.common.sdk.api.response.LetvResponse;
import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.wmscommon.dto.PagedQueryDto;
import com.letv.wmscommon.dto.PagedResultDto;
import java.util.List;

/**
 * UserService接口
 * 
 * @author yuguodong
 * @version 2016-10-24 17:11:37
 * 
 */
public interface UserService {

    /**
     * 批量增加对象信息
     * 
     * @param userList
     * @return
     */
    public boolean batchInsert(List<User> userList);

    /**
     * 单个增加对象信息
     * 
     * @param user
     * @return
     */
    public LetvResponse<Boolean> insert(User user);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<User> queryUserList(UserQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     *
     * @return
     */
    public LetvResponse<PagedResultDto<User>> queryUserListWithPage(PagedQueryDto<UserQuery> pagedQuery);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param user
     *            　
     * @return
     */
    public boolean delete(User user);


    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param users
     *            User集合
     * @return
     */
    public boolean batchDelete(User[] users);

    /****************************以上是生成代码***********************************************/

    /**
     * 根据用户名和密码查询用户
     * @param queryBean
     * @return
     */
    public LetvResponse<User> searchUserByNameAndPassword(UserQuery queryBean);

    /**
     * 根据用户编码获取用户信息
     * @param queryBean
     * @return
     */
    public LetvResponse<User> searchUserByUserCode(UserQuery queryBean);
    /**
     * 根据用户名获取用户信息
     * @param queryBean
     * @return
     */
    public LetvResponse<User> searchUserByUserName(UserQuery queryBean);

    /**
     * 根据用户登录名或者中文名称查询用户信息
     * @param queryBean
     * @return
     */
    public LetvResponse<List<User>> searchUserByCodeOrName(UserQuery queryBean);

    /**
     * 获取cookie名称
     * @return
     */
    public LetvResponse<String> getCookieName();

    /**
     * 启用、禁用 用户
     * @param user
     * @return
     */
    public LetvResponse<Boolean> enableOrDisable(User user);

    /**
     * 根据主键获取对象信息
     *
     * @param userQuery
     *            主键字段
     * @return 对象信息
     */
    public LetvResponse<User> getUserById(UserQuery userQuery);

    /**
     * 根据用户编码删除用户信息
     * @param user
     * @return
     */
    public LetvResponse<Boolean> deleteByUserCode(User user);

    /**
     * 更新 对象信息
     *
     * @param user
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public LetvResponse<Boolean> update(User user);

    /**
     * 批量上传用户信息
     * @param list
     * @return
     */
    public LetvResponse<Boolean> uploadUsers(List<User> list);

    /**
     * 修改密码
     * @param user
     * @return
     */
    public LetvResponse<Boolean> updatePassword(User user) ;

    /**
     * 根据用户编码查询用户信息，批量接口
     * @param queryBean
     * @return
     */
    public LetvResponse<List<User>> searchUserByCodes(UserQuery queryBean);
}
