package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsInternationalStandard;
import com.letv.tbtSps.domain.query.RelationSpsInternationalStandardQuery;
/**
 * RelationSpsInternationalStandardDao接口<br/>
 * 对'sps信息表-国际标准关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsInternationalStandardDao {
    
    /**
     * 新增对象
     * 
     * @param relationSpsInternationalStandard 
     * @return
     */
    public boolean insert(RelationSpsInternationalStandard relationSpsInternationalStandard);

    /**
     * 更新对象
     * 
     * @param relationSpsInternationalStandard
     * @return
     */
    public boolean update(RelationSpsInternationalStandard relationSpsInternationalStandard);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardList(RelationSpsInternationalStandardQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationSpsInternationalStandardCount(RelationSpsInternationalStandardQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsInternationalStandard> queryRelationSpsInternationalStandardListWithPage(RelationSpsInternationalStandardQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationSpsInternationalStandard
     * @return
     */
    public boolean delete(RelationSpsInternationalStandard relationSpsInternationalStandard);
    public boolean deleteByCode(RelationSpsInternationalStandard relationSpsInternationalStandard);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationSpsInternationalStandard getRelationSpsInternationalStandardById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationSpsInternationalStandard
     * @return
     */
    public boolean exist(RelationSpsInternationalStandard relationSpsInternationalStandard);

}
