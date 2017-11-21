package com.letv.tbtSps.service;

import java.util.List;
import java.util.Map;

import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.dto.SolrDto;
import com.letv.tbtSps.domain.dto.SolrDtoQuery;
import com.letv.tbtSps.domain.dto.SpsBtbMulityDto;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.common.utils.page.PageUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * SpsInfoService接口
 * 
 * @author yuguodong
 * @version 2017-3-25 22:43:03
 * 
 */
public interface SpsInfoService {

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
     * 创建sps、btb数据
     * @param spsInfo
     * @param files
     * @param contents
     * @param userName
     * @param spsBtbMulityDto
     * @return
     */
    public String[] createOrderInfo(SpsInfo spsInfo , List<MultipartFile> files ,String contents , String userName,SpsBtbMulityDto spsBtbMulityDto);

    /**
     * 修改sps、btb数据
     * @param spsInfo
     * @param files
     * @param contents
     * @param userName
     * @param spsBtbMulityDto
     * @return
     */
    public String[] updateOrderInfo(SpsInfo spsInfo , List<MultipartFile> files ,String contents , String userName,SpsBtbMulityDto spsBtbMulityDto,String fileIds);
    /**
     * 根据通报成员分组查询通报成员拥有的年份
     * @param queryBean
     * @return
     */
    public List<SpsInfo> queryCountryDateByCountry(SpsInfoQuery queryBean);

    /**
     * 下发， 分配给专家
     * @param tableContent
     * @param userName
     */
    public String[] sendNotice(String tableContent,String userName);
    /**
     * 专家评议
     * @param spsInfoLog
     * @param files
     * @param userName
     * @return
     */
    public String[] doReview(SpsInfoLog spsInfoLog , List<MultipartFile> files, String userName);

    /**
     * 评议汇总保存
     * @param spsInfoLog
     * @param files
     * @param userName
     * @return
     */
    public String[] summaryReview(SpsInfoLog spsInfoLog , List<MultipartFile> files, String userName);

    /**
     * 评议汇总提交
     * @param spsInfoLog
     * @param files
     * @param userName
     * @return
     */
    public String[] summaryReviewSubmit(SpsInfoLog spsInfoLog , List<MultipartFile> files, String userName);

    /**
     * 反馈
     * @param spsInfoLog
     * @param files
     * @param userName
     * @return
     */
    public String[] feedBackSubmit(SpsInfoLog spsInfoLog , List<MultipartFile> files, String userName);

    /**
     * 查询专家未评议和已评议的内容
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoListExperts(SpsInfoQuery queryBean ,PageUtil pageUtil);
    /**
     * 查询专家未评议和已评议的内容
     * @param queryBean
     * @return
     */
    public List<SpsInfo> querySpsInfoExpertsAll(SpsInfoQuery queryBean ,PageUtil pageUtil);

    /**
     * 激活
     * @param tableContent
     * @param userName
     * @return
     */
    public String[] jihuo(String tableContent,String userName);

    /**
     * solr查询
     * @param solrDtoQuery
     * @param pageUtil
     * @return
     */
    public List<SolrDto> sorlQuery(SolrDtoQuery solrDtoQuery , PageUtil pageUtil) throws Exception;
}
