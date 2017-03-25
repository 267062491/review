package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.Country;
import com.letv.tbtSps.domain.query.CountryQuery;
/**
 * CountryDao接口<br/>
 * 对'通报成员'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface CountryDao {
    
    /**
     * 新增对象
     * 
     * @param country 
     * @return
     */
    public boolean insert(Country country);

    /**
     * 更新对象
     * 
     * @param country
     * @return
     */
    public boolean update(Country country);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Country> queryCountryList(CountryQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryCountryCount(CountryQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Country> queryCountryListWithPage(CountryQuery queryBean);

    /**
     * 删除记录
     * 
     * @param country
     * @return
     */
    public boolean delete(Country country);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public Country getCountryById(Long id);

    /**
     * 判断是否存在
     * 
     * @param country
     * @return
     */
    public boolean exist(Country country);

}
