package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.InternationalStandard;
import com.letv.tbtSps.domain.query.InternationalStandardQuery;
/**
 * InternationalStandardDao接口<br/>
 * 对'国际标准'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface InternationalStandardDao {
    
    /**
     * 新增对象
     * 
     * @param internationalStandard 
     * @return
     */
    public boolean insert(InternationalStandard internationalStandard);

    /**
     * 更新对象
     * 
     * @param internationalStandard
     * @return
     */
    public boolean update(InternationalStandard internationalStandard);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<InternationalStandard> queryInternationalStandardList(InternationalStandardQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryInternationalStandardCount(InternationalStandardQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<InternationalStandard> queryInternationalStandardListWithPage(InternationalStandardQuery queryBean);

    /**
     * 删除记录
     * 
     * @param internationalStandard
     * @return
     */
    public boolean delete(InternationalStandard internationalStandard);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public InternationalStandard getInternationalStandardById(Long id);

    /**
     * 判断是否存在
     * 
     * @param internationalStandard
     * @return
     */
    public boolean exist(InternationalStandard internationalStandard);

}
