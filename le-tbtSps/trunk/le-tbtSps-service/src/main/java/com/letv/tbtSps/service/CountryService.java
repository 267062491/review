package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.Country;
import com.letv.tbtSps.domain.query.CountryQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * CountryService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface CountryService {

    /**
     * 批量增加对象信息
     * 
     * @param countryList
     * @return
     */
    public boolean insert(List<Country> countryList);

    /**
     * 单个增加对象信息
     * 
     * @param country
     * @return
     */
    public boolean insert(Country country);

    /**
     * 更新 对象信息
     * 
     * @param country
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(Country country);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Country> queryCountryList(CountryQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<Country> queryCountryListWithPage(CountryQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param country
     *            　
     * @return
     */
    public boolean delete(Country country);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public Country getCountryById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param countrys
     *            Country集合
     * @return
     */
    public boolean delete(Country[] countrys);
}
