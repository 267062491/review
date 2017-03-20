package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.SystemPar;
import com.letv.tbtSps.domain.query.SystemParQuery;
/**
 * SystemParDao接口<br/>
 * 对'系统参数表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
public interface SystemParDao {
    
    /**
     * 新增对象
     * 
     * @param systemPar 
     * @return
     */
    public boolean insert(SystemPar systemPar);

    /**
     * 更新对象
     * 
     * @param systemPar
     * @return
     */
    public boolean update(SystemPar systemPar);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SystemPar> querySystemParList(SystemParQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int querySystemParCount(SystemParQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SystemPar> querySystemParListWithPage(SystemParQuery queryBean);

    /**
     * 删除记录
     * 
     * @param systemPar
     * @return
     */
    public boolean delete(SystemPar systemPar);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public SystemPar getSystemParById(Long id);

    /**
     * 判断是否存在
     * 
     * @param systemPar
     * @return
     */
    public boolean exist(SystemPar systemPar);

}
