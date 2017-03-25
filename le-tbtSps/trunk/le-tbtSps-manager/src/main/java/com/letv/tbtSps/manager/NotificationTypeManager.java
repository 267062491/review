package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.NotificationType;
import com.letv.tbtSps.domain.query.NotificationTypeQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * NotificationTypeManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface NotificationTypeManager {

    /**
     * 批量增加对象信息
     * 
     * @param notificationTypeList
     * @return
     */
    public boolean insert(List<NotificationType> notificationTypeList);

    /**
     * 单个增加对象信息
     * 
     * @param notificationType
     * @return
     */
    public boolean insert(NotificationType notificationType);

    /**
     * 更新 对象信息
     * 
     * @param notificationType
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(NotificationType notificationType);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<NotificationType> queryNotificationTypeList(NotificationTypeQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<NotificationType> queryNotificationTypeListWithPage(NotificationTypeQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryNotificationTypeCount(NotificationTypeQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param notificationType
     *            　
     * @return
     */
    public boolean delete(NotificationType notificationType);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public NotificationType getNotificationTypeById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param notificationTypes
     *            NotificationType集合
     * @return
     */
    public boolean delete(NotificationType[] notificationTypes);

    /**
     * 判断是否存在
     * 
     * @param notificationType
     * @return
     */
    public boolean exist(NotificationType notificationType);
}
