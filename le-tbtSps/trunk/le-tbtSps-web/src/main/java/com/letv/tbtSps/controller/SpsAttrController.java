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

import com.letv.tbtSps.domain.SpsAttr;
import com.letv.tbtSps.domain.query.SpsAttrQuery;
import com.letv.tbtSps.service.SpsAttrService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * SpsAttrController ：sps信息表对应的附件控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("spsAttr")
public class SpsAttrController extends BaseController {

    @Autowired
    private SpsAttrService spsAttrService;

    /** 视图前缀 */
    private static final String viewPrefix ="spsAttr";
    
    private static final Log LOG = LogFactory.getLog(SpsAttrController.class);

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
    public String queryByPage(Model model, PageUtil page, SpsAttrQuery query) {
        try {
            List<SpsAttr> dataList = spsAttrService.querySpsAttrListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("spsAttr queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * sps信息表对应的附件----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * sps信息表对应的附件----添加
     * 
     * @param spsAttr
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(SpsAttr spsAttr) {
        try {
            spsAttr.setCreateUser(getLoginUserCnName());
            if (spsAttrService.insert(spsAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("spsAttr add fail, exist spsAttr.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("spsAttr add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps信息表对应的附件----更新跳转
     * 
     * @param model
     * @param spsAttr
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, SpsAttr spsAttr) {
        try {
            SpsAttr spsAttrResult = spsAttrService.getSpsAttrById(spsAttr.getId());
            model.addAttribute("spsAttr", spsAttrResult);
        } catch (Exception e) {
            LOG.error("spsAttr updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps信息表对应的附件----更新
     * 
     * @param model
     * @param spsAttr
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, SpsAttr spsAttr) {
        try {
            spsAttr.setUpdateUser(getLoginUserCnName());
            if (spsAttrService.update(spsAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("spsAttr update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表对应的附件----删除
     * 
     * @param spsAttr
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(SpsAttr spsAttr) {
        try {
            spsAttr.setUpdateUser(getLoginUserCnName());
            if (spsAttrService.delete(spsAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("spsAttr delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps信息表对应的附件----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(SpsAttrQuery query) {
        try {
            List<SpsAttr> list = spsAttrService.querySpsAttrList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("spsAttr query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps信息表对应的附件详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(SpsAttrQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            SpsAttr spsAttr = spsAttrService.getSpsAttrById(query.getId());
            if (spsAttr != null) {
                return new Wrapper<SpsAttr>().result(spsAttr);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps信息表对应的附件详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail spsAttr has error.", e);
            return error();
        }
    }
}
