package org.example.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test2 {
    public static void main(String[] args) throws IOException {
        // 读取多个Excel文件
        List<File> files = new ArrayList<>();
        files.add(new File("excel1.xlsx"));
        files.add(new File("excel2.xlsx"));
        files.add(new File("excel3.xlsx"));

        // 定义维度列表
        List<String> dimensions = new ArrayList<>();
        dimensions.add("维度1");
        dimensions.add("维度2");
        dimensions.add("维度3");

        // 定义数据字典
        Map<String, Map<String, Double>> dataDict = new HashMap<>();

        // 遍历多个Excel文件，将数据存入数据字典中
        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            // 获取表头行
            Row headerRow = sheet.getRow(0);

            // 获取表头列名
            Map<Integer, String> columnNames = new HashMap<>();
            for (Cell cell : headerRow) {
                int columnIndex = cell.getColumnIndex();
                String columnName = cell.getStringCellValue();
                columnNames.put(columnIndex, columnName);
            }

            // 遍历数据行，将数据存入数据字典中
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);

                // 获取维度值
                List<String> dimensionValues = new ArrayList<>();
                for (String dimension : dimensions) {
                    Cell dimensionCell = dataRow.getCell(columnNames.entrySet().stream()
                            .filter(entry -> entry.getValue().equals(dimension)).findFirst().get().getKey());
                    dimensionValues.add(dimensionCell.getStringCellValue());
                }

                // 获取数据值
                Cell dataCell = dataRow.getCell(columnNames.entrySet().stream()
                        .filter(entry -> !dimensions.contains(entry.getValue())).findFirst().get().getKey());
                double dataValue = dataCell.getNumericCellValue();

                // 存入数据字典
                String key = String.join(",", dimensionValues);
                if (dataDict.containsKey(key)) {
                    dataDict.get(key).put(file.getName(), dataValue);
                } else {
                    Map<String, Double> dataMap = new HashMap<>();
                    dataMap.put(file.getName(), dataValue);
                    dataDict.put(key, dataMap);
                }
            }

            workbook.close();
            fis.close();
        }

        // 创建新的Excel文件
        Workbook newWorkbook = new XSSFWorkbook();
        Sheet newSheet = newWorkbook.createSheet("合并数据");

        // 写入表头行
        Row headerRow = newSheet.createRow(0);
        int columnIndex = 0;
        for (String dimension : dimensions) {
            Cell dimensionCell = headerRow.createCell(columnIndex++);
            dimensionCell.setCellValue(dimension);
        }
        for (File file : files) {
            Cell fileCell = headerRow.createCell(columnIndex++);
            fileCell.setCellValue(file.getName());
        }

        // 写入数据行
        int rowIndex = 1;
        for (Map.Entry<String, Map<String, Double>> entry : dataDict.entrySet()) {
            Row dataRow = newSheet.createRow(rowIndex++);
            String[] dimensionValues = entry.getKey().split(",");
            columnIndex = 0;
            for (String dimensionValue : dimensionValues) {
                Cell dimensionValueCell = dataRow.createCell(columnIndex++);
                dimensionValueCell.setCellValue(dimensionValue);
            }
            for (File file : files) {
                Cell dataCell = dataRow.createCell(columnIndex++);
                if (entry.getValue().containsKey(file.getName())) {
                    dataCell.setCellValue(entry.getValue().get(file.getName()));
                } else {
                    dataCell.setCellValue(0);
                }
            }
        }

        // 设置单元格样式
        XSSFCellStyle dataCellStyle = (XSSFCellStyle) newWorkbook.createCellStyle();
        dataCellStyle.setDataFormat(newWorkbook.createDataFormat().getFormat("#,##0.00"));
        for (int i = 1; i <= newSheet.getLastRowNum(); i++) {
            Row dataRow = newSheet.getRow(i);
            for (int j = dimensions.size(); j < dataRow.getLastCellNum(); j++) {
                Cell dataCell = dataRow.getCell(j);
                dataCell.setCellStyle(dataCellStyle);
            }
        }

        // 保存新的Excel文件
        FileOutputStream fos = new FileOutputStream("merged.xlsx");
        newWorkbook.write(fos);
        newWorkbook.close();
        fos.close();
    }
}