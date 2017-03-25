package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.NotificationCategory;
import com.letv.tbtSps.domain.query.NotificationCategoryQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * NotificationCategoryService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface NotificationCategoryService {

    /**
     * 批量增加对象信息
     * 
     * @param notificationCategoryList
     * @return
     */
    public boolean insert(List<NotificationCategory> notificationCategoryList);

    /**
     * 单个增加对象信息
     * 
     * @param notificationCategory
     * @return
     */
    public boolean insert(NotificationCategory notificationCategory);

    /**
     * 更新 对象信息
     * 
     * @param notificationCategory
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(NotificationCategory notificationCategory);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<NotificationCategory> queryNotificationCategoryList(NotificationCategoryQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<NotificationCategory> queryNotificationCategoryListWithPage(NotificationCategoryQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param notificationCategory
     *            　
     * @return
     */
    public boolean delete(NotificationCategory notificationCategory);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public NotificationCategory getNotificationCategoryById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param notificationCategorys
     *            NotificationCategory集合
     * @return
     */
    public boolean delete(NotificationCategory[] notificationCategorys);
}
