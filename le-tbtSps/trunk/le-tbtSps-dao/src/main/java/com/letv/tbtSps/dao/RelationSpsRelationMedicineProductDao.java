package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsRelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineProductQuery;
/**
 * RelationSpsRelationMedicineProductDao接口<br/>
 * 对'sps信息表-相关农产品关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsRelationMedicineProductDao {
    
    /**
     * 新增对象
     * 
     * @param relationSpsRelationMedicineProduct 
     * @return
     */
    public boolean insert(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct);

    /**
     * 更新对象
     * 
     * @param relationSpsRelationMedicineProduct
     * @return
     */
    public boolean update(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductList(RelationSpsRelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationSpsRelationMedicineProductCount(RelationSpsRelationMedicineProductQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsRelationMedicineProduct> queryRelationSpsRelationMedicineProductListWithPage(RelationSpsRelationMedicineProductQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationSpsRelationMedicineProduct
     * @return
     */
    public boolean delete(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct);
    public boolean deleteByCode(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationSpsRelationMedicineProduct getRelationSpsRelationMedicineProductById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationSpsRelationMedicineProduct
     * @return
     */
    public boolean exist(RelationSpsRelationMedicineProduct relationSpsRelationMedicineProduct);

}
