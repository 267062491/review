package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.Tbinfo;
import com.letv.tbtSps.domain.query.TbinfoQuery;
/**
 * TbinfoDao接口<br/>
 * 对'tbt信息表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TbinfoDao {
    
    /**
     * 新增对象
     * 
     * @param tbinfo 
     * @return
     */
    public boolean insert(Tbinfo tbinfo);

    /**
     * 更新对象
     * 
     * @param tbinfo
     * @return
     */
    public boolean update(Tbinfo tbinfo);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Tbinfo> queryTbinfoList(TbinfoQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryTbinfoCount(TbinfoQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Tbinfo> queryTbinfoListWithPage(TbinfoQuery queryBean);

    /**
     * 删除记录
     * 
     * @param tbinfo
     * @return
     */
    public boolean delete(Tbinfo tbinfo);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public Tbinfo getTbinfoById(Long id);

    /**
     * 判断是否存在
     * 
     * @param tbinfo
     * @return
     */
    public boolean exist(Tbinfo tbinfo);

}
