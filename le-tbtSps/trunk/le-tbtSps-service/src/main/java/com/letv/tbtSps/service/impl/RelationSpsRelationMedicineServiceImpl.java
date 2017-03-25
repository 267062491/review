package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationSpsRelationMedicine;
import com.letv.tbtSps.domain.query.RelationSpsRelationMedicineQuery;
import com.letv.tbtSps.manager.RelationSpsRelationMedicineManager;
import com.letv.tbtSps.service.RelationSpsRelationMedicineService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationSpsRelationMedicineService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationSpsRelationMedicineServiceImpl implements RelationSpsRelationMedicineService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationSpsRelationMedicineServiceImpl.class);

    @Autowired
    private RelationSpsRelationMedicineManager relationSpsRelationMedicineManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineService.batchInsert")
    public boolean insert(List<RelationSpsRelationMedicine> relationSpsRelationMedicineList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationSpsRelationMedicineList)) {
                resultFlag = relationSpsRelationMedicineManager.insert(relationSpsRelationMedicineList);
            } else {
                LOG.warn("RelationSpsRelationMedicineServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineService.insert")
    public boolean insert(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsRelationMedicine) {
                if (relationSpsRelationMedicineManager.exist(relationSpsRelationMedicine)) {
                    throw new ExistedException();
                }
                resultFlag = relationSpsRelationMedicineManager.insert(relationSpsRelationMedicine);
            } else {
                LOG.warn("RelationSpsRelationMedicineServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationSpsRelationMedicineServiceImpl#insert failed, relationSpsRelationMedicine has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineService.update")
    public boolean update(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsRelationMedicine && null != relationSpsRelationMedicine.getId()) {
                resultFlag = relationSpsRelationMedicineManager.update(relationSpsRelationMedicine);
            } else {
                LOG.warn("RelationSpsRelationMedicineServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineService.queryRelationSpsRelationMedicineList")
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineList(RelationSpsRelationMedicineQuery queryBean) {
        List<RelationSpsRelationMedicine> relationSpsRelationMedicineList = null;
        try {
            relationSpsRelationMedicineList = relationSpsRelationMedicineManager.queryRelationSpsRelationMedicineList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineServiceImpl#queryRelationSpsRelationMedicineList has error.", e);
        }
        return relationSpsRelationMedicineList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineService.queryRelationSpsRelationMedicineListWithPage")
    public List<RelationSpsRelationMedicine> queryRelationSpsRelationMedicineListWithPage(RelationSpsRelationMedicineQuery queryBean, PageUtil pageUtil) {
        List<RelationSpsRelationMedicine> relationSpsRelationMedicineList = null;
        try {
            relationSpsRelationMedicineList = relationSpsRelationMedicineManager.queryRelationSpsRelationMedicineListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineServiceImpl#queryRelationSpsRelationMedicineListWithPage has error.", e);
        }
        return relationSpsRelationMedicineList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineService.delete")
    public boolean delete(RelationSpsRelationMedicine relationSpsRelationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsRelationMedicine && null != relationSpsRelationMedicine.getId()) {
                resultFlag = relationSpsRelationMedicineManager.delete(relationSpsRelationMedicine);
            } else {
                LOG.warn("RelationSpsRelationMedicineServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineService.batchDelete")
    public boolean delete(RelationSpsRelationMedicine[] relationSpsRelationMedicines) {
        boolean resultFlag = false;
        try {
            if (null != relationSpsRelationMedicines && relationSpsRelationMedicines.length > 0) {
                resultFlag = relationSpsRelationMedicineManager.delete(relationSpsRelationMedicines);
            } else {
                LOG.warn("RelationSpsRelationMedicineServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationSpsRelationMedicineService.getRelationSpsRelationMedicineById")
    public RelationSpsRelationMedicine getRelationSpsRelationMedicineById(Long id) {
        RelationSpsRelationMedicine relationSpsRelationMedicine = null;
        try {
            if (null != id) {
                relationSpsRelationMedicine = relationSpsRelationMedicineManager.getRelationSpsRelationMedicineById(id);
            } else {
                LOG.warn("RelationSpsRelationMedicineServiceImpl#getRelationSpsRelationMedicineById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationSpsRelationMedicineServiceImpl#getRelationSpsRelationMedicineById has error.", e);
        }
        return relationSpsRelationMedicine;
    }
}

