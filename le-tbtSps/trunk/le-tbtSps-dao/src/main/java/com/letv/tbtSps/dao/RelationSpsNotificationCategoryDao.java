package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsNotificationCategory;
import com.letv.tbtSps.domain.query.RelationSpsNotificationCategoryQuery;
/**
 * RelationSpsNotificationCategoryDao接口<br/>
 * 对'sps信息表通报分类关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsNotificationCategoryDao {
    
    /**
     * 新增对象
     * 
     * @param relationSpsNotificationCategory 
     * @return
     */
    public boolean insert(RelationSpsNotificationCategory relationSpsNotificationCategory);

    /**
     * 更新对象
     * 
     * @param relationSpsNotificationCategory
     * @return
     */
    public boolean update(RelationSpsNotificationCategory relationSpsNotificationCategory);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryList(RelationSpsNotificationCategoryQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationSpsNotificationCategoryCount(RelationSpsNotificationCategoryQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryListWithPage(RelationSpsNotificationCategoryQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationSpsNotificationCategory
     * @return
     */
    public boolean delete(RelationSpsNotificationCategory relationSpsNotificationCategory);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationSpsNotificationCategory getRelationSpsNotificationCategoryById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationSpsNotificationCategory
     * @return
     */
    public boolean exist(RelationSpsNotificationCategory relationSpsNotificationCategory);

}
