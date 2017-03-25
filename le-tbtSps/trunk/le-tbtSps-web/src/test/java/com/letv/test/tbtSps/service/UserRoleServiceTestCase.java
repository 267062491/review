package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.UserRole;
import com.letv.tbtSps.domain.query.UserRoleQuery;
import com.letv.tbtSps.service.UserRoleService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * UserRoleService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class UserRoleServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(userRoleService);
        UserRole userRole = new UserRole();
        Long tUId = null; //TODO 初始化
        userRole.setTUId(tUId);
        String userCode = null; //TODO 初始化
        userRole.setUserCode(userCode);
        userRole.setCreateUser(TestConstants.UER_NAME);
        boolean result = userRoleService.insert(userRole);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(userRoleService);
        UserRole userRole = new UserRole();
        Long tUId = null; //TODO 初始化// 已经存在的
        userRole.setTUId(tUId);
        String userCode = null; //TODO 初始化// 已经存在的
        userRole.setUserCode(userCode);
        userRole.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = userRoleService.insert(userRole);
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
        Assert.notNull(userRoleService);
        UserRole userRole = new UserRole();
        userRole.setId(TEST_DEFAULT_EXIST_ID);
        Long tUId = null; //TODO 初始化
        userRole.setTUId(tUId);
        String userCode = null; //TODO 初始化
        userRole.setUserCode(userCode);
        userRole.setUpdateUser(TestConstants.UER_NAME);
        boolean result = userRoleService.delete(userRole);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(userRoleService);
        UserRole userRole = new UserRole();
        userRole.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        Long tUId = null; //TODO 初始化
        userRole.setTUId(tUId);
        String userCode = null; //TODO 初始化
        userRole.setUserCode(userCode);
        userRole.setUpdateUser(TestConstants.UER_NAME);
        boolean result = userRoleService.delete(userRole);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(userRoleService);
        UserRole userRole = new UserRole();
        userRole.setId(TEST_DEFAULT_EXIST_ID);
        Long tUId = null; //TODO 初始化
        userRole.setTUId(tUId);
        String userCode = null; //TODO 初始化
        userRole.setUserCode(userCode);
        userRole.setUpdateUser(TestConstants.UER_NAME);
        boolean result = userRoleService.update(userRole);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(userRoleService);
        UserRole userRole = new UserRole();
        userRole.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        Long tUId = null; //TODO 初始化
        userRole.setTUId(tUId);
        String userCode = null; //TODO 初始化
        userRole.setUserCode(userCode);
        userRole.setUpdateUser(TestConstants.UER_NAME);
        boolean result = userRoleService.update(userRole);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(userRoleService);
        UserRole c = userRoleService.getUserRoleById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(userRoleService);
        UserRole c = userRoleService.getUserRoleById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(userRoleService);
        UserRoleQuery queryBean = null;
        PageUtil pageUtil = null;
        List<UserRole> list = userRoleService.queryUserRoleListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(userRoleService);
        UserRoleQuery queryBean = new UserRoleQuery();
        Long tUId = null; //TODO 初始化
        queryBean.setTUId(tUId);
        String userCode = null; //TODO 初始化
        queryBean.setUserCode(userCode);
        List<UserRole> list = userRoleService.queryUserRoleList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
