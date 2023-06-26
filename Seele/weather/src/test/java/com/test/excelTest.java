package com.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class excelTest {
    public List<String> readExcel(String path){
        List<String> list = new ArrayList<String>();
        try {
            // 文件输入流
            InputStream ips = new FileInputStream(path);
            // 文件读取类实例化
            Workbook wb = null;
            // 判断后缀名
            if (path.substring(path.lastIndexOf(".")+1).equalsIgnoreCase("xlsx")){
                wb = new XSSFWorkbook(ips);
            }else if (path.substring(path.lastIndexOf(".")+1).equalsIgnoreCase("xls")){
                wb = new HSSFWorkbook(ips);
            }
            // 第一张表
            Sheet st = wb.getSheetAt(0);
            // 总行数
            int l = st.getPhysicalNumberOfRows();
            // 循环读取
            for (int i=1; i<l; i++){
                // 每一行
                Row row = st.getRow(i);
                int h = row.getPhysicalNumberOfCells();
                for (int j=0; j<h; j++){
                    Cell cell = row.getCell(j);
                    cell.setCellType(CellType.STRING);
                    String area = cell.getStringCellValue();
                    list.add(area);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        excelTest e = new excelTest();
        List<String> r = e.readExcel("src/test/java/com/test/city.xlsx");
        System.out.println(r);
    }
}
