package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsNotificationCategory;
import com.letv.tbtSps.domain.query.RelationSpsNotificationCategoryQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * RelationSpsNotificationCategoryManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsNotificationCategoryManager {

    /**
     * 批量增加对象信息
     * 
     * @param relationSpsNotificationCategoryList
     * @return
     */
    public boolean insert(List<RelationSpsNotificationCategory> relationSpsNotificationCategoryList);

    /**
     * 单个增加对象信息
     * 
     * @param relationSpsNotificationCategory
     * @return
     */
    public boolean insert(RelationSpsNotificationCategory relationSpsNotificationCategory);

    /**
     * 更新 对象信息
     * 
     * @param relationSpsNotificationCategory
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationSpsNotificationCategory relationSpsNotificationCategory);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryList(RelationSpsNotificationCategoryQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationSpsNotificationCategory> queryRelationSpsNotificationCategoryListWithPage(RelationSpsNotificationCategoryQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryRelationSpsNotificationCategoryCount(RelationSpsNotificationCategoryQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsNotificationCategory
     *            　
     * @return
     */
    public boolean delete(RelationSpsNotificationCategory relationSpsNotificationCategory);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationSpsNotificationCategory getRelationSpsNotificationCategoryById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsNotificationCategorys
     *            RelationSpsNotificationCategory集合
     * @return
     */
    public boolean delete(RelationSpsNotificationCategory[] relationSpsNotificationCategorys);

    /**
     * 判断是否存在
     * 
     * @param relationSpsNotificationCategory
     * @return
     */
    public boolean exist(RelationSpsNotificationCategory relationSpsNotificationCategory);
}
