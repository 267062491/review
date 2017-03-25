package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsTargereason;
import com.letv.tbtSps.domain.query.RelationSpsTargereasonQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * RelationSpsTargereasonService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsTargereasonService {

    /**
     * 批量增加对象信息
     * 
     * @param relationSpsTargereasonList
     * @return
     */
    public boolean insert(List<RelationSpsTargereason> relationSpsTargereasonList);

    /**
     * 单个增加对象信息
     * 
     * @param relationSpsTargereason
     * @return
     */
    public boolean insert(RelationSpsTargereason relationSpsTargereason);

    /**
     * 更新 对象信息
     * 
     * @param relationSpsTargereason
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(RelationSpsTargereason relationSpsTargereason);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsTargereason> queryRelationSpsTargereasonList(RelationSpsTargereasonQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<RelationSpsTargereason> queryRelationSpsTargereasonListWithPage(RelationSpsTargereasonQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsTargereason
     *            　
     * @return
     */
    public boolean delete(RelationSpsTargereason relationSpsTargereason);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public RelationSpsTargereason getRelationSpsTargereasonById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param relationSpsTargereasons
     *            RelationSpsTargereason集合
     * @return
     */
    public boolean delete(RelationSpsTargereason[] relationSpsTargereasons);
}
