package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationMedicine;
import com.letv.tbtSps.domain.query.RelationMedicineQuery;
/**
 * RelationMedicineDao接口<br/>
 * 对'相关农药'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationMedicineDao {
    
    /**
     * 新增对象
     * 
     * @param relationMedicine 
     * @return
     */
    public boolean insert(RelationMedicine relationMedicine);

    /**
     * 更新对象
     * 
     * @param relationMedicine
     * @return
     */
    public boolean update(RelationMedicine relationMedicine);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationMedicine> queryRelationMedicineList(RelationMedicineQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationMedicineCount(RelationMedicineQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationMedicine> queryRelationMedicineListWithPage(RelationMedicineQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationMedicine
     * @return
     */
    public boolean delete(RelationMedicine relationMedicine);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationMedicine getRelationMedicineById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationMedicine
     * @return
     */
    public boolean exist(RelationMedicine relationMedicine);

}
