package com.letv.tbtSps.service;

import java.util.List;

import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.common.utils.page.PageUtil;

/**
 * ResourceService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface ResourceService {

    /**
     * 批量增加对象信息
     * 
     * @param resourceList
     * @return
     */
    public boolean insert(List<Resource> resourceList);

    /**
     * 单个增加对象信息
     * 
     * @param resource
     * @return
     */
    public boolean insert(Resource resource);

    /**
     * 更新 对象信息
     * 
     * @param resource
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(Resource resource);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<Resource> queryResourceList(ResourceQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<Resource> queryResourceListWithPage(ResourceQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param resource
     *            　
     * @return
     */
    public boolean delete(Resource resource);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public Resource getResourceById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param resources
     *            Resource集合
     * @return
     */
    public boolean delete(Resource[] resources);
}
