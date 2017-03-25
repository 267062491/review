package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.UserCountry;
import com.letv.tbtSps.domain.query.UserCountryQuery;
/**
 * UserCountryDao接口<br/>
 * 对'用户通报成员关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface UserCountryDao {
    
    /**
     * 新增对象
     * 
     * @param userCountry 
     * @return
     */
    public boolean insert(UserCountry userCountry);

    /**
     * 更新对象
     * 
     * @param userCountry
     * @return
     */
    public boolean update(UserCountry userCountry);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<UserCountry> queryUserCountryList(UserCountryQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryUserCountryCount(UserCountryQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<UserCountry> queryUserCountryListWithPage(UserCountryQuery queryBean);

    /**
     * 删除记录
     * 
     * @param userCountry
     * @return
     */
    public boolean delete(UserCountry userCountry);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public UserCountry getUserCountryById(Long id);

    /**
     * 判断是否存在
     * 
     * @param userCountry
     * @return
     */
    public boolean exist(UserCountry userCountry);

}
