package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.SpsResidualInfo;
import com.letv.tbtSps.domain.query.SpsResidualInfoQuery;
/**
 * SpsResidualInfoDao接口<br/>
 * 对'sps残留量信息'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsResidualInfoDao {
    
    /**
     * 新增对象
     * 
     * @param spsResidualInfo 
     * @return
     */
    public boolean insert(SpsResidualInfo spsResidualInfo);

    /**
     * 更新对象
     * 
     * @param spsResidualInfo
     * @return
     */
    public boolean update(SpsResidualInfo spsResidualInfo);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsResidualInfo> querySpsResidualInfoList(SpsResidualInfoQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int querySpsResidualInfoCount(SpsResidualInfoQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsResidualInfo> querySpsResidualInfoListWithPage(SpsResidualInfoQuery queryBean);

    /**
     * 删除记录
     * 
     * @param spsResidualInfo
     * @return
     */
    public boolean delete(SpsResidualInfo spsResidualInfo);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public SpsResidualInfo getSpsResidualInfoById(Long id);

    /**
     * 判断是否存在
     * 
     * @param spsResidualInfo
     * @return
     */
    public boolean exist(SpsResidualInfo spsResidualInfo);

}
