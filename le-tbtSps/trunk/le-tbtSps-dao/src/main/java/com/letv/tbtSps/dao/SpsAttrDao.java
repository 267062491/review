package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.SpsAttr;
import com.letv.tbtSps.domain.query.SpsAttrQuery;
/**
 * SpsAttrDao接口<br/>
 * 对'sps信息表对应的附件'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsAttrDao {
    
    /**
     * 新增对象
     * 
     * @param spsAttr 
     * @return
     */
    public boolean insert(SpsAttr spsAttr);

    /**
     * 更新对象
     * 
     * @param spsAttr
     * @return
     */
    public boolean update(SpsAttr spsAttr);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsAttr> querySpsAttrList(SpsAttrQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int querySpsAttrCount(SpsAttrQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsAttr> querySpsAttrListWithPage(SpsAttrQuery queryBean);

    /**
     * 删除记录
     * 
     * @param spsAttr
     * @return
     */
    public boolean delete(SpsAttr spsAttr);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public SpsAttr getSpsAttrById(Long id);

    /**
     * 判断是否存在
     * 
     * @param spsAttr
     * @return
     */
    public boolean exist(SpsAttr spsAttr);

}
