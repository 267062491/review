package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.RelationTbnotificationCategory;
import com.letv.tbtSps.domain.query.RelationTbnotificationCategoryQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * RelationTbnotificationCategoryManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationTbnotificationCategoryManager {

    /**
     * 批量增加对象信息
     * 
     * @param relationTbnotificationCategoryList
     * @return
     */
    public boolean insert(List<RelationTbnotificationCategory> relationTbnotificationCategoryList);

    /**
     * 单个增加对象信息
     * 
     * @param relationTbnotificationCategory
     * @return
     */
    public boolean insert(RelationTbnotificationCategory relationTbnotificationCategory);

    /**
     * 更新 对象信息
     * 
     * @param relationTbnotificationCategory
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationTbnotificationCategory relationTbnotificationCategory);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryList(RelationTbnotificationCategoryQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationTbnotificationCategory> queryRelationTbnotificationCategoryListWithPage(RelationTbnotificationCategoryQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryRelationTbnotificationCategoryCount(RelationTbnotificationCategoryQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTbnotificationCategory
     *            　
     * @return
     */
    public boolean delete(RelationTbnotificationCategory relationTbnotificationCategory);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationTbnotificationCategory getRelationTbnotificationCategoryById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTbnotificationCategorys
     *            RelationTbnotificationCategory集合
     * @return
     */
    public boolean delete(RelationTbnotificationCategory[] relationTbnotificationCategorys);

    /**
     * 判断是否存在
     * 
     * @param relationTbnotificationCategory
     * @return
     */
    public boolean exist(RelationTbnotificationCategory relationTbnotificationCategory);
}
