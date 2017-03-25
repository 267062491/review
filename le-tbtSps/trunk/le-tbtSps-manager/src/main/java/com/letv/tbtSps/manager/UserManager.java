package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.UserQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * UserManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface UserManager {

    /**
     * 批量增加对象信息
     * 
     * @param userList
     * @return
     */
    public boolean insert(List<User> userList);

    /**
     * 单个增加对象信息
     * 
     * @param user
     * @return
     */
    public boolean insert(User user);

    /**
     * 更新 对象信息
     * 
     * @param user
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(User user);

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
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<User> queryUserListWithPage(UserQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryUserCount(UserQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param user
     *            　
     * @return
     */
    public boolean delete(User user);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public User getUserById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param users
     *            User集合
     * @return
     */
    public boolean delete(User[] users);

    /**
     * 判断是否存在
     * 
     * @param user
     * @return
     */
    public boolean exist(User user);
}
