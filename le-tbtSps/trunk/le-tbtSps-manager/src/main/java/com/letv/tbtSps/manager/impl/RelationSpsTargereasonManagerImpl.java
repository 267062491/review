package com.letv.tbtSps.manager.impl;

import java.util.List;

import com.letv.common.manager.BaseManager;
import com.letv.common.utils.page.PageUtil;
import com.letv.tbtSps.domain.RelationSpsTargereason;
import com.letv.tbtSps.domain.query.RelationSpsTargereasonQuery;
import com.letv.tbtSps.dao.RelationSpsTargereasonDao;
import com.letv.tbtSps.manager.RelationSpsTargereasonManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RelationSpsTargereasonManager接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
@Component
public class RelationSpsTargereasonManagerImpl extends BaseManager implements RelationSpsTargereasonManager {
	
    @Autowired
    private RelationSpsTargereasonDao relationSpsTargereasonDao;

    /**
     * {@inheritDoc}
     */
    public boolean insert(final List<RelationSpsTargereason> relationSpsTargereasonList) {
        boolean resultFlag = false;
        if (null != relationSpsTargereasonList && relationSpsTargereasonList.size() > 0) {
            for (RelationSpsTargereason relationSpsTargereason : relationSpsTargereasonList) {
                resultFlag = relationSpsTargereasonDao.insert(relationSpsTargereason);
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
    public boolean insert(RelationSpsTargereason relationSpsTargereason) {
        return relationSpsTargereasonDao.insert(relationSpsTargereason);
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(final RelationSpsTargereason relationSpsTargereason) {
        return relationSpsTargereasonDao.update(relationSpsTargereason);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsTargereason> queryRelationSpsTargereasonList(RelationSpsTargereasonQuery queryBean) {
        return relationSpsTargereasonDao.queryRelationSpsTargereasonList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public List<RelationSpsTargereason> queryRelationSpsTargereasonListWithPage(RelationSpsTargereasonQuery queryBean,
            PageUtil pageUtil) {
        if (null == queryBean) {
            queryBean = new RelationSpsTargereasonQuery();
        }

        // 查询总数
        int totalItem = queryRelationSpsTargereasonCount(queryBean);

        if (pageUtil == null) {
            pageUtil = new PageUtil();
        }
        pageUtil.setTotalRow(totalItem);
        pageUtil.init();
        
        if (totalItem > 0) {
            queryBean.setPageIndex(pageUtil.getCurPage());
            queryBean.setPageSize(pageUtil.getPageSize());
            // 调用Dao翻页方法
            return relationSpsTargereasonDao.queryRelationSpsTargereasonListWithPage(queryBean);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int queryRelationSpsTargereasonCount(RelationSpsTargereasonQuery queryBean) {
        return relationSpsTargereasonDao.queryRelationSpsTargereasonCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(RelationSpsTargereason relationSpsTargereason) {
        return relationSpsTargereasonDao.delete(relationSpsTargereason);
    }

    /**
     * {@inheritDoc}
     */
    public RelationSpsTargereason getRelationSpsTargereasonById(Long id) {
        return relationSpsTargereasonDao.getRelationSpsTargereasonById(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean delete(final RelationSpsTargereason[] relationSpsTargereasons) {
        boolean resultFlag = false;
        if (null != relationSpsTargereasons && relationSpsTargereasons.length > 0) {
            for (int i = 0; i < relationSpsTargereasons.length; i++) {
                resultFlag = delete(relationSpsTargereasons[i]);
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
    public boolean exist(RelationSpsTargereason relationSpsTargereason) {
        return relationSpsTargereasonDao.exist(relationSpsTargereason);
    }
}
