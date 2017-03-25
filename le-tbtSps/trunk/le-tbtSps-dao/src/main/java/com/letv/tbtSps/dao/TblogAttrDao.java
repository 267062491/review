package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.TblogAttr;
import com.letv.tbtSps.domain.query.TblogAttrQuery;
/**
 * TblogAttrDao接口<br/>
 * 对'tbt评审信息表对应的附件'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TblogAttrDao {
    
    /**
     * 新增对象
     * 
     * @param tblogAttr 
     * @return
     */
    public boolean insert(TblogAttr tblogAttr);

    /**
     * 更新对象
     * 
     * @param tblogAttr
     * @return
     */
    public boolean update(TblogAttr tblogAttr);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<TblogAttr> queryTblogAttrList(TblogAttrQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryTblogAttrCount(TblogAttrQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<TblogAttr> queryTblogAttrListWithPage(TblogAttrQuery queryBean);

    /**
     * 删除记录
     * 
     * @param tblogAttr
     * @return
     */
    public boolean delete(TblogAttr tblogAttr);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public TblogAttr getTblogAttrById(Long id);

    /**
     * 判断是否存在
     * 
     * @param tblogAttr
     * @return
     */
    public boolean exist(TblogAttr tblogAttr);

}
