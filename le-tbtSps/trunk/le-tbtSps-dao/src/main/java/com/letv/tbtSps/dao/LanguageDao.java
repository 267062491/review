package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.Language;
import com.letv.tbtSps.domain.query.LanguageQuery;
/**
 * LanguageDao接口<br/>
 * 对'原文语种'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface LanguageDao {
    
    /**
     * 新增对象
     * 
     * @param language 
     * @return
     */
    public boolean insert(Language language);

    /**
     * 更新对象
     * 
     * @param language
     * @return
     */
    public boolean update(Language language);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Language> queryLanguageList(LanguageQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryLanguageCount(LanguageQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Language> queryLanguageListWithPage(LanguageQuery queryBean);

    /**
     * 删除记录
     * 
     * @param language
     * @return
     */
    public boolean delete(Language language);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public Language getLanguageById(Long id);

    /**
     * 判断是否存在
     * 
     * @param language
     * @return
     */
    public boolean exist(Language language);

}
