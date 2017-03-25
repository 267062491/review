package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.RelationTblanguage;
import com.letv.tbtSps.domain.query.RelationTblanguageQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * RelationTblanguageManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationTblanguageManager {

    /**
     * 批量增加对象信息
     * 
     * @param relationTblanguageList
     * @return
     */
    public boolean insert(List<RelationTblanguage> relationTblanguageList);

    /**
     * 单个增加对象信息
     * 
     * @param relationTblanguage
     * @return
     */
    public boolean insert(RelationTblanguage relationTblanguage);

    /**
     * 更新 对象信息
     * 
     * @param relationTblanguage
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationTblanguage relationTblanguage);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTblanguage> queryRelationTblanguageList(RelationTblanguageQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationTblanguage> queryRelationTblanguageListWithPage(RelationTblanguageQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryRelationTblanguageCount(RelationTblanguageQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTblanguage
     *            　
     * @return
     */
    public boolean delete(RelationTblanguage relationTblanguage);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationTblanguage getRelationTblanguageById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTblanguages
     *            RelationTblanguage集合
     * @return
     */
    public boolean delete(RelationTblanguage[] relationTblanguages);

    /**
     * 判断是否存在
     * 
     * @param relationTblanguage
     * @return
     */
    public boolean exist(RelationTblanguage relationTblanguage);
}
