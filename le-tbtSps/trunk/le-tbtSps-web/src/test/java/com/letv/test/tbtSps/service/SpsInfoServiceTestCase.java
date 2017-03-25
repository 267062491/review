package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.tbtSps.service.SpsInfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * SpsInfoService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class SpsInfoServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private SpsInfoService spsInfoService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(spsInfoService);
        SpsInfo spsInfo = new SpsInfo();
        String spsCode = null; //TODO 初始化
        spsInfo.setSpsCode(spsCode);
        String countryCode = null; //TODO 初始化
        spsInfo.setCountryCode(countryCode);
        spsInfo.setCreateUser(TestConstants.UER_NAME);
        boolean result = spsInfoService.insert(spsInfo);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(spsInfoService);
        SpsInfo spsInfo = new SpsInfo();
        String spsCode = null; //TODO 初始化// 已经存在的
        spsInfo.setSpsCode(spsCode);
        String countryCode = null; //TODO 初始化// 已经存在的
        spsInfo.setCountryCode(countryCode);
        spsInfo.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = spsInfoService.insert(spsInfo);
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
        Assert.notNull(spsInfoService);
        SpsInfo spsInfo = new SpsInfo();
        spsInfo.setId(TEST_DEFAULT_EXIST_ID);
        String spsCode = null; //TODO 初始化
        spsInfo.setSpsCode(spsCode);
        String countryCode = null; //TODO 初始化
        spsInfo.setCountryCode(countryCode);
        spsInfo.setUpdateUser(TestConstants.UER_NAME);
        boolean result = spsInfoService.delete(spsInfo);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(spsInfoService);
        SpsInfo spsInfo = new SpsInfo();
        spsInfo.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String spsCode = null; //TODO 初始化
        spsInfo.setSpsCode(spsCode);
        String countryCode = null; //TODO 初始化
        spsInfo.setCountryCode(countryCode);
        spsInfo.setUpdateUser(TestConstants.UER_NAME);
        boolean result = spsInfoService.delete(spsInfo);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(spsInfoService);
        SpsInfo spsInfo = new SpsInfo();
        spsInfo.setId(TEST_DEFAULT_EXIST_ID);
        String spsCode = null; //TODO 初始化
        spsInfo.setSpsCode(spsCode);
        String countryCode = null; //TODO 初始化
        spsInfo.setCountryCode(countryCode);
        spsInfo.setUpdateUser(TestConstants.UER_NAME);
        boolean result = spsInfoService.update(spsInfo);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(spsInfoService);
        SpsInfo spsInfo = new SpsInfo();
        spsInfo.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String spsCode = null; //TODO 初始化
        spsInfo.setSpsCode(spsCode);
        String countryCode = null; //TODO 初始化
        spsInfo.setCountryCode(countryCode);
        spsInfo.setUpdateUser(TestConstants.UER_NAME);
        boolean result = spsInfoService.update(spsInfo);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(spsInfoService);
        SpsInfo c = spsInfoService.getSpsInfoById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(spsInfoService);
        SpsInfo c = spsInfoService.getSpsInfoById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(spsInfoService);
        SpsInfoQuery queryBean = null;
        PageUtil pageUtil = null;
        List<SpsInfo> list = spsInfoService.querySpsInfoListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(spsInfoService);
        SpsInfoQuery queryBean = new SpsInfoQuery();
        String spsCode = null; //TODO 初始化
        queryBean.setSpsCode(spsCode);
        String countryCode = null; //TODO 初始化
        queryBean.setCountryCode(countryCode);
        List<SpsInfo> list = spsInfoService.querySpsInfoList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
