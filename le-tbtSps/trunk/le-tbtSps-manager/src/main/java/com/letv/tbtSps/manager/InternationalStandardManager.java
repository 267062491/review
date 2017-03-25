package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.InternationalStandard;
import com.letv.tbtSps.domain.query.InternationalStandardQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * InternationalStandardManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface InternationalStandardManager {

    /**
     * 批量增加对象信息
     * 
     * @param internationalStandardList
     * @return
     */
    public boolean insert(List<InternationalStandard> internationalStandardList);

    /**
     * 单个增加对象信息
     * 
     * @param internationalStandard
     * @return
     */
    public boolean insert(InternationalStandard internationalStandard);

    /**
     * 更新 对象信息
     * 
     * @param internationalStandard
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(InternationalStandard internationalStandard);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<InternationalStandard> queryInternationalStandardList(InternationalStandardQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<InternationalStandard> queryInternationalStandardListWithPage(InternationalStandardQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryInternationalStandardCount(InternationalStandardQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param internationalStandard
     *            　
     * @return
     */
    public boolean delete(InternationalStandard internationalStandard);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public InternationalStandard getInternationalStandardById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param internationalStandards
     *            InternationalStandard集合
     * @return
     */
    public boolean delete(InternationalStandard[] internationalStandards);

    /**
     * 判断是否存在
     * 
     * @param internationalStandard
     * @return
     */
    public boolean exist(InternationalStandard internationalStandard);
}
