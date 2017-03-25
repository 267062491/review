package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.RelationSpsNotificationCategory;
import com.letv.tbtSps.domain.query.RelationSpsNotificationCategoryQuery;
import com.letv.tbtSps.service.RelationSpsNotificationCategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * RelationSpsNotificationCategoryService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class RelationSpsNotificationCategoryServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private RelationSpsNotificationCategoryService relationSpsNotificationCategoryService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategory relationSpsNotificationCategory = new RelationSpsNotificationCategory();
        String notificationCategoryCode = null; //TODO 初始化
        relationSpsNotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String spsCode = null; //TODO 初始化
        relationSpsNotificationCategory.setSpsCode(spsCode);
        relationSpsNotificationCategory.setCreateUser(TestConstants.UER_NAME);
        boolean result = relationSpsNotificationCategoryService.insert(relationSpsNotificationCategory);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategory relationSpsNotificationCategory = new RelationSpsNotificationCategory();
        String notificationCategoryCode = null; //TODO 初始化// 已经存在的
        relationSpsNotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String spsCode = null; //TODO 初始化// 已经存在的
        relationSpsNotificationCategory.setSpsCode(spsCode);
        relationSpsNotificationCategory.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = relationSpsNotificationCategoryService.insert(relationSpsNotificationCategory);
            Assert.isTrue(!result);
        } catch (ExistedException e) {
            ex = e;
        }
        Assert.notNull(ex);
    }

    /**
     * 测试删除数据-成功
     */
    @Test
    public void testDelete() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategory relationSpsNotificationCategory = new RelationSpsNotificationCategory();
        relationSpsNotificationCategory.setId(TEST_DEFAULT_EXIST_ID);
        String notificationCategoryCode = null; //TODO 初始化
        relationSpsNotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String spsCode = null; //TODO 初始化
        relationSpsNotificationCategory.setSpsCode(spsCode);
        relationSpsNotificationCategory.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsNotificationCategoryService.delete(relationSpsNotificationCategory);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategory relationSpsNotificationCategory = new RelationSpsNotificationCategory();
        relationSpsNotificationCategory.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String notificationCategoryCode = null; //TODO 初始化
        relationSpsNotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String spsCode = null; //TODO 初始化
        relationSpsNotificationCategory.setSpsCode(spsCode);
        relationSpsNotificationCategory.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsNotificationCategoryService.delete(relationSpsNotificationCategory);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategory relationSpsNotificationCategory = new RelationSpsNotificationCategory();
        relationSpsNotificationCategory.setId(TEST_DEFAULT_EXIST_ID);
        String notificationCategoryCode = null; //TODO 初始化
        relationSpsNotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String spsCode = null; //TODO 初始化
        relationSpsNotificationCategory.setSpsCode(spsCode);
        relationSpsNotificationCategory.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsNotificationCategoryService.update(relationSpsNotificationCategory);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategory relationSpsNotificationCategory = new RelationSpsNotificationCategory();
        relationSpsNotificationCategory.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String notificationCategoryCode = null; //TODO 初始化
        relationSpsNotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String spsCode = null; //TODO 初始化
        relationSpsNotificationCategory.setSpsCode(spsCode);
        relationSpsNotificationCategory.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsNotificationCategoryService.update(relationSpsNotificationCategory);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategory c = relationSpsNotificationCategoryService.getRelationSpsNotificationCategoryById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategory c = relationSpsNotificationCategoryService.getRelationSpsNotificationCategoryById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategoryQuery queryBean = null;
        PageUtil pageUtil = null;
        List<RelationSpsNotificationCategory> list = relationSpsNotificationCategoryService.queryRelationSpsNotificationCategoryListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(relationSpsNotificationCategoryService);
        RelationSpsNotificationCategoryQuery queryBean = new RelationSpsNotificationCategoryQuery();
        String notificationCategoryCode = null; //TODO 初始化
        queryBean.setNotificationCategoryCode(notificationCategoryCode);
        String spsCode = null; //TODO 初始化
        queryBean.setSpsCode(spsCode);
        List<RelationSpsNotificationCategory> list = relationSpsNotificationCategoryService.queryRelationSpsNotificationCategoryList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
