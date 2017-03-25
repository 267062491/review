package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsInternationalStandard;
import com.letv.tbtSps.domain.query.RelationSpsInternationalStandardQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * RelationSpsInternationalStandardService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsInternationalStandardService {

    /**
     * 批量增加对象信息
     * 
     * @param relationSpsInternationalStandardList
     * @return
     */
    public boolean insert(List<RelationSpsInternationalStandard> relationSpsInternationalStandardList);

    /**
     * 单个增加对象信息
     * 
     * @param relationSpsInternationalStandard
     * @return
     */
    public boolean insert(RelationSpsInternationalStandard relationSpsInternationalStandard);

    /**
     * 更新 对象信息
     * 
     * @param relationSpsInternationalStandard
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationSpsInternationalStandard relationSpsInternationalStandard);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardList(RelationSpsInternationalStandardQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardListWithPage(RelationSpsInternationalStandardQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsInternationalStandard
     *            　
     * @return
     */
    public boolean delete(RelationSpsInternationalStandard relationSpsInternationalStandard);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationSpsInternationalStandard getRelationSpsInternationalStandardById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsInternationalStandards
     *            RelationSpsInternationalStandard集合
     * @return
     */
    public boolean delete(RelationSpsInternationalStandard[] relationSpsInternationalStandards);
}
