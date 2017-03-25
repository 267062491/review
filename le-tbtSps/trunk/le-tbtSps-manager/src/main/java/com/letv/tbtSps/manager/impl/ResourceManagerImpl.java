package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.Resource;
import com.letv.tbtSps.domain.query.ResourceQuery;
import com.letv.tbtSps.dao.ResourceDao;
import com.letv.tbtSps.manager.ResourceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ResourceManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class ResourceManagerImpl extends BaseManager implements ResourceManager {
	
    @Autowired
    private ResourceDao resourceDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<Resource> resourceList) {
        boolean resultFlag = false;
        if (null != resourceList && resourceList.size() > 0) {
            for (Resource resource : resourceList) {
                resultFlag = resourceDao.insert(resource);
                if (!resultFlag) {
                    throw new RuntimeException("批量新增表信息异常");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean insert(Resource resource) {
        return resourceDao.insert(resource);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final Resource resource) {
        return resourceDao.update(resource);
    }

    /**
     * {@inheritDoc}
     */
    public List<Resource> queryResourceList(ResourceQuery queryBean) {
        return resourceDao.queryResourceList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<Resource> queryResourceListWithPage(ResourceQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new ResourceQuery();
        }

        // 查询总数
        int totalItem = queryResourceCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return resourceDao.queryResourceListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryResourceCount(ResourceQuery queryBean) {
        return resourceDao.queryResourceCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(Resource resource) {
        return resourceDao.delete(resource);
    }

    /**
     * {@inheritDoc}
     */
    public Resource getResourceById(Long id) {
        return resourceDao.getResourceById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final Resource[] resources) {
        boolean resultFlag = false;
        if (null != resources && resources.length > 0) {
            for (int i = 0; i < resources.length; i++) {
                resultFlag = delete(resources[i]);
                if (!resultFlag) {
                    throw new RuntimeException("批量删除表信息异常!");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    public boolean exist(Resource resource) {
        return resourceDao.exist(resource);
    }
}
