package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationTbrelationMedicine;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineQuery;
import com.letv.tbtSps.manager.RelationTbrelationMedicineManager;
import com.letv.tbtSps.service.RelationTbrelationMedicineService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationTbrelationMedicineService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationTbrelationMedicineServiceImpl implements RelationTbrelationMedicineService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationTbrelationMedicineServiceImpl.class);

    @Autowired
    private RelationTbrelationMedicineManager relationTbrelationMedicineManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineService.batchInsert")
    public boolean insert(List<RelationTbrelationMedicine> relationTbrelationMedicineList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationTbrelationMedicineList)) {
                resultFlag = relationTbrelationMedicineManager.insert(relationTbrelationMedicineList);
            } else {
                LOG.warn("RelationTbrelationMedicineServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineService.insert")
    public boolean insert(RelationTbrelationMedicine relationTbrelationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationTbrelationMedicine) {
                if (relationTbrelationMedicineManager.exist(relationTbrelationMedicine)) {
                    throw new ExistedException();
                }
                resultFlag = relationTbrelationMedicineManager.insert(relationTbrelationMedicine);
            } else {
                LOG.warn("RelationTbrelationMedicineServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationTbrelationMedicineServiceImpl#insert failed, relationTbrelationMedicine has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineService.update")
    public boolean update(RelationTbrelationMedicine relationTbrelationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationTbrelationMedicine && null != relationTbrelationMedicine.getId()) {
                resultFlag = relationTbrelationMedicineManager.update(relationTbrelationMedicine);
            } else {
                LOG.warn("RelationTbrelationMedicineServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineService.queryRelationTbrelationMedicineList")
    public List<RelationTbrelationMedicine> queryRelationTbrelationMedicineList(RelationTbrelationMedicineQuery queryBean) {
        List<RelationTbrelationMedicine> relationTbrelationMedicineList = null;
        try {
            relationTbrelationMedicineList = relationTbrelationMedicineManager.queryRelationTbrelationMedicineList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineServiceImpl#queryRelationTbrelationMedicineList has error.", e);
        }
        return relationTbrelationMedicineList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineService.queryRelationTbrelationMedicineListWithPage")
    public List<RelationTbrelationMedicine> queryRelationTbrelationMedicineListWithPage(RelationTbrelationMedicineQuery queryBean, PageUtil pageUtil) {
        List<RelationTbrelationMedicine> relationTbrelationMedicineList = null;
        try {
            relationTbrelationMedicineList = relationTbrelationMedicineManager.queryRelationTbrelationMedicineListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineServiceImpl#queryRelationTbrelationMedicineListWithPage has error.", e);
        }
        return relationTbrelationMedicineList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineService.delete")
    public boolean delete(RelationTbrelationMedicine relationTbrelationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationTbrelationMedicine && null != relationTbrelationMedicine.getId()) {
                resultFlag = relationTbrelationMedicineManager.delete(relationTbrelationMedicine);
            } else {
                LOG.warn("RelationTbrelationMedicineServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineService.batchDelete")
    public boolean delete(RelationTbrelationMedicine[] relationTbrelationMedicines) {
        boolean resultFlag = false;
        try {
            if (null != relationTbrelationMedicines && relationTbrelationMedicines.length > 0) {
                resultFlag = relationTbrelationMedicineManager.delete(relationTbrelationMedicines);
            } else {
                LOG.warn("RelationTbrelationMedicineServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationTbrelationMedicineService.getRelationTbrelationMedicineById")
    public RelationTbrelationMedicine getRelationTbrelationMedicineById(Long id) {
        RelationTbrelationMedicine relationTbrelationMedicine = null;
        try {
            if (null != id) {
                relationTbrelationMedicine = relationTbrelationMedicineManager.getRelationTbrelationMedicineById(id);
            } else {
                LOG.warn("RelationTbrelationMedicineServiceImpl#getRelationTbrelationMedicineById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationTbrelationMedicineServiceImpl#getRelationTbrelationMedicineById has error.", e);
        }
        return relationTbrelationMedicine;
    }
}

