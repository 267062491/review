package com.letv.tbtSps.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.Map;

/**
 * http://blog.csdn.net/zhanwentao2/article/details/7255432
 * Description 导出word文档
 * 主要就是配置一下模板，然后按照模板中的${neiorng} 进行文字替换
 * Created by ygd on 2017/11/21.
 */
public class ExportWord {

    private static final Log LOG = LogFactory.getLog(ExportWord.class);

    /**
     * 生成word文件并保存到服务器指定路径下
     * @param dataMap 需要导出的数据结构 ， key是模板里配置的名称 ， value是对应的内容
     * @param templetDir 模板文件存放的路径
     * @param templetFileNameFTL 模板文件名称 ftl 后缀
     * @param exportDir 导出文件存放路径
     * @return 导出文件名称
     */
    public static String createWord(Map<String,Object> dataMap , String templetDir , String templetFileNameFTL , String exportDir){
        String exportFileName = templetFileNameFTL+Math.random()*10000+".doc";
        try {
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            configuration.setDirectoryForTemplateLoading(new File(templetDir));
            Template t = configuration.getTemplate(templetFileNameFTL); //文件名
            File outFile = new File(exportDir+File.pathSeparator+exportFileName);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            t.process(dataMap, out);
        } catch (IOException e) {
            LOG.error("ExportWord.createWord.IOException:",e);
        } catch (TemplateException e) {
            LOG.error("ExportWord.createWord.TemplateException:",e);
        }
        return exportFileName ;
    }

    /**
     * todo  这有个问题， 当出现特殊字符的时候，生成word失败  例如：<w:tr
     * @param dataMap
     */
    private static void getData(Map<String, Object> dataMap) {
        dataMap.put("bianhao", "标题");
        dataMap.put("riqi", "2012");
        dataMap.put("chengyuan", "2");
        dataMap.put("biaoti", "13");
        dataMap.put("neirong", "唐鑫");
        dataMap.put("jieguo", "在刚刚的那个ftl文件中直接编辑：" +
                "打开FTL文件，搜索   \n" +
                "找到第一个，tr的意思不用解释了吧，代表着一行。\n" +
                "这也意味着我找到了Table中的第一行，但是我需要遍历的不是从第一行开始，而是从第二行。\n" +
                "好的，继续搜索，找到第二个。");
//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//        for (int i = 0; i < 10; i++) {
//            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("number", i);
//            map.put("content", "内容"+i);
//            list.add(map);
//        }


//        dataMap.put("list", list);
    }



    public static void main(String[] args) {
        ExportWord test = new ExportWord();
//        test.createWord();
    }

}
