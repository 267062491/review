package com.letv.tbtSps.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.letv.common.utils.serialize.JsonHelper;
import com.letv.tbtSps.domain.dto.SolrDto;
import com.letv.tbtSps.service.SolrService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description
 * Created by ygd on 2017/11/13.
 */
public class SolrServiceImpl implements SolrService {
    /** LOG */
    private static final Log LOG = LogFactory.getLog(SolrServiceImpl.class);

    /**
     * solr http服务地址
     */
    public static String SOLR_URL;

    /**
     * solr的core
     */
    public static String SOLR_CORE;

    static {
        try {
            SOLR_URL = "http://localhost:8081/SHFW_SEARCH";
            SOLR_CORE = "jobs";
//            SOLR_URL = PropertiesHelper.newInstance().getValue("solr.server");
//            SOLR_CORE = PropertiesHelper.newInstance().getValue("solr.home.jobs");
        } catch (Exception e) {
            LOG.error("SolrServiceImpl.static.error:",e);
        }
    }
    /**
     * 获取solr服务
     *
     * @return
     */
    private static HttpSolrClient getSolrClient(String core) {
        HttpSolrClient hsc = new HttpSolrClient(SOLR_URL + core);
        return hsc;
    }

    /**
     * 添加文档，通过map的方式
     *
     * @param map
     * @param core
     * @throws Exception
     */
    public static void addDocument(Map<String, String> map, String core)
            throws Exception {
        SolrInputDocument sid = new SolrInputDocument();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sid.addField(entry.getKey(), entry.getValue());
        }
        HttpSolrClient solrClient = getSolrClient("/" + core);
        solrClient.add(sid);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 添加文档，通过bean方式
     *
     * @param persons
     * @param core
     * @throws Exception
     */
    public static void addDocumentByBean(List<SolrDto> persons, String core)
            throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        solrClient.addBeans(persons);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 根据id集合删除索引
     *
     * @param ids
     * @param core
     * @throws Exception
     */
    public static void deleteDocumentByIds(List<String> ids, String core)
            throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        solrClient.deleteById(ids);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 查询solr
     * @param core solrHome
     * @param queryCondition 查询条件 countryContent:语文 OR countryContent:数学
     * @param sortColumn 排序字段
     * @param sortRule 排序规则，升序or将序  值为 ASC / DESC
     * @param start 从哪条开始查
     * @param row 查询多少条
     * @param highlight 是否高亮
     * @param highlightColumn 高亮的字段名
     * @param fq fq过滤语句
     * @throws Exception
     */
    public static Map<String ,Object> solrQuery(String core , String queryCondition,String sortColumn , String sortRule
            , int start , int row , boolean highlight , String highlightColumn,String fq) {
        try{
            Map<String ,Object> map = Maps.newHashMap();
            HttpSolrClient solrClient = getSolrClient("/" + core);
            SolrQuery sq = new SolrQuery();
            sq.set("q",queryCondition);//sq.set("q", "countryContent:语文 OR countryContent:数学"); 可以查询出语文和数学的数据
            // 排序
            sq.setSort(sortColumn, "ASC".equals(sortColumn)?SolrQuery.ORDER.asc:SolrQuery.ORDER.desc);
            // 分页 从第0条开始取，取一条
            sq.setStart(start);
            sq.setRows(row);
            // 设置高亮
            sq.setHighlight(highlight);
            // 设置高亮的字段
            sq.addHighlightField(highlightColumn);
            // 设置高亮的样式
            sq.setHighlightSimplePre("<font color='red'>");
            sq.setHighlightSimplePost("</font>");
            if(!StringUtils.isEmpty(fq)){
                sq.addFilterQuery("fq");
            }

            QueryResponse result = solrClient.query(sq);
            long totalItem = result.getResults().getNumFound();// 获取总命中数
            // 获取对象信息,需要传入对应对象的类class
            List<SolrDto> solrDtoList = result.getBeans(SolrDto.class);
            map.put("solrDtoList",solrDtoList);
            map.put("totalItem",Integer.valueOf(totalItem+""));

            /**
             * 高亮这，难道还要循环去修改数据么， 有其他的办法么 ？
             */
            NamedList list = (NamedList) result.getResponse().get("highlighting");
            for (SolrDto solrDto : solrDtoList) {
                LOG.info("olrServiceImpl.solrQuery.solrDto"+JsonHelper.toJson(solrDto));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    Map.Entry<String, SimpleOrderedMap> entry = (Map.Entry<String, SimpleOrderedMap>) it.next();
                    if(null!=entry.getKey() && entry.getKey().equals(solrDto.getId())){
                        SimpleOrderedMap simpleOrderedMap = entry.getValue();
                        List<String> simpleOrderedList = (List<String>) simpleOrderedMap.get("countryContent");
                        if(null!=simpleOrderedList){
                            solrDto.setCountryContent(simpleOrderedList.get(0));// TODO: 2017/11/20 这里有个问题，高亮的时候，如果多值，只能设置一个高亮
                        }
                        break;
                    }
                }
            }
            commitAndCloseSolr(solrClient);
            return map ;
        }catch (Exception e){
            LOG.error("SolrServiceImpl.solrQuery.error:",e);
            return null ;
        }

    }

    public static void getDocument(String core) throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        SolrQuery sq = new SolrQuery();

