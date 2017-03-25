package com.letv.tbtSps.controller;
   

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letv.tbtSps.domain.RelationMedicineProduct;
import com.letv.tbtSps.domain.query.RelationMedicineProductQuery;
import com.letv.tbtSps.service.RelationMedicineProductService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * RelationMedicineProductController ：相关农产品控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("relationMedicineProduct")
public class RelationMedicineProductController extends BaseController {

    @Autowired
    private RelationMedicineProductService relationMedicineProductService;

    /** 视图前缀 */
    private static final String viewPrefix ="relationMedicineProduct";
    
    private static final Log LOG = LogFactory.getLog(RelationMedicineProductController.class);

    /**
     * 首页
     * 
     * @param model
     * @param page
     * @param query
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
    public String queryByPage(Model model, PageUtil page, RelationMedicineProductQuery query) {
        try {
            List<RelationMedicineProduct> dataList = relationMedicineProductService.queryRelationMedicineProductListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("relationMedicineProduct queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 相关农产品----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 相关农产品----添加
     * 
     * @param relationMedicineProduct
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(RelationMedicineProduct relationMedicineProduct) {
        try {
            relationMedicineProduct.setCreateUser(getLoginUserCnName());
            if (relationMedicineProductService.insert(relationMedicineProduct)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("relationMedicineProduct add fail, exist relationMedicineProduct.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("relationMedicineProduct add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 相关农产品----更新跳转
     * 
     * @param model
     * @param relationMedicineProduct
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, RelationMedicineProduct relationMedicineProduct) {
        try {
            RelationMedicineProduct relationMedicineProductResult = relationMedicineProductService.getRelationMedicineProductById(relationMedicineProduct.getId());
            model.addAttribute("relationMedicineProduct", relationMedicineProductResult);
        } catch (Exception e) {
            LOG.error("relationMedicineProduct updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 相关农产品----更新
     * 
     * @param model
     * @param relationMedicineProduct
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, RelationMedicineProduct relationMedicineProduct) {
        try {
            relationMedicineProduct.setUpdateUser(getLoginUserCnName());
            if (relationMedicineProductService.update(relationMedicineProduct)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("relationMedicineProduct update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 相关农产品----删除
     * 
     * @param relationMedicineProduct
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(RelationMedicineProduct relationMedicineProduct) {
        try {
            relationMedicineProduct.setUpdateUser(getLoginUserCnName());
            if (relationMedicineProductService.delete(relationMedicineProduct)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("relationMedicineProduct delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 相关农产品----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(RelationMedicineProductQuery query) {
        try {
            List<RelationMedicineProduct> list = relationMedicineProductService.queryRelationMedicineProductList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("relationMedicineProduct query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询相关农产品详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(RelationMedicineProductQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            RelationMedicineProduct relationMedicineProduct = relationMedicineProductService.getRelationMedicineProductById(query.getId());
            if (relationMedicineProduct != null) {
                return new Wrapper<RelationMedicineProduct>().result(relationMedicineProduct);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询相关农产品详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail relationMedicineProduct has error.", e);
            return error();
        }
    }
}
