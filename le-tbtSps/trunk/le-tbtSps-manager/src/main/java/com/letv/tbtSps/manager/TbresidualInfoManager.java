package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.TbresidualInfo;
import com.letv.tbtSps.domain.query.TbresidualInfoQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * TbresidualInfoManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TbresidualInfoManager {

    /**
     * 批量增加对象信息
     * 
     * @param tbresidualInfoList
     * @return
     */
    public boolean insert(List<TbresidualInfo> tbresidualInfoList);

    /**
     * 单个增加对象信息
     * 
     * @param tbresidualInfo
     * @return
     */
    public boolean insert(TbresidualInfo tbresidualInfo);

    /**
     * 更新 对象信息
     * 
     * @param tbresidualInfo
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(TbresidualInfo tbresidualInfo);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<TbresidualInfo> queryTbresidualInfoList(TbresidualInfoQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<TbresidualInfo> queryTbresidualInfoListWithPage(TbresidualInfoQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryTbresidualInfoCount(TbresidualInfoQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param tbresidualInfo
     *            　
     * @return
     */
    public boolean delete(TbresidualInfo tbresidualInfo);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public TbresidualInfo getTbresidualInfoById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param tbresidualInfos
     *            TbresidualInfo集合
     * @return
     */
    public boolean delete(TbresidualInfo[] tbresidualInfos);

    /**
     * 判断是否存在
     * 
     * @param tbresidualInfo
     * @return
     */
    public boolean exist(TbresidualInfo tbresidualInfo);
}
