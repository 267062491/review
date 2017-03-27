package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.TblogAttr;
import com.letv.tbtSps.domain.query.TblogAttrQuery;
import com.letv.tbtSps.service.TblogAttrService;
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
 * TblogAttrController ：tbt评审信息表对应的附件控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("tblogAttr")
public class TblogAttrController extends ReviewBaseController {

    @Autowired
    private TblogAttrService tblogAttrService;

    /** 视图前缀 */
    private static final String viewPrefix ="tblogAttr";
    
    private static final Log LOG = LogFactory.getLog(TblogAttrController.class);

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
    public String queryByPage(Model model, PageUtil page, TblogAttrQuery query) {
        try {
            List<TblogAttr> dataList = tblogAttrService.queryTblogAttrListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("tblogAttr queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt评审信息表对应的附件----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt评审信息表对应的附件----添加
     * 
     * @param tblogAttr
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(TblogAttr tblogAttr) {
        try {
            tblogAttr.setCreateUser(getLoginUserCnName());
            if (tblogAttrService.insert(tblogAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("tblogAttr add fail, exist tblogAttr.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("tblogAttr add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt评审信息表对应的附件----更新跳转
     * 
     * @param model
     * @param tblogAttr
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, TblogAttr tblogAttr) {
        try {
            TblogAttr tblogAttrResult = tblogAttrService.getTblogAttrById(tblogAttr.getId());
            model.addAttribute("tblogAttr", tblogAttrResult);
        } catch (Exception e) {
            LOG.error("tblogAttr updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt评审信息表对应的附件----更新
     * 
     * @param model
     * @param tblogAttr
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, TblogAttr tblogAttr) {
        try {
            tblogAttr.setUpdateUser(getLoginUserCnName());
            if (tblogAttrService.update(tblogAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("tblogAttr update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt评审信息表对应的附件----删除
     * 
     * @param tblogAttr
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(TblogAttr tblogAttr) {
        try {
            tblogAttr.setUpdateUser(getLoginUserCnName());
            if (tblogAttrService.delete(tblogAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("tblogAttr delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt评审信息表对应的附件----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(TblogAttrQuery query) {
        try {
            List<TblogAttr> list = tblogAttrService.queryTblogAttrList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("tblogAttr query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt评审信息表对应的附件详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(TblogAttrQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            TblogAttr tblogAttr = tblogAttrService.getTblogAttrById(query.getId());
            if (tblogAttr != null) {
                return new Wrapper<TblogAttr>().result(tblogAttr);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt评审信息表对应的附件详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail tblogAttr has error.", e);
            return error();
        }
    }
}
