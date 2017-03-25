package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.RelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationMedicineProductQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * RelationMedicineProductService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationMedicineProductService {

    /**
     * 批量增加对象信息
     * 
     * @param relationMedicineProductList
     * @return
     */
    public boolean insert(List<RelationMedicineProduct> relationMedicineProductList);

    /**
     * 单个增加对象信息
     * 
     * @param relationMedicineProduct
     * @return
     */
    public boolean insert(RelationMedicineProduct relationMedicineProduct);

    /**
     * 更新 对象信息
     * 
     * @param relationMedicineProduct
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationMedicineProduct relationMedicineProduct);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationMedicineProduct> queryRelationMedicineProductList(RelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationMedicineProduct> queryRelationMedicineProductListWithPage(RelationMedicineProductQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationMedicineProduct
     *            　
     * @return
     */
    public boolean delete(RelationMedicineProduct relationMedicineProduct);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationMedicineProduct getRelationMedicineProductById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationMedicineProducts
     *            RelationMedicineProduct集合
     * @return
     */
    public boolean delete(RelationMedicineProduct[] relationMedicineProducts);
}
