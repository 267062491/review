package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.SpsAttr;
import com.letv.tbtSps.domain.query.SpsAttrQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * SpsAttrService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsAttrService {

    /**
     * 批量增加对象信息
     * 
     * @param spsAttrList
     * @return
     */
    public boolean insert(List<SpsAttr> spsAttrList);

    /**
     * 单个增加对象信息
     * 
     * @param spsAttr
     * @return
     */
    public boolean insert(SpsAttr spsAttr);

    /**
     * 更新 对象信息
     * 
     * @param spsAttr
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(SpsAttr spsAttr);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsAttr> querySpsAttrList(SpsAttrQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<SpsAttr> querySpsAttrListWithPage(SpsAttrQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param spsAttr
     *            　
     * @return
     */
    public boolean delete(SpsAttr spsAttr);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public SpsAttr getSpsAttrById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param spsAttrs
     *            SpsAttr集合
     * @return
     */
    public boolean delete(SpsAttr[] spsAttrs);
}
