package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsNotificationType;
import com.letv.tbtSps.domain.query.RelationSpsNotificationTypeQuery;
/**
 * RelationSpsNotificationTypeDao接口<br/>
 * 对'sps信息表-通报类型关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsNotificationTypeDao {
    
    /**
     * 新增对象
     * 
     * @param relationSpsNotificationType 
     * @return
     */
    public boolean insert(RelationSpsNotificationType relationSpsNotificationType);

    /**
     * 更新对象
     * 
     * @param relationSpsNotificationType
     * @return
     */
    public boolean update(RelationSpsNotificationType relationSpsNotificationType);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeList(RelationSpsNotificationTypeQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationSpsNotificationTypeCount(RelationSpsNotificationTypeQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsNotificationType> queryRelationSpsNotificationTypeListWithPage(RelationSpsNotificationTypeQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationSpsNotificationType
     * @return
     */
    public boolean delete(RelationSpsNotificationType relationSpsNotificationType);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationSpsNotificationType getRelationSpsNotificationTypeById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationSpsNotificationType
     * @return
     */
    public boolean exist(RelationSpsNotificationType relationSpsNotificationType);

}
