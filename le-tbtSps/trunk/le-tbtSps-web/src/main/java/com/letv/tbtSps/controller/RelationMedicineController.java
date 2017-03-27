package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.RelationMedicine;
import com.letv.tbtSps.domain.query.RelationMedicineQuery;
import com.letv.tbtSps.service.RelationMedicineService;
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
 * RelationMedicineController ：相关农药控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationMedicine")
public class RelationMedicineController extends ReviewBaseController {

    @Autowired
    private RelationMedicineService relationMedicineService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationMedicine";
    
    private static final Log LOG = LogFactory.getLog(RelationMedicineController.class);

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
    public String queryByPage(Model model, PageUtil page, RelationMedicineQuery query) {
        try {
            List<RelationMedicine> dataList = relationMedicineService.queryRelationMedicineListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationMedicine queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 相关农药----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 相关农药----添加
     * 
     * @param relationMedicine
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationMedicine relationMedicine) {
        try {
            relationMedicine.setCreateUser(getLoginUserCnName());
            if (relationMedicineService.insert(relationMedicine)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationMedicine add fail, exist relationMedicine.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationMedicine add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 相关农药----更新跳转
     * 
     * @param model
     * @param relationMedicine
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationMedicine relationMedicine) {
        try {
            RelationMedicine relationMedicineResult = relationMedicineService.getRelationMedicineById(relationMedicine.getId());
            model.addAttribute("relationMedicine", relationMedicineResult);
        } catch (Exception e) {
            LOG.error("relationMedicine updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 相关农药----更新
     * 
     * @param model
     * @param relationMedicine
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationMedicine relationMedicine) {
        try {
            relationMedicine.setUpdateUser(getLoginUserCnName());
            if (relationMedicineService.update(relationMedicine)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationMedicine update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 相关农药----删除
     * 
     * @param relationMedicine
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationMedicine relationMedicine) {
        try {
            relationMedicine.setUpdateUser(getLoginUserCnName());
            if (relationMedicineService.delete(relationMedicine)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationMedicine delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 相关农药----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationMedicineQuery query) {
        try {
            List<RelationMedicine> list = relationMedicineService.queryRelationMedicineList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationMedicine query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询相关农药详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationMedicineQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationMedicine relationMedicine = relationMedicineService.getRelationMedicineById(query.getId());
            if (relationMedicine != null) {
                return new Wrapper<RelationMedicine>().result(relationMedicine);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询相关农药详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationMedicine has error.", e);
            return error();
        }
    }
}
