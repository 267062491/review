package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.TbinfoLog;
import com.letv.tbtSps.domain.query.TbinfoLogQuery;
/**
 * TbinfoLogDao接口<br/>
 * 对'tbt信息操作日志表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TbinfoLogDao {
    
    /**
     * 新增对象
     * 
     * @param tbinfoLog 
     * @return
     */
    public boolean insert(TbinfoLog tbinfoLog);

    /**
     * 更新对象
     * 
     * @param tbinfoLog
     * @return
     */
    public boolean update(TbinfoLog tbinfoLog);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<TbinfoLog> queryTbinfoLogList(TbinfoLogQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryTbinfoLogCount(TbinfoLogQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<TbinfoLog> queryTbinfoLogListWithPage(TbinfoLogQuery queryBean);

    /**
     * 删除记录
     * 
     * @param tbinfoLog
     * @return
     */
    public boolean delete(TbinfoLog tbinfoLog);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public TbinfoLog getTbinfoLogById(Long id);

    /**
     * 判断是否存在
     * 
     * @param tbinfoLog
     * @return
     */
    public boolean exist(TbinfoLog tbinfoLog);

}
