package com.letv.tbtSps.controller;


import com.letv.common.utils.exception.ExistedException;
import com.letv.common.utils.page.PageUtil;
import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.SystemPar;
import com.letv.tbtSps.domain.query.SystemParQuery;
import com.letv.tbtSps.service.SystemParService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.letv.tbtSps.service.test.HighChartsDomain;
//import com.letv.tbtSps.service.test.Person;
//import com.letv.tbtSps.service.test.SolrJTest;
//import com.letv.tbtSps.utils.solr.SolrUtils;

/**
 * SystemParController ：系统参数表控制器
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
*/
@Controller
@RequestMapping("systemPar")
public class SystemParController extends ReviewBaseController {

    @Autowired
    private SystemParService systemParService;

    /** 视图前缀 */
    private static final String viewPrefix ="systemPar";
    
    private static final Log LOG = LogFactory.getLog(SystemParController.class);

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
    public String queryByPage(Model model, PageUtil page, SystemParQuery query) {
        try {
//            SolrJTest solr = new SolrJTest();
//            List<Person> list = solr.querySolr();


            List<SystemPar> dataList = systemParService.querySystemParListWithPage(query, page);
//            model.addAttribute("dataList", list);// 数据集合
            model.addAttribute("query", query);// 查询参数
            model.addAttribute("page", page);// 分页
        } catch (Exception e) {
            LOG.error("systemPar queryByPage has error.", e);
        }
        return viewPrefix + "/index";
    }

    /**
     * 系统参数表----添加跳转
     * 
     * @return
     */
    @RequestMapping(value = "addForward")
    public String addForward() {
        return viewPrefix + "/add";
    }

    /**
     * 系统参数表----添加
     * 
     * @param systemPar
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Wrapper<?> add(SystemPar systemPar) {
        try {
            systemPar.setCreateUser(getLoginUserCnName());
            if (systemParService.insert(systemPar)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "添加成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
            }
        } catch (ExistedException e) {
            LOG.warn("systemPar add fail, exist systemPar.");
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败，已经存在");
        } catch (Exception e) {
            LOG.error("systemPar add has error.", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "添加失败！");
        }
    }

    /**
     * 系统参数表----更新跳转
     * 
     * @param model
     * @param systemPar
     * @return
     */
    @RequestMapping(value = "updateForward")
    public String updateForward(Model model, SystemPar systemPar) {
        try {
            SystemPar systemParResult = systemParService.getSystemParById(systemPar.getId());
            model.addAttribute("systemPar", systemParResult);
        } catch (Exception e) {
            LOG.error("systemPar updateForward has error.", e);
        }
        return viewPrefix + "/update";
    }

    /**
     * 系统参数表----更新
     * 
     * @param model
     * @param systemPar
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Wrapper<?> update(Model model, SystemPar systemPar) {
        try {
            systemPar.setUpdateUser(getLoginUserCnName());
            if (systemParService.update(systemPar)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "更新成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "更新失败！");
            }
        } catch (Exception e) {
            LOG.error("systemPar update has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 系统参数表----删除
     * 
     * @param systemPar
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Wrapper<?> delete(SystemPar systemPar) {
        try {
            systemPar.setUpdateUser(getLoginUserCnName());
            if (systemParService.delete(systemPar)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "删除成功！");
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "删除失败！");
            }
        } catch (Exception e) {
            LOG.error("systemPar delete has error.", e);
            return WrapMapper.error();
        }
    }

    /**
     * 系统参数表----查询-无分页
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "query")
    @ResponseBody
    public Wrapper<?> query(SystemParQuery query) {
        try {
            List<SystemPar> list = systemParService.querySystemParList(query);
            if (!CollectionUtils.isEmpty(list)) {
                return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, list);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询配置信息失败！");
            }
        } catch (Exception e) {
            LOG.error("systemPar query has error.", e);
            return WrapMapper.error();
        }
    }
    
    /**
     * 查询系统参数表详情
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Wrapper<?> detail(SystemParQuery query) {
        if (null == query || null == query.getId()) {
            return illegalArgument();
        }

        try {
            SystemPar systemPar = systemParService.getSystemParById(query.getId());
            if (systemPar != null) {
                return new Wrapper<SystemPar>().result(systemPar);
            } else {
                return WrapMapper.wrap(Wrapper.ERROR_CODE, "查询系统参数表详情失败！");
            }
        } catch (Exception e) {
            LOG.warn("detail systemPar has error.", e);
            return error();
        }
    }

    /**
     * highCharts 柱状图 例子
     * @return
     */
    @RequestMapping(value = "highChartsColumn")
    @ResponseBody
    public Wrapper<?> highChartsColumn(){

        List<String> title = new ArrayList<String>() ;
        title.add("一月");
        title.add("二月");
        title.add("三月");
        title.add("四月");
        title.add("五月");
        title.add("六月");
        title.add("七月");
        title.add("八月");
        title.add("九月");
        title.add("十月");
        title.add("十一月");
        title.add("十二月");

//        List<HighChartsDomain> arrs = new ArrayList<HighChartsDomain>() ;
//        for(int i = 0 ; i<4 ; i++){
//            HighChartsDomain highChartsDomain = new HighChartsDomain();
//            highChartsDomain.setName("name"+i);
//            List<Integer> data = new ArrayList<Integer>() ;
//            for(int j = 0 ; j<12 ; j++){
//                data.add(123);
//            }
//            highChartsDomain.setData(data);
//            arrs.add(highChartsDomain);
//        }

        Map<String , Object> map = new HashMap<String,Object>();
        map.put("title",title);
//        map.put("arr",arrs);
        return new Wrapper<Map<String , Object>>().result(map);
    }



}
