package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationTbrelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineProductQuery;
/**
 * RelationTbrelationMedicineProductDao接口<br/>
 * 对'tbt信息表-相关农产品关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationTbrelationMedicineProductDao {
    
    /**
     * 新增对象
     * 
     * @param relationTbrelationMedicineProduct 
     * @return
     */
    public boolean insert(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct);

    /**
     * 更新对象
     * 
     * @param relationTbrelationMedicineProduct
     * @return
     */
    public boolean update(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductList(RelationTbrelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationTbrelationMedicineProductCount(RelationTbrelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTbrelationMedicineProduct> queryRelationTbrelationMedicineProductListWithPage(RelationTbrelationMedicineProductQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationTbrelationMedicineProduct
     * @return
     */
    public boolean delete(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationTbrelationMedicineProduct getRelationTbrelationMedicineProductById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationTbrelationMedicineProduct
     * @return
     */
    public boolean exist(RelationTbrelationMedicineProduct relationTbrelationMedicineProduct);

}
