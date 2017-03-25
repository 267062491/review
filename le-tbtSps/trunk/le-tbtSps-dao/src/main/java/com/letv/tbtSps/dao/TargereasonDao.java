package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.Targereason;
import com.letv.tbtSps.domain.query.TargereasonQuery;
/**
 * TargereasonDao接口<br/>
 * 对'目标理由'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TargereasonDao {
    
    /**
     * 新增对象
     * 
     * @param targereason 
     * @return
     */
    public boolean insert(Targereason targereason);

    /**
     * 更新对象
     * 
     * @param targereason
     * @return
     */
    public boolean update(Targereason targereason);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Targereason> queryTargereasonList(TargereasonQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryTargereasonCount(TargereasonQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Targereason> queryTargereasonListWithPage(TargereasonQuery queryBean);

    /**
     * 删除记录
     * 
     * @param targereason
     * @return
     */
    public boolean delete(Targereason targereason);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public Targereason getTargereasonById(Long id);

    /**
     * 判断是否存在
     * 
     * @param targereason
     * @return
     */
    public boolean exist(Targereason targereason);

}
