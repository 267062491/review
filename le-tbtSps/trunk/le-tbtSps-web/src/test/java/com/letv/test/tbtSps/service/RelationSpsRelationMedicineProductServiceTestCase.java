package com.letv.test.tbtSps.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.letv.tbtSps.domain.RelationSpsRelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineProductQuery;
import com.letv.tbtSps.service.RelationSpsRelationMedicineProductService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.test.base.BaseTransactionTestCase;
import com.letv.test.base.TestConstants;

/**
 * RelationSpsRelationMedicineProductService单元测试
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
public class RelationSpsRelationMedicineProductServiceTestCase extends BaseTransactionTestCase {

    /** 不存在的 ID-删改查-失败的测试用例 */
    private static final long TEST_NOT_EXIST_ID = -2L;
    
    /** 默认 存在的ID-删改查-成功的测试用例,数据库需存在改ID对应的数据 */
    private static final long TEST_DEFAULT_EXIST_ID = 1L;

    @Autowired
    private RelationSpsRelationMedicineProductService relationSpsRelationMedicineProductService;

    /**
     * 测试插入数据-成功
     */
    @Test
    public void testInsert() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct();
        String relationMedicineProductCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setRelationMedicineProductCode(relationMedicineProductCode);
        String spsCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setSpsCode(spsCode);
        relationSpsRelationMedicineProduct.setCreateUser(TestConstants.UER_NAME);
        boolean result = relationSpsRelationMedicineProductService.insert(relationSpsRelationMedicineProduct);
        Assert.isTrue(result);
    }

    /**
     * 测试插入数据-失败（重复、错误、长度越界等）
     */
    @Test
    public void testInsertFailure() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct();
        String relationMedicineProductCode = null; //TODO 初始化// 已经存在的
        relationSpsRelationMedicineProduct.setRelationMedicineProductCode(relationMedicineProductCode);
        String spsCode = null; //TODO 初始化// 已经存在的
        relationSpsRelationMedicineProduct.setSpsCode(spsCode);
        relationSpsRelationMedicineProduct.setCreateUser(TestConstants.UER_NAME);
        ExistedException ex = null;
        try {
            boolean result = relationSpsRelationMedicineProductService.insert(relationSpsRelationMedicineProduct);
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
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct();
        relationSpsRelationMedicineProduct.setId(TEST_DEFAULT_EXIST_ID);
        String relationMedicineProductCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setRelationMedicineProductCode(relationMedicineProductCode);
        String spsCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setSpsCode(spsCode);
        relationSpsRelationMedicineProduct.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsRelationMedicineProductService.delete(relationSpsRelationMedicineProduct);
        Assert.isTrue(result);
    }
    
    /**
     * 测试删除数据-失败（ID不存在等）
     */
    @Test
    public void testDeleteFailure() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct();
        relationSpsRelationMedicineProduct.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String relationMedicineProductCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setRelationMedicineProductCode(relationMedicineProductCode);
        String spsCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setSpsCode(spsCode);
        relationSpsRelationMedicineProduct.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsRelationMedicineProductService.delete(relationSpsRelationMedicineProduct);
        Assert.isTrue(!result);
    }

    /**
     * 测试修改数据-成功
     */
    @Test
    public void testUpdate() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct();
        relationSpsRelationMedicineProduct.setId(TEST_DEFAULT_EXIST_ID);
        String relationMedicineProductCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setRelationMedicineProductCode(relationMedicineProductCode);
        String spsCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setSpsCode(spsCode);
        relationSpsRelationMedicineProduct.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsRelationMedicineProductService.update(relationSpsRelationMedicineProduct);
        Assert.isTrue(result);
    }

    /**
     * 测试修改数据-失败（ID不存在等）
     */
    @Test
    public void testUpdateFailure() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct = new RelationSpsRelationMedicineProduct();
        relationSpsRelationMedicineProduct.setId(TEST_NOT_EXIST_ID);// 不存在的ID
        String relationMedicineProductCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setRelationMedicineProductCode(relationMedicineProductCode);
        String spsCode = null; //TODO 初始化
        relationSpsRelationMedicineProduct.setSpsCode(spsCode);
        relationSpsRelationMedicineProduct.setUpdateUser(TestConstants.UER_NAME);
        boolean result = relationSpsRelationMedicineProductService.update(relationSpsRelationMedicineProduct);
        Assert.isTrue(!result);
    }

    /**
     * 测试依据ID查询数据-成功
     */
    @Test
    public void testQuery() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProduct c = relationSpsRelationMedicineProductService.getRelationSpsRelationMedicineProductById(TEST_DEFAULT_EXIST_ID);
        Assert.notNull(c);
    }

    /**
     * 测试依据ID查询数据-失败（ID不存在等）
     */
    @Test
    public void testQueryFailure() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProduct c = relationSpsRelationMedicineProductService.getRelationSpsRelationMedicineProductById(TEST_NOT_EXIST_ID);// 不存在的ID
        Assert.isNull(c);
    }

    /**
     * 测试分页查询数据-成功
     */
    @Test
    public void testQueryWithPage() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProductQuery queryBean = null;
        PageUtil pageUtil = null;
        List<RelationSpsRelationMedicineProduct> list = relationSpsRelationMedicineProductService.queryRelationSpsRelationMedicineProductListWithPage(
                queryBean, pageUtil);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }

    /**
     * 测试查询数据集合-成功
     */
    @Test
    public void testQueryList() {
        Assert.notNull(relationSpsRelationMedicineProductService);
        RelationSpsRelationMedicineProductQuery queryBean = new RelationSpsRelationMedicineProductQuery();
        String relationMedicineProductCode = null; //TODO 初始化
        queryBean.setRelationMedicineProductCode(relationMedicineProductCode);
        String spsCode = null; //TODO 初始化
        queryBean.setSpsCode(spsCode);
        List<RelationSpsRelationMedicineProduct> list = relationSpsRelationMedicineProductService.queryRelationSpsRelationMedicineProductList(queryBean);
        Assert.notEmpty(list);
        logger.info("list size = " + list.size());
    }
}
