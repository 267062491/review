package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.RelationTbnotificationCategory;
import com.letv.tbtSps.domain.query.RelationTbnotificationCategoryQuery;
import com.letv.tbtSps.service.RelationTbnotificationCategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * RelationTbnotificationCategoryService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class RelationTbnotificationCategoryServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private RelationTbnotificationCategoryService relationTbnotificationCategoryService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategory relationTbnotificationCategory = new RelationTbnotificationCategory();
        String notificationCategoryCode = null; //TODO 初始化
        relationTbnotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String tbtCode = null; //TODO 初始化
        relationTbnotificationCategory.setTbtCode(tbtCode);
        relationTbnotificationCategory.setCreateUser(TestConstants.UER_NAME);
        boolean result = relationTbnotificationCategoryService.insert(relationTbnotificationCategory);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategory relationTbnotificationCategory = new RelationTbnotificationCategory();
        String notificationCategoryCode = null; //TODO 初始化// 已经存在的
        relationTbnotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String tbtCode = null; //TODO 初始化// 已经存在的
        relationTbnotificationCategory.setTbtCode(tbtCode);
        relationTbnotificationCategory.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = relationTbnotificationCategoryService.insert(relationTbnotificationCategory);
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
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategory relationTbnotificationCategory = new RelationTbnotificationCategory();
        relationTbnotificationCategory.setId(TEST_DEFAULT_EXIST_ID);
        String notificationCategoryCode = null; //TODO 初始化
        relationTbnotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String tbtCode = null; //TODO 初始化
        relationTbnotificationCategory.setTbtCode(tbtCode);
        relationTbnotificationCategory.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationTbnotificationCategoryService.delete(relationTbnotificationCategory);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategory relationTbnotificationCategory = new RelationTbnotificationCategory();
        relationTbnotificationCategory.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String notificationCategoryCode = null; //TODO 初始化
        relationTbnotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String tbtCode = null; //TODO 初始化
        relationTbnotificationCategory.setTbtCode(tbtCode);
        relationTbnotificationCategory.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationTbnotificationCategoryService.delete(relationTbnotificationCategory);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategory relationTbnotificationCategory = new RelationTbnotificationCategory();
        relationTbnotificationCategory.setId(TEST_DEFAULT_EXIST_ID);
        String notificationCategoryCode = null; //TODO 初始化
        relationTbnotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String tbtCode = null; //TODO 初始化
        relationTbnotificationCategory.setTbtCode(tbtCode);
        relationTbnotificationCategory.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationTbnotificationCategoryService.update(relationTbnotificationCategory);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategory relationTbnotificationCategory = new RelationTbnotificationCategory();
        relationTbnotificationCategory.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String notificationCategoryCode = null; //TODO 初始化
        relationTbnotificationCategory.setNotificationCategoryCode(notificationCategoryCode);
        String tbtCode = null; //TODO 初始化
        relationTbnotificationCategory.setTbtCode(tbtCode);
        relationTbnotificationCategory.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationTbnotificationCategoryService.update(relationTbnotificationCategory);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategory c = relationTbnotificationCategoryService.getRelationTbnotificationCategoryById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategory c = relationTbnotificationCategoryService.getRelationTbnotificationCategoryById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategoryQuery queryBean = null;
        PageUtil pageUtil = null;
        List<RelationTbnotificationCategory> list = relationTbnotificationCategoryService.queryRelationTbnotificationCategoryListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(relationTbnotificationCategoryService);
        RelationTbnotificationCategoryQuery queryBean = new RelationTbnotificationCategoryQuery();
        String notificationCategoryCode = null; //TODO 初始化
        queryBean.setNotificationCategoryCode(notificationCategoryCode);
        String tbtCode = null; //TODO 初始化
        queryBean.setTbtCode(tbtCode);
        List<RelationTbnotificationCategory> list = relationTbnotificationCategoryService.queryRelationTbnotificationCategoryList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
