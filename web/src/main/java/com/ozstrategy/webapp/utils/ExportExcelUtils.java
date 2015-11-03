package com.ozstrategy.webapp.utils;


import com.ozstrategy.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 8/16/15.
 */
public class ExportExcelUtils {
    public static void export(String sheetName,ExcelUtils.Rows rows,String outputFile, List<Map<String,Object>> contexts,HttpServletResponse response, HttpServletRequest request){
        try {
            HSSFWorkbook workbook = ExcelUtils.export(sheetName, rows, contexts);
            OutputStream fOut=response.getOutputStream();
            response.setCharacterEncoding("application/ms-excel;charset=UTF-8");
            response.setCharacterEncoding("ms-excel/html;charset=UTF-8");
            response.setContentType("application/ms-excel");
            String finalExportZipFileName =null;
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                finalExportZipFileName = URLEncoder.encode(outputFile, "UTF-8");
            } else {
                finalExportZipFileName = new String(outputFile.getBytes("UTF-8"), "ISO8859-1");
            }
            response.setHeader("Content-Disposition", "attachment;Filename=" + finalExportZipFileName+".xls");
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
