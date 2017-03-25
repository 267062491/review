package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.SpsAttr;
import com.letv.tbtSps.domain.query.SpsAttrQuery;
import com.letv.tbtSps.service.SpsAttrService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * SpsAttrService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class SpsAttrServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private SpsAttrService spsAttrService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(spsAttrService);
        SpsAttr spsAttr = new SpsAttr();
        String spsCode = null; //TODO 初始化
        spsAttr.setSpsCode(spsCode);
        String fileName = null; //TODO 初始化
        spsAttr.setFileName(fileName);
        spsAttr.setCreateUser(TestConstants.UER_NAME);
        boolean result = spsAttrService.insert(spsAttr);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(spsAttrService);
        SpsAttr spsAttr = new SpsAttr();
        String spsCode = null; //TODO 初始化// 已经存在的
        spsAttr.setSpsCode(spsCode);
        String fileName = null; //TODO 初始化// 已经存在的
        spsAttr.setFileName(fileName);
        spsAttr.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = spsAttrService.insert(spsAttr);
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
        Assert.notNull(spsAttrService);
        SpsAttr spsAttr = new SpsAttr();
        spsAttr.setId(TEST_DEFAULT_EXIST_ID);
        String spsCode = null; //TODO 初始化
        spsAttr.setSpsCode(spsCode);
        String fileName = null; //TODO 初始化
        spsAttr.setFileName(fileName);
        spsAttr.setUpdateUser(TestConstants.UER_NAME);
        boolean result = spsAttrService.delete(spsAttr);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(spsAttrService);
        SpsAttr spsAttr = new SpsAttr();
        spsAttr.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String spsCode = null; //TODO 初始化
        spsAttr.setSpsCode(spsCode);
        String fileName = null; //TODO 初始化
        spsAttr.setFileName(fileName);
        spsAttr.setUpdateUser(TestConstants.UER_NAME);
        boolean result = spsAttrService.delete(spsAttr);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(spsAttrService);
        SpsAttr spsAttr = new SpsAttr();
        spsAttr.setId(TEST_DEFAULT_EXIST_ID);
        String spsCode = null; //TODO 初始化
        spsAttr.setSpsCode(spsCode);
        String fileName = null; //TODO 初始化
        spsAttr.setFileName(fileName);
        spsAttr.setUpdateUser(TestConstants.UER_NAME);
        boolean result = spsAttrService.update(spsAttr);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(spsAttrService);
        SpsAttr spsAttr = new SpsAttr();
        spsAttr.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String spsCode = null; //TODO 初始化
        spsAttr.setSpsCode(spsCode);
        String fileName = null; //TODO 初始化
        spsAttr.setFileName(fileName);
        spsAttr.setUpdateUser(TestConstants.UER_NAME);
        boolean result = spsAttrService.update(spsAttr);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(spsAttrService);
        SpsAttr c = spsAttrService.getSpsAttrById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(spsAttrService);
        SpsAttr c = spsAttrService.getSpsAttrById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(spsAttrService);
        SpsAttrQuery queryBean = null;
        PageUtil pageUtil = null;
        List<SpsAttr> list = spsAttrService.querySpsAttrListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(spsAttrService);
        SpsAttrQuery queryBean = new SpsAttrQuery();
        String spsCode = null; //TODO 初始化
        queryBean.setSpsCode(spsCode);
        String fileName = null; //TODO 初始化
        queryBean.setFileName(fileName);
        List<SpsAttr> list = spsAttrService.querySpsAttrList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
