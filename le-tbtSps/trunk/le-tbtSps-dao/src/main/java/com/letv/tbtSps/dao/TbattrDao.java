package com.letv.tbtSps.dao;

import java.util.List;

import com.letv.tbtSps.domain.Tbattr;
import com.letv.tbtSps.domain.query.TbattrQuery;
/**
 * TbattrDao接口<br/>
 * 对'tbt信息表对应的附件'表进行基本的操作
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TbattrDao {
    
    /**
     * 新增对象
     * 
     * @param tbattr 
     * @return
     */
    public boolean insert(Tbattr tbattr);

    /**
     * 更新对象
     * 
     * @param tbattr
     * @return
     */
    public boolean update(Tbattr tbattr);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Tbattr> queryTbattrList(TbattrQuery queryBean);

    /**
     * 根据查询Bean获取总数
     * 
     * @param queryBean
     * @return
     */
    public int queryTbattrCount(TbattrQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Tbattr> queryTbattrListWithPage(TbattrQuery queryBean);

    /**
     * 删除记录
     * 
     * @param tbattr
     * @return
     */
    public boolean delete(Tbattr tbattr);

    /**
     * 根据主键获取对象
     * 
     * @param id
     *            主键字段
     * @return
     */
    public Tbattr getTbattrById(Long id);

    /**
     * 判断是否存在
     * 
     * @param tbattr
     * @return
     */
    public boolean exist(Tbattr tbattr);

}
