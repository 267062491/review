package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.RelationTbrelationMedicine;
import com.letv.tbtSps.domain.query.RelationTbrelationMedicineQuery;
import com.letv.tbtSps.service.RelationTbrelationMedicineService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * RelationTbrelationMedicineController ：tbt信息表-相关农药关联表控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationTbrelationMedicine")
public class RelationTbrelationMedicineController extends ReviewBaseController {

    @Autowired
    private RelationTbrelationMedicineService relationTbrelationMedicineService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationTbrelationMedicine";
    
    private static final Log LOG = LogFactory.getLog(RelationTbrelationMedicineController.class);

    /**
     * 首页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        return viewPrefix + "/index";
    }
    
    /**
     * 分页 查询数据
     * 
     * @param model
     * @param page
     * @param query
     * @return
     */
    @RequestMapping(value = "queryByPage")
    public String queryByPage(Model model, PageUtil page, RelationTbrelationMedicineQuery query) {
        try {
            List<RelationTbrelationMedicine> dataList = relationTbrelationMedicineService.queryRelationTbrelationMedicineListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationTbrelationMedicine queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt信息表-相关农药关联表----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt信息表-相关农药关联表----添加
     * 
     * @param relationTbrelationMedicine
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationTbrelationMedicine relationTbrelationMedicine) {
        try {
            relationTbrelationMedicine.setCreateUser(getLoginUserCnName());
            if (relationTbrelationMedicineService.insert(relationTbrelationMedicine)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationTbrelationMedicine add fail, exist relationTbrelationMedicine.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationTbrelationMedicine add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt信息表-相关农药关联表----更新跳转
     * 
     * @param model
     * @param relationTbrelationMedicine
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationTbrelationMedicine relationTbrelationMedicine) {
        try {
            RelationTbrelationMedicine relationTbrelationMedicineResult = relationTbrelationMedicineService.getRelationTbrelationMedicineById(relationTbrelationMedicine.getId());
            model.addAttribute("relationTbrelationMedicine", relationTbrelationMedicineResult);
        } catch (Exception e) {
            LOG.error("relationTbrelationMedicine updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt信息表-相关农药关联表----更新
     * 
     * @param model
     * @param relationTbrelationMedicine
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationTbrelationMedicine relationTbrelationMedicine) {
        try {
            relationTbrelationMedicine.setUpdateUser(getLoginUserCnName());
            if (relationTbrelationMedicineService.update(relationTbrelationMedicine)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbrelationMedicine update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表-相关农药关联表----删除
     * 
     * @param relationTbrelationMedicine
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationTbrelationMedicine relationTbrelationMedicine) {
        try {
            relationTbrelationMedicine.setUpdateUser(getLoginUserCnName());
            if (relationTbrelationMedicineService.delete(relationTbrelationMedicine)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbrelationMedicine delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表-相关农药关联表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationTbrelationMedicineQuery query) {
        try {
            List<RelationTbrelationMedicine> list = relationTbrelationMedicineService.queryRelationTbrelationMedicineList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationTbrelationMedicine query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt信息表-相关农药关联表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationTbrelationMedicineQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationTbrelationMedicine relationTbrelationMedicine = relationTbrelationMedicineService.getRelationTbrelationMedicineById(query.getId());
            if (relationTbrelationMedicine != null) {
                return new Wrapper<RelationTbrelationMedicine>().result(relationTbrelationMedicine);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt信息表-相关农药关联表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationTbrelationMedicine has error.", e);
            return error();
        }
    }
}
