package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsLanguage;
import com.letv.tbtSps.domain.query.RelationSpsLanguageQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * RelationSpsLanguageManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsLanguageManager {

    /**
     * 批量增加对象信息
     * 
     * @param relationSpsLanguageList
     * @return
     */
    public boolean insert(List<RelationSpsLanguage> relationSpsLanguageList);

    /**
     * 单个增加对象信息
     * 
     * @param relationSpsLanguage
     * @return
     */
    public boolean insert(RelationSpsLanguage relationSpsLanguage);

    /**
     * 更新 对象信息
     * 
     * @param relationSpsLanguage
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationSpsLanguage relationSpsLanguage);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsLanguage> queryRelationSpsLanguageList(RelationSpsLanguageQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationSpsLanguage> queryRelationSpsLanguageListWithPage(RelationSpsLanguageQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryRelationSpsLanguageCount(RelationSpsLanguageQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsLanguage
     *            　
     * @return
     */
    public boolean delete(RelationSpsLanguage relationSpsLanguage);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationSpsLanguage getRelationSpsLanguageById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsLanguages
     *            RelationSpsLanguage集合
     * @return
     */
    public boolean delete(RelationSpsLanguage[] relationSpsLanguages);

    /**
     * 判断是否存在
     * 
     * @param relationSpsLanguage
     * @return
     */
    public boolean exist(RelationSpsLanguage relationSpsLanguage);
}
