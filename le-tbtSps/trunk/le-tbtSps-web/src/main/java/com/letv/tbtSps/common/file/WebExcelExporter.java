package com.letv.tbtSps.common.file;

import com.letv.common.utils.file.ExcelExporter;
import com.letv.common.utils.file.FileUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-14
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
public class WebExcelExporter {
    private final ExcelExporter excelExporter;
    private final String encodeFileName;

    /**
     * 构建 WebExcelExporter
     *
     * @param fileName
     *            Excel文件名
     * @param sheetName
     *            Sheet名称
     * @param cellNames
     *            列标题的数组
     * @param fieldNames
     *            数据对象的属性数组
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @throws java.io.IOException
     */
    public WebExcelExporter(String fileName, String sheetName, String[] cellNames, String[] fieldNames,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        Assert.notNull(fileName, "fileName must be not null");
        Assert.notNull(request, "request must be not null");
        Assert.notNull(response, "response must be not null");

        response.setContentType("application/vnd.ms-excel");
        this.encodeFileName = FileUtils.encodeFilename(fileName, request.getHeader("user-agent"));
        response.setHeader("Content-Disposition", "attachment;filename=" + encodeFileName);

        this.excelExporter = new ExcelExporter(sheetName, cellNames, fieldNames, response.getOutputStream());
    }

    /**
     * 追加数据，写入sheet尾部
     *
     * @param datas
     * @return
     * @throws java.io.IOException
     */
    public <T> WebExcelExporter append(Collection<T> datas) throws IOException {
        this.excelExporter.append(datas);
        return this;
    }

    /**
     * Write out this workbook to an Outputstream, then close Outputstream.
     *
     * @throws java.io.IOException
     */
    public void write() throws IOException {
        this.excelExporter.write();
    }

    /**
     * @return the encodeFileName
     */
    public String getEncodeFileName() {
        return this.encodeFileName;
    }

    /**
     * get Excel File Name
     *
     * @return the excelFileName
     */
    public String getExcelFileName() {
        return this.getEncodeFileName();
    }
}
