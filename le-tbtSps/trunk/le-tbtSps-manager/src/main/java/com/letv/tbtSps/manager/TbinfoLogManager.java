package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.TbinfoLog;
import com.letv.tbtSps.domain.query.TbinfoLogQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * TbinfoLogManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TbinfoLogManager {

    /**
     * 批量增加对象信息
     * 
     * @param tbinfoLogList
     * @return
     */
    public boolean insert(List<TbinfoLog> tbinfoLogList);

    /**
     * 单个增加对象信息
     * 
     * @param tbinfoLog
     * @return
     */
    public boolean insert(TbinfoLog tbinfoLog);

    /**
     * 更新 对象信息
     * 
     * @param tbinfoLog
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(TbinfoLog tbinfoLog);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<TbinfoLog> queryTbinfoLogList(TbinfoLogQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<TbinfoLog> queryTbinfoLogListWithPage(TbinfoLogQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryTbinfoLogCount(TbinfoLogQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param tbinfoLog
     *            　
     * @return
     */
    public boolean delete(TbinfoLog tbinfoLog);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public TbinfoLog getTbinfoLogById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param tbinfoLogs
     *            TbinfoLog集合
     * @return
     */
    public boolean delete(TbinfoLog[] tbinfoLogs);

    /**
     * 判断是否存在
     * 
     * @param tbinfoLog
     * @return
     */
    public boolean exist(TbinfoLog tbinfoLog);
}
