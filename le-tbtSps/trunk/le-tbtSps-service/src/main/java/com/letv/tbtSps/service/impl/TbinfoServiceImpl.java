package com.letv.tbtSps.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.letv.tbtSps.domain.Tbinfo;
import com.letv.tbtSps.domain.query.TbinfoQuery;
import com.letv.tbtSps.manager.TbinfoManager;
import com.letv.tbtSps.service.TbinfoService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perf4j.aop.Profiled;
 
/**
 * TbinfoService接口的实现类
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
 * 
 */
@Service
public class TbinfoServiceImpl implements TbinfoService {

    /** LOG */
    private static final Log LOG = LogFactory.getLog(TbinfoServiceImpl.class);

    @Autowired
    private TbinfoManager tbinfoManager;

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoService.batchInsert")
    public boolean insert(List<Tbinfo> tbinfoList) {
        boolean resultFlag = false;
        try {
            if (CollectionUtils.isNotEmpty(tbinfoList)) {
                resultFlag = tbinfoManager.insert(tbinfoList);
            } else {
                LOG.warn("TbinfoServiceImpl#batchInsert failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoServiceImpl#batchInsert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoService.insert")
    public boolean insert(Tbinfo tbinfo) {
        boolean resultFlag = false;
        try {
            if (null != tbinfo) {
                if (tbinfoManager.exist(tbinfo)) {
                    throw new ExistedException();
                }
                resultFlag = tbinfoManager.insert(tbinfo);
            } else {
                LOG.warn("TbinfoServiceImpl#insert failed, param is illegal.");
            }
        } catch (ExistedException e) {
            LOG.warn("TbinfoServiceImpl#insert failed, tbinfo has existed.");
            throw e;
        } catch (Exception e) {
            LOG.error("TbinfoServiceImpl#insert has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoService.update")
    public boolean update(Tbinfo tbinfo) {
        boolean resultFlag = false;
        try {
            if (null != tbinfo && null != tbinfo.getId()) {
                resultFlag = tbinfoManager.update(tbinfo);
            } else {
                LOG.warn("TbinfoServiceImpl#update failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoServiceImpl#update has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoService.queryTbinfoList")
    public List<Tbinfo> queryTbinfoList(TbinfoQuery queryBean) {
        List<Tbinfo> tbinfoList = null;
        try {
            tbinfoList = tbinfoManager.queryTbinfoList(queryBean);
        } catch (Exception e) {
            LOG.error("TbinfoServiceImpl#queryTbinfoList has error.", e);
        }
        return tbinfoList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoService.queryTbinfoListWithPage")
    public List<Tbinfo> queryTbinfoListWithPage(TbinfoQuery queryBean, PageUtil pageUtil) {
        List<Tbinfo> tbinfoList = null;
        try {
            tbinfoList = tbinfoManager.queryTbinfoListWithPage(queryBean, pageUtil);
        } catch (Exception e) {
            LOG.error("TbinfoServiceImpl#queryTbinfoListWithPage has error.", e);
        }
        return tbinfoList;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoService.delete")
    public boolean delete(Tbinfo tbinfo) {
        boolean resultFlag = false;
        try {
            if (null != tbinfo && null != tbinfo.getId()) {
                resultFlag = tbinfoManager.delete(tbinfo);
            } else {
                LOG.warn("TbinfoServiceImpl#delete param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoServiceImpl#delete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoService.batchDelete")
    public boolean delete(Tbinfo[] tbinfos) {
        boolean resultFlag = false;
        try {
            if (null != tbinfos && tbinfos.length > 0) {
                resultFlag = tbinfoManager.delete(tbinfos);
            } else {
                LOG.warn("TbinfoServiceImpl#batchDelete failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoServiceImpl#batchDelete has error.", e);
        }
        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Profiled(tag = "TbinfoService.getTbinfoById")
    public Tbinfo getTbinfoById(Long id) {
        Tbinfo tbinfo = null;
        try {
            if (null != id) {
                tbinfo = tbinfoManager.getTbinfoById(id);
            } else {
                LOG.warn("TbinfoServiceImpl#getTbinfoById failed, param is illegal.");
            }
        } catch (Exception e) {
            LOG.error("TbinfoServiceImpl#getTbinfoById has error.", e);
        }
        return tbinfo;
    }
}

