package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.RelationTbrelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineProductQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * RelationTbrelationMedicineProductManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationTbrelationMedicineProductManager {

    /**
     * 批量增加对象信息
     * 
     * @param relationTbrelationMedicineProductList
     * @return
     */
    public boolean insert(List<RelationTbrelationMedicineProduct> relationTbrelationMedicineProductList);

    /**
     * 单个增加对象信息
     * 
     * @param relationTbrelationMedicineProduct
     * @return
     */
    public boolean insert(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct);

    /**
     * 更新 对象信息
     * 
     * @param relationTbrelationMedicineProduct
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductList(RelationTbrelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductListWithPage(RelationTbrelationMedicineProductQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryRelationTbrelationMedicineProductCount(RelationTbrelationMedicineProductQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTbrelationMedicineProduct
     *            　
     * @return
     */
    public boolean delete(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationTbrelationMedicineProduct getRelationTbrelationMedicineProductById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTbrelationMedicineProducts
     *            RelationTbrelationMedicineProduct集合
     * @return
     */
    public boolean delete(RelationTbrelationMedicineProduct[] relationTbrelationMedicineProducts);

    /**
     * 判断是否存在
     * 
     * @param relationTbrelationMedicineProduct
     * @return
     */
    public boolean exist(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct);
}
