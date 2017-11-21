package com.letv.tbtSps.controller;

import com.google.common.collect.Maps;
import com.letv.common.utils.config.PropertiesHelper;
import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.Country;
import com.letv.tbtSps.domain.SpsInfo;
import com.letv.tbtSps.domain.SpsInfoLog;
import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.domain.query.CountryQuery;
import com.letv.tbtSps.domain.query.SpsInfoLogQuery;
import com.letv.tbtSps.domain.query.SpsInfoQuery;
import com.letv.tbtSps.service.CountryService;
import com.letv.tbtSps.service.SpsInfoLogService;
import com.letv.tbtSps.service.SpsInfoService;
import com.letv.tbtSps.service.SpsLogAttrService;
import com.letv.tbtSps.utils.ExportWord;
import com.letv.tbtSps.utils.enums.Sps_Tbt_InfoStatus;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-15
 * Time: 下午10:13
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("fileDownload")
public class FileDownload extends ReviewBaseController {

    @Autowired
    private SpsLogAttrService spsLogAttrService ;
    @Autowired
    private SpsInfoService spsInfoService ;
    @Autowired
    private CountryService countryService ;
    @Autowired
    private SpsInfoLogService spsInfoLogService ;

    /**
     * 下载文件
     * @param fileName
     * @return
     * @throws IOException
     */
    @RequestMapping("download")
    public ResponseEntity<byte[]> download( Long fileName) throws IOException {
        SpsLogAttr spsLogAttr = spsLogAttrService.getSpsLogAttrById(fileName);
        String filePath = spsLogAttr.getFilePath();
        String fileN = spsLogAttr.getFileName();
        String path=filePath+fileN;
        File file=new File(path);
        HttpHeaders headers = new HttpHeaders();
//        String fileName=new String("你好.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileN);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    /**
     * 导出word文件并下载
     * @param spsCode
     * @return
     * @throws IOException
     */
    @RequestMapping("exportWord")
    public ResponseEntity<byte[]> exportWord( String spsCode) throws IOException {
        Map<String,Object> data = Maps.newHashMap();
        SpsInfoQuery spsInfoQuery = new SpsInfoQuery();
        spsInfoQuery.setSpsCode(spsCode);
        List<SpsInfo> spsInfoList = spsInfoService.querySpsInfoList(spsInfoQuery);
        if(!CollectionUtils.isEmpty(spsInfoList)){
            SpsInfo spsInfo = spsInfoList.get(0);
            data.put("spsCode",spsCode);
            data.put("sendDate",spsInfo.getPublishDate());// 通报日期
            data.put("title",spsInfo.getFileTitle());// 文件标题
            data.put("content",spsInfo.getContentDes());// 内容描述
            CountryQuery countryQuery = new CountryQuery();
            countryQuery.setCountryCode(spsInfo.getCountryCode());// 通报成员编码
            List<Country> countryList = countryService.queryCountryList(countryQuery);
            if(!CollectionUtils.isEmpty(countryList)){
                data.put("country",countryList.get(0).getCountryContent());
            }
            /**
             * 还要查询一下，最终评议结果
             */
            SpsInfoLogQuery spsInfoLogQuery = new SpsInfoLogQuery();
            spsInfoLogQuery.setSpsCode(spsCode);
            spsInfoLogQuery.setState(Sps_Tbt_InfoStatus.HUIZONG_PINGYI.getStatusCode());
            List<SpsInfoLog> spsInfoLogList = spsInfoLogService.querySpsInfoLogList(spsInfoLogQuery);
            if(CollectionUtils.isEmpty(spsInfoLogList)){
                data.put("result",spsInfoLogList.get(0).getContent());
            }
            String filePath = PropertiesHelper.newInstance().getValue("sps_attr_addr_temp");
            String exportFileName = ExportWord.createWord(data,PropertiesHelper.newInstance().getValue("sps_attr_addr_templet"), "content.ftl" , filePath);
            String path=filePath+File.separator+exportFileName;
            File file=new File(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", exportFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        }
        return null ;
    }
}
