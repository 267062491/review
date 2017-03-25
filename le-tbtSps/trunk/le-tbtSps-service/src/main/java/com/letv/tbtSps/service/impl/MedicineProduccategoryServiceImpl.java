package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.MedicineProduccategory;
import com.letv.tbtSps.domain.query.MedicineProduccategoryQuery;
import com.letv.tbtSps.manager.MedicineProduccategoryManager;
import com.letv.tbtSps.service.MedicineProduccategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * MedicineProduccategoryService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class MedicineProduccategoryServiceImpl implements MedicineProduccategoryService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(MedicineProduccategoryServiceImpl.class);

    @Autowired
    private MedicineProduccategoryManager medicineProduccategoryManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "MedicineProduccategoryService.batchInsert")
    public boolean insert(List<MedicineProduccategory> medicineProduccategoryList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(medicineProduccategoryList)) {
                resultFlag = medicineProduccategoryManager.insert(medicineProduccategoryList);
            } else {
                LOG.warn("MedicineProduccategoryServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("MedicineProduccategoryServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "MedicineProduccategoryService.insert")
    public boolean insert(MedicineProduccategory medicineProduccategory) {
        boolean resultFlag = false;
        try {
            if (null != medicineProduccategory) {
                if (medicineProduccategoryManager.exist(medicineProduccategory)) {
                    throw new ExistedException();
                }
                resultFlag = medicineProduccategoryManager.insert(medicineProduccategory);
            } else {
                LOG.warn("MedicineProduccategoryServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("MedicineProduccategoryServiceImpl#insert failed, medicineProduccategory has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("MedicineProduccategoryServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "MedicineProduccategoryService.update")
    public boolean update(MedicineProduccategory medicineProduccategory) {
        boolean resultFlag = false;
        try {
            if (null != medicineProduccategory && null != medicineProduccategory.getId()) {
                resultFlag = medicineProduccategoryManager.update(medicineProduccategory);
            } else {
                LOG.warn("MedicineProduccategoryServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("MedicineProduccategoryServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "MedicineProduccategoryService.queryMedicineProduccategoryList")
    public List<MedicineProduccategory> queryMedicineProduccategoryList(MedicineProduccategoryQuery queryBean) {
        List<MedicineProduccategory> medicineProduccategoryList = null;
        try {
            medicineProduccategoryList = medicineProduccategoryManager.queryMedicineProduccategoryList(queryBean);
        } catch (Exception e) {
            LOG.error("MedicineProduccategoryServiceImpl#queryMedicineProduccategoryList has error.", e);
        }
        return medicineProduccategoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "MedicineProduccategoryService.queryMedicineProduccategoryListWithPage")
    public List<MedicineProduccategory> queryMedicineProduccategoryListWithPage(MedicineProduccategoryQuery queryBean, PageUtil pageUtil) {
        List<MedicineProduccategory> medicineProduccategoryList = null;
        try {
            medicineProduccategoryList = medicineProduccategoryManager.queryMedicineProduccategoryListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("MedicineProduccategoryServiceImpl#queryMedicineProduccategoryListWithPage has error.", e);
        }
        return medicineProduccategoryList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "MedicineProduccategoryService.delete")
    public boolean delete(MedicineProduccategory medicineProduccategory) {
        boolean resultFlag = false;
        try {
            if (null != medicineProduccategory && null != medicineProduccategory.getId()) {
                resultFlag = medicineProduccategoryManager.delete(medicineProduccategory);
            } else {
                LOG.warn("MedicineProduccategoryServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("MedicineProduccategoryServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "MedicineProduccategoryService.batchDelete")
    public boolean delete(MedicineProduccategory[] medicineProduccategorys) {
        boolean resultFlag = false;
        try {
            if (null != medicineProduccategorys && medicineProduccategorys.length > 0) {
                resultFlag = medicineProduccategoryManager.delete(medicineProduccategorys);
            } else {
                LOG.warn("MedicineProduccategoryServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("MedicineProduccategoryServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "MedicineProduccategoryService.getMedicineProduccategoryById")
    public MedicineProduccategory getMedicineProduccategoryById(Long id) {
        MedicineProduccategory medicineProduccategory = null;
        try {
            if (null != id) {
                medicineProduccategory = medicineProduccategoryManager.getMedicineProduccategoryById(id);
            } else {
                LOG.warn("MedicineProduccategoryServiceImpl#getMedicineProduccategoryById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("MedicineProduccategoryServiceImpl#getMedicineProduccategoryById has error.", e);
        }
        return medicineProduccategory;
    }
}

