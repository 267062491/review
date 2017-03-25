package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationMedicineProductQuery;
/**
 * RelationMedicineProductDao接口<br/>
 * 对'相关农产品'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationMedicineProductDao {
    
    /**
     * 新增对象
     * 
     * @param relationMedicineProduct 
     * @return
     */
    public boolean insert(RelationMedicineProduct relationMedicineProduct);

    /**
     * 更新对象
     * 
     * @param relationMedicineProduct
     * @return
     */
    public boolean update(RelationMedicineProduct relationMedicineProduct);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationMedicineProduct> queryRelationMedicineProductList(RelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationMedicineProductCount(RelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationMedicineProduct> queryRelationMedicineProductListWithPage(RelationMedicineProductQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationMedicineProduct
     * @return
     */
    public boolean delete(RelationMedicineProduct relationMedicineProduct);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationMedicineProduct getRelationMedicineProductById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationMedicineProduct
     * @return
     */
    public boolean exist(RelationMedicineProduct relationMedicineProduct);

}
