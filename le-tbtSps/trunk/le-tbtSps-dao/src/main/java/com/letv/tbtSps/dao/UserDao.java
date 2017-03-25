package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.User;
import com.letv.tbtSps.domain.query.UserQuery;
/**
 * UserDao接口<br/>
 * 对'用户表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface UserDao {
    
    /**
     * 新增对象
     * 
     * @param user 
     * @return
     */
    public boolean insert(User user);

    /**
     * 更新对象
     * 
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<User> queryUserList(UserQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryUserCount(UserQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<User> queryUserListWithPage(UserQuery queryBean);

    /**
     * 删除记录
     * 
     * @param user
     * @return
     */
    public boolean delete(User user);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public User getUserById(Long id);

    /**
     * 判断是否存在
     * 
     * @param user
     * @return
     */
    public boolean exist(User user);

}
