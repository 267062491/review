package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationTblanguage;
import com.letv.tbtSps.domain.query.RelationTblanguageQuery;
/**
 * RelationTblanguageDao接口<br/>
 * 对'tbt信息表-原文语种关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationTblanguageDao {
    
    /**
     * 新增对象
     * 
     * @param relationTblanguage 
     * @return
     */
    public boolean insert(RelationTblanguage relationTblanguage);

    /**
     * 更新对象
     * 
     * @param relationTblanguage
     * @return
     */
    public boolean update(RelationTblanguage relationTblanguage);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTblanguage> queryRelationTblanguageList(RelationTblanguageQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationTblanguageCount(RelationTblanguageQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTblanguage> queryRelationTblanguageListWithPage(RelationTblanguageQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationTblanguage
     * @return
     */
    public boolean delete(RelationTblanguage relationTblanguage);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationTblanguage getRelationTblanguageById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationTblanguage
     * @return
     */
    public boolean exist(RelationTblanguage relationTblanguage);

}
