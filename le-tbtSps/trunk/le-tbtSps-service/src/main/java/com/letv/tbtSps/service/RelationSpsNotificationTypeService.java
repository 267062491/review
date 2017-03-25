package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsNotificationType;
import com.letv.tbtSps.domain.query.RelationSpsNotificationTypeQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * RelationSpsNotificationTypeService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsNotificationTypeService {

    /**
     * 批量增加对象信息
     * 
     * @param relationSpsNotificationTypeList
     * @return
     */
    public boolean insert(List<RelationSpsNotificationType> relationSpsNotificationTypeList);

    /**
     * 单个增加对象信息
     * 
     * @param relationSpsNotificationType
     * @return
     */
    public boolean insert(RelationSpsNotificationType relationSpsNotificationType);

    /**
     * 更新 对象信息
     * 
     * @param relationSpsNotificationType
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationSpsNotificationType relationSpsNotificationType);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeList(RelationSpsNotificationTypeQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeListWithPage(RelationSpsNotificationTypeQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsNotificationType
     *            　
     * @return
     */
    public boolean delete(RelationSpsNotificationType relationSpsNotificationType);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationSpsNotificationType getRelationSpsNotificationTypeById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsNotificationTypes
     *            RelationSpsNotificationType集合
     * @return
     */
    public boolean delete(RelationSpsNotificationType[] relationSpsNotificationTypes);
}
