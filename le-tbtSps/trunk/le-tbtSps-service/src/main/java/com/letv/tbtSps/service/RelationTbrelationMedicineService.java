package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.RelationTbrelationMedicine;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * RelationTbrelationMedicineService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationTbrelationMedicineService {

    /**
     * 批量增加对象信息
     * 
     * @param relationTbrelationMedicineList
     * @return
     */
    public boolean insert(List<RelationTbrelationMedicine> relationTbrelationMedicineList);

    /**
     * 单个增加对象信息
     * 
     * @param relationTbrelationMedicine
     * @return
     */
    public boolean insert(RelationTbrelationMedicine relationTbrelationMedicine);

    /**
     * 更新 对象信息
     * 
     * @param relationTbrelationMedicine
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationTbrelationMedicine relationTbrelationMedicine);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationTbrelationMedicine> queryRelationTbrelationMedicineList(RelationTbrelationMedicineQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationTbrelationMedicine> queryRelationTbrelationMedicineListWithPage(RelationTbrelationMedicineQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTbrelationMedicine
     *            　
     * @return
     */
    public boolean delete(RelationTbrelationMedicine relationTbrelationMedicine);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationTbrelationMedicine getRelationTbrelationMedicineById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationTbrelationMedicines
     *            RelationTbrelationMedicine集合
     * @return
     */
    public boolean delete(RelationTbrelationMedicine[] relationTbrelationMedicines);
}
