package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.Targereason;
import com.letv.tbtSps.domain.query.TargereasonQuery;
import com.letv.tbtSps.manager.TargereasonManager;
import com.letv.tbtSps.service.TargereasonService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * TargereasonService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class TargereasonServiceImpl implements TargereasonService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(TargereasonServiceImpl.class);

    @Autowired
    private TargereasonManager targereasonManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TargereasonService.batchInsert")
    public boolean insert(List<Targereason> targereasonList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(targereasonList)) {
                resultFlag = targereasonManager.insert(targereasonList);
            } else {
                LOG.warn("TargereasonServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TargereasonServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TargereasonService.insert")
    public boolean insert(Targereason targereason) {
        boolean resultFlag = false;
        try {
            if (null != targereason) {
                if (targereasonManager.exist(targereason)) {
                    throw new ExistedException();
                }
                resultFlag = targereasonManager.insert(targereason);
            } else {
                LOG.warn("TargereasonServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("TargereasonServiceImpl#insert failed, targereason has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("TargereasonServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TargereasonService.update")
    public boolean update(Targereason targereason) {
        boolean resultFlag = false;
        try {
            if (null != targereason && null != targereason.getId()) {
                resultFlag = targereasonManager.update(targereason);
            } else {
                LOG.warn("TargereasonServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TargereasonServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TargereasonService.queryTargereasonList")
    public List<Targereason> queryTargereasonList(TargereasonQuery queryBean) {
        List<Targereason> targereasonList = null;
        try {
            targereasonList = targereasonManager.queryTargereasonList(queryBean);
        } catch (Exception e) {
            LOG.error("TargereasonServiceImpl#queryTargereasonList has error.", e);
        }
        return targereasonList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TargereasonService.queryTargereasonListWithPage")
    public List<Targereason> queryTargereasonListWithPage(TargereasonQuery queryBean, PageUtil pageUtil) {
        List<Targereason> targereasonList = null;
        try {
            targereasonList = targereasonManager.queryTargereasonListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("TargereasonServiceImpl#queryTargereasonListWithPage has error.", e);
        }
        return targereasonList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TargereasonService.delete")
    public boolean delete(Targereason targereason) {
        boolean resultFlag = false;
        try {
            if (null != targereason && null != targereason.getId()) {
                resultFlag = targereasonManager.delete(targereason);
            } else {
                LOG.warn("TargereasonServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TargereasonServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TargereasonService.batchDelete")
    public boolean delete(Targereason[] targereasons) {
        boolean resultFlag = false;
        try {
            if (null != targereasons && targereasons.length > 0) {
                resultFlag = targereasonManager.delete(targereasons);
            } else {
                LOG.warn("TargereasonServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TargereasonServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TargereasonService.getTargereasonById")
    public Targereason getTargereasonById(Long id) {
        Targereason targereason = null;
        try {
            if (null != id) {
                targereason = targereasonManager.getTargereasonById(id);
            } else {
                LOG.warn("TargereasonServiceImpl#getTargereasonById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TargereasonServiceImpl#getTargereasonById has error.", e);
        }
        return targereason;
    }
}

