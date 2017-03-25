package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.domain.query.SpsLogAttrQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * SpsLogAttrManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsLogAttrManager {

    /**
     * 批量增加对象信息
     * 
     * @param spsLogAttrList
     * @return
     */
    public boolean insert(List<SpsLogAttr> spsLogAttrList);

    /**
     * 单个增加对象信息
     * 
     * @param spsLogAttr
     * @return
     */
    public boolean insert(SpsLogAttr spsLogAttr);

    /**
     * 更新 对象信息
     * 
     * @param spsLogAttr
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(SpsLogAttr spsLogAttr);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsLogAttr> querySpsLogAttrList(SpsLogAttrQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<SpsLogAttr> querySpsLogAttrListWithPage(SpsLogAttrQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int querySpsLogAttrCount(SpsLogAttrQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param spsLogAttr
     *            　
     * @return
     */
    public boolean delete(SpsLogAttr spsLogAttr);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public SpsLogAttr getSpsLogAttrById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param spsLogAttrs
     *            SpsLogAttr集合
     * @return
     */
    public boolean delete(SpsLogAttr[] spsLogAttrs);

    /**
     * 判断是否存在
     * 
     * @param spsLogAttr
     * @return
     */
    public boolean exist(SpsLogAttr spsLogAttr);
}
