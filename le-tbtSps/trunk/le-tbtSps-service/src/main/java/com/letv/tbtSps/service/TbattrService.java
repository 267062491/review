package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.Tbattr;
import com.letv.tbtSps.domain.query.TbattrQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * TbattrService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TbattrService {

    /**
     * 批量增加对象信息
     * 
     * @param tbattrList
     * @return
     */
    public boolean insert(List<Tbattr> tbattrList);

    /**
     * 单个增加对象信息
     * 
     * @param tbattr
     * @return
     */
    public boolean insert(Tbattr tbattr);

    /**
     * 更新 对象信息
     * 
     * @param tbattr
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(Tbattr tbattr);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Tbattr> queryTbattrList(TbattrQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<Tbattr> queryTbattrListWithPage(TbattrQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param tbattr
     *            　
     * @return
     */
    public boolean delete(Tbattr tbattr);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public Tbattr getTbattrById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param tbattrs
     *            Tbattr集合
     * @return
     */
    public boolean delete(Tbattr[] tbattrs);
}
