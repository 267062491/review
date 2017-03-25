package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.MedicineProduccategory;
import com.letv.tbtSps.domain.query.MedicineProduccategoryQuery;
/**
 * MedicineProduccategoryDao接口<br/>
 * 对'农产品分类'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface MedicineProduccategoryDao {
    
    /**
     * 新增对象
     * 
     * @param medicineProduccategory 
     * @return
     */
    public boolean insert(MedicineProduccategory medicineProduccategory);

    /**
     * 更新对象
     * 
     * @param medicineProduccategory
     * @return
     */
    public boolean update(MedicineProduccategory medicineProduccategory);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<MedicineProduccategory> queryMedicineProduccategoryList(MedicineProduccategoryQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryMedicineProduccategoryCount(MedicineProduccategoryQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<MedicineProduccategory> queryMedicineProduccategoryListWithPage(MedicineProduccategoryQuery queryBean);

    /**
     * 删除记录
     * 
     * @param medicineProduccategory
     * @return
     */
    public boolean delete(MedicineProduccategory medicineProduccategory);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public MedicineProduccategory getMedicineProduccategoryById(Long id);

    /**
     * 判断是否存在
     * 
     * @param medicineProduccategory
     * @return
     */
    public boolean exist(MedicineProduccategory medicineProduccategory);

}
