package com.letv.tbtSps.service.impl;

import com.letv.tbtSps.domain.dto.SolrDto;
import com.letv.tbtSps.service.SolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Description
 * Created by ygd on 2017/11/13.
 */
public class SolrServiceImpl implements SolrService {
    /**
     * solr http服务地址
     */
    public static String SOLR_URL;

    /**
     * solr的core
     */
    public static String SOLR_CORE;

    static {
//        Properties properties = new Properties();
//        String path = SolrServiceImpl.class.getResource("/").getFile().toString()
//                + "solr.properties";
        try {
//            FileInputStream fis = new FileInputStream(new File(path));
//            properties.load(fis);
//            SOLR_URL = properties.getProperty("SOLR_URL");
//            SOLR_CORE = properties.getProperty("SOLR_CORE");
            SOLR_URL = "http://localhost:8080/SHFW_SEARCH";
            SOLR_CORE = "jobs";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 主函数入口
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 1.测试插入文档
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "00001");
        map.put("name", "lijie");
        map.put("age", "24");
        map.put("addr", "深圳");
        addDocument(map, SOLR_CORE);

        // 2.通过bean添加document
        List<SolrDto> persons = new ArrayList<SolrDto>();
        persons.add(new SolrDto("00002", "lisi", 25, "重庆"));
        persons.add(new SolrDto("00003", "wangwu", 26, "上海"));
        addDocumentByBean(persons, SOLR_CORE);

        // 3.根据id集合删除索引
        List<String> ids = new ArrayList<String>();
        ids.add("00001");
        ids.add("00002");
        ids.add("00003");
        deleteDocumentByIds(ids, SOLR_CORE);

        // 4.查询
        getDocument(SOLR_CORE);

        // 5.spell测试
        getSpell(SOLR_CORE);

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
     * 添加文档
     *
     * @param map
     * @param core
     * @throws Exception
     */
    private static void addDocument(Map<String, String> map, String core)
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
    private static void addDocumentByBean(List<SolrDto> persons, String core)
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
    private static void deleteDocumentByIds(List<String> ids, String core)
            throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        solrClient.deleteById(ids);
        commitAndCloseSolr(solrClient);
    }

    private static void getDocument(String core) throws Exception {
        HttpSolrClient solrClient = getSolrClient("/" + core);
        SolrQuery sq = new SolrQuery();

        // q查询
        sq.set("q", "id:00003");

        // filter查询
        sq.addFilterQuery("id:[0 TO 00003]");

        // 排序
        sq.setSort("id", SolrQuery.ORDER.asc);

        // 分页 从第0条开始取，取一条
        sq.setStart(0);
        sq.setRows(1);

        // 设置高亮
        sq.setHighlight(true);

        // 设置高亮的字段
        sq.addHighlightField("name");

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
        List<SolrDto> persons = result.getBeans(SolrDto.class);
        System.out.println("一共查询到" + persons.size() + "条记录");
        for (SolrDto person : persons) {
            System.out.println(person);
        }
        commitAndCloseSolr(solrClient);
    }

    /**
     * 查询使用spell接口，输入错误，solr可以给出建议词
     *
     * @param core
     * @throws Exception
     */
    private static void getSpell(String core) throws Exception {
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
}
