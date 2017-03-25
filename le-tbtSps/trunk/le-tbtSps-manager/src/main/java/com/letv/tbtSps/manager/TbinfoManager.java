package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.Tbinfo;
import com.letv.tbtSps.domain.query.TbinfoQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * TbinfoManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TbinfoManager {

    /**
     * 批量增加对象信息
     * 
     * @param tbinfoList
     * @return
     */
    public boolean insert(List<Tbinfo> tbinfoList);

    /**
     * 单个增加对象信息
     * 
     * @param tbinfo
     * @return
     */
    public boolean insert(Tbinfo tbinfo);

    /**
     * 更新 对象信息
     * 
     * @param tbinfo
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(Tbinfo tbinfo);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Tbinfo> queryTbinfoList(TbinfoQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<Tbinfo> queryTbinfoListWithPage(TbinfoQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryTbinfoCount(TbinfoQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param tbinfo
     *            　
     * @return
     */
    public boolean delete(Tbinfo tbinfo);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public Tbinfo getTbinfoById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param tbinfos
     *            Tbinfo集合
     * @return
     */
    public boolean delete(Tbinfo[] tbinfos);

    /**
     * 判断是否存在
     * 
     * @param tbinfo
     * @return
     */
    public boolean exist(Tbinfo tbinfo);
}
