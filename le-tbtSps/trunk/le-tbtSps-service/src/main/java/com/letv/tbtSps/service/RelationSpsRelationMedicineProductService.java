package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsRelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineProductQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * RelationSpsRelationMedicineProductService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsRelationMedicineProductService {

    /**
     * 批量增加对象信息
     * 
     * @param relationSpsRelationMedicineProductList
     * @return
     */
    public boolean insert(List<RelationSpsRelationMedicineProduct> relationSpsRelationMedicineProductList);

    /**
     * 单个增加对象信息
     * 
     * @param relationSpsRelationMedicineProduct
     * @return
     */
    public boolean insert(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct);

    /**
     * 更新 对象信息
     * 
     * @param relationSpsRelationMedicineProduct
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductList(RelationSpsRelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductListWithPage(RelationSpsRelationMedicineProductQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsRelationMedicineProduct
     *            　
     * @return
     */
    public boolean delete(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationSpsRelationMedicineProduct getRelationSpsRelationMedicineProductById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsRelationMedicineProducts
     *            RelationSpsRelationMedicineProduct集合
     * @return
     */
    public boolean delete(RelationSpsRelationMedicineProduct[] relationSpsRelationMedicineProducts);
}
