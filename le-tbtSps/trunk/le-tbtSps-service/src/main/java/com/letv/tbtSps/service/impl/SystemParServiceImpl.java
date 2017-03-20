package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.SystemPar;
import com.letv.tbtSps.domain.query.SystemParQuery;
import com.letv.tbtSps.manager.SystemParManager;
import com.letv.tbtSps.service.SystemParService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * SystemParService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
@Service
public class SystemParServiceImpl implements SystemParService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(SystemParServiceImpl.class);

    @Autowired
    private SystemParManager systemParManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SystemParService.batchInsert")
    public boolean insert(List<SystemPar> systemParList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(systemParList)) {
                resultFlag = systemParManager.insert(systemParList);
            } else {
                LOG.warn("SystemParServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SystemParServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SystemParService.insert")
    public boolean insert(SystemPar systemPar) {
        boolean resultFlag = false;
        try {
            if (null != systemPar) {
                if (systemParManager.exist(systemPar)) {
                    throw new ExistedException();
                }
                resultFlag = systemParManager.insert(systemPar);
            } else {
                LOG.warn("SystemParServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("SystemParServiceImpl#insert failed, systemPar has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("SystemParServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SystemParService.update")
    public boolean update(SystemPar systemPar) {
        boolean resultFlag = false;
        try {
            if (null != systemPar && null != systemPar.getId()) {
                resultFlag = systemParManager.update(systemPar);
            } else {
                LOG.warn("SystemParServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SystemParServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SystemParService.querySystemParList")
    public List<SystemPar> querySystemParList(SystemParQuery queryBean) {
        List<SystemPar> systemParList = null;
        try {
            systemParList = systemParManager.querySystemParList(queryBean);
        } catch (Exception e) {
            LOG.error("SystemParServiceImpl#querySystemParList has error.", e);
        }
        return systemParList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SystemParService.querySystemParListWithPage")
    public List<SystemPar> querySystemParListWithPage(SystemParQuery queryBean, PageUtil pageUtil) {
        List<SystemPar> systemParList = null;
        try {
            systemParList = systemParManager.querySystemParListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("SystemParServiceImpl#querySystemParListWithPage has error.", e);
        }
        return systemParList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SystemParService.delete")
    public boolean delete(SystemPar systemPar) {
        boolean resultFlag = false;
        try {
            if (null != systemPar && null != systemPar.getId()) {
                resultFlag = systemParManager.delete(systemPar);
            } else {
                LOG.warn("SystemParServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SystemParServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SystemParService.batchDelete")
    public boolean delete(SystemPar[] systemPars) {
        boolean resultFlag = false;
        try {
            if (null != systemPars && systemPars.length > 0) {
                resultFlag = systemParManager.delete(systemPars);
            } else {
                LOG.warn("SystemParServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SystemParServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "SystemParService.getSystemParById")
    public SystemPar getSystemParById(Long id) {
        SystemPar systemPar = null;
        try {
            if (null != id) {
                systemPar = systemParManager.getSystemParById(id);
            } else {
                LOG.warn("SystemParServiceImpl#getSystemParById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("SystemParServiceImpl#getSystemParById has error.", e);
        }
        return systemPar;
    }
}

