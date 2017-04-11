package com.letv.tbtSps.manager;

import java.util.List;
import java.util.Map;

import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.common.utils.page.PageUtil;
/**
 * SpsInfoManager接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsInfoManager {

    /**
     * 批量增加对象信息
     * 
     * @param spsInfoList
     * @return
     */
    public boolean insert(List<SpsInfo> spsInfoList);

    /**
     * 单个增加对象信息
     * 
     * @param spsInfo
     * @return
     */
    public boolean insert(SpsInfo spsInfo);

    /**
     * 更新 对象信息
     * 
     * @param spsInfo
     *            对象信息对象
     * @return false：失败 true：成功
     */
    public boolean update(SpsInfo spsInfo);

    /**
     * 根据查询Bean获取对象集合，无翻页
     * 
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoList(SpsInfoQuery queryBean);

    /**
     * 根据查询Bean获取对象集合，带翻页
     * 
     * @param queryBean
     * @param pageUtil
     * @return
     */
    public List<SpsInfo> querySpsInfoListWithPage(SpsInfoQuery queryBean,
            PageUtil pageUtil);

    /**
     * 根据查询Bean获取对象信息总数
     * 
     * @param queryBean
     *            对象信息查询对象
     * @return 对象信息总数
     */
    public int querySpsInfoCount(SpsInfoQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     * 
     * @param spsInfo
     *            　
     * @return
     */
    public boolean delete(SpsInfo spsInfo);

    /**
     * 根据主键获取对象信息
     * 
     * @param id
     *            主键字段
     * @return 对象信息
     */
    public SpsInfo getSpsInfoById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     * 
     * @param spsInfos
     *            SpsInfo集合
     * @return
     */
    public boolean delete(SpsInfo[] spsInfos);

    /**
     * 判断是否存在
     * 
     * @param spsInfo
     * @return
     */
    public boolean exist(SpsInfo spsInfo);

    /**
     * 创建sps、tbt
     * @param map
     */
    public boolean insertOrderInfo(Map<String,Object> map) throws Exception;

    /**
     * 根据通报成员分组查询通报成员拥有的年份
     * @param queryBean
     * @return
     */
    public List<SpsInfo> queryCountryDateByCountry(SpsInfoQuery queryBean);

    /**
     * 根据spsCode 修改spsInfo
     * @param spsInfo
     * @return
     */
    public boolean updateSpsInfoBySpsCode(SpsInfo spsInfo);

    /**
     * 下发任务
     * @param spsInfo
     * @return
     */
    public boolean updateSpsInfoSend(SpsInfo spsInfo, List<SpsInfoLog> list_spsInfoLog);

    /**
     * 专家评议
     * @param spsInfoLog
     * @param list_spsAttr
     * @return
     */
    public boolean insertDoReivew(SpsInfoLog spsInfoLog, List<SpsLogAttr> list_spsAttr);

    /**
     * 评议汇总保存
     * @param spsInfoLog
     * @param list_spsAttr
     * @return
     */
    public boolean insertSummaryReview(SpsInfoLog spsInfoLog, List<SpsLogAttr> list_spsAttr);

    /**
     * 评议汇总提交
     * @param spsInfo
     * @param spsInfoLog
     * @param list_spsAttr
     * @return
     */
    public boolean insertSummaryReviewSubmit(SpsInfo spsInfo ,SpsInfoLog spsInfoLog, List<SpsLogAttr> list_spsAttr);
}
