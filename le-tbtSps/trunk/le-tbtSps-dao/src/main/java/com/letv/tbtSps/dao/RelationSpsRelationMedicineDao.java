package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsRelationMedicine;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineQuery;
/**
 * RelationSpsRelationMedicineDao接口<br/>
 * 对'sps信息表-相关农药关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsRelationMedicineDao {
    
    /**
     * 新增对象
     * 
     * @param relationSpsRelationMedicine 
     * @return
     */
    public boolean insert(RelationSpsRelationMedicine relationSpsRelationMedicine);

    /**
     * 更新对象
     * 
     * @param relationSpsRelationMedicine
     * @return
     */
    public boolean update(RelationSpsRelationMedicine relationSpsRelationMedicine);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineList(RelationSpsRelationMedicineQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationSpsRelationMedicineCount(RelationSpsRelationMedicineQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineListWithPage(RelationSpsRelationMedicineQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationSpsRelationMedicine
     * @return
     */
    public boolean delete(RelationSpsRelationMedicine relationSpsRelationMedicine);
    public boolean deleteByCode(RelationSpsRelationMedicine relationSpsRelationMedicine);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationSpsRelationMedicine getRelationSpsRelationMedicineById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationSpsRelationMedicine
     * @return
     */
    public boolean exist(RelationSpsRelationMedicine relationSpsRelationMedicine);

}
