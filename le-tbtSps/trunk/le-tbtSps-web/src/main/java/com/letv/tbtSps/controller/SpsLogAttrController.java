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

import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.domain.query.SpsLogAttrQuery;
import com.letv.tbtSps.service.SpsLogAttrService;
import com.letv.common.utils.exception.ExistedException;
import com.letv.common.controller.base.BaseController;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;

/**
 * SpsLogAttrController ：sps评审信息表对应的附件控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("spsLogAttr")
public class SpsLogAttrController extends BaseController {

    @Autowired
    private SpsLogAttrService spsLogAttrService;

    /** 视图前缀 */
    private static final String viewPrefix ="spsLogAttr";
    
    private static final Log LOG = LogFactory.getLog(SpsLogAttrController.class);

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
    public String queryByPage(Model model, PageUtil page, SpsLogAttrQuery query) {
        try {
            List<SpsLogAttr> dataList = spsLogAttrService.querySpsLogAttrListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("spsLogAttr queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * sps评审信息表对应的附件----添加跳转
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * sps评审信息表对应的附件----添加
     * 
     * @param spsLogAttr
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(SpsLogAttr spsLogAttr) {
        try {
            spsLogAttr.setCreateUser(getLoginUserCnName());
            if (spsLogAttrService.insert(spsLogAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("spsLogAttr add fail, exist spsLogAttr.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("spsLogAttr add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * sps评审信息表对应的附件----更新跳转
     * 
     * @param model
     * @param spsLogAttr
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, SpsLogAttr spsLogAttr) {
        try {
            SpsLogAttr spsLogAttrResult = spsLogAttrService.getSpsLogAttrById(spsLogAttr.getId());
            model.addAttribute("spsLogAttr", spsLogAttrResult);
        } catch (Exception e) {
            LOG.error("spsLogAttr updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * sps评审信息表对应的附件----更新
     * 
     * @param model
     * @param spsLogAttr
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, SpsLogAttr spsLogAttr) {
        try {
            spsLogAttr.setUpdateUser(getLoginUserCnName());
            if (spsLogAttrService.update(spsLogAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("spsLogAttr update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps评审信息表对应的附件----删除
     * 
     * @param spsLogAttr
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(SpsLogAttr spsLogAttr) {
        try {
            spsLogAttr.setUpdateUser(getLoginUserCnName());
            if (spsLogAttrService.delete(spsLogAttr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("spsLogAttr delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * sps评审信息表对应的附件----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(SpsLogAttrQuery query) {
        try {
            List<SpsLogAttr> list = spsLogAttrService.querySpsLogAttrList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("spsLogAttr query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询sps评审信息表对应的附件详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(SpsLogAttrQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            SpsLogAttr spsLogAttr = spsLogAttrService.getSpsLogAttrById(query.getId());
            if (spsLogAttr != null) {
                return new Wrapper<SpsLogAttr>().result(spsLogAttr);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询sps评审信息表对应的附件详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail spsLogAttr has error.", e);
            return error();
        }
    }
}
