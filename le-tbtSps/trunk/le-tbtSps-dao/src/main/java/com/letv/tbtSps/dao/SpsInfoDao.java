package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
/**
 * SpsInfoDao接口<br/>
 * 对'sps信息表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsInfoDao {
    
    /**
     * 新增对象
     * 
     * @param spsInfo 
     * @return
     */
    public boolean insert(SpsInfo spsInfo);

    /**
     * 更新对象
     * 
     * @param spsInfo
     * @return
     */
    public boolean update(SpsInfo spsInfo);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoList(SpsInfoQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int querySpsInfoCount(SpsInfoQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoListWithPage(SpsInfoQuery queryBean);

    /**
     * 删除记录
     * 
     * @param spsInfo
     * @return
     */
    public boolean delete(SpsInfo spsInfo);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public SpsInfo getSpsInfoById(Long id);

    /**
     * 判断是否存在
     * 
     * @param spsInfo
     * @return
     */
    public boolean exist(SpsInfo spsInfo);

}