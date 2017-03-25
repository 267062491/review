package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.RoleResource;
import com.letv.tbtSps.domain.query.RoleResourceQuery;
import com.letv.tbtSps.service.RoleResourceService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * RoleResourceService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class RoleResourceServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(roleResourceService);
        RoleResource roleResource = new RoleResource();
        Long tRId = null; //TODO 初始化
        roleResource.setTRId(tRId);
        String roleCode = null; //TODO 初始化
        roleResource.setRoleCode(roleCode);
        roleResource.setCreateUser(TestConstants.UER_NAME);
        boolean result = roleResourceService.insert(roleResource);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(roleResourceService);
        RoleResource roleResource = new RoleResource();
        Long tRId = null; //TODO 初始化// 已经存在的
        roleResource.setTRId(tRId);
        String roleCode = null; //TODO 初始化// 已经存在的
        roleResource.setRoleCode(roleCode);
        roleResource.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = roleResourceService.insert(roleResource);
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
        Assert.notNull(roleResourceService);
        RoleResource roleResource = new RoleResource();
        roleResource.setId(TEST_DEFAULT_EXIST_ID);
        Long tRId = null; //TODO 初始化
        roleResource.setTRId(tRId);
        String roleCode = null; //TODO 初始化
        roleResource.setRoleCode(roleCode);
        roleResource.setUpdateUser(TestConstants.UER_NAME);
        boolean result = roleResourceService.delete(roleResource);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(roleResourceService);
        RoleResource roleResource = new RoleResource();
        roleResource.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        Long tRId = null; //TODO 初始化
        roleResource.setTRId(tRId);
        String roleCode = null; //TODO 初始化
        roleResource.setRoleCode(roleCode);
        roleResource.setUpdateUser(TestConstants.UER_NAME);
        boolean result = roleResourceService.delete(roleResource);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(roleResourceService);
        RoleResource roleResource = new RoleResource();
        roleResource.setId(TEST_DEFAULT_EXIST_ID);
        Long tRId = null; //TODO 初始化
        roleResource.setTRId(tRId);
        String roleCode = null; //TODO 初始化
        roleResource.setRoleCode(roleCode);
        roleResource.setUpdateUser(TestConstants.UER_NAME);
        boolean result = roleResourceService.update(roleResource);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(roleResourceService);
        RoleResource roleResource = new RoleResource();
        roleResource.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        Long tRId = null; //TODO 初始化
        roleResource.setTRId(tRId);
        String roleCode = null; //TODO 初始化
        roleResource.setRoleCode(roleCode);
        roleResource.setUpdateUser(TestConstants.UER_NAME);
        boolean result = roleResourceService.update(roleResource);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(roleResourceService);
        RoleResource c = roleResourceService.getRoleResourceById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(roleResourceService);
        RoleResource c = roleResourceService.getRoleResourceById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(roleResourceService);
        RoleResourceQuery queryBean = null;
        PageUtil pageUtil = null;
        List<RoleResource> list = roleResourceService.queryRoleResourceListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(roleResourceService);
        RoleResourceQuery queryBean = new RoleResourceQuery();
        Long tRId = null; //TODO 初始化
        queryBean.setTRId(tRId);
        String roleCode = null; //TODO 初始化
        queryBean.setRoleCode(roleCode);
        List<RoleResource> list = roleResourceService.queryRoleResourceList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
