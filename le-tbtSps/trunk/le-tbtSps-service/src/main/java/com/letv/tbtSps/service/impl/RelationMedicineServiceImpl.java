package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.RelationMedicine;
import com.letv.tbtSps.domain.query.RelationMedicineQuery;
import com.letv.tbtSps.manager.RelationMedicineManager;
import com.letv.tbtSps.service.RelationMedicineService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * RelationMedicineService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class RelationMedicineServiceImpl implements RelationMedicineService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(RelationMedicineServiceImpl.class);

    @Autowired
    private RelationMedicineManager relationMedicineManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineService.batchInsert")
    public boolean insert(List<RelationMedicine> relationMedicineList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(relationMedicineList)) {
                resultFlag = relationMedicineManager.insert(relationMedicineList);
            } else {
                LOG.warn("RelationMedicineServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineService.insert")
    public boolean insert(RelationMedicine relationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationMedicine) {
                if (relationMedicineManager.exist(relationMedicine)) {
                    throw new ExistedException();
                }
                resultFlag = relationMedicineManager.insert(relationMedicine);
            } else {
                LOG.warn("RelationMedicineServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("RelationMedicineServiceImpl#insert failed, relationMedicine has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("RelationMedicineServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineService.update")
    public boolean update(RelationMedicine relationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationMedicine && null != relationMedicine.getId()) {
                resultFlag = relationMedicineManager.update(relationMedicine);
            } else {
                LOG.warn("RelationMedicineServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineService.queryRelationMedicineList")
    public List<RelationMedicine> queryRelationMedicineList(RelationMedicineQuery queryBean) {
        List<RelationMedicine> relationMedicineList = null;
        try {
            relationMedicineList = relationMedicineManager.queryRelationMedicineList(queryBean);
        } catch (Exception e) {
            LOG.error("RelationMedicineServiceImpl#queryRelationMedicineList has error.", e);
        }
        return relationMedicineList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineService.queryRelationMedicineListWithPage")
    public List<RelationMedicine> queryRelationMedicineListWithPage(RelationMedicineQuery queryBean, PageUtil pageUtil) {
        List<RelationMedicine> relationMedicineList = null;
        try {
            relationMedicineList = relationMedicineManager.queryRelationMedicineListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("RelationMedicineServiceImpl#queryRelationMedicineListWithPage has error.", e);
        }
        return relationMedicineList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineService.delete")
    public boolean delete(RelationMedicine relationMedicine) {
        boolean resultFlag = false;
        try {
            if (null != relationMedicine && null != relationMedicine.getId()) {
                resultFlag = relationMedicineManager.delete(relationMedicine);
            } else {
                LOG.warn("RelationMedicineServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineService.batchDelete")
    public boolean delete(RelationMedicine[] relationMedicines) {
        boolean resultFlag = false;
        try {
            if (null != relationMedicines && relationMedicines.length > 0) {
                resultFlag = relationMedicineManager.delete(relationMedicines);
            } else {
                LOG.warn("RelationMedicineServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "RelationMedicineService.getRelationMedicineById")
    public RelationMedicine getRelationMedicineById(Long id) {
        RelationMedicine relationMedicine = null;
        try {
            if (null != id) {
                relationMedicine = relationMedicineManager.getRelationMedicineById(id);
            } else {
                LOG.warn("RelationMedicineServiceImpl#getRelationMedicineById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("RelationMedicineServiceImpl#getRelationMedicineById has error.", e);
        }
        return relationMedicine;
    }
}

