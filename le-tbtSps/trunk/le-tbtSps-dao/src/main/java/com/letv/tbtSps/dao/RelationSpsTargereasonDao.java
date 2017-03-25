package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.RelationSpsTargereason;
import com.letv.tbtSps.domain.query.RelationSpsTargereasonQuery;
/**
 * RelationSpsTargereasonDao接口<br/>
 * 对'sps信息表-目标理由关联表'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface RelationSpsTargereasonDao {
    
    /**
     * 新增对象
     * 
     * @param relationSpsTargereason 
     * @return
     */
    public boolean insert(RelationSpsTargereason relationSpsTargereason);

    /**
     * 更新对象
     * 
     * @param relationSpsTargereason
     * @return
     */
    public boolean update(RelationSpsTargereason relationSpsTargereason);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsTargereason> queryRelationSpsTargereasonList(RelationSpsTargereasonQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryRelationSpsTargereasonCount(RelationSpsTargereasonQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<RelationSpsTargereason> queryRelationSpsTargereasonListWithPage(RelationSpsTargereasonQuery queryBean);

    /**
     * 删除记录
     * 
     * @param relationSpsTargereason
     * @return
     */
    public boolean delete(RelationSpsTargereason relationSpsTargereason);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public RelationSpsTargereason getRelationSpsTargereasonById(Long id);

    /**
     * 判断是否存在
     * 
     * @param relationSpsTargereason
     * @return
     */
    public boolean exist(RelationSpsTargereason relationSpsTargereason);

}
