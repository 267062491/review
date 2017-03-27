package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.Tbattr;
import com.letv.tbtSps.domain.query.TbattrQuery;
import com.letv.tbtSps.service.TbattrService;
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
 * TbattrController ：tbt信息表对应的附件控制器
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:04
*/
@Controller
@RequestMapping("tbattr")
public class TbattrController extends ReviewBaseController {

    @Autowired
    private TbattrService tbattrService;

    /** 视图前缀 */
    private static final String viewPrefix ="tbattr";
    
    private static final Log LOG = LogFactory.getLog(TbattrController.class);

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
    public String queryByPage(Model model, PageUtil page, TbattrQuery query) {
        try {
            List<Tbattr> dataList = tbattrService.queryTbattrListWithPage(query, page);
            model.addAttribute("dataList", dataList);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("tbattr queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * tbt信息表对应的附件----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * tbt信息表对应的附件----添加
     * 
     * @param tbattr
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(Tbattr tbattr) {
        try {
            tbattr.setCreateUser(getLoginUserCnName());
            if (tbattrService.insert(tbattr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("tbattr add fail, exist tbattr.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("tbattr add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * tbt信息表对应的附件----更新跳转
     * 
     * @param model
     * @param tbattr
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, Tbattr tbattr) {
        try {
            Tbattr tbattrResult = tbattrService.getTbattrById(tbattr.getId());
            model.addAttribute("tbattr", tbattrResult);
        } catch (Exception e) {
            LOG.error("tbattr updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * tbt信息表对应的附件----更新
     * 
     * @param model
     * @param tbattr
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, Tbattr tbattr) {
        try {
            tbattr.setUpdateUser(getLoginUserCnName());
            if (tbattrService.update(tbattr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("tbattr update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表对应的附件----删除
     * 
     * @param tbattr
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(Tbattr tbattr) {
        try {
            tbattr.setUpdateUser(getLoginUserCnName());
            if (tbattrService.delete(tbattr)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("tbattr delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * tbt信息表对应的附件----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(TbattrQuery query) {
        try {
            List<Tbattr> list = tbattrService.queryTbattrList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("tbattr query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询tbt信息表对应的附件详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(TbattrQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            Tbattr tbattr = tbattrService.getTbattrById(query.getId());
            if (tbattr != null) {
                return new Wrapper<Tbattr>().result(tbattr);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询tbt信息表对应的附件详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail tbattr has error.", e);
            return error();
        }
    }
}
