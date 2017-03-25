package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsRelationMedicine;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * RelationSpsRelationMedicineService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsRelationMedicineService {

    /**
     * 批量增加对象信息
     * 
     * @param relationSpsRelationMedicineList
     * @return
     */
    public boolean insert(List<RelationSpsRelationMedicine> relationSpsRelationMedicineList);

    /**
     * 单个增加对象信息
     * 
     * @param relationSpsRelationMedicine
     * @return
     */
    public boolean insert(RelationSpsRelationMedicine relationSpsRelationMedicine);

    /**
     * 更新 对象信息
     * 
     * @param relationSpsRelationMedicine
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationSpsRelationMedicine relationSpsRelationMedicine);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineList(RelationSpsRelationMedicineQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineListWithPage(RelationSpsRelationMedicineQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsRelationMedicine
     *            　
     * @return
     */
    public boolean delete(RelationSpsRelationMedicine relationSpsRelationMedicine);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationSpsRelationMedicine getRelationSpsRelationMedicineById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsRelationMedicines
     *            RelationSpsRelationMedicine集合
     * @return
     */
    public boolean delete(RelationSpsRelationMedicine[] relationSpsRelationMedicines);
}
