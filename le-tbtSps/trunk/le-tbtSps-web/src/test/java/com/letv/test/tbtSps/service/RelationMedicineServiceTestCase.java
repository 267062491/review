package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.RelationMedicine;
import com.letv.tbtSps.domain.query.RelationMedicineQuery;
import com.letv.tbtSps.service.RelationMedicineService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * RelationMedicineService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class RelationMedicineServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private RelationMedicineService relationMedicineService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(relationMedicineService);
        RelationMedicine relationMedicine = new RelationMedicine();
        String relationMedicineCode = null; //TODO 初始化
        relationMedicine.setRelationMedicineCode(relationMedicineCode);
        String relationMedicineZh = null; //TODO 初始化
        relationMedicine.setRelationMedicineZh(relationMedicineZh);
        relationMedicine.setCreateUser(TestConstants.UER_NAME);
        boolean result = relationMedicineService.insert(relationMedicine);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(relationMedicineService);
        RelationMedicine relationMedicine = new RelationMedicine();
        String relationMedicineCode = null; //TODO 初始化// 已经存在的
        relationMedicine.setRelationMedicineCode(relationMedicineCode);
        String relationMedicineZh = null; //TODO 初始化// 已经存在的
        relationMedicine.setRelationMedicineZh(relationMedicineZh);
        relationMedicine.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = relationMedicineService.insert(relationMedicine);
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
        Assert.notNull(relationMedicineService);
        RelationMedicine relationMedicine = new RelationMedicine();
        relationMedicine.setId(TEST_DEFAULT_EXIST_ID);
        String relationMedicineCode = null; //TODO 初始化
        relationMedicine.setRelationMedicineCode(relationMedicineCode);
        String relationMedicineZh = null; //TODO 初始化
        relationMedicine.setRelationMedicineZh(relationMedicineZh);
        relationMedicine.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationMedicineService.delete(relationMedicine);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(relationMedicineService);
        RelationMedicine relationMedicine = new RelationMedicine();
        relationMedicine.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String relationMedicineCode = null; //TODO 初始化
        relationMedicine.setRelationMedicineCode(relationMedicineCode);
        String relationMedicineZh = null; //TODO 初始化
        relationMedicine.setRelationMedicineZh(relationMedicineZh);
        relationMedicine.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationMedicineService.delete(relationMedicine);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(relationMedicineService);
        RelationMedicine relationMedicine = new RelationMedicine();
        relationMedicine.setId(TEST_DEFAULT_EXIST_ID);
        String relationMedicineCode = null; //TODO 初始化
        relationMedicine.setRelationMedicineCode(relationMedicineCode);
        String relationMedicineZh = null; //TODO 初始化
        relationMedicine.setRelationMedicineZh(relationMedicineZh);
        relationMedicine.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationMedicineService.update(relationMedicine);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(relationMedicineService);
        RelationMedicine relationMedicine = new RelationMedicine();
        relationMedicine.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String relationMedicineCode = null; //TODO 初始化
        relationMedicine.setRelationMedicineCode(relationMedicineCode);
        String relationMedicineZh = null; //TODO 初始化
        relationMedicine.setRelationMedicineZh(relationMedicineZh);
        relationMedicine.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationMedicineService.update(relationMedicine);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(relationMedicineService);
        RelationMedicine c = relationMedicineService.getRelationMedicineById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(relationMedicineService);
        RelationMedicine c = relationMedicineService.getRelationMedicineById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(relationMedicineService);
        RelationMedicineQuery queryBean = null;
        PageUtil pageUtil = null;
        List<RelationMedicine> list = relationMedicineService.queryRelationMedicineListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(relationMedicineService);
        RelationMedicineQuery queryBean = new RelationMedicineQuery();
        String relationMedicineCode = null; //TODO 初始化
        queryBean.setRelationMedicineCode(relationMedicineCode);
        String relationMedicineZh = null; //TODO 初始化
        queryBean.setRelationMedicineZh(relationMedicineZh);
        List<RelationMedicine> list = relationMedicineService.queryRelationMedicineList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
