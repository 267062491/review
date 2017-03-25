package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.domain.query.SpsLogAttrQuery;
/**
 * SpsLogAttrDao接口<br/>
 * 对'sps评审信息表对应的附件'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsLogAttrDao {
    
    /**
     * 新增对象
     * 
     * @param spsLogAttr 
     * @return
     */
    public boolean insert(SpsLogAttr spsLogAttr);

    /**
     * 更新对象
     * 
     * @param spsLogAttr
     * @return
     */
    public boolean update(SpsLogAttr spsLogAttr);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsLogAttr> querySpsLogAttrList(SpsLogAttrQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int querySpsLogAttrCount(SpsLogAttrQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsLogAttr> querySpsLogAttrListWithPage(SpsLogAttrQuery queryBean);

    /**
     * 删除记录
     * 
     * @param spsLogAttr
     * @return
     */
    public boolean delete(SpsLogAttr spsLogAttr);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public SpsLogAttr getSpsLogAttrById(Long id);

    /**
     * 判断是否存在
     * 
     * @param spsLogAttr
     * @return
     */
    public boolean exist(SpsLogAttr spsLogAttr);

}
