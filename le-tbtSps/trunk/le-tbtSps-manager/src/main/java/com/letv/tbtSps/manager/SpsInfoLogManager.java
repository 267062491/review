package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.query.SpsInfoLogQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * SpsInfoLogManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsInfoLogManager {

    /**
     * 批量增加对象信息
     * 
     * @param spsInfoLogList
     * @return
     */
    public boolean insert(List<SpsInfoLog> spsInfoLogList);

    /**
     * 单个增加对象信息
     * 
     * @param spsInfoLog
     * @return
     */
    public boolean insert(SpsInfoLog spsInfoLog);

    /**
     * 更新 对象信息
     * 
     * @param spsInfoLog
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(SpsInfoLog spsInfoLog);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsInfoLog> querySpsInfoLogList(SpsInfoLogQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<SpsInfoLog> querySpsInfoLogListWithPage(SpsInfoLogQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int querySpsInfoLogCount(SpsInfoLogQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param spsInfoLog
     *            　
     * @return
     */
    public boolean delete(SpsInfoLog spsInfoLog);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public SpsInfoLog getSpsInfoLogById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param spsInfoLogs
     *            SpsInfoLog集合
     * @return
     */
    public boolean delete(SpsInfoLog[] spsInfoLogs);

    /**
     * 判断是否存在
     * 
     * @param spsInfoLog
     * @return
     */
    public boolean exist(SpsInfoLog spsInfoLog);
}
