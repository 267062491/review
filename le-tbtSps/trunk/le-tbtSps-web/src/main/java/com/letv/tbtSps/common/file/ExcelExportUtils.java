package com.letv.tbtSps.common.file;

import com.letv.common.utils.file.ExcelUtils;
import com.letv.common.utils.file.FileUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-14
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
public class ExcelExportUtils {
    /**
     * 导出Excel
     *
     * @param fileName
     *            Excel文件名
     * @param sheetName
     *            Excel Sheet名
     * @param cellNames
     *            表头名
     * @param dataList
     *            数据
     * @param fieldNames
     *            数据属性集合，与表头一一对应
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws java.io.IOException
     */
    public static <T> String exportExcelFile(String fileName, String sheetName, String[] cellNames, List<T> dataList,
                                             String[] fieldNames, HttpServletRequest request, HttpServletResponse response)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
            IOException {
        return ExcelExportUtils.exportExcelFile(fileName, sheetName, cellNames, dataList, fieldNames, null, request,
                response);
    }

    /**
     * 导出Excel
     *
     * @param fileName
     *            Excel文件名
     * @param sheetName
     *            Excel Sheet名
     * @param cellNames
     *            表头名
     * @param dataList
     *            数据
     * @param fieldNames
     *            数据属性集合，与表头一一对应
     * @param cellTypes
     *            单元格数据类型，与数据属性一一对应
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws java.io.IOException
     */
    public static <T> String exportExcelFile(String fileName, String sheetName, String[] cellNames, List<T> dataList,
                                             String[] fieldNames, int[] cellTypes, HttpServletRequest request, HttpServletResponse response)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
            IOException {
        Assert.notNull(fileName, "fileName must be not null");
        Assert.notNull(request, "request must be not null");
        Assert.notNull(response, "response must be not null");

        response.setContentType("application/vnd.ms-excel");
        String encodeFileName = FileUtils.encodeFilename(fileName, request.getHeader("user-agent"));
        response.setHeader("Content-Disposition", "attachment;filename=" + encodeFileName);
        ExcelUtils.exportFile(sheetName, cellNames, dataList, fieldNames, cellTypes, response.getOutputStream());
        return encodeFileName;
    }
}