//        sq.setParam()
        // q查询
        sq.set("q", "countryContent:数学 OR notificationTypeCode:notificationTypeCode&hl.preserveMulti=true");


        // filter查询
//        sq.addFilterQuery("id:[0 TO 00003]");

        // 排序
        sq.setSort("id", SolrQuery.ORDER.asc);

        // 分页 从第0条开始取，取一条
        sq.setStart(0);
        sq.setRows(10);

        // 设置高亮
        sq.setHighlight(true);

        // 设置高亮的字段
        sq.addHighlightField("countryContent");
        sq.addHighlightField("notificationTypeCode");

        // 设置高亮的样式
        sq.setHighlightSimplePre("<font color='red'>");
        sq.setHighlightSimplePost("</font>");

        QueryResponse result = solrClient.query(sq);

        // 这里可以从result获得查询数据(两种方式如下)

        // 1.获取document数据
        System.out.println("1.获取document数据-------------------------");
        SolrDocumentList results = result.getResults();
        // 获取查询的条数
        System.out.println("一共查询到" + results.getNumFound() + "条记录");
        for (SolrDocument solrDocument : results) {
            System.out.println("id:" + solrDocument.get("id"));
            System.out.println("name:" + solrDocument.get("name"));
            System.out.println("age:" + solrDocument.get("age"));
            System.out.println("addr:" + solrDocument.get("addr"));
        }

        // 2.获取对象信息,需要传入对应对象的类class
        System.out.println("2.获取对象信息,需要传入对应对象的类class-----------");
        List<SolrDto> solrDtoList = result.getBeans(SolrDto.class);
        System.out.println("一共查询到" + solrDtoList.size() + "条记录");
        NamedList list = (NamedList) result.getResponse().get("highlighting");

        for (SolrDto solrDto : solrDtoList) {
            System.out.println(solrDto);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Map.Entry<String, SimpleOrderedMap> entry = (Map.Entry<String, SimpleOrderedMap>) it.next();
                if(null!=entry.getKey() && entry.getKey().equals(solrDto.getId())){
                    SimpleOrderedMap simpleOrderedMap = entry.getValue();
                    List<String> simpleOrderedList = (List<String>) simpleOrderedMap.get("countryContent");
                    if(null!=simpleOrderedList){
                        solrDto.setCountryContent(simpleOrderedList.get(0));// TODO: 2017/11/20 这里有个问题，高亮的时候，如果多值，只能设置一个高亮
                    }
                    break;
                }
            }
        }
        System.out.print(solrDtoList);
        commitAndCloseSolr(solrClient);
    }

    /**
     * 查询使用spell接口，输入错误，solr可以给出建议词
     *
     * @param core
     * @throws Exception
     */
    public static void getSpell(String core) throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        SolrQuery sq = new SolrQuery();
        sq.set("qt", "/spell");

        // 原本是lisi，这里拼写错误，测试solr返回建议词语
        sq.set("q", "liss");
        QueryResponse query = solrClient.query(sq);
        SolrDocumentList results = query.getResults();

        // 获取查询条数
        long count = results.getNumFound();

        // 判断是否查询到
        if (count == 0) {
            SpellCheckResponse spellCheckResponse = query
                    .getSpellCheckResponse();
            List<SpellCheckResponse.Collation> collatedResults = spellCheckResponse
                    .getCollatedResults();
            for (SpellCheckResponse.Collation collation : collatedResults) {
                long numberOfHits = collation.getNumberOfHits();
                System.out.println("建议条数为:" + numberOfHits);

                List<SpellCheckResponse.Correction> misspellingsAndCorrections = collation
                        .getMisspellingsAndCorrections();
                for (SpellCheckResponse.Correction correction : misspellingsAndCorrections) {
                    String source = correction.getOriginal();
                    String current = correction.getCorrection();
                    System.out.println("推荐词语为：" + current + "原始的输入为：" + source);
                }
            }
        } else {
            for (SolrDocument solrDocument : results) {
                // 获取key集合
                Collection<String> fieldNames = solrDocument.getFieldNames();

                // 根据key集合输出value
                for (String field : fieldNames) {
                    System.out.println("key: " + field + ",value: "
                            + solrDocument.get(field));
                }
            }
        }

        // 关闭连接
        commitAndCloseSolr(solrClient);
    }

    /**
     * 提交以及关闭服务
     *
     * @param solrClient
     * @throws Exception
     */
    private static void commitAndCloseSolr(HttpSolrClient solrClient)
            throws Exception {
        solrClient.commit();
        solrClient.close();
    }


    /**
     * 主函数入口
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 1.测试插入文档
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("spsCode", "00001");
//        map.put("name", "lijie");
//        map.put("age", "24");
//        map.put("addr", "深圳");
//        addDocument(map, SOLR_CORE);

        // 2.通过bean添加document
//        List<SolrDto> persons = new ArrayList<SolrDto>();
//        List<String> listCode = Lists.newArrayList();
//        listCode.add("notificationTypeCode001");
//        listCode.add("notificationTypeCode002");
//        listCode.add("notificationTypeCode003");
//        List<String> listContent = Lists.newArrayList();
//        listContent.add("中华人共和国");
//        SolrDto solrDto = new SolrDto("spsCode1", listCode, listContent);
//        solrDto.setId("spsCode6");
//        solrDto.setCountryContent("小学数学");
//        solrDto.setCountryCode("小学数学");
//        persons.add(solrDto);
//
//        List<String> listCode1 = Lists.newArrayList();
//        listCode1.add("notificationTypeCode001");
//        listCode1.add("notificationTypeCode002");
//        listCode1.add("notificationTypeCode003");
//        List<String> listContent1 = Lists.newArrayList();
//        listContent1.add("印度");
//        SolrDto solrDto1 = new SolrDto("spsCode1", listCode, listContent1);
//        solrDto1.setId("spsCode5");
//        solrDto1.setCountryContent("高中语文");
//        solrDto1.setCountryCode("高中语文");
//        persons.add(solrDto1);
//        addDocumentByBean(persons, SOLR_CORE);
//
//        // 3.根据id集合删除索引
//        List<String> ids = new ArrayList<String>();
//        ids.add("00001");
//        ids.add("00002");
//        ids.add("00003");
//        deleteDocumentByIds(ids, SOLR_CORE);
//
//        // 4.查询
        getDocument(SOLR_CORE);
//
//        // 5.spell测试
//        getSpell(SOLR_CORE);
//        getDocument(SOLR_CORE);

    }
}
