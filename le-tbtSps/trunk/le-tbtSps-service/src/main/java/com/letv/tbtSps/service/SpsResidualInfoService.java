package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.SpsResidualInfo;
import com.letv.tbtSps.domain.query.SpsResidualInfoQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * SpsResidualInfoService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsResidualInfoService {

    /**
     * 批量增加对象信息
     * 
     * @param spsResidualInfoList
     * @return
     */
    public boolean insert(List<SpsResidualInfo> spsResidualInfoList);

    /**
     * 单个增加对象信息
     * 
     * @param spsResidualInfo
     * @return
     */
    public boolean insert(SpsResidualInfo spsResidualInfo);

    /**
     * 更新 对象信息
     * 
     * @param spsResidualInfo
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(SpsResidualInfo spsResidualInfo);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsResidualInfo> querySpsResidualInfoList(SpsResidualInfoQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<SpsResidualInfo> querySpsResidualInfoListWithPage(SpsResidualInfoQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param spsResidualInfo
     *            　
     * @return
     */
    public boolean delete(SpsResidualInfo spsResidualInfo);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public SpsResidualInfo getSpsResidualInfoById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param spsResidualInfos
     *            SpsResidualInfo集合
     * @return
     */
    public boolean delete(SpsResidualInfo[] spsResidualInfos);
}
