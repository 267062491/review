package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * SpsInfoService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsInfoService {

    /**
     * 批量增加对象信息
     * 
     * @param spsInfoList
     * @return
     */
    public boolean insert(List<SpsInfo> spsInfoList);

    /**
     * 单个增加对象信息
     * 
     * @param spsInfo
     * @return
     */
    public boolean insert(SpsInfo spsInfo);

    /**
     * 更新 对象信息
     * 
     * @param spsInfo
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(SpsInfo spsInfo);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoList(SpsInfoQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<SpsInfo> querySpsInfoListWithPage(SpsInfoQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param spsInfo
     *            　
     * @return
     */
    public boolean delete(SpsInfo spsInfo);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public SpsInfo getSpsInfoById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param spsInfos
     *            SpsInfo集合
     * @return
     */
    public boolean delete(SpsInfo[] spsInfos);
}