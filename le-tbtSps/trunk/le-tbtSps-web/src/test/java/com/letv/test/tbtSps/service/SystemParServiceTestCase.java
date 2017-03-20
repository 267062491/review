package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.SystemPar;
import com.letv.tbtSps.domain.query.SystemParQuery;
import com.letv.tbtSps.service.SystemParService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * SystemParService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
public class SystemParServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private SystemParService systemParService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(systemParService);
        SystemPar systemPar = new SystemPar();
        String warehouseNo = null; //TODO 初始化
        systemPar.setWarehouseNo(warehouseNo);
        String parCode = null; //TODO 初始化
        systemPar.setParCode(parCode);
        systemPar.setCreateUser(TestConstants.UER_NAME);
        boolean result = systemParService.insert(systemPar);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(systemParService);
        SystemPar systemPar = new SystemPar();
        String warehouseNo = null; //TODO 初始化// 已经存在的
        systemPar.setWarehouseNo(warehouseNo);
        String parCode = null; //TODO 初始化// 已经存在的
        systemPar.setParCode(parCode);
        systemPar.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = systemParService.insert(systemPar);
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
        Assert.notNull(systemParService);
        SystemPar systemPar = new SystemPar();
        systemPar.setId(TEST_DEFAULT_EXIST_ID);
        String warehouseNo = null; //TODO 初始化
        systemPar.setWarehouseNo(warehouseNo);
        String parCode = null; //TODO 初始化
        systemPar.setParCode(parCode);
        systemPar.setUpdateUser(TestConstants.UER_NAME);
        boolean result = systemParService.delete(systemPar);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(systemParService);
        SystemPar systemPar = new SystemPar();
        systemPar.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String warehouseNo = null; //TODO 初始化
        systemPar.setWarehouseNo(warehouseNo);
        String parCode = null; //TODO 初始化
        systemPar.setParCode(parCode);
        systemPar.setUpdateUser(TestConstants.UER_NAME);
        boolean result = systemParService.delete(systemPar);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(systemParService);
        SystemPar systemPar = new SystemPar();
        systemPar.setId(TEST_DEFAULT_EXIST_ID);
        String warehouseNo = null; //TODO 初始化
        systemPar.setWarehouseNo(warehouseNo);
        String parCode = null; //TODO 初始化
        systemPar.setParCode(parCode);
        systemPar.setUpdateUser(TestConstants.UER_NAME);
        boolean result = systemParService.update(systemPar);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(systemParService);
        SystemPar systemPar = new SystemPar();
        systemPar.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String warehouseNo = null; //TODO 初始化
        systemPar.setWarehouseNo(warehouseNo);
        String parCode = null; //TODO 初始化
        systemPar.setParCode(parCode);
        systemPar.setUpdateUser(TestConstants.UER_NAME);
        boolean result = systemParService.update(systemPar);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(systemParService);
        SystemPar c = systemParService.getSystemParById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(systemParService);
        SystemPar c = systemParService.getSystemParById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(systemParService);
        SystemParQuery queryBean = null;
        PageUtil pageUtil = null;
        List<SystemPar> list = systemParService.querySystemParListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(systemParService);
        SystemParQuery queryBean = new SystemParQuery();
        String warehouseNo = null; //TODO 初始化
        queryBean.setWarehouseNo(warehouseNo);
        String parCode = null; //TODO 初始化
        queryBean.setParCode(parCode);
        List<SystemPar> list = systemParService.querySystemParList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
