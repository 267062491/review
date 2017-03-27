package com.letv.tbtSps.controller;
   

import java.util.List;

import com.letv.tbtSps.common.controller.ReviewBaseController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letv.tbtSps.domain.MedicineProduccategory;
import com.letv.tbtSps.domain.query.MedicineProduccategoryQuery;
import com.letv.tbtSps.service.MedicineProduccategoryService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * MedicineProduccategoryController ：农产品分类控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("medicineProduccategory")
public class MedicineProduccategoryController extends ReviewBaseController {

    @Autowired
    private MedicineProduccategoryService medicineProduccategoryService;

    /** 视图前缀 */
    private static final String viewPrefix ="medicineProduccategory";
    
    private static final Log LOG = LogFactory.getLog(MedicineProduccategoryController.class);

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
    public String queryByPage(Model model, PageUtil page, MedicineProduccategoryQuery query) {
        try {
            List<MedicineProduccategory> dataList = medicineProduccategoryService.queryMedicineProduccategoryListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("medicineProduccategory queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 农产品分类----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 农产品分类----添加
     * 
     * @param medicineProduccategory
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(MedicineProduccategory medicineProduccategory) {
        try {
            medicineProduccategory.setCreateUser(getLoginUserCnName());
            if (medicineProduccategoryService.insert(medicineProduccategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("medicineProduccategory add fail, exist medicineProduccategory.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("medicineProduccategory add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 农产品分类----更新跳转
     * 
     * @param model
     * @param medicineProduccategory
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, MedicineProduccategory medicineProduccategory) {
        try {
            MedicineProduccategory medicineProduccategoryResult = medicineProduccategoryService.getMedicineProduccategoryById(medicineProduccategory.getId());
            model.addAttribute("medicineProduccategory", medicineProduccategoryResult);
        } catch (Exception e) {
            LOG.error("medicineProduccategory updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 农产品分类----更新
     * 
     * @param model
     * @param medicineProduccategory
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, MedicineProduccategory medicineProduccategory) {
        try {
            medicineProduccategory.setUpdateUser(getLoginUserCnName());
            if (medicineProduccategoryService.update(medicineProduccategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("medicineProduccategory update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 农产品分类----删除
     * 
     * @param medicineProduccategory
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(MedicineProduccategory medicineProduccategory) {
        try {
            medicineProduccategory.setUpdateUser(getLoginUserCnName());
            if (medicineProduccategoryService.delete(medicineProduccategory)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("medicineProduccategory delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 农产品分类----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(MedicineProduccategoryQuery query) {
        try {
            List<MedicineProduccategory> list = medicineProduccategoryService.queryMedicineProduccategoryList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("medicineProduccategory query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询农产品分类详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(MedicineProduccategoryQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            MedicineProduccategory medicineProduccategory = medicineProduccategoryService.getMedicineProduccategoryById(query.getId());
            if (medicineProduccategory != null) {
                return new Wrapper<MedicineProduccategory>().result(medicineProduccategory);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询农产品分类详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail medicineProduccategory has error.", e);
            return error();
        }
    }
}
