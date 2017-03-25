package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.Language;
import com.letv.tbtSps.domain.query.LanguageQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * LanguageManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface LanguageManager {

    /**
     * 批量增加对象信息
     * 
     * @param languageList
     * @return
     */
    public boolean insert(List<Language> languageList);

    /**
     * 单个增加对象信息
     * 
     * @param language
     * @return
     */
    public boolean insert(Language language);

    /**
     * 更新 对象信息
     * 
     * @param language
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(Language language);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Language> queryLanguageList(LanguageQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<Language> queryLanguageListWithPage(LanguageQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryLanguageCount(LanguageQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param language
     *            　
     * @return
     */
    public boolean delete(Language language);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public Language getLanguageById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param languages
     *            Language集合
     * @return
     */
    public boolean delete(Language[] languages);

    /**
     * 判断是否存在
     * 
     * @param language
     * @return
     */
    public boolean exist(Language language);
}
