package com.zhy.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV转Excel工具类
 */
public class CsvToExcelConverter {
    
    public static void main(String[] args) {
        String csvFilePath = "d:\\code\\my\\java-study\\test_data.csv";
        String excelFilePath = "d:\\code\\my\\java-study\\test_data.xlsx";
        
        try {
            convertCsvToExcel(csvFilePath, excelFilePath);
            System.out.println("CSV文件已成功转换为Excel文件: " + excelFilePath);
        } catch (IOException e) {
            System.err.println("转换过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void convertCsvToExcel(String csvFilePath, String excelFilePath) throws IOException {
        List<String[]> csvData = readCsvFile(csvFilePath);
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("数据表");
            
            for (int i = 0; i < csvData.size(); i++) {
                Row row = sheet.createRow(i);
                String[] rowData = csvData.get(i);
                
                for (int j = 0; j < rowData.length; j++) {
                    Cell cell = row.createCell(j);
                    String cellValue = rowData[j];
                    
                    if (isNumeric(cellValue)) {
                        cell.setCellValue(Double.parseDouble(cellValue));
                    } else {
                        cell.setCellValue(cellValue);
                    }
                }
            }
            
            for (int i = 0; i < csvData.get(0).length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                workbook.write(fileOut);
            }
        }
    }
    
    private static List<String[]> readCsvFile(String csvFilePath) throws IOException {
        List<String[]> csvData = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(csvFilePath), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                csvData.add(fields);
            }
        }
        
        return csvData;
    }
    
    private static boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}