package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationTbnotificationCategory;
import com.letv.tbtSps.domain.query.RelationTbnotificationCategoryQuery;
/**
 * RelationTbnotificationCategoryDao接口<br/>
 * 对'tbt信息表通报分类关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationTbnotificationCategoryDao {
    
    /**
     * 新增对象
     * 
     * @param relationTbnotificationCategory 
     * @return
     */
    public boolean insert(RelationTbnotificationCategory relationTbnotificationCategory);

    /**
     * 更新对象
     * 
     * @param relationTbnotificationCategory
     * @return
     */
    public boolean update(RelationTbnotificationCategory relationTbnotificationCategory);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryList(RelationTbnotificationCategoryQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationTbnotificationCategoryCount(RelationTbnotificationCategoryQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryListWithPage(RelationTbnotificationCategoryQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationTbnotificationCategory
     * @return
     */
    public boolean delete(RelationTbnotificationCategory relationTbnotificationCategory);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationTbnotificationCategory getRelationTbnotificationCategoryById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationTbnotificationCategory
     * @return
     */
    public boolean exist(RelationTbnotificationCategory relationTbnotificationCategory);

}
