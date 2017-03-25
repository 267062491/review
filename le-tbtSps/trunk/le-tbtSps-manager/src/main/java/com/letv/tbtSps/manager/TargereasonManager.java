package com.letv.tbtSps.manager;

import java.util.List;

import com.letv.tbtSps.domain.Targereason;
import com.letv.tbtSps.domain.query.TargereasonQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * TargereasonManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface TargereasonManager {

    /**
     * 批量增加对象信息
     * 
     * @param targereasonList
     * @return
     */
    public boolean insert(List<Targereason> targereasonList);

    /**
     * 单个增加对象信息
     * 
     * @param targereason
     * @return
     */
    public boolean insert(Targereason targereason);

    /**
     * 更新 对象信息
     * 
     * @param targereason
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(Targereason targereason);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Targereason> queryTargereasonList(TargereasonQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<Targereason> queryTargereasonListWithPage(TargereasonQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int queryTargereasonCount(TargereasonQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param targereason
     *            　
     * @return
     */
    public boolean delete(Targereason targereason);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public Targereason getTargereasonById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param targereasons
     *            Targereason集合
     * @return
     */
    public boolean delete(Targereason[] targereasons);

    /**
     * 判断是否存在
     * 
     * @param targereason
     * @return
     */
    public boolean exist(Targereason targereason);
}
