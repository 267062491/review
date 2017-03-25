package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsLanguage;
import com.letv.tbtSps.domain.query.RelationSpsLanguageQuery;
/**
 * RelationSpsLanguageDao接口<br/>
 * 对'sps信息表-原文语种关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsLanguageDao {
    
    /**
     * 新增对象
     * 
     * @param relationSpsLanguage 
     * @return
     */
    public boolean insert(RelationSpsLanguage relationSpsLanguage);

    /**
     * 更新对象
     * 
     * @param relationSpsLanguage
     * @return
     */
    public boolean update(RelationSpsLanguage relationSpsLanguage);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsLanguage> queryRelationSpsLanguageList(RelationSpsLanguageQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationSpsLanguageCount(RelationSpsLanguageQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsLanguage> queryRelationSpsLanguageListWithPage(RelationSpsLanguageQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationSpsLanguage
     * @return
     */
    public boolean delete(RelationSpsLanguage relationSpsLanguage);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationSpsLanguage getRelationSpsLanguageById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationSpsLanguage
     * @return
     */
    public boolean exist(RelationSpsLanguage relationSpsLanguage);

}
