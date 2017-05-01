package com.letv.tbtSps.controller;

import com.letv.tbtSps.common.controller.ReviewBaseController;
import com.letv.tbtSps.domain.SpsLogAttr;
import com.letv.tbtSps.service.SpsLogAttrService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

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
}
