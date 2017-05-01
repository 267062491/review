package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.query.SpsInfoLogQuery;
/**
 * SpsInfoLogDao接口<br/>
 * 对'sps信息操作日志表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsInfoLogDao {
    
    /**
     * 新增对象
     * 
     * @param spsInfoLog 
     * @return
     */
    public boolean insert(SpsInfoLog spsInfoLog);

    /**
     * 更新对象
     * 
     * @param spsInfoLog
     * @return
     */
    public boolean update(SpsInfoLog spsInfoLog);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsInfoLog> querySpsInfoLogList(SpsInfoLogQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int querySpsInfoLogCount(SpsInfoLogQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsInfoLog> querySpsInfoLogListWithPage(SpsInfoLogQuery queryBean);
    public List<SpsInfoLog> queryExperts(SpsInfoLogQuery queryBean);
    public List<SpsInfoLog> queryUnReviewLog(SpsInfoLogQuery queryBean);

    /**
     * 删除记录
     * 
     * @param spsInfoLog
     * @return
     */
    public boolean delete(SpsInfoLog spsInfoLog);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public SpsInfoLog getSpsInfoLogById(Long id);

    /**
     * 判断是否存在
     * 
     * @param spsInfoLog
     * @return
     */
    public boolean exist(SpsInfoLog spsInfoLog);

    /**
     * 根据spsCode修改canEdit状态
     * @param spsInfoLog
     * @return
     */
    public boolean updateCanEditBySpsCode(SpsInfoLog spsInfoLog);





}
