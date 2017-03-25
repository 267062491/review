package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.RelationTbnotificationType;
import com.letv.tbtSps.domain.query.RelationTbnotificationTypeQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * RelationTbnotificationTypeManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationTbnotificationTypeManager {

    /**
     * 批量增加对象信息
     * 
     * @param relationTbnotificationTypeList
     * @return
     */
    public boolean insert(List<RelationTbnotificationType> relationTbnotificationTypeList);

    /**
     * 单个增加对象信息
     * 
     * @param relationTbnotificationType
     * @return
     */
    public boolean insert(RelationTbnotificationType relationTbnotificationType);

    /**
     * 更新 对象信息
     * 
     * @param relationTbnotificationType
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationTbnotificationType relationTbnotificationType);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTbnotificationType> queryRelationTbnotificationTypeList(RelationTbnotificationTypeQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationTbnotificationType> queryRelationTbnotificationTypeListWithPage(RelationTbnotificationTypeQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryRelationTbnotificationTypeCount(RelationTbnotificationTypeQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTbnotificationType
     *            　
     * @return
     */
    public boolean delete(RelationTbnotificationType relationTbnotificationType);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationTbnotificationType getRelationTbnotificationTypeById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTbnotificationTypes
     *            RelationTbnotificationType集合
     * @return
     */
    public boolean delete(RelationTbnotificationType[] relationTbnotificationTypes);

    /**
     * 判断是否存在
     * 
     * @param relationTbnotificationType
     * @return
     */
    public boolean exist(RelationTbnotificationType relationTbnotificationType);
}
