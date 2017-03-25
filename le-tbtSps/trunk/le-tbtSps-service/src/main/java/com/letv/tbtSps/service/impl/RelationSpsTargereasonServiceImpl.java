package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationSpsTargereason;
import com.letv.tbtSps.domain.query.RelationSpsTargereasonQuery;
import com.letv.tbtSps.manager.RelationSpsTargereasonManager;
import com.letv.tbtSps.service.RelationSpsTargereasonService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationSpsTargereasonService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationSpsTargereasonServiceImpl implements RelationSpsTargereasonService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationSpsTargereasonServiceImpl.class);

    @Autowired
    private RelationSpsTargereasonManager relationSpsTargereasonManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsTargereasonService.batchInsert")
    public boolean insert(List<RelationSpsTargereason> relationSpsTargereasonList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationSpsTargereasonList)) {
                resultFlag = relationSpsTargereasonManager.insert(relationSpsTargereasonList);
            } else {
                LOG.warn("RelationSpsTargereasonServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsTargereasonServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsTargereasonService.insert")
    public boolean insert(RelationSpsTargereason relationSpsTargereason) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsTargereason) {
                if (relationSpsTargereasonManager.exist(relationSpsTargereason)) {
                    throw new ExistedException();
                }
                resultFlag = relationSpsTargereasonManager.insert(relationSpsTargereason);
            } else {
                LOG.warn("RelationSpsTargereasonServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationSpsTargereasonServiceImpl#insert failed, relationSpsTargereason has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationSpsTargereasonServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsTargereasonService.update")
    public boolean update(RelationSpsTargereason relationSpsTargereason) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsTargereason && null != relationSpsTargereason.getId()) {
                resultFlag = relationSpsTargereasonManager.update(relationSpsTargereason);
            } else {
                LOG.warn("RelationSpsTargereasonServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsTargereasonServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsTargereasonService.queryRelationSpsTargereasonList")
    public List<RelationSpsTargereason> queryRelationSpsTargereasonList(RelationSpsTargereasonQuery queryBean) {
        List<RelationSpsTargereason> relationSpsTargereasonList = null;
        try {
            relationSpsTargereasonList = relationSpsTargereasonManager.queryRelationSpsTargereasonList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationSpsTargereasonServiceImpl#queryRelationSpsTargereasonList has error.", e);
        }
        return relationSpsTargereasonList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsTargereasonService.queryRelationSpsTargereasonListWithPage")
    public List<RelationSpsTargereason> queryRelationSpsTargereasonListWithPage(RelationSpsTargereasonQuery queryBean, PageUtil pageUtil) {
        List<RelationSpsTargereason> relationSpsTargereasonList = null;
        try {
            relationSpsTargereasonList = relationSpsTargereasonManager.queryRelationSpsTargereasonListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationSpsTargereasonServiceImpl#queryRelationSpsTargereasonListWithPage has error.", e);
        }
        return relationSpsTargereasonList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsTargereasonService.delete")
    public boolean delete(RelationSpsTargereason relationSpsTargereason) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsTargereason && null != relationSpsTargereason.getId()) {
                resultFlag = relationSpsTargereasonManager.delete(relationSpsTargereason);
            } else {
                LOG.warn("RelationSpsTargereasonServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsTargereasonServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsTargereasonService.batchDelete")
    public boolean delete(RelationSpsTargereason[] relationSpsTargereasons) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsTargereasons && relationSpsTargereasons.length > 0) {
                resultFlag = relationSpsTargereasonManager.delete(relationSpsTargereasons);
            } else {
                LOG.warn("RelationSpsTargereasonServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsTargereasonServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsTargereasonService.getRelationSpsTargereasonById")
    public RelationSpsTargereason getRelationSpsTargereasonById(Long id) {
        RelationSpsTargereason relationSpsTargereason = null;
        try {
            if (null != id) {
                relationSpsTargereason = relationSpsTargereasonManager.getRelationSpsTargereasonById(id);
            } else {
                LOG.warn("RelationSpsTargereasonServiceImpl#getRelationSpsTargereasonById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsTargereasonServiceImpl#getRelationSpsTargereasonById has error.", e);
        }
        return relationSpsTargereason;
    }
}

