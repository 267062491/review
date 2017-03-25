package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.RelationSpsLanguage;
import com.letv.tbtSps.domain.query.RelationSpsLanguageQuery;
import com.letv.tbtSps.service.RelationSpsLanguageService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * RelationSpsLanguageService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class RelationSpsLanguageServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private RelationSpsLanguageService relationSpsLanguageService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguage relationSpsLanguage = new RelationSpsLanguage();
        String languageCode = null; //TODO 初始化
        relationSpsLanguage.setLanguageCode(languageCode);
        String spsCode = null; //TODO 初始化
        relationSpsLanguage.setSpsCode(spsCode);
        relationSpsLanguage.setCreateUser(TestConstants.UER_NAME);
        boolean result = relationSpsLanguageService.insert(relationSpsLanguage);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguage relationSpsLanguage = new RelationSpsLanguage();
        String languageCode = null; //TODO 初始化// 已经存在的
        relationSpsLanguage.setLanguageCode(languageCode);
        String spsCode = null; //TODO 初始化// 已经存在的
        relationSpsLanguage.setSpsCode(spsCode);
        relationSpsLanguage.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = relationSpsLanguageService.insert(relationSpsLanguage);
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
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguage relationSpsLanguage = new RelationSpsLanguage();
        relationSpsLanguage.setId(TEST_DEFAULT_EXIST_ID);
        String languageCode = null; //TODO 初始化
        relationSpsLanguage.setLanguageCode(languageCode);
        String spsCode = null; //TODO 初始化
        relationSpsLanguage.setSpsCode(spsCode);
        relationSpsLanguage.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsLanguageService.delete(relationSpsLanguage);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguage relationSpsLanguage = new RelationSpsLanguage();
        relationSpsLanguage.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String languageCode = null; //TODO 初始化
        relationSpsLanguage.setLanguageCode(languageCode);
        String spsCode = null; //TODO 初始化
        relationSpsLanguage.setSpsCode(spsCode);
        relationSpsLanguage.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsLanguageService.delete(relationSpsLanguage);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguage relationSpsLanguage = new RelationSpsLanguage();
        relationSpsLanguage.setId(TEST_DEFAULT_EXIST_ID);
        String languageCode = null; //TODO 初始化
        relationSpsLanguage.setLanguageCode(languageCode);
        String spsCode = null; //TODO 初始化
        relationSpsLanguage.setSpsCode(spsCode);
        relationSpsLanguage.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsLanguageService.update(relationSpsLanguage);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguage relationSpsLanguage = new RelationSpsLanguage();
        relationSpsLanguage.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String languageCode = null; //TODO 初始化
        relationSpsLanguage.setLanguageCode(languageCode);
        String spsCode = null; //TODO 初始化
        relationSpsLanguage.setSpsCode(spsCode);
        relationSpsLanguage.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsLanguageService.update(relationSpsLanguage);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguage c = relationSpsLanguageService.getRelationSpsLanguageById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguage c = relationSpsLanguageService.getRelationSpsLanguageById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguageQuery queryBean = null;
        PageUtil pageUtil = null;
        List<RelationSpsLanguage> list = relationSpsLanguageService.queryRelationSpsLanguageListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(relationSpsLanguageService);
        RelationSpsLanguageQuery queryBean = new RelationSpsLanguageQuery();
        String languageCode = null; //TODO 初始化
        queryBean.setLanguageCode(languageCode);
        String spsCode = null; //TODO 初始化
        queryBean.setSpsCode(spsCode);
        List<RelationSpsLanguage> list = relationSpsLanguageService.queryRelationSpsLanguageList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
