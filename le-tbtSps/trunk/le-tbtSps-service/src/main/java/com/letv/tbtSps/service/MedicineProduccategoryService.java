package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.MedicineProduccategory;
import com.letv.tbtSps.domain.query.MedicineProduccategoryQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * MedicineProduccategoryService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface MedicineProduccategoryService {

    /**
     * 批量增加对象信息
     * 
     * @param medicineProduccategoryList
     * @return
     */
    public boolean insert(List<MedicineProduccategory> medicineProduccategoryList);

    /**
     * 单个增加对象信息
     * 
     * @param medicineProduccategory
     * @return
     */
    public boolean insert(MedicineProduccategory medicineProduccategory);

    /**
     * 更新 对象信息
     * 
     * @param medicineProduccategory
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(MedicineProduccategory medicineProduccategory);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<MedicineProduccategory> queryMedicineProduccategoryList(MedicineProduccategoryQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<MedicineProduccategory> queryMedicineProduccategoryListWithPage(MedicineProduccategoryQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param medicineProduccategory
     *            　
     * @return
     */
    public boolean delete(MedicineProduccategory medicineProduccategory);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public MedicineProduccategory getMedicineProduccategoryById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param medicineProduccategorys
     *            MedicineProduccategory集合
     * @return
     */
    public boolean delete(MedicineProduccategory[] medicineProduccategorys);
}
