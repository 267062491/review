package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.TbresidualInfo;
import com.letv.tbtSps.domain.query.TbresidualInfoQuery;
/**
 * TbresidualInfoDao接口<br/>
 * 对'tbt残留量信息'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TbresidualInfoDao {
    
    /**
     * 新增对象
     * 
     * @param tbresidualInfo 
     * @return
     */
    public boolean insert(TbresidualInfo tbresidualInfo);

    /**
     * 更新对象
     * 
     * @param tbresidualInfo
     * @return
     */
    public boolean update(TbresidualInfo tbresidualInfo);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<TbresidualInfo> queryTbresidualInfoList(TbresidualInfoQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryTbresidualInfoCount(TbresidualInfoQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<TbresidualInfo> queryTbresidualInfoListWithPage(TbresidualInfoQuery queryBean);

    /**
     * 删除记录
     * 
     * @param tbresidualInfo
     * @return
     */
    public boolean delete(TbresidualInfo tbresidualInfo);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public TbresidualInfo getTbresidualInfoById(Long id);

    /**
     * 判断是否存在
     * 
     * @param tbresidualInfo
     * @return
     */
    public boolean exist(TbresidualInfo tbresidualInfo);

}
